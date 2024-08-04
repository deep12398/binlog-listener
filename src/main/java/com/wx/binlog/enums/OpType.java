package com.wx.binlog.enums;

import com.github.shyiko.mysql.binlog.event.EventType;

/**
 * @description: 不同版本数据库binlog记录操作与实际操作的映射枚举
 * @author: wangxu
 * @date: 2023-06-28
 *
 */
public enum OpType {

    ADD,
    UPDATE,
    DELETE,
    OTHER;

    public static OpType to(EventType eventType) {

        switch (eventType) {
            case WRITE_ROWS:
                return ADD;
            case UPDATE_ROWS:
                return UPDATE;
            case DELETE_ROWS:
                return DELETE;

            default:
                return OTHER;
        }
    }
}
