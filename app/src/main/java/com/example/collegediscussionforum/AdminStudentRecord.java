package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AdminStudentRecord extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_record);

        recyclerView = findViewById(R.id.admin_student_record_recyclerView);

        StudentDBHelper studentDBHelper=new StudentDBHelper(AdminStudentRecord.this);
        AdminStudentAdapter adminStudentAdapter=new AdminStudentAdapter(studentDBHelper.getStudentRecord(),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminStudentAdapter);

    }
}