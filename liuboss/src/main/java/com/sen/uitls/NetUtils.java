package com.sen.uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class NetUtils {
    private static DisplayMetrics dm;
    private static TelephonyManager telephonyManager = null;
    private static ConnectivityManager connManager = null;




    /**
     * 判断网络是否连接
     * 
     * @param context
     * @return
     */
    public static boolean isNetWork(Context context) {
        if (connManager == null) {
            connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable()) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络类型 USystem.getNetType()<BR>
     * 
     * @return
     */
    public static String getNetTypeName(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "无网络";
            }
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return activeNetworkInfo.getTypeName();
            } else {
                String typeName = activeNetworkInfo.getSubtypeName();
                //Log.i("网络名称:", typeName);
                if (typeName == null || typeName.length() == 0) {
                    return "未知网络";
                } else if (typeName.length() > 3) {
                    return activeNetworkInfo.getSubtypeName().substring(0, 4);
                } else {
                    return activeNetworkInfo.getSubtypeName().substring(0,
                            typeName.length());
                }
            }
        } else {
            return "无网络";
        }
    }
}
