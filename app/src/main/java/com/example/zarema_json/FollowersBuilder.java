package com.example.zarema_json;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

public class FollowersBuilder {


    public static String getData() {
        String data = "https://api.github.com/users/proffix4/following";
        return HttpClient.getHTMLData(data);
    }
    private static Bitmap getFollowerImage(String code) {
        return HttpClient.getHTMLImage(code + ".jpg");
    }

    private static Followers dataParsing(String json, String nameF) {
        Followers followers = new Followers();
        try {
            JSONArray _arr = new JSONArray(json);
            for (int i = 0; i< _arr.length(); i++){
                JSONObject _obj = _arr.getJSONObject(i);
                if (_obj.getString("login").equals(nameF)){
                    followers.setId(_obj.getInt("id"));
                    followers.setName(_obj.getString("login"));
                    followers.setUserURL(_obj.getString("html_url"));
                    followers.setRepository(_obj.getString("repos_url"));
                    followers.setIconName(_obj.getString("avatar_url"));
                    followers.setIcon(getFollowerImage(followers.getIconName()));
                }
            }

        } catch (Exception ignore) {
        }
        return followers;
    }

    public static Followers buildFollowers (String nameF) {
        Followers followers = dataParsing(getData(), nameF);
        return followers;
    }


}
