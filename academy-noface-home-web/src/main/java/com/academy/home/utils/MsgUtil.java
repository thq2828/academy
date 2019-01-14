package com.academy.home.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class MsgUtil {

    private static final String URL = "https://app.cloopen.com:8883/2013-12-26/Accounts/8a216da866f16d680166f5f772590246/SMS/TemplateSMS?sig=";
    private static final String ID_TOKEN = "8a216da866f16d680166f5f7725902461728cee24dc04d2e984cab1363387449";
    private static final String SID = "8a216da866f16d680166f5f772590246";

    private static Map<String, String> params = new HashMap<>();

    static {
        params.put("appId", "8a216da866f16d680166f5f772b0024d");
        params.put("templateId", "1");
    }

    private static String setInfo(String phone, int num) throws IOException {
        // API验证参数 MD5(账户Id + 账户授权令牌 + 时间戳)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = dateFormat.format(new Date());
        String sigParameter = Objects.requireNonNull(MD5Util.encrypt(ID_TOKEN + time, "")).toUpperCase();

        // 验证信息， Base64(账户Id + 冒号 + 时间戳)
        String authorization = SID + ":" + time;
        String base = Base64.getEncoder().encodeToString(authorization.getBytes());

        params.put("to", phone);
        params.put("datas", Arrays.toString(new int[]{num, 10}));
        String json = JSONObject.wrap(params).toString();

        // 发送HTTP请求
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL + sigParameter);
        // 请求体
        StringEntity stringEntity = new StringEntity(json, "UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.addHeader("Authorization", base);
        httpPost.setEntity(stringEntity);

        HttpResponse response = httpclient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public static Integer sendMsg(String phone) throws IOException {
        // 发送手机号， 验证码
        int num = 100000 + (int) (Math.random() * 900000);
        String xml = setInfo(phone, num);
        Map json = XML.toJSONObject(xml).toMap();
        Map statusInfo = (Map) json.get("Response");
        if(!"000000".equals(statusInfo.get("statusCode"))){
            log.info("请求错误 msg = {}", statusInfo.get("statusMsg"));
            return null;
        }
        return num;
    }
}
