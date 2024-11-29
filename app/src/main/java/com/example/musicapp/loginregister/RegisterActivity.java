package com.example.musicapp.loginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.musicapp.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnRegister;
    private AppDatabase db;
    private UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = AppDatabase.getInstance(this);
        userDao = db.userDao();

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(v -> register());
    }

    private void register() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            User newUser = new User(username, password);
            userDao.insert(newUser);
            runOnUiThread(() -> {
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}

