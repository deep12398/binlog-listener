package com.wx.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Qinyi.
 */
public class BinLogConstants {

    private static final String DB_NAME = "micpdb";

    public static class AD_PLAN_TABLE_INFO {

        public static final String TABLE_NAME = "ic_dd_card_code";

        public static final String COLUMN_TENANT_ID = "tenant_id";
        public static final String COLUMN_CARD_CODE = "card_code";
        public static final String COLUMN_CARD_ACC_NUM = "card_acc_num";
        public static final String COLUMN_REC_FLAG = "rec_flag";
        public static final String COLUMN_MAINSUB_TYPE = "mainsub_type";
        public static final String COLUMN_UPDATE_TIME = "update_time";
        public static final String COLUMN_FIXID = "fixid";
    }


    public static Map<String, String> table2Db;

    static {
        table2Db = new HashMap<>();
        table2Db.put(AD_PLAN_TABLE_INFO.TABLE_NAME, DB_NAME);
    }
}
