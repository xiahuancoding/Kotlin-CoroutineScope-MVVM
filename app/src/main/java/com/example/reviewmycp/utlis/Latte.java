package com.example.reviewmycp.utlis;

import android.content.Context;
import android.os.Handler;

/**
 *
 */

public final class Latte {

  public static Configurator init(Context context) {
    Configurator.getInstance()
            .getLatteConfigs()
            .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
    return Configurator.getInstance();
  }

  public static Configurator clear() {
    Configurator.getInstance().getLatteConfigs().clear();
    return Configurator.getInstance();
  }

  public static Configurator getConfigurator() {
    return Configurator.getInstance();
  }

  public static <T> T getConfiguration(Object key) {
    return getConfigurator().getConfiguration(key);
  }

  public static void setUserId(int value) {
    getConfigurator().withUseId(value);
  }

  public static Context getApplicationContext() {
    return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
  }

  public static Handler getHandler() {
    return getConfiguration(ConfigKeys.HANDLER);
  }

  public static void test() {
  }
}