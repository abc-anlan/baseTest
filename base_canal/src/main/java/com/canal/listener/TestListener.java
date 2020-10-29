package com.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;

@CanalEventListener//声明当前的类是canal监听类
public class TestListener {

    @ListenPoint(schema = "ceshi",table = "goods")
    public void update(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        System.out.println("goods表数据发生变化");

        //获取变化前数据
        rowData.getBeforeColumnsList().forEach((c)->{
            System.out.println("改变前数据"+c.getName()+"=="+c.getValue());
        });

        //获取变后数据
        rowData.getAfterColumnsList().forEach((c)->{
            System.out.println("改变前数据"+c.getName()+"=="+c.getValue());
        });
    }
}
