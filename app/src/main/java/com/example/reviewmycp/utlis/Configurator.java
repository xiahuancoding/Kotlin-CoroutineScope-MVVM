package com.example.reviewmycp.utlis;

import android.app.Activity;
import android.os.Handler;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created  on 2017/3/29
 */

public final class Configurator {

    private static HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    @SuppressWarnings("UnqualifiedMethodAccess")
    public final void configure() {
        // initIcons();
//        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);

    }



    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }


    public final Configurator withH5IM(String h5) {
        LATTE_CONFIGS.put(ConfigKeys.H5_IM, h5);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withDeBug(boolean isBeta) {
        LATTE_CONFIGS.put(ConfigKeys.DEBUG, isBeta);
        return this;
    }

    public final Configurator withWWXTYPE(String type) {
        LATTE_CONFIGS.put(ConfigKeys.APP_WWX_PAY_TYPE, type);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

//    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
//        EventManager manager = EventManager.getInstance();
//        manager.addEvent(name, event);
//        return this;
//    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    private void checkConfiguration() {
        boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }



    public Configurator withLiveHost(String wxMiniId) {
        LATTE_CONFIGS.put(ConfigKeys.LIVE_HOST, wxMiniId);
        return this;
    }

    public Configurator withImWs(String appImWs) {
        LATTE_CONFIGS.put(ConfigKeys.APP_IM_WS, appImWs);
        return this;
    }

    public Configurator withStatistic(String appImWs) {
        LATTE_CONFIGS.put(ConfigKeys.APP_STITSTIC_HOST, appImWs);
        return this;
    }

    public Configurator withImHost(String appImWs) {
        LATTE_CONFIGS.put(ConfigKeys.APP_IM_HOST, appImWs);
        return this;
    }

//    public void withVideoCache(HttpProxyCacheServer mProxy) {
//        LATTE_CONFIGS.put(ConfigKeys.VIDEO_CACHE, mProxy);
//    }

    public Configurator withUseId(int value) {
        LATTE_CONFIGS.put(ConfigKeys.USERID, value);
        return this;
    }

    public Configurator withLiveIm(String appLiveIm) {
        LATTE_CONFIGS.put(ConfigKeys.APP_LIVE_IM, appLiveIm);
        return this;
    }

    public Configurator withWwxPay(String wwxPay) {
        LATTE_CONFIGS.put(ConfigKeys.WWX_PAY, wwxPay);
        return this;
    }

    public Configurator withCImWs(String appImWs) {
        LATTE_CONFIGS.put(ConfigKeys.C_APP_IM_WS, appImWs);
        return this;
    }

    public Configurator withCImApp(String appImgApi) {
        LATTE_CONFIGS.put(ConfigKeys.C_API_IM, appImgApi);
        return this;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
}
