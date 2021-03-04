package kp673.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";
    private EditText etCaption;
    private Button btnCaptureImage;
    private ImageView ivPreview;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCaption= findViewById(R.id.etCaption);
        btnCaptureImage=findViewById(R.id.btnCaptureImage);
        ivPreview= findViewById(R.id.ivPreview);
        btnPost=findViewById(R.id.btnPost);
        queryPost();

    }

    private void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
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
            }
        });
    }
}