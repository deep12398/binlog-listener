package com.wx.binlog.service;

import com.wx.binlog.dto.MySqlRowData;

/**
 * 功能描述: binlog解析后的投递方法;
 *
 * @Author: wangxu
 * @Date: 2023/6/28 9:18
 */
public interface ISender {

    /**
     * 投递数据
     * @param
     * @return
     */
    boolean send(MySqlRowData rowData);

}
