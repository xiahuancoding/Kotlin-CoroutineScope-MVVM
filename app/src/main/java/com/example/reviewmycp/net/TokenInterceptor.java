package com.example.reviewmycp.net;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.example.reviewmycp.utlis.LattePreference;
import com.example.reviewmycp.utlis.UidUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/** token拦截器，统一给所有的请求接口传token参数，还有一些公共参数
 *
 */

public class TokenInterceptor implements Interceptor {

    public TokenInterceptor() {

    }

    private static String bodyToString(final RequestBody request) {
        Buffer buffer = new Buffer();
        try {
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        } finally {
            buffer = null;
        }
    }

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        //清理参数限制
   /*     String mUrl=chain.request().url().toString();
        if(mUrl.contains(Latte.getConfiguration(ConfigKeys.APP_STITSTIC_HOST))){
            return chain.proceed(chain.request()) ;
        }*/
//        UserProfileDao dao = DatabaseManager.getInstance().getDao();
//        String token = "";
        String liveToken = "";
        liveToken = LattePreference.getCustomAppProfile("liveToken");
//        if (dao != null) {
//            List<UserProfile> userProfiles = dao.loadAll();
//            if (userProfiles.size() > 0) {
//                token = userProfiles.get(0).getAccessToken();
//            }
//        }


        String token = LattePreference.getCustomAppProfile("login_token");
        LogUtils.d("login_token 读取保存的token= "+token);

        String appName = AppUtils.getAppName();
        String version = AppUtils.getAppVersionName();
        String systemVersion = DeviceUtils.getSDKVersionName();
        String deviceId = UidUtils.getUniquePsuedoID();
        String deviceModel = DeviceUtils.getModel();

        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("liveToken", liveToken)
                .addHeader("systemType", "android")
                .addHeader("platform","0")
                //这个用于直播参数 4拉流 3推流
                .addHeader("Live-Platform","4")
                .addHeader("merchant","mycp")
                //这个用于后台IM
                    .addHeader("auth-key","Bearer "+token)
                .build();
        String method = request.method();
        if ("GET".equals(method)) {
            HttpUrl newUrl = request.url()
                    .newBuilder()
                    .addEncodedQueryParameter("appName", appName)
                    .addQueryParameter("appVersion", version)
                    .addQueryParameter("systemType", "android")
                    .addQueryParameter("systemVersion", systemVersion)
                    .addQueryParameter("deviceId", deviceId)
                    .addQueryParameter("deviceModel", deviceModel)
                    .build();
            request = request.newBuilder().url(newUrl).build();
//            LatteLogger.d("URL  GET", newUrl.toString());
        } else if ("POST".equals(method)) {
            RequestBody body = request.body();// 得到请求体
            if (body instanceof FormBody) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                formBodyBuilder.add("appName", appName);
                formBodyBuilder.add("appVersion", version);
                formBodyBuilder.add("systemType", "android");
                formBodyBuilder.add("systemVersion", systemVersion);
                formBodyBuilder.add("deviceId", deviceId);
                formBodyBuilder.add("deviceModel", deviceModel);
                Request.Builder newRequestBuild = request.newBuilder();
                RequestBody formBody = formBodyBuilder.build();
                String postBodyString = bodyToString(body);
                postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
//                LatteLogger.d("URL POST FORM", request.url().toString() + "?" + postBodyString);
                newRequestBuild.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
            } else if (body instanceof MultipartBody) {
                MultipartBody oldBodyMultipart = (MultipartBody) body;
                List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/plain"), appName);
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/plain"), version);
                RequestBody requestBody3 = RequestBody.create(MediaType.parse("text/plain"), "android");
                RequestBody requestBody4 = RequestBody.create(MediaType.parse("text/plain"), systemVersion);
                RequestBody requestBody5 = RequestBody.create(MediaType.parse("text/plain"), deviceId);
                RequestBody requestBody6 = RequestBody.create(MediaType.parse("text/plain"), deviceModel);
                String postBodyString = "";
                for (MultipartBody.Part part : oldPartList) {
                    builder.addPart(part);
                    postBodyString += (bodyToString(part.body()) + "\n");
                }
                postBodyString += (bodyToString(requestBody1) + "\n");
                postBodyString += (bodyToString(requestBody2) + "\n");
                postBodyString += (bodyToString(requestBody3) + "\n");
//              builder.addPart(oldBody);  //不能用这个方法，因为不知道oldBody的类型，可能是PartMap过来的，也可能是多个Part过来的，所以需要重新逐个加载进去
                builder.addPart(requestBody1);
                builder.addPart(requestBody2);
                builder.addPart(requestBody3);
                builder.addPart(requestBody4);
                builder.addPart(requestBody5);
                builder.addPart(requestBody6);
                Request.Builder newRequestBuild = request.newBuilder();
                request = newRequestBuild.post(builder.build()).build();
//                LatteLogger.d("URL POST MultipartBody", request.url().toString() + "?" + postBodyString);
            } else {
                Buffer buffer = new Buffer();// 创建缓存
                body.writeTo(buffer);//将请求体内容,写入缓存
                String parameterStr = buffer.readUtf8();// 读取参数字符串
                //如果是json串就解析 从新加餐 如果是字符串就进行修改 具体业务逻辑自己加
                if (!TextUtils.isEmpty(parameterStr)) {
                    Map<String, Object> map = JSON.parseObject(parameterStr, Map.class);
                    map.put("appName", appName);
                    map.put("appVersion", version);
                    map.put("systemType", "android");
                    map.put("systemVersion", systemVersion);
                    map.put("systemVersion", systemVersion);
                    map.put("deviceId", deviceId);
                    map.put("deviceModel", deviceModel);
                    parameterStr = JSON.toJSONString(map);
//                    LatteLogger.d("URL POST JSON", request.url().toString() + "?" + parameterStr);
                }
                //对应请求头大伙按照自己的传输方式 定义
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), parameterStr);
                request = request.newBuilder().post(requestBody).build();
            }
        }
        if (NetworkUtils.isConnected()) {
            return chain.proceed(request);
        } else { // 如果没有网络，则返回缓存未过期一个月的数据
            Request newRequest = request.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "only-if-cached, max-stale=" + 30 * 24 * 60 * 60)
                    .build();
            return chain.proceed(newRequest);
        }
    }
}
