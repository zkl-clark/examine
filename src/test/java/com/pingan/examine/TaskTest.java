package com.pingan.examine;

import com.pingan.examine.utils.UpdateHdfsTask;
import org.junit.Test;

import java.util.Timer;

public class TaskTest {
    private static UpdateHdfsTask task=new UpdateHdfsTask();

    public static void main(String[] args) {
        startTinmerTask();
    }
    public static void startTinmerTask() {
        Timer time=new Timer();
        time.schedule(task,0,1000*10);

    }

}
