package kp673.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

    }
}