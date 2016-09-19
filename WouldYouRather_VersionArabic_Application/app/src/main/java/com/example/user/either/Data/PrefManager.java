package com.example.user.either.Data;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.user.either.Model.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Zouheir on 27/07/2016.
 */
public class PrefManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    int PRIVATE_MODE = 0;

    Context _context;

    private static final String PREF_NAME = "logindata";
    private static final String LOGIN = "login";
    private static final String TECHNICIEN = "user";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLogin(User user) {
        editor.putBoolean(LOGIN, true);
        editor.putString(TECHNICIEN, user.toString());
        editor.commit();
    }




    public boolean isLoggedIn() {
        return pref.getBoolean(LOGIN, false);
    }

    public User getUser() {
        String[] t = pref.getString(TECHNICIEN, "defaulstring").split(",");
        User user = null;
        if (t.length > 1) {
            DateFormat df = new SimpleDateFormat("YYYY/MM/DD");
            try {
                user = new User(Integer.parseInt(t[0]), t[1], t[2], t[3],df.parse(t[4]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public void logout() {
        editor.putBoolean(LOGIN, false);
        editor.putString(TECHNICIEN, null);
        editor.commit();
    }

}
