package com.example.administrator.moran.engine;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.moran.R;
import com.example.administrator.moran.util.AESEncryptor;

/**
 * Created by Administrator on 2015/12/6.
 */
public class TokenEngine {

    /**
     * 读取验证信息并解密
     * @param context
     * @return
     */
    public static TokenInfo getTokenInfo(Context context){

        SharedPreferences sp = context.getSharedPreferences("moran",Context.MODE_PRIVATE);
        String user_id = sp.getString("user_id", "");
        String token = sp.getString("token","");

        try{
            user_id = AESEncryptor.decrypt(context.getString(R.string.random_seed),user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            token = AESEncryptor.decrypt(context.getString(R.string.random_seed),token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TokenInfo(user_id,token);
    }

    /**
     * 构造验证信息对象
     */
    public static class TokenInfo{
        public String user_id;
        public String token;

        public TokenInfo(String user_id,String token){
            this.user_id = user_id;
            this.token = token;
        }
    }
}
