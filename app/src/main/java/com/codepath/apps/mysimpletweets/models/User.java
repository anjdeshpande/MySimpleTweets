package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by adeshpa on 6/4/16.
 */
public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String status;
    private String followers;
    private String following;
    private String statusCount;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getStatusCount() {
        return statusCount;
    }

    public static User fromJson(JSONObject json) {
        User user = new User();

        try {
            user.name = json.getString("name");
            user.uid = json.getLong("id");
            user.screenName = "@" + json.getString("screen_name");
            user.profileImageUrl = json.getString("profile_image_url");
            user.status = json.getString("description");
            user.followers= json.getString("followers_count");
            user.following= json.getString("friends_count");
            user.statusCount = json.getString("statuses_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
