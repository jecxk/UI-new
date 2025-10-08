package vn.edu.usth.irc.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthRepository {
    private static final String PREF = "irc_auth";
    private static final String KEY_USER = "username";
    private final SharedPreferences sp;

    private AuthRepository(Context ctx){ sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE); }
    public static AuthRepository get(Context ctx){ return new AuthRepository(ctx.getApplicationContext()); }

    public boolean login(String username, String password){
        // TODO: gọi API thật. Hiện tại demo: accept mọi thứ không rỗng
        if(username == null || username.trim().isEmpty()) return false;
        sp.edit().putString(KEY_USER, username.trim()).apply();
        return true;
    }

    public boolean register(String username, String password){
        // TODO: call API register; demo coi như thành công nếu đủ 4 ký tự
        return login(username, password);
    }

    public boolean isLoggedIn(){ return sp.contains(KEY_USER); }
    public String username(){ return sp.getString(KEY_USER, "User"); }
    public void logout(){ sp.edit().clear().apply(); }
}
