package com.example.dreamtale.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PrefManager {


    private static final String MY_PREFERENCES = "LONG_TV";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String IS_LOGIN = "IS_LOGIN";
    private static final String IS_FIRST_START = "IS_FIRST_START";
    private static final String LIST_CATEGORY = "IS_FIRST_START";
    private static final String HOME_DATA = "HOME_DATA";
    private static final String HISTORY_SEARCH = "HISTORY_SEARCH";

    public synchronized static SharedPreferences getPreference(Context context) {
        if (context != null) {
            return context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        }
        return null;
    }

    public static void saveAccessTokenInfo(Context context, String accessToken) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(ACCESS_TOKEN, accessToken);
            editor.apply();
        }
    }
    public static void saveRefreshTokenInfo(Context context, String refreshToken) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(REFRESH_TOKEN, refreshToken);
            editor.apply();
        }
    }

    public static String getAccessToken(Context context) {
        SharedPreferences preferences = getPreference(context);

        if (preferences != null) {
            return preferences.getString(ACCESS_TOKEN, "");
        }

        return "";
    }

    public static void saveListCategory(Context context, String data) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(LIST_CATEGORY, data);
            editor.apply();
        }
    }

    public static List<Category> getListCategory(Context context) {
        SharedPreferences preferences = getPreference(context);

        if (preferences != null) {
            return new ArrayList<>();
        }

        return null;
    }



    public static void setLogin(Context context, boolean login) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(IS_LOGIN, login);
            editor.apply();
        }
    }

    public static boolean isLogged(Context context) {
        SharedPreferences preferences = getPreference(context);

        if (preferences != null) {
            return preferences.getBoolean(IS_LOGIN, false);
        }

        return false;
    }

    public static boolean isFirstStart(Context context) {
        SharedPreferences preferences = getPreference(context);

        if (preferences != null) {
            return preferences.getBoolean(IS_FIRST_START, true);
        }

        return false;
    }

    public static void setIsFirstStart(Context context, boolean firstStart) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(IS_FIRST_START, firstStart);
            editor.apply();
        }
    }


    public static ContentDTO getHomeData(Context context) {
        SharedPreferences preferences = getPreference(context);

        if (preferences != null) {
           String data = preferences.getString(HOME_DATA, "");

           if (!data.matches("")) {
               ContentDTO dt = new Gson().fromJson(data, ContentDTO.class);

               return dt;
           } else {
               return null;
           }
        }

        return null;
    }

    public static void setHomeData(Context context, ContentDTO<Box> data) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(HOME_DATA, new Gson().toJson(data).toString());
            editor.apply();
        }
    }

    public static void clearHomeCache(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(HOME_DATA);
            editor.apply();
        }
    }

    public static void setHistorySearch(Context context, List<Content> contentList) {
        if (context == null) {
            return;
        }

        SharedPreferences preferences = getPreference(context);
        if (preferences != null) {
            Gson gson = new Gson();
            String data = gson.toJson(contentList);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(HISTORY_SEARCH, data);
            editor.apply();

        }
    }

    public static List<Content> getHistorySearch(Context context) {
        SharedPreferences preferences = getPreference(context);

        if (preferences != null) {
            String data = preferences.getString(HISTORY_SEARCH, "");

            if (!data.matches("")) {
                getList(context, data);
            } else {
                return null;
            }
        }
        return null;
    }

    public static List<Content> getList(Context context, String data){
        List<Content> arrayItems = new ArrayList<>();
        if (data != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Content>>(){}.getType();
            arrayItems = gson.fromJson(data, type);
        }

        return arrayItems;
    }

}
