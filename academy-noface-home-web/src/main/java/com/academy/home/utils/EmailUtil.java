package com.academy.home.utils;

import com.academy.core.pojo.Code;
import com.academy.core.service.CodeService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class EmailUtil {
    private static final String URL = "http://api.sendcloud.net/apiv2/mail/send";

    private static String setInfo(String email, int num) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("apiUser", "joee_test_ObRIzs"));
        params.add(new BasicNameValuePair("apiKey", "aHCS6H6MXjziZriw"));
        params.add(new BasicNameValuePair("from", "academy-noface@joeeeee.org"));
        params.add(new BasicNameValuePair("fromName", "求学大作战"));
        params.add(new BasicNameValuePair("subject", "验证邮件"));
        params.add(new BasicNameValuePair("to", email));
        params.add(new BasicNameValuePair("html", Integer.toString(num)));

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL);
        System.out.println(URL);
        System.out.println(params.toString());
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public static Integer sendMsg(String email) throws IOException {
        // 验证码
        int num = 100000 + (int) (Math.random() * 900000);
        String json = setInfo(email, num);
        Map statusInfo = new JSONObject(json).toMap();
        System.out.println(statusInfo);
        if(!"200".equals(statusInfo.get("statusCode").toString())){
            log.info("请求错误 msg = {}", statusInfo.get("message"));
            return null;
        }
        return num;
    }
}
