package kp673.parstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import kp673.parstagram.Post;
import kp673.parstagram.PostsAdapter;
import kp673.parstagram.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    public static final String TAG="FeedFragment";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;
    protected SwipeRefreshLayout swipeContainer;
    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts=view.findViewById(R.id.rvPosts);
        allPosts= new ArrayList<>();
        adapter= new PostsAdapter(getContext(),allPosts);
        rvPosts.setAdapter(adapter);
        swipeContainer= (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clear();
                queryPost();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright);

        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        //create Data source
        queryPost();
    }


    protected void queryPost(){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
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

    public void clear(){
        allPosts.clear();
        adapter.notifyDataSetChanged();
    }

    public void addAll(List<Post> posts){
        allPosts.addAll(posts);
        adapter.notifyDataSetChanged();
    }
}