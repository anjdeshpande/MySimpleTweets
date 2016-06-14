package com.codepath.apps.advancedtwitterclient.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.advancedtwitterclient.R;
import com.codepath.apps.advancedtwitterclient.fragments.UserTimelineFragment;
import com.codepath.apps.advancedtwitterclient.models.User;
import com.codepath.apps.advancedtwitterclient.restEndpoints.TwitterApplication;
import com.codepath.apps.advancedtwitterclient.restEndpoints.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {
    TwitterClient client;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        client = TwitterApplication.getRestClient();
        // get account info

        client.getUserInfo(new JsonHttpResponseHandler( ) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user = User.fromJson(response);
                // Current user account info
                getSupportActionBar().setTitle(user.getScreenName());
                populateUserHeader(user);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        // get screen name passed from activity launched it
        String screenName = getIntent().getStringExtra("screen_name");
        if (savedInstanceState == null) {
            UserTimelineFragment fragmentTimeline = UserTimelineFragment.newInstance(screenName);
            // Display user fragment in this activity - dynamic way
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, fragmentTimeline);
            ft.commit();
        }
    }

    private void populateUserHeader(User user) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCompose:
                Toast.makeText(this, "Menu item tapped !", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
