package com.qsh.shopping.util.ordercode;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class EveryDaySerialNumber extends QshSerialNumber {
    
    protected final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    protected DecimalFormat df = null;
    
    public EveryDaySerialNumber(int width) {
        if(width < 1) {
            throw new IllegalArgumentException("Parameter length must be great than 1!");
        }
        char[] chs = new char[width];
        for(int i = 0; i < width; i++) {
            chs[i] = '0';
        }
        df = new DecimalFormat(new String(chs));
    }
    
    protected String process() {
        Date date = new Date();
        int n = getOrUpdateNumber(date, 1);
        return format(date) + format(n);
    }
    
    protected String format(Date date) {
        return sdf.format(date);
    }
    protected String format(int num) {
        return df.format(num);
    }
    
    /**
     * 获得序列号，同时更新持久化存储中的序列
     * @param current 当前的日期
     * @param start   初始化的序号
     * @return 所获得新的序列号
     */
    protected abstract int getOrUpdateNumber(Date current, int start);

}
