package com.example.bloodbank.utility.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private static SharedPreferences sharedPreferences = null;


    private static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "blood_banK_shared_pref", Context.MODE_PRIVATE);
        }
    }

    //API TOKEN
    public static void saveUserApiToken(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("API_TOKEN", data_Value);
            editor.apply();
        }
    }

    public static String loadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        return sharedPreferences.getString(data_Key, null);
    }

    //USER LOGIN DETAILS
    public static void saveUserPhoneNo(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_REG_PHONE_NO", data_Value);
            editor.apply();
        }
    }

    public static void saveUserPassword(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_REG_PASSWORD", data_Value);
            editor.apply();
        }
    }

    public static String loadUserPhoneNo(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("USER_REG_PHONE_NO", null);
    }

    public static String loadUserPassword(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("USER_REG_PASSWORD", null);
    }

    public static String loadUserApiToken(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("USER_API_TOKEN", null);
    }

    public static void removeUserLoginDetails(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("USER_REG_PHONE_NO");
            editor.remove("USER_REG_PASSWORD");
            editor.apply();
        }
    }

    //Device Token
    public static void saveUserDeviceToken(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_REG_DEVICE_TOKEN", data_Value);
            editor.apply();
        }
    }

    public static String loadUserDeviceToken(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("USER_REG_DEVICE_TOKEN", null);
    }

    public static void removeUserDeviceToken(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("USER_REG_DEVICE_TOKEN");
            editor.apply();
        }
    }
}
