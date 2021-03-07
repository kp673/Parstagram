package kp673.parstagram.fragments;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import kp673.parstagram.Post;

public class ProfileFragment extends FeedFragment{
    @Override
    protected void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e!= null){
                    Log.e(TAG,"Couldn't find content",e);
                    return;
                }
                for (Post post: posts){
                    Log.i(TAG, "Post: "+post.getDescription()+ " username: "+post.getUser().getUsername());
                }
                addAll(posts);
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
