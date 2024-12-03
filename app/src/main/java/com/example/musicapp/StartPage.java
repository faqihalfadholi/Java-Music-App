package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.musicapp.databinding.ActivityStartPageBinding;

public class StartPage extends AppCompatActivity {

    private TextView welcomeText;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        welcomeText = findViewById(R.id.welcomeText);
        btn = findViewById(R.id.btn_start);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartPage.this, MainActivity.class);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");

        if (username != null && !username.isEmpty()) {
            welcomeText.setText("Welcome " + username);
        } else {
            welcomeText.setText("Welcome!");
        }
    }

}