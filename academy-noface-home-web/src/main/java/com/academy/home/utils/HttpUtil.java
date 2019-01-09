package com.academy.home.utils;

import com.academy.core.pojo.User;
import io.swagger.models.auth.In;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class HttpUtil {

    private static final String TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx2750055a558bbe86&secret=fe33aae20890da44fc14c709468b7a91&code=";
    private static final String TOKEN_PROPERTY = "&grant_type=authorization_code";

    private static final String INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=";
    private static final String INFO_PROPERTY = "&lang=zh_CN";

    public static Map getUsr(String code) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(TOKEN_URL + code + TOKEN_PROPERTY);
        HttpResponse response = httpClient.execute(httpGet);
        Map json = new JSONObject(EntityUtils.toString(response.getEntity())).toMap();

        if(Objects.isNull(json.get("errcode"))) {
            return getInfo(json);
        }else {
            return null;
        }
    }

    private static Map getInfo(Map json) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(INFO_URL + json.get("access_token").toString() + "&openid=" + json.get("openid").toString() + INFO_PROPERTY);
        HttpResponse response = httpClient.execute(httpGet);
        return new JSONObject(EntityUtils.toString(response.getEntity(), "UTF-8")).toMap();
    }


}
