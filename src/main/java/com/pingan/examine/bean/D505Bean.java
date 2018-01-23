package com.pingan.examine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
public class D505Bean {

    private String D505_01;//住院登记流水号，参照表D504字段D504_01
    private String E505_01;//明细序号
    private String D505_14;//地区代码，参照S101-04
    private String D505_02;//住院处方流水号
    private String E505_02;//项目类型
    private String D505_03;//费用分类编码，参考TY_FEE_CLASS的CODE
    private String E505_03;//农合编码，参考S501_01中药品或者诊疗项目编码，（参见医药价格目录表）
    private String D505_04;//his项目编码
    private String E505_04;//项目名称，参考S301-04或S501-01
    private String D505_05;//规格
    private String D505_06;//剂型
    private String D505_07;//单价
    private String D505_08;//数量，付数与数量两者必须有其一
    private String D505_09;//付数
    private String D505_10;//实收金额，金额=数量*单价，实收金额是折扣后的金额
    private String D505_12;//医生姓名
    private String D505_13;//开单时间
    private String E505_05;//保内比例
    private String E505_06;//保内金额
    private String E505_07;//经办人（姓名）
    private String E505_08;//录入时间(指该条明细数据录入的时间)
    private String E505_09;//参合年度
    private String E505_99;//数据最新修改时间戳
    private String E505_10;//0正常1删除
    private String N505_15;//药物剂型代码（药物剂型类别在特定编码体系中的代码）
    private String N505_01;//超限价金额
    private String N505_02;//按比例自付金额
    private String N505_11;
    private String N505_12;
    private String N505_13;
    private String N505_14;
    private String N505_16;
    private String N505_17;
    private String N505_18;
    private String N505_19;
    private String E505_98;
    private String N505_20;
    private String N505_21;
    private String N505_22;
    private String N505_23;
    private String N505_24;
    private String N505_25;
    private String N505_26;
    private String E505_97;

    public String getD505_01() {
        return D505_01;
    }

    public String getE505_01() {
        return E505_01;
    }

    public String getD505_14() {
        return D505_14;
    }

    public String getD505_02() {
        return D505_02;
    }

    public String getE505_02() {
        return E505_02;
    }

    public String getD505_03() {
        return D505_03;
    }

    public String getE505_03() {
        return E505_03;
    }

    public String getD505_04() {
        return D505_04;
    }

    public String getE505_04() {
        return E505_04;
    }

    public String getD505_05() {
        return D505_05;
    }

    public String getD505_06() {
        return D505_06;
    }

    public String getD505_07() {
        return D505_07;
    }

    public String getD505_08() {
        return D505_08;
    }

    public String getD505_09() {
        return D505_09;
    }

    public String getD505_10() {
        return D505_10;
    }

    public String getD505_12() {
        return D505_12;
    }

    public String getD505_13() {
        return D505_13;
    }

    public String getE505_05() {
        return E505_05;
    }

    public String getE505_06() {
        return E505_06;
    }

    public String getE505_07() {
        return E505_07;
    }

    public String getE505_08() {
        return E505_08;
    }

    public String getE505_09() {
        return E505_09;
    }

    public String getE505_99() {
        return E505_99;
    }

    public String getE505_10() {
        return E505_10;
    }

    public String getN505_15() {
        return N505_15;
    }

    public String getN505_01() {
        return N505_01;
    }

    public String getN505_02() {
        return N505_02;
    }

    public String getN505_11() {
        return N505_11;
    }

    public String getN505_12() {
        return N505_12;
    }

    public String getN505_13() {
        return N505_13;
    }

    public String getN505_14() {
        return N505_14;
    }

    public String getN505_16() {
        return N505_16;
    }

    public String getN505_17() {
        return N505_17;
    }

    public String getN505_18() {
        return N505_18;
    }

    public String getN505_19() {
        return N505_19;
    }

    public String getE505_98() {
        return E505_98;
    }

    public String getN505_20() {
        return N505_20;
    }

    public String getN505_21() {
        return N505_21;
    }

    public String getN505_22() {
        return N505_22;
    }

    public String getN505_23() {
        return N505_23;
    }

    public String getN505_24() {
        return N505_24;
    }

    public String getN505_25() {
        return N505_25;
    }

    public String getN505_26() {
        return N505_26;
    }

    public String getE505_97() {
        return E505_97;
    }

    public D505Bean(){

    }
    public D505Bean(List<String>list){
        this(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7),list.get(8),list.get(9),
                list.get(10),list.get(11),list.get(12),list.get(13),list.get(14),list.get(15),list.get(16),list.get(17),list.get(18),list.get(19),
                list.get(20),list.get(21),list.get(22),list.get(23),list.get(24),list.get(25),list.get(26),list.get(27),list.get(28),list.get(29),
                list.get(30),list.get(31),list.get(32),list.get(33),list.get(34),list.get(35),list.get(36),list.get(37),list.get(38),list.get(39),
                list.get(40),list.get(41),list.get(42),list.get(43) );
    }

    public D505Bean(String d505_01, String e505_01, String d505_14, String d505_02, String e505_02, String d505_03, String e505_03, String d505_04, String e505_04, String d505_05, String d505_06, String d505_07, String d505_08, String d505_09, String d505_10, String d505_12, String d505_13, String e505_05, String e505_06, String e505_07, String e505_08, String e505_09, String e505_99, String e505_10, String n505_15, String n505_01, String n505_02, String n505_11, String n505_12, String n505_13, String n505_14, String n505_16, String n505_17, String n505_18, String n505_19, String e505_98, String n505_20, String n505_21, String n505_22, String n505_23, String n505_24, String n505_25, String n505_26, String e505_97) {
        D505_01 = d505_01;
        E505_01 = e505_01;
        D505_14 = d505_14;
        D505_02 = d505_02;
        E505_02 = e505_02;
        D505_03 = d505_03;
        E505_03 = e505_03;
        D505_04 = d505_04;
        E505_04 = e505_04;
        D505_05 = d505_05;
        D505_06 = d505_06;
        D505_07 = d505_07;
        D505_08 = d505_08;
        D505_09 = d505_09;
        D505_10 = d505_10;
        D505_12 = d505_12;
        D505_13 = d505_13;
        E505_05 = e505_05;
        E505_06 = e505_06;
        E505_07 = e505_07;
        E505_08 = e505_08;
        E505_09 = e505_09;
        E505_99 = e505_99;
        E505_10 = e505_10;
        N505_15 = n505_15;
        N505_01 = n505_01;
        N505_02 = n505_02;
        N505_11 = n505_11;
        N505_12 = n505_12;
        N505_13 = n505_13;
        N505_14 = n505_14;
        N505_16 = n505_16;
        N505_17 = n505_17;
        N505_18 = n505_18;
        N505_19 = n505_19;
        E505_98 = e505_98;
        N505_20 = n505_20;
        N505_21 = n505_21;
        N505_22 = n505_22;
        N505_23 = n505_23;
        N505_24 = n505_24;
        N505_25 = n505_25;
        N505_26 = n505_26;
        E505_97 = e505_97;
    }
}
