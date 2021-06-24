package com.example.collegediscussionforum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminStudentAdapter extends RecyclerView.Adapter<AdminStudentAdapter.AdminStudentViewHolder> {

    private List<Student> studentList;
    private Context context;

    public AdminStudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminStudentAdapter.AdminStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_student_record_item,parent,false);
        AdminStudentViewHolder adminStudentViewHolder=new AdminStudentViewHolder(view);
        return adminStudentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminStudentAdapter.AdminStudentViewHolder holder, int position) {

        holder.imageView.setImageBitmap(studentList.get(position).getImg());
        holder.name.setText("Name: "+studentList.get(position).getName());
        holder.rollNo.setText("Roll No: "+studentList.get(position).getRoll_no());
        holder.email.setText("Email: "+studentList.get(position).getEmail());
        holder.course.setText("Course: "+studentList.get(position).getCourse());
        holder.contact.setText("Contact: "+studentList.get(position).getContact());

        int id = studentList.get(position).getId();

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDBHelper studentDBHelper=new StudentDBHelper(context.getApplicationContext());
                boolean b =studentDBHelper.deleteStudentWithID(id);

                if(b){
                    Toast.makeText(context.getApplicationContext(),"Student Has Been Deleted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context.getApplicationContext(),"Now This Student is not Present...",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class AdminStudentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,rollNo,email,course,contact;

        Button deleteButton;


        public AdminStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.admin_item_Image);
            name = itemView.findViewById(R.id.admin_student_name);
            rollNo = itemView.findViewById(R.id.admin_student_roll_no);
            email = itemView.findViewById(R.id.admin_student_email);
            course = itemView.findViewById(R.id.admin_student_course);
            contact = itemView.findViewById(R.id.admin_student_contact);

            deleteButton = itemView.findViewById(R.id.admin_delete_student_record);
        }
    }
}
