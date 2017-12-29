package com.base.web.okhttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author long  on 16/1/20.
 *         okHttp请求方式封装
 *         1,返回值 只会返回code[200..300)之间的请求
 *         2,可以支持Http body内容（发送到服务器不在from表单中，需要使用流的方式接）请求 post json数据或者get请求数据.
 *         3,可以支持get和post同步
 *         4,修改单独get请求
 *         5,修改单独post from表单请求 提交表单形式
 */
public class OkHttpManager extends AbstractHttpManager {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    //严格控制http请求
    private void init() {
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads().detectDiskWrites().detectNetwork()
//                .penaltyLog().build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
//                .penaltyLog().penaltyDeath().build());
    }

    /**
     * post body json数据
     * 回调出结果
     *
     * @param url      请求地址
     * @param callback 反馈结果
     * @param params   参数
     */
    @Override
    public void request(int method, String url, Map<String, String> params, final HttpBusinessCallback callback) {
        if (url == null || "".equals(url)) {
            return;
        }
        if (params == null) {
            return;
        }
        init();//Android 2.3提供一个称为严苛模式（StrictMode）的调试特性
        OkHttpClient client = getClient();
        Request request = null;
        if (method == Method.GET) {
            String strUrl = restructureURL(Method.GET, url, params);
            request = new Request.Builder().url(strUrl).build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            RequestBody requestBody = builder.build();
            request = new Request.Builder().url(url).post(requestBody).build();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException ex) {
                callback.onFailure(ex);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    callback.onSucceed(result);
                }
            }
        });
    }


    /**
     * get 请求
     *
     * @param url    地址
     * @param params 参数
     * @return 结果
     */
    @Override
    public String getRequest(String url, Map<String, String> params) {
        if (url == null || "".equals(url)) {
            return null;
        }
        init();//Android 2.3提供一个称为严苛模式（StrictMode）的调试特性
        String strUrl = restructureURL(Method.GET, url, params);
        //创建okHttpClient对象
        OkHttpClient client = getClient();
        //创建一个Request
        Request request = new Request.Builder().url(strUrl).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get 请求
     *
     * @param url 地址
     */
    @Override
    public void getRequest(String url, final HttpBusinessCallback callback) {
        if (url == null || "".equals(url)) {
            return;
        }
        init();//Android 2.3提供一个称为严苛模式（StrictMode）的调试特性

        //创建一个Request
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = getClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException exception) {
                callback.onFailure(exception);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    callback.onSucceed(result);
                } else {
                    Exception exception = new Exception(String.valueOf(response.code()));
                    callback.onFailure(exception);
                }
            }
        });
    }

    /**
     * @param url    url
     * @param params map　参数
     * @return response
     */
    @Override
    public String postRequest(String url, Map<String, String> params) {
        if (url == null || "".equals(url)) {
            return null;
        }
        init();//Android 2.3提供一个称为严苛模式（StrictMode）的调试特性
        OkHttpClient client = getClient();
        String json = getJson(params);
        Request request = null;
        if (json != null) {
            RequestBody body = RequestBody.create(JSON, json);
            request = new Request.Builder().url(url).post(body).build();
        }
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * post from 提交请求(一般使用这个)
     */
    public String postRequest(String url, RequestBody params) {
        if (url == null || "".equals(url)) {
            return null;
        }
        init();//Android 2.3提供一个称为严苛模式（StrictMode）的调试特性
        OkHttpClient client = getClient();
        Request request = new Request.Builder().url(url).post(params).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * json提交 异步
     */
    @Override
    public void postJsonRequest(String url, JSONObject requestJson, int hashCodeTag, final HttpBusinessCallback callback) {
        //通过FormBody对象添加多个请求参数键值对
        if (requestJson == null) {
            requestJson = new JSONObject();
        }
        RequestBody jsonBody = RequestBody.create(JSON, requestJson.toString());
        //通过请求地址和请求体构造Post请求对象Request
        final Request request = new Request.Builder().tag(hashCodeTag).url(url).post(jsonBody).build();
        OkHttpClient client = getClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException exception) {
                callback.onFailure(exception);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    callback.onSuccess(result);
                } else {
                    Exception exception = new Exception(String.valueOf(response.code()));
                    callback.onFailure(exception);
                }
            }
        });
    }

    /**
     * 异步 请求
     *
     * @param url         url
     * @param requestJson request json
     * @param hashCodeTag code
     * @param timeout     超时时间
     * @param callback    回调
     */
    @Override
    public void postJsonRequest(String url, JSONObject requestJson, int hashCodeTag, int timeout, final HttpBusinessCallback callback) {
        //通过FormBody对象添加多个请求参数键值对
        if (requestJson == null) {
            requestJson = new JSONObject();
        }
        RequestBody jsonBody = RequestBody.create(JSON, requestJson.toString());
        //通过请求地址和请求体构造Post请求对象Request
        Request request = new Request.Builder().tag(hashCodeTag).url(url).post(jsonBody).build();
        OkHttpClient client = getClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException exception) {
                callback.onFailure(exception);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    callback.onSuccess(result);
                } else {
                    Exception exception = new Exception(String.valueOf(response.code()));
                    callback.onFailure(exception);
                }
            }
        });
    }

    /**
     * post  from 请求(一般使用这个)
     */
    public void postRequest(String url, RequestBody params, final HttpCallback callback) {
        if (url == null || url.equals("")) {
            return;
        }
        init();//Android 2.3提供一个称为严苛模式（StrictMode）的调试特性
        OkHttpClient client = getClient();
        Request request = new Request.Builder().url(url).post(params).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    callback.onSucceed(result);
                }
            }
        });
    }

    private String getJson(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        String json;
        boolean error = false;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key == null || value == null) {
                continue;
            }
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
            }
            if (error) {
                break;
            }
        }
        json = jsonObject.toString();
        return json;
    }

    public static Request getFileUploadRequest(String url, String name, File file, MediaType mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (file.exists() && name != null && !"".equals(name)) {
            builder.addFormDataPart(name, file.getName(), RequestBody.create(mediaType, file));
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    public static Request getFileUploadRequest(String url, Map<String, String> params, String name, File file, MediaType mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (file.exists() && name != null && !"".equals(name)) {
            builder.addFormDataPart(name, file.getName(), RequestBody.create(mediaType, file));
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.addFormDataPart(key, value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }


    /**
     * @param url  url
     * @param name 名称
     * @param file 文件
     * @return Request
     */
    public static Request getPicUploadRequest(String url, String name, File file) {
        MediaType PNG_TYPE = MediaType.parse("image/png");
        MediaType JPG_TYPE = MediaType.parse("image/jpg");

        MediaType mediaType = PNG_TYPE;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (file.exists() && name != null && !"".equals(name)) {
            if (".jpg".endsWith(file.getName())) {
                mediaType = JPG_TYPE;
            }
            if (".png".endsWith(file.getName())) {
                mediaType = PNG_TYPE;
            }
            builder.addFormDataPart(name, file.getName(), RequestBody.create(mediaType, file));
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    public static Request getPicUploadRequest(String url, String name, File file, Map<String, String> params) {
        MediaType PNG_TYPE = MediaType.parse("image/png");
        MediaType JPG_TYPE = MediaType.parse("image/jpg");

        MediaType mediaType = PNG_TYPE;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (file.exists() && name != null && !"".equals(name)) {
            if (".jpg".endsWith(file.getName())) {
                mediaType = JPG_TYPE;
            }
            if (".png".endsWith(file.getName())) {
                mediaType = PNG_TYPE;
            }
            builder.addFormDataPart(name, file.getName(), RequestBody.create(mediaType, file));
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.addFormDataPart(key, value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

}
