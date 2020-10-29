package com.common.result;

import java.io.Serializable;

public class Page<T> implements Serializable {
    //当前默认第一页
    public  static final Integer pageNum=1;

    //默认每页显示条数
    public static final Integer pageSize=20;

    //判断当前是否为空或者小于1
    public static Integer cpn(Integer Cpage){
        if (null == Cpage||Cpage<1){
            Cpage=pageNum;
        }
        return Cpage;
    }
}
