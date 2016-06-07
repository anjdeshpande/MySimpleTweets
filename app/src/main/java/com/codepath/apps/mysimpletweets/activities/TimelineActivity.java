package com.codepath.apps.mysimpletweets.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.fragments.ComposeDialogFragment;
import com.codepath.apps.mysimpletweets.listeners.EndlessScrollListener;
import com.codepath.apps.mysimpletweets.restEndpoints.TwitterApplication;
import com.codepath.apps.mysimpletweets.restEndpoints.TwitterClient;
import com.codepath.apps.mysimpletweets.adapters.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets = new ArrayList<Tweet>();
        aTweets = new TweetsArrayAdapter(this, tweets);
        lvTweets.setAdapter(aTweets);

        // Attach the listener to the AdapterView onCreate
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                //customLoadMoreDataFromApi(page);
                populateTimeline(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                refreshContent();
            }
        });


        client = TwitterApplication.getRestClient(); // singletone client
        // Initialize to show first page
        populateTimeline(1);
    }

    // send api request to get timeline json
    // fill listview
    private void populateTimeline(int sinceId) {
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // deserialize json and create models add to adapter
                aTweets.addAll(Tweet.fromJSONArray(json));
                // load model data in list view
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", "JSON Object");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        }, sinceId);
    }

    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter

    }

    // fake a network operation's delayed response
    // this is just for demonstration, not real code!
    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tweets = new ArrayList<Tweet>();
                aTweets = new TweetsArrayAdapter(TimelineActivity.this, tweets);
                lvTweets.setAdapter(aTweets);
                populateTimeline(1);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_timeline, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // code to handle the menu item tap
        if (item.getItemId() == R.id.menu_compose) {
            //showEditDialog();
            Intent i = new Intent(this,PostTweets.class);
            startActivity(i);

            return true;
        }

        return (super.onOptionsItemSelected(item));
    }

    public void onTweetClick(View view) {
        Intent i = new Intent(this,PostTweets.class);
        startActivity(i);
    }

    public void clickReply(View v)
    {
        Toast.makeText(v.getContext(),
                "Reply Icon",
                Toast.LENGTH_LONG).show();
    }

    public void clickRetweet(View v)
    {
        Toast.makeText(v.getContext(),
                "Retweet Icon",
                Toast.LENGTH_LONG).show();
    }

    public void clickLike(View v)
    {
        Toast.makeText(v.getContext(), "Like Icon", Toast.LENGTH_SHORT).show();
    }

    /*private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ComposeDialogFragment editNameDialog = new ComposeDialogFragment();
        editNameDialog.show(fm, "fragment_edit_name");
    }*/

}
