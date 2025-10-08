package vn.edu.usth.irc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private BottomNavigationView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_IRC);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        bottom = findViewById(R.id.bottomBar);

        // default: MessageList
        replace(new MessageListFragment());

        bottom.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                replace(PlaceholderHomeFragment.newInstance());
                return true;
            } else if (item.getItemId() == R.id.nav_message) {
                replace(new MessageListFragment());
                return true;
            }
            return false;
        });

        NavigationView nav = findViewById(R.id.navChannels);
        nav.setNavigationItemSelectedListener(menuItem -> {
            // open selected channel -> Go to MessageList (or filter)
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    public void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    private void replace(Fragment f) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, f);
        ft.commit();
    }
}
