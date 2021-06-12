package com.lingman.lib.network.http;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Author: Norton
 * Date: 2019-07-30 08:39
 * Description: 这里做提交参数的数据包装，把key value转成json
 */
public class RequestPack {

    /**
     * 请求参数
     */
    private static HashMap<String, Object> params = new HashMap<>();


    private static class Holder {
        private static RequestPack mInstance = new RequestPack();
    }

    /**
     * 单例
     * @param
     */
    public static RequestPack getDefault( ) {
        params.clear();
        RequestPack requestPack = Holder.mInstance;
        if (Holder.mInstance == null) {
            requestPack = new RequestPack();
        }
        return requestPack;
    }


    public Map<String, Object> getParams() {
        return params;
    }


    public RequestPack addParams(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public RequestPack putParams(Map<String, Object> map) {
        params.putAll(map);
        return this;
    }


    public RequestBody build() {
        String jsonObj =  JSON.toJSONString(params);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObj);
        return body;
    }


}
