package com.hao.project.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.hao.project.dto.Msg;
import com.hao.project.util.SignUtils;

import java.util.HashMap;
import java.util.Map;

public class HaoCongMingClient {
    private static final String URL = "http://haoge.chat:9090/api/openai";
    private final String accessKey;
    private final String secretKey;

    public HaoCongMingClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String doChat(Msg msg) {
        String json = JSONUtil.toJsonStr(msg);
        HttpResponse httpResponse = HttpRequest.post(URL+"/chat")
                .addHeaders(getHeaderMap())
                .body(json)
                .execute();
        return httpResponse.body();
    }

    public String getImage(Msg msg) {
        String json = JSONUtil.toJsonStr(msg);
        HttpResponse httpResponse = HttpRequest.post(URL+"/image")
                .addHeaders(getHeaderMap())
                .body(json)
                .execute();
        return httpResponse.body();
    }

    private Map<String,String> getHeaderMap() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign("message", secretKey));
        return hashMap;
    }
}
