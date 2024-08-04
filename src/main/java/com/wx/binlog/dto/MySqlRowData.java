package com.wx.binlog.dto;

import com.wx.binlog.enums.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 投递之前使用的实体类
 * @author: wangxu
 * @date: 2023-06-28
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySqlRowData {

    private String tableName;

    private OpType opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
