package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String isUserLogin = sharedPreferences.getString(LoginActivity.LOGIN_USERNAME_KEY, null);

        Toast.makeText(this, "Welcome " + isUserLogin, Toast.LENGTH_SHORT).show();

    }
}