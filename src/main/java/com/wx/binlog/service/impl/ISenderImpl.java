package com.wx.binlog.service.impl;

import com.wx.binlog.dto.MySqlRowData;
import com.wx.binlog.service.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 功能描述: ;
 *
 * @Author: wangxu
 * @Date: 2024/8/4 20:18
 */
@Slf4j
@Service
@ConditionalOnProperty(
        name = {"binlog.btn"},
        havingValue = "true"
)
public class ISenderImpl implements ISender {
    @Override
    public boolean send(MySqlRowData rowData) {
        return true;
    }
}
