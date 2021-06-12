package com.lingman.lib.common.bean;


import com.lingman.lib.base.app.AppConfig;

/**
 * Author: Norton
 * Date: 2019-09-02 08:40
 * Description: EventBus事件传递包装类
 */
public class EventBean<V> {
    private AppConfig.EventBusKey key;
    private V value;

    public EventBean(AppConfig.EventBusKey key) {
        this.key = key;
    }

    public EventBean(AppConfig.EventBusKey key, V value) {
        this.key = key;
        this.value = value;
    }

    public AppConfig.EventBusKey getKey() {
        return key;
    }

    public void setKey(AppConfig.EventBusKey key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
