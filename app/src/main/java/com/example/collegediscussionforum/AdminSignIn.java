package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminSignIn extends AppCompatActivity {

    Button adminSignIn;

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        username = findViewById(R.id.admin_sign_in_username);
        password = findViewById(R.id.admin_sign_in_Password);

        adminSignIn = findViewById(R.id.button_sign_in_admin);

        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();
                if (TextUtils.isEmpty(user_name))
                {
                    username.setError("enter Username");
                }
                else if(TextUtils.isEmpty(pass_word))
                {
                    password.setError("enter password");
                }else {

                    if (user_name.equals("akshay") && pass_word.equals("akshay")) {
                        Intent intent = new Intent(AdminSignIn.this,AdminHome.class);
                        intent.putExtra("user_email_id",user_name);
                        startActivity(intent);
                        Toast.makeText(AdminSignIn.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminSignIn.this, "Username or Password Wrong..!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}