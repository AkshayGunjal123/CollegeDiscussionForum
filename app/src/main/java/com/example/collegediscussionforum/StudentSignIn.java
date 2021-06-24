package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentSignIn extends AppCompatActivity {

    Button studentSingUp,studentSignIn;

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);

        username = findViewById(R.id.student_sign_in_username);
        password = findViewById(R.id.student_sign_in_Password);

        studentSingUp = findViewById(R.id.button_sign_up_student);
        studentSignIn = findViewById(R.id.button_sign_in_student);

        studentSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSignIn.this,StudentSignUp.class));
            }
        });

        studentSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();

                if (TextUtils.isEmpty(user_name))
                {
                    username.setError("Invalid User Name or Email");
                }
                else if(TextUtils.isEmpty(pass_word))
                {
                    password.setError("enter password");
                }else {

                    StudentDBHelper studentDBHelper= new StudentDBHelper(StudentSignIn.this);
                    Student student=studentDBHelper.validateStudent(user_name,pass_word);

                    if(student!=null){

                        Intent intent = new Intent(StudentSignIn.this,StudentHome.class);
                        intent.putExtra("user_email_id",user_name);
                        startActivity(intent);
                        Toast.makeText(StudentSignIn.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(StudentSignIn.this, "Username or Password Wrong...!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}