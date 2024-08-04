package com.wx.binlog;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.wx.config.BinlogAutoconfiguration;
import com.wx.binlog.listener.AggregationListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


import java.io.IOException;

/**
 * @description:
 * @author: wangxu
 * @date: 2023-6-27
 */
@Slf4j
@Component
@ConditionalOnProperty(
        name = {"binlog.btn"},
        havingValue = "true"
)
public class BinlogClient {

    private BinaryLogClient client;

    private final BinlogAutoconfiguration config;
    private final AggregationListener listener;

    @Autowired
    public BinlogClient(BinlogAutoconfiguration config, AggregationListener listener) {
        this.config = config;
        this.listener = listener;
    }

    public void connect() {
        if (config.getBtn()){
            new Thread(() -> {
                client = new BinaryLogClient(
                        config.getHost(),
                        config.getPort(),
                        config.getSchema(),
                        config.getUsername(),
                        config.getPassword()
                );

                client.registerEventListener(listener);

                try {
                    log.info("connecting to mysql start");
                    client.connect();
                    log.info("connecting to mysql done");
                } catch (IOException ex) {
                    log.error("connecting to mysql error");
                }

            }).start();
        }
    }

    public void close() {
        try {
            client.disconnect();
        } catch (IOException ex) {
            log.error("close mysql connecting error");
        }
    }
}
