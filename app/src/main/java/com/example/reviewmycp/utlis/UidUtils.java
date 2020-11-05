package com.example.reviewmycp.utlis;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import java.util.UUID;

public class UidUtils {
    /**
     * 返回 唯一的虚拟 ID
     *
     * @return ID
     */
    public static String getUniquePsuedoID() {
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        // API >= 9 的设备才有 android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // 如果用户更新了系统或 root 了他们的设备，该 API 将会产生重复记录
        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "serial";
        }

        // 最后，组合上述值并生成 UUID 作为唯一 ID
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * android 设备名称
     *
     * @return
     */
    public static String getDeveiceBrand() {
        return Build.BRAND;
    }

    /**
     * 判断是否平板设备
     *
     * @param context
     * @return true:平板,false:手机
     */
    public static String isTabletDevice(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE ? "pad" : "phone";
    }


}
