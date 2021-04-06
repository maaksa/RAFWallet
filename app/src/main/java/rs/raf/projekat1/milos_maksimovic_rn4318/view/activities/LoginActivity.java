package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;

public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_USERNAME_KEY = "isUserLogin";
    public static final String USERNAME = "username";
    public static final String SURNAME = "surname";
    public static final String BANK_NAME = "bankName";

    private static final String PASSWORD = "12345";

    private EditText usernameEt;
    private EditText surnameEt;
    private EditText bankNameEt;
    private EditText passwordEt;

    private TextView nameErrorTv;
    private TextView surnameErrorTv;
    private TextView passwordErrorTv;
    private TextView bankNameErrorTv;

    private Button loginBtn;

    private String username;
    private String surname;
    private String bankName;
    private String password;

    private boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initView();
        initListeners();
    }

    private void initView() {
        usernameEt = findViewById(R.id.nameEtLogin);
        surnameEt = findViewById(R.id.surnameEtLogin);
        bankNameEt = findViewById(R.id.bankNameEtLogin);
        passwordEt = findViewById(R.id.passwordEtLogin);

        loginBtn = findViewById(R.id.loginBtn);

        nameErrorTv = findViewById(R.id.nameError);
        surnameErrorTv = findViewById(R.id.surnameError);
        passwordErrorTv = findViewById(R.id.passwordError);
        bankNameErrorTv = findViewById(R.id.bankNameError);
    }

    private void initListeners() {

        usernameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    nameErrorTv.setText("");
                    nameErrorTv.setVisibility(View.INVISIBLE);
                    check = true;
                } else {
                    nameErrorTv.setText("Niste uneli ime");
                    nameErrorTv.setVisibility(View.VISIBLE);
                    check = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        surnameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    surnameErrorTv.setText("");
                    surnameErrorTv.setVisibility(View.INVISIBLE);
                    check = true;
                } else {
                    surnameErrorTv.setText("Niste uneli prezime");
                    surnameErrorTv.setVisibility(View.VISIBLE);
                    check = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < 5) {
                    passwordErrorTv.setText("Sifra mora biti duza od 5 karaktera");
                    passwordErrorTv.setVisibility(View.VISIBLE);
                    check = false;
                }
                if (s.toString().length() >= 5) {
                    passwordErrorTv.setText("");
                    passwordErrorTv.setVisibility(View.INVISIBLE);
                    check = true;
                }
            }
        });

        bankNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    bankNameErrorTv.setText("");
                    bankNameErrorTv.setVisibility(View.INVISIBLE);
                    check = true;
                } else {
                    bankNameErrorTv.setText("Niste uneli ime banke");
                    bankNameErrorTv.setVisibility(View.VISIBLE);
                    check = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginBtn.setOnClickListener(v -> {

            getLoginFields();
            if (!password.equals(PASSWORD)) {
                passwordErrorTv.setText("Pogresna sifra");
                passwordErrorTv.setVisibility(View.VISIBLE);
                check = false;
            }
            checkLoginFields();
            if (check) {
                //restartErrors();
                //user = new User(username, password);
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                sharedPreferences
                        .edit()
                        .putString(USERNAME, username)
                        .putString(SURNAME, surname)
                        .putString(BANK_NAME, bankName)
                        .apply();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }


    private boolean checkLoginFields() {

        boolean toReturn = true;
        if (username.isEmpty()) {
            nameErrorTv.setText("Niste uneli ime");
            nameErrorTv.setVisibility(View.VISIBLE);
            toReturn = false;
        }
        if (surname.isEmpty()) {
            surnameErrorTv.setText("Niste uneli prezime");
            surnameErrorTv.setVisibility(View.VISIBLE);
            toReturn = false;
        }
        if (password.isEmpty()) {
            passwordErrorTv.setText("Niste uneli sifru");
            passwordErrorTv.setVisibility(View.VISIBLE);
            toReturn = false;
        }
        if (bankName.isEmpty()) {
            bankNameErrorTv.setText("Niste uneli ime banke");
            bankNameErrorTv.setVisibility(View.VISIBLE);
            toReturn = false;
        }
        if (password.length() < 5) {
            passwordErrorTv.setText("Sifra mora biti duza od 5 karaktera");
            passwordErrorTv.setVisibility(View.VISIBLE);
            toReturn = false;
        }
        if (!password.equals(PASSWORD)) {
            passwordErrorTv.setText("Pogresna sifra");
            passwordErrorTv.setVisibility(View.VISIBLE);
            toReturn = false;
        }

        return toReturn;
    }

    private void getLoginFields() {
        username = usernameEt.getText().toString();
        surname = surnameEt.getText().toString();
        bankName = bankNameEt.getText().toString();
        password = passwordEt.getText().toString();
    }
}