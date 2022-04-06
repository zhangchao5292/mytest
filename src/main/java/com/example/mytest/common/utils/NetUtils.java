package com.example.mytest.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2022/3/15
 */
public class NetUtils {

    private static final String DEF_CHARSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 2000;
    private static final int DEF_READ_TIMEOUT = 10000;

    public static String getData(String myurl) {
        return getData(myurl, null);
    }

    public static String getData(String myurl, Map<String, String> headers) {
        return request(myurl, "GET", null, headers);
    }

    public static String postData(String myurl, String input) {
        return postData(myurl, input, null);
    }

    public static String postData(String myurl, String input, Map<String, String> headers) {
        return request(myurl, "POST", input, headers);
    }

    /**
     * 请求接口查询数据
     *
     * @param myurl   请求地址
     * @param method  请求方式 GET、POST
     * @param input   请求内容
     * @param headers 请求头
     */
    private static String request(String myurl, String method, String input, Map<String, String> headers) {
        String result = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(myurl).openConnection();

            if ("GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }

            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);// 连接超时 单位毫秒
            conn.setReadTimeout(DEF_READ_TIMEOUT);// 读取超时 单位毫秒
            conn.setInstanceFollowRedirects(false);

            if (null != headers && !headers.isEmpty()) {//设置请求头
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    conn.addRequestProperty(header.getKey(), header.getValue());
                }
            }

            if ("POST".equals(method) && input != null && input.length() != 0) {
                //如果是json格式数据设置请求头
                if (input.startsWith("{") || input.startsWith("[")) {
                    conn.addRequestProperty("Content-Type", "application/json");
                }

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(input.getBytes(DEF_CHARSET));
                outputStream.flush();// 写完后再传送，防止某些服务器上数据写不完整就跨域发送
            }

            String charsetName = DEF_CHARSET;
            String contentType = conn.getHeaderField("Content-Type");
            if (contentType != null && contentType.length() > 0) {
                if (contentType.contains("charset=")) {
                    charsetName = contentType.substring(contentType.indexOf("charset=") + 8);
                }
            }

            int responseCode = conn.getResponseCode();

            InputStream inputStream;
            if (responseCode >= 400) {
                inputStream = conn.getErrorStream();
            } else {
                inputStream = conn.getInputStream();
            }

            if (null != inputStream) {
                result = changeInputStream(inputStream, charsetName);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String changeInputStream(InputStream inputStream, String charsetName) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }

    public static byte[] getByteData(String myurl) {
        return requestByte(myurl, "GET", null);
    }

    public static byte[] postByteData(String myurl, String input) {
        return requestByte(myurl, "POST", input);
    }

    /**
     * 获取远程数据流
     *
     * @param myurl  请求路径
     * @param method 请求方式 GET、POST
     * @param input  输入参数
     */
    private static byte[] requestByte(String myurl, String method, String input) {
        byte[] result = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(myurl).openConnection();

            if ("GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }

            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);// 连接超时 单位毫秒
            conn.setReadTimeout(DEF_READ_TIMEOUT * 10);// 读取超时 单位毫秒
            conn.setInstanceFollowRedirects(false);

            if ("POST".equals(method) && input != null) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(input.getBytes(DEF_CHARSET));
                outputStream.flush();// 写完后在传送，防止某些服务器上数据写不完整就跨域发送
            }

            if (conn.getResponseCode() == 200) {
                result = changeInputStream(conn.getInputStream());
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static byte[] changeInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inputStream.close();
        return data;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("h-app-id", "137");
        String url = "http://test2-internal-management-center.weimiaocaishang.com/api/period/getLiveStudentPermit?room_id=10003938&mid=1634201943214663&chapter_id=null&video_id=10005372";
        String data = getData(url, map);
        System.out.println(data);
    }


}
