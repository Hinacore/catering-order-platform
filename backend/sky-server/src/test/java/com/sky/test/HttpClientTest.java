package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.net.www.http.HttpClient;

@SpringBootTest
public class HttpClientTest {
    /**
     * 测试通过httpClient发送GET方式的请求
     */
    @Test
    public void testGET() throws Exception {
        //1创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2创建请求对象
        HttpGet httpGet=new HttpGet("http://localhost:8080/user/shop/status");
        //3发送请求，接受响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取响应状态码
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("statusCode:"+statusCode);

        HttpEntity entity=response.getEntity();
        String body= EntityUtils.toString(entity);
        System.out.println("body:"+body);

        //关闭资源
        response.close();
        httpClient.close();
    }

    /**
     * 测试通过httpClient发送POST方式的请求
     */
    @Test
    public void testPOST() throws Exception {
        //1创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2创建请求对象
        HttpPost httpPost=new HttpPost("http://localhost:8080/admin/employee/login");

        JSONObject json=new JSONObject();
        json.put("username","admin");
        json.put("password","123456");

        StringEntity entity=new StringEntity(json.toString());
        //设置请求体内容类型
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        //3发送请求，接受响应结果
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("响应码:"+statusCode);
        //解析返回结果
        HttpEntity entity1=response.getEntity();
        String body= EntityUtils.toString(entity1);
        System.out.println("响应数据为:"+body);

        response.close();
        httpClient.close();
    }
}
