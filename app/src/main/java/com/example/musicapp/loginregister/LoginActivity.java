package com.example.musicapp.loginregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.musicapp.R;
import com.example.musicapp.StartPage;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private CheckBox cbRememberMe;
    private AppDatabase db;
    private UserDAO userDao;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppDatabase.getInstance(this);
        userDao = db.userDao();
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        cbRememberMe = findViewById(R.id.checkBoxRememberMe);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(v -> login());
        btnRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // Check if credentials are saved
        if (sharedPreferences.getBoolean("remember_me", false)) {
            etUsername.setText(sharedPreferences.getString("username", ""));
            etPassword.setText(sharedPreferences.getString("password", ""));
            cbRememberMe.setChecked(true);
        }
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

                    startActivity(new Intent(LoginActivity.this, StartPage.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}

