package com.mm.util;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.setting.yaml.YamlUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉报警消息
 *
 * @author lwl
 */
public class DingTalkUtil {

    static Dict dict = YamlUtil.loadByPath("application.yml");

    /**
     * 获取请求的URL
     *
     * @return
     * @throws Exception
     */
    public static String getURL() {
        // 从配置文件中获取参数
        Map dingtalk = dict.getByPath("dingtalk", Map.class);
        String webhook = (String) dingtalk.get("webhook");
        String secret = (String) dingtalk.get("secret");
        if (StrUtil.isBlank(webhook) || StrUtil.isBlank(secret)) {
            return null;
        }
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.getEncoder().encode(signData)), "UTF-8");
            String url = webhook + "&timestamp=" + timestamp + "&sign=" + sign;
            return url;
        } catch (Exception e) {
            StaticLog.error("DingTalkUtil getURL e:{}", e);
        }
        return null;
    }

    /**
     * 发送文本消息
     *
     * @param msg 消息
     * @return
     */
    public static String sendText(String msg) {
        return sendText(msg, null, null);
    }

    /**
     * 发送文本消息
     *
     * @param msg       消息
     * @param atMobiles 手机号
     * @return
     */
    public static String sendText(String msg, List<String> atMobiles) {
        return sendText(msg, atMobiles, null);
    }

    /**
     * 发送文本消息并@所有人
     *
     * @param msg 消息
     * @return
     */
    public static String sendTextAtAll(String msg) {
        return sendText(msg, null, true);
    }

    /**
     * 发送文本消息
     *
     * @param msg       消息
     * @param atMobiles 手机号
     * @param isAtAll   是否@所有人 默认否
     * @return
     */
    private static String sendText(String msg, List<String> atMobiles, Boolean isAtAll) {
        Map<String, Object> body = new HashMap<>(2);
        body.put("msgtype", "text");
        Map<String, Object> content = new HashMap<>(1);
        content.put("content", msg);
        body.put("text", content);
        if (IterUtil.isNotEmpty(atMobiles) || isAtAll != null) {
            Map<String, Object> at = new HashMap<>(2);
            if (IterUtil.isNotEmpty(atMobiles)) {
                at.put("atMobiles", atMobiles);
            }
            if (isAtAll != null) {
                at.put("isAtAll", isAtAll);
            }
            body.put("at", at);
        }
        String url = getURL();
        if (url == null) {
            return null;
        }
        String json = JSONUtil.toJsonStr(body);
        StaticLog.debug("DingTalkUtil sendText url:{}", url);
        StaticLog.debug("DingTalkUtil sendText json:{}", json);
        String response = HttpUtil.post(url, json);
        StaticLog.debug("DingTalkUtil sendText response:{}", response);
        return response;
    }
}
