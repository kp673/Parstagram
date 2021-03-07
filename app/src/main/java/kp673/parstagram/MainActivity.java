package kp673.parstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kp673.parstagram.fragments.ComposeFragment;
import kp673.parstagram.fragments.FeedFragment;
import kp673.parstagram.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();


    public static final String TAG="MainActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView= findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.actionHome:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        fragment= new FeedFragment();
                        break;
                    case R.id.actionCompose:
                        Toast.makeText(MainActivity.this, "Compose", Toast.LENGTH_SHORT).show();
                        fragment= new ComposeFragment();
                        break;
                    case R.id.actionProfile:
                    default:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        fragment= new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer,fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.actionHome);

    }
}