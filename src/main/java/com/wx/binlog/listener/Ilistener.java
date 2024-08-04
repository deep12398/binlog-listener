package com.wx.binlog.listener;


import com.wx.binlog.dto.BinlogRowData;

/**
 * @description: 抽象的监听器接口
 * @author: wangxu
 * @return:
 */
public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
