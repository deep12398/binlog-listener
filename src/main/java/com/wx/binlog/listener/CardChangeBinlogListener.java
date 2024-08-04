package com.wx.binlog.listener;

import com.github.shyiko.mysql.binlog.event.*;

import com.wx.binlog.dto.BinlogRowData;
import com.wx.binlog.dto.MySqlRowData;
import com.wx.binlog.dto.TableTemplate;
import com.wx.binlog.enums.OpType;
import com.wx.binlog.service.ISender;
import com.wx.constant.BinLogConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 卡介质变更的监听器
 * @author: wangxu
 * @date:
 */
@Component
@Slf4j
@ConditionalOnProperty(
        name = {"binlog.btn"},
        havingValue = "true"
)
public class CardChangeBinlogListener implements Ilistener {

    private final AggregationListener aggregationListener;

    private final ISender sender;

    @Autowired
    public CardChangeBinlogListener(AggregationListener aggregationListener, ISender sender) {
        this.aggregationListener = aggregationListener;
        this.sender = sender;
    }


    @Override
    @PostConstruct
    public void register() {

        log.info("CardChangeBinlogListener register db and table info");
        BinLogConstants.table2Db.forEach((k, v) ->
                aggregationListener.register(v, k, this));
    }

    @Override
    public void onEvent(BinlogRowData eventData) {
        TableTemplate table = eventData.getTable();
        EventType eventType = eventData.getEventType();
        log.info("表{}数据发生变更，操作类型为：{}，数据修改前为：{}，数据修改后为：{}",eventData.getTable().getTableName(),eventData.getEventType(),eventData.getBefore(),eventData.getAfter());
        // 包装成最后需要投递的数据
        MySqlRowData rowData = new MySqlRowData();

        rowData.setTableName(table.getTableName());
        OpType opType = OpType.to(eventType);
        rowData.setOpType(opType);

        // 取出模板中该操作对应的字段列表
        List<String> fieldList = table.getOpTypeFieldSetMap().get(opType);
        if (null == fieldList) {
            log.warn("{} not support for {}", opType, table.getTableName());
            return;
        }

        for (Map<String, String> afterMap : eventData.getAfter()) {

            Map<String, String> _afterMap = new HashMap<>();

            for (Map.Entry<String, String> entry : afterMap.entrySet()) {

                String colName = entry.getKey();
                String colValue = entry.getValue();

                _afterMap.put(colName, colValue);
            }

            rowData.getFieldValueMap().add(_afterMap);
        }

        sender.send(rowData);
    }


}
