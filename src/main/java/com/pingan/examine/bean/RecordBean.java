package com.pingan.examine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public class RecordBean {
    private D504Bean d504Bean;//基础表
    private List<D505Bean> d505Beans;//就诊信息明细表

    public RecordBean(D504Bean d504Bean, List<D505Bean> d505Beans) {
        this.d504Bean = d504Bean;
        this.d505Beans = d505Beans;
    }

    public RecordBean() {
    }

    public D504Bean getD504Bean() {
        return d504Bean;
    }

    public void setD504Bean(D504Bean d504Bean) {
        this.d504Bean = d504Bean;
    }

    public List<D505Bean> getD505Beans() {
        return d505Beans;
    }

    public void setD505Beans(List<D505Bean> d505Beans) {
        this.d505Beans = d505Beans;
    }
}
