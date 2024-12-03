package com.example.musicapp.loginregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.musicapp.R;
import com.example.musicapp.StartPage;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin ;
    private TextView tvRegister;
    private CheckBox cbRememberMe;
    private AppDatabase db;
    private UserDAO userDao;
    private SharedPreferences sharedPreferences;
    SpannableString ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = AppDatabase.getInstance(this);
        userDao = db.userDao();
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        cbRememberMe = findViewById(R.id.checkBoxRememberMe);
        btnLogin = findViewById(R.id.buttonLogin);
        tvRegister = findViewById(R.id.buttonRegister);
        ss = new SpannableString("Don't have an account? Sign up here");


        btnLogin.setOnClickListener(v -> login());
        setupRegisterLink();



        if (sharedPreferences.getBoolean("remember_me", false)) {
            etUsername.setText(sharedPreferences.getString("username", ""));
            etPassword.setText(sharedPreferences.getString("password", ""));
            cbRememberMe.setChecked(true);
        }
    }

    private void setupRegisterLink() {

        SpannableString ss = new SpannableString("Don't have an account? Sign up here");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };


        int startIndex = ss.length() - 13;
        int endIndex = ss.length();


        ss.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new ForegroundColorSpan(Color.BLUE), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvRegister.setText(ss);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
        tvRegister.setHighlightColor(Color.TRANSPARENT);
    }


    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        new Thread(() -> {
            User user = userDao.login(username, password);
            runOnUiThread(() -> {
                if (user != null) {

                    if (cbRememberMe.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("remember_me", true);
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.apply();
                    } else {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                    }

                    Intent intent = new Intent(LoginActivity.this, StartPage.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}

