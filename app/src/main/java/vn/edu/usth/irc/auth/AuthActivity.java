package vn.edu.usth.irc.auth;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.usth.irc.MainActivity;
import vn.edu.usth.irc.R;

public class AuthActivity extends AppCompatActivity implements NavTo {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AuthRepository.get(this).isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_auth);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.authContainer, new LoginFragment())
                .commit();
    }

    @Override public void toRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.authContainer, new RegisterFragment())
                .addToBackStack(null).commit();
    }

    @Override public void toLogin() {
        getSupportFragmentManager().popBackStack();
    }

    @Override public void toMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
