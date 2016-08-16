package com.appsgemzi.zouheir.wouldyourather2.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by Zouheir on 22/06/2016.
 */
public class Manager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    int PRIVATE_MODE = 0;

    Context _context;

    private static final String PREF_NAME = "wyr2";

    private static final String USER_ID = "user_id";

    public Manager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUser(String user) {
        editor.putString(USER_ID, user);
        editor.commit();
    }

    public String getUser() {
        return pref.getString(USER_ID, "defaultString");
    }

}
