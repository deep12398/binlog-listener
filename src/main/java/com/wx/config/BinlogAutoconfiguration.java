package com.wx.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Qinyi.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "binlog")
@ConditionalOnProperty(
        name = {"binlog.btn"},
        havingValue = "true"
)
public class BinlogAutoconfiguration {

    private String host;
    private Integer port;
    private String username;
    private String schema;
    private String password;
    private Boolean btn= false;

}
