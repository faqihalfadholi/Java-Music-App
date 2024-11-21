package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.musicapp.databinding.ActivityStartPageBinding;

public class StartPage extends AppCompatActivity {


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        btn = findViewById(R.id.btn_start);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartPage.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}