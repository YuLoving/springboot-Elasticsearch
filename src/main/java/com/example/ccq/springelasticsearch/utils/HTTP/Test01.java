import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) throws Exception {
        //1.创建HttpClient实例
        HttpClientBuilder Client = HttpClientBuilder.create();
        //2.创建请求方法的实例
            //GET请求使用HttpGet，POST请求使用HttpPost，并传入请求的URL
            String url="";
            // POST请求
            HttpPost post = new HttpPost(url);
            // GET请求，URL中带请求参数
            HttpGet get = new HttpGet(url);

        //3.添加请求参数
                //普通形式
                    List<NameValuePair> list = new ArrayList<>();
                    list.add(new BasicNameValuePair("username", "admin"));
                    list.add(new BasicNameValuePair("password", "123456"));

                        // GET请求方式
                        // 由于GET请求的参数是拼装在URL后方，所以需要构建一个完整的URL，再创建HttpGet实例
                        URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com");
                        uriBuilder.setParameters(list);
                        HttpGet get1 = new HttpGet(uriBuilder.build());

                         // POST请求方式
                        post.setEntity(new UrlEncodedFormEntity(list, Charsets.UTF_8));

                //JSON形式
                       // Map<String,String> map = new HashMap<>();
                       // map.put("username", "admin");
                       // map.put("password", "123456");
                        JSONObject jsonObject = new JSONObject();
                            jsonObject.put("username", "admin");
                            jsonObject.put("password", "123456");
                      //  String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());
                          String json=jsonObject.toJSONString();
                        post.setEntity(new StringEntity(json, Charsets.UTF_8));
                        post.addHeader("Content-Type", "application/json");
    }
}
