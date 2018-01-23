package com.pingan.examine.utils;

import com.pingan.examine.start.ConfigFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * 上传文件到hdfs定时任务
 * Created by Administrator on 2017/12/16.
 */
public class UpdateHdfsTask extends TimerTask{

    //本地原始文件夹
    private String adoptlocalpath ;
    //本地合并文件夹
    private String adoptlocalmargepath ;
    //hadf文件夹
    private String adopthdfspath;

    private String noadoptlocalpath ;

    private String noadoptlocalmargepath ;

    private String noadopthdfspath ;
    //合并文件时，每个文件的行数
    private  int filelinenumber=1000;

    public UpdateHdfsTask() {
        ConfigFactory.initConfig();
        this.adoptlocalpath = ConfigFactory.localpath+ File.separator+"adopt";
        this.adoptlocalmargepath = ConfigFactory.localpath+File.separator+"margeadopt";
        this.adopthdfspath  = ConfigFactory.hdfspath+File.separator+"adopt";
        this.noadoptlocalpath = ConfigFactory.localpath+File.separator+"noadopt";
        this.noadoptlocalmargepath =  ConfigFactory.localpath+File.separator+"margenoadopt";
        this.noadopthdfspath = ConfigFactory.hdfspath+ File.separator+"noadopt";;
    }

    @Override
    public void run(){
        System.out.println("定时器执行");
        try{

            //审核通过的文件
            boolean flag = margeFile(adoptlocalpath,adoptlocalmargepath);
            System.out.println("合并通过文件完成，合并结果："+flag);
            if(flag){
                flag = HdfsUtil.localFolderUploadHDFS(ConfigFactory.hadoopconf,adoptlocalmargepath,adopthdfspath);
                System.out.println("通过文件上传hdfs完成，上传结果："+flag);
                if(flag){
                    FileUtil.delAllFile(adoptlocalmargepath);
                }
            }

            //审核未通过的文件
            flag = margeFile(noadoptlocalpath,noadoptlocalmargepath);
            System.out.println("合并未通过文件完成，合并结果："+flag);
            if(flag){
                flag = HdfsUtil.localFolderUploadHDFS(ConfigFactory.hadoopconf,noadoptlocalmargepath,noadopthdfspath);
                System.out.println("未通过文件上传hdfs完成，上传结果："+flag);
                if(flag){
                    FileUtil.delAllFile(noadoptlocalmargepath);
                }
            }


        }catch(IOException e){
            e.printStackTrace();
        }

    }


    /**
     * 合并文件并写入磁盘
     * @param localpath 原始文件保存路径
     * @param margepath 合并后的文件保存路径
     * @return 完全写成功返回true，否则返回false
     * @throws IOException
     */
    private boolean margeFile(String localpath,String margepath) throws  IOException{
        File[] files = new File(localpath).listFiles();
        if(files==null || files.length<1){
            System.out.println("本地没有要上传的文件："+localpath);
            return  false;
        }
        System.out.println(localpath+" 该文件夹下面有"+files.length);
        List<String> magerList = new ArrayList<String>(filelinenumber);
        for(File file:files){
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"utf-8");
            LineNumberReader lnr = new LineNumberReader(reader);
            StringBuffer sb = new StringBuffer();
            int cnt = 0;
            while(true){
                String str = lnr.readLine();
                if(str==null){
                    break;
                }
                sb.append(str);
                cnt++;
            }
            lnr.close();
            reader.close();
            inputStream.close();
            magerList.add(sb.toString());
            System.out.println(magerList.size());
            if(magerList.size()==filelinenumber && ! writeFile(magerList,margepath)){
                System.out.println(magerList.size());
                return false;

            }

        }
        boolean flag = writeFile(magerList,margepath);
        if(flag){
            for(File file:files){
                file.delete();
            }

        }
        return  flag;
    }

    /**
     * 把合并好的文件写入磁盘
     * @param magerList 合并后的文件
     * @param margepath 合并后的文件保存路径
     * @return 保存成功或没有要保存的数据返回true,否则返回false
     * @throws IOException
     */
    private boolean writeFile(List<String> magerList,String margepath)throws IOException{
        if(magerList ==null || magerList.size()<1){
            return true;
        }
        String filepath = FileUtil.getFullPath(margepath,System.currentTimeMillis()+".txt");
        boolean writeFlag = FileUtil.writeFile(filepath,magerList,true);
        magerList.clear();
        if(!writeFlag){
            System.out.println("写入文件失败："+filepath);
            return  false;
        }else{
            return true;
        }


    }
}
