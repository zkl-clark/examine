package com.pingan.examine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
public class D504Bean {

    private String D504_01;//住院登记流水号
    private String D504_02;//个人编码，参照D401中E401_01字段
    private String D504_28;//地区代码，参照S101-04
    private String E504_01;//补偿年度（此次补偿发生的运行年度值）
    private String D504_03;//患者姓名
    private String D504_04;//患者性别，参照S101-01（GB/T2261-1980）
    private String D504_05;//患者身份证号，无身份证者填无
    private String D504_06;//年龄
    private String D504_07;//家庭编号，参照D301表中D301_01字段
    private String E504_02;//病人所在乡村组，参照D201表D201_01字段
    private String D504_08;//医疗证/卡号，无卡号者填医疗证号，医疗证编号规则：前6位为《中华人民共和国行政区划代码》中本县（区、县级市）的代码，7-12位为乡村组的编码，13-16位为“户编号”
    private String D504_09;//住院号
    private String D504_10;//就诊类型，参照S301-05
    private String D504_11;//入院时间
    private String D504_12;//出院时间
    private String D504_13;//实际住院天数
    private String D504_14;//就医机构代码，参照D101D的D101_01字段（S201-01（WS218-2002））
    private String D504_15;//就医机构级别，参照S201-06
    private String D504_16;//入院科室，参照S201-03
    private String N504_11;//
    private String D504_17;//出院科室，参照S201-03
    private String N504_12;
    private String D504_18;//经治医生姓名
    private String D504_19;//入院状态，参照S301-02
    private String D504_20;//出院状态，参照S301-03
    private String N504_13;
    private String N504_14;
    private String N504_15;
    private String D504_21;//疾病ICD代码，参照S301-01（GB/T14396-2001）
    private String N504_28;
    private String N504_29;
    private String D504_22;//并发症，参照S301-01（GB/T14396-2001）
    private String D504_23;//手术名称代码，参照S301-08（ICD-9CM3）
    private String D504_24;//转往医院名称
    private String D504_25;//转往医院级别，参照S201-06
    private String D504_26;//民政通知书号
    private String D504_27;//生育证号
    private String E504_03;//住院信息录入经办人姓名
    private String E504_04;//住院信息录入经办人编码
    private String E504_05;//信息录入时间
    private String E504_07;//单病种治疗方式
    private String E504_08;//就诊机构名称
    private String E504_06;//疾病名称
    private String E504_10;//手术名称
    private String E504_11;//并发症名称
    private String E504_98;//
    private String E504_99;//数据最新修改时间戳
    private String E504_12;//数据来源（1 县平台 2 即时结报）
    private String E504_13;//0正常 1删除
    private String D504_29;//转往医院代码
    private String N504_02;//与手术名称对应的ICD-9-CM-3手术及医疗操作分类
    private String N504_16;
    private String N504_17;
    private String N504_18;
    private String N504_19;
    private String N504_20;
    private String N504_21;
    private String N504_22;
    private String N504_23;
    private String N504_24;
    private String N504_25;
    private String N504_26;
    private String N504_27;
    private String E504_97;
    private String N504_99;

    public String getD504_01() {
        return D504_01;
    }

    public String getD504_02() {
        return D504_02;
    }

    public String getD504_28() {
        return D504_28;
    }

    public String getE504_01() {
        return E504_01;
    }

    public String getD504_03() {
        return D504_03;
    }

    public String getD504_04() {
        return D504_04;
    }

    public String getD504_05() {
        return D504_05;
    }

    public String getD504_06() {
        return D504_06;
    }

    public String getD504_07() {
        return D504_07;
    }

    public String getE504_02() {
        return E504_02;
    }

    public String getD504_08() {
        return D504_08;
    }

    public String getD504_09() {
        return D504_09;
    }

    public String getD504_10() {
        return D504_10;
    }

    public String getD504_11() {
        return D504_11;
    }

    public String getD504_12() {
        return D504_12;
    }

    public String getD504_13() {
        return D504_13;
    }

    public String getD504_14() {
        return D504_14;
    }

    public String getD504_15() {
        return D504_15;
    }

    public String getD504_16() {
        return D504_16;
    }

    public String getN504_11() {
        return N504_11;
    }

    public String getD504_17() {
        return D504_17;
    }

    public String getN504_12() {
        return N504_12;
    }

    public String getD504_18() {
        return D504_18;
    }

    public String getD504_19() {
        return D504_19;
    }

    public String getD504_20() {
        return D504_20;
    }

    public String getN504_13() {
        return N504_13;
    }

    public String getN504_14() {
        return N504_14;
    }

    public String getN504_15() {
        return N504_15;
    }

    public String getD504_21() {
        return D504_21;
    }

    public String getN504_28() {
        return N504_28;
    }

    public String getN504_29() {
        return N504_29;
    }

    public String getD504_22() {
        return D504_22;
    }

    public String getD504_23() {
        return D504_23;
    }

    public String getD504_24() {
        return D504_24;
    }

    public String getD504_25() {
        return D504_25;
    }

    public String getD504_26() {
        return D504_26;
    }

    public String getD504_27() {
        return D504_27;
    }

    public String getE504_03() {
        return E504_03;
    }

    public String getE504_04() {
        return E504_04;
    }

    public String getE504_05() {
        return E504_05;
    }

    public String getE504_07() {
        return E504_07;
    }

    public String getE504_08() {
        return E504_08;
    }

    public String getE504_06() {
        return E504_06;
    }

    public String getE504_10() {
        return E504_10;
    }

    public String getE504_11() {
        return E504_11;
    }

    public String getE504_98() {
        return E504_98;
    }

    public String getE504_99() {
        return E504_99;
    }

    public String getE504_12() {
        return E504_12;
    }

    public String getE504_13() {
        return E504_13;
    }

    public String getD504_29() {
        return D504_29;
    }

    public String getN504_02() {
        return N504_02;
    }

    public String getN504_16() {
        return N504_16;
    }

    public String getN504_17() {
        return N504_17;
    }

    public String getN504_18() {
        return N504_18;
    }

    public String getN504_19() {
        return N504_19;
    }

    public String getN504_20() {
        return N504_20;
    }

    public String getN504_21() {
        return N504_21;
    }

    public String getN504_22() {
        return N504_22;
    }

    public String getN504_23() {
        return N504_23;
    }

    public String getN504_24() {
        return N504_24;
    }

    public String getN504_25() {
        return N504_25;
    }

    public String getN504_26() {
        return N504_26;
    }

    public String getN504_27() {
        return N504_27;
    }

    public String getE504_97() {
        return E504_97;
    }

    public String getN504_99() {
        return N504_99;
    }

    public D504Bean(List<String>list){
        this(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7),list.get(8),list.get(9),
        list.get(10),list.get(11),list.get(12),list.get(13),list.get(14),list.get(15),list.get(16),list.get(17),list.get(18),list.get(19),
        list.get(20),list.get(21),list.get(22),list.get(23),list.get(24),list.get(25),list.get(26),list.get(27),list.get(28),list.get(29),
        list.get(30),list.get(31),list.get(32),list.get(33),list.get(34),list.get(35),list.get(36),list.get(37),list.get(38),list.get(39),
        list.get(40),list.get(41),list.get(42),list.get(43),list.get(44),list.get(45),list.get(46),list.get(47),list.get(48),list.get(49),
        list.get(50),list.get(51),list.get(52),list.get(53),list.get(54),list.get(55),list.get(56),list.get(57),list.get(58),list.get(59),
        list.get(60),list.get(61),list.get(62),list.get(63),list.get(64));
    }

    public D504Bean(String d504_01, String d504_02, String d504_28, String e504_01, String d504_03, String d504_04, String d504_05, String d504_06, String d504_07, String e504_02, String d504_08, String d504_09, String d504_10, String d504_11, String d504_12, String d504_13, String d504_14, String d504_15, String d504_16, String n504_11, String d504_17, String n504_12, String d504_18, String d504_19, String d504_20, String n504_13, String n504_14, String n504_15, String d504_21, String n504_28, String n504_29, String d504_22, String d504_23, String d504_24, String d504_25, String d504_26, String d504_27, String e504_03, String e504_04, String e504_05, String e504_07, String e504_08, String e504_06, String e504_10, String e504_11, String e504_98, String e504_99, String e504_12, String e504_13, String d504_29, String n504_02, String n504_16, String n504_17, String n504_18, String n504_19, String n504_20, String n504_21, String n504_22, String n504_23, String n504_24, String n504_25, String n504_26, String n504_27, String e504_97, String n504_99) {
        D504_01 = d504_01;
        D504_02 = d504_02;
        D504_28 = d504_28;
        E504_01 = e504_01;
        D504_03 = d504_03;
        D504_04 = d504_04;
        D504_05 = d504_05;
        D504_06 = d504_06;
        D504_07 = d504_07;
        E504_02 = e504_02;
        D504_08 = d504_08;
        D504_09 = d504_09;
        D504_10 = d504_10;
        D504_11 = d504_11;
        D504_12 = d504_12;
        D504_13 = d504_13;
        D504_14 = d504_14;
        D504_15 = d504_15;
        D504_16 = d504_16;
        N504_11 = n504_11;
        D504_17 = d504_17;
        N504_12 = n504_12;
        D504_18 = d504_18;
        D504_19 = d504_19;
        D504_20 = d504_20;
        N504_13 = n504_13;
        N504_14 = n504_14;
        N504_15 = n504_15;
        D504_21 = d504_21;
        N504_28 = n504_28;
        N504_29 = n504_29;
        D504_22 = d504_22;
        D504_23 = d504_23;
        D504_24 = d504_24;
        D504_25 = d504_25;
        D504_26 = d504_26;
        D504_27 = d504_27;
        E504_03 = e504_03;
        E504_04 = e504_04;
        E504_05 = e504_05;
        E504_07 = e504_07;
        E504_08 = e504_08;
        E504_06 = e504_06;
        E504_10 = e504_10;
        E504_11 = e504_11;
        E504_98 = e504_98;
        E504_99 = e504_99;
        E504_12 = e504_12;
        E504_13 = e504_13;
        D504_29 = d504_29;
        N504_02 = n504_02;
        N504_16 = n504_16;
        N504_17 = n504_17;
        N504_18 = n504_18;
        N504_19 = n504_19;
        N504_20 = n504_20;
        N504_21 = n504_21;
        N504_22 = n504_22;
        N504_23 = n504_23;
        N504_24 = n504_24;
        N504_25 = n504_25;
        N504_26 = n504_26;
        N504_27 = n504_27;
        E504_97 = e504_97;
        N504_99 = n504_99;
    }
}
