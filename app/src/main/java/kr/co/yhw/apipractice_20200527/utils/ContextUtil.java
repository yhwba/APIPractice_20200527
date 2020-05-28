package kr.co.yhw.apipractice_20200527.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

    private static final String prefName = "APIPracticePref";

    private static final String LOGIN_USER_TOKEN ="LOGIN_USER_TOKEN";

    public static void setLoginUserToken(Context context, String token){
        SharedPreferences pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_TOKEN, token).apply();
    }

    public static String getLoginUserToken (Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        return pref.getString(LOGIN_USER_TOKEN,"");
    }

}
