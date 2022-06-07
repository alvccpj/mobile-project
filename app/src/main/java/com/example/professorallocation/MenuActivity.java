package com.example.professorallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btDepartments;
    private Button btCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btCourses = findViewById(R.id.btCourses);
        btDepartments = findViewById(R.id.btDepartments);

        btCourses.setOnClickListener(view -> {

            Intent mensageiro = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mensageiro);

        });

        btDepartments.setOnClickListener(view -> {

            Intent mensageiro2 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mensageiro2);

        });
    }
}