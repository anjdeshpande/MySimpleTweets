# Week 3 Project: Simple Twitter Client

## Overview

Build a simple Twitter client that supports viewing a Twitter timeline and composing a new tweet.

Time spent: 15 hours spent in total

# User Stories

Completed user stories:

 * [x] Required: User can sign in to Twitter using OAuth login
 * [x] Required: User can view the tweets from their home timeline
 			User should be displayed the username, name, and body for each tweet
			User should be displayed the relative timestamp for each tweet "8m", "7h"
			User can view more tweets as they scroll with infinite pagination
 * [x] Required: User can compose a new tweet
            User can click a “Compose” icon in the Action Bar on the top right
            User can then enter a new tweet and post this to twitter
            User is taken back to home timeline with new tweet visible in timeline 
 
 * [x] Advanced: While composing a tweet, user can see a character counter with characters remaining for tweet out of 140
 * [x] Advanced: Links in tweets are clickable and will launch the web browser (see autolink) 
 * [x] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
 * [x] Advanced: Improve the user interface and theme the app to feel "twitter branded" 
 * [x] Bonus: Move the "Compose" action to a FloatingActionButton instead of on the AppBar. 
 * [x] Bonus: Replace Picasso with Glide for more efficient image rendering.

 Walkthrough of all user stories:

![Video Walkthrough] (MySimpleTweets1.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

Open-source libraries used
    * Android Async HTTP - Simple asynchronous HTTP requests with JSON parsing
    * Glide - Image loading and caching library for Android

