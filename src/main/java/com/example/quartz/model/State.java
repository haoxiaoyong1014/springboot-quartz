package com.example.quartz.model;

/**
 * Created by haoxy on 2018/10/8.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public enum  State {

    TRI_YEAR(1),

    TRI_MONTH(2),

    TRI_DAY(3),

    TRI_HOUR(4),

    TRI_SECOND(5),

    TRI_WEEK(6);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    State(Integer value) {
        this.value = value;
    }
}
