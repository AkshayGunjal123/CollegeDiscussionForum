package com.example.collegediscussionforum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "StudentDB";


    //Student Table
    private static final String STUDENT_INFO_TABLE = "student_table";

    private static final String KEY_Id = "student_id";
    private static final String KEY_STUDENT_Image = "student_image";
    private static final String KEY_STUDENT_Name = "student_name";
    private static final String KEY_STUDENT_Roll = "student_roll_no";
    private static final String KEY_STUDENT_Email = "student_email";
    private static final String KEY_STUDENT_Course = "student_course";
    private static final String KEY_STUDENT_Contact = "student_contact";
    private static final String KEY_STUDENT_Password = "student_password";


    String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
            KEY_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_STUDENT_Image + " BLOB, " +
            KEY_STUDENT_Name + " TEXT, " +
            KEY_STUDENT_Roll + " TEXT, " +
            KEY_STUDENT_Email + " TEXT, " +
            KEY_STUDENT_Course + " TEXT, " +
            KEY_STUDENT_Contact + " TEXT, " +
            KEY_STUDENT_Password + " TEXT " + ")";




    public StudentDBHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+STUDENT_INFO_TABLE);
        onCreate(db);
    }

    public long addStudent(Student student){

        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStored = student.getImg();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStored.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte imageInByte[] = byteArrayOutputStream.toByteArray();

        try {

            ContentValues contentValues=new ContentValues();
            contentValues.put(KEY_STUDENT_Image,imageInByte);
            contentValues.put(KEY_STUDENT_Name,student.getName());
            contentValues.put(KEY_STUDENT_Roll,student.getRoll_no());
            contentValues.put(KEY_STUDENT_Email,student.getEmail());
            contentValues.put(KEY_STUDENT_Course,student.getCourse());
            contentValues.put(KEY_STUDENT_Contact,student.getContact());
            contentValues.put(KEY_STUDENT_Password,student.getPassword());

            long result = db.insert(STUDENT_INFO_TABLE,null,contentValues);
            db.close();
            return result;
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return 0;
    }

    public Student validateStudent(String email,String password){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM student_table where student_email ='"+email+"' and student_password='"+password+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            Student student=new Student();
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(2));
            student.setRoll_no(cursor.getString(3));
            student.setEmail(cursor.getString(4));
            student.setCourse(cursor.getString(5));
            student.setContact(cursor.getString(6));
            return student;
        }
        return null;
    }

    public List<Student> getStudentRecord(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();

        List<Student> studentList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Student student=new Student();
            student.setId(Integer.parseInt(cursor.getString(0)));

            byte byteArrayImage [] = cursor.getBlob(1);
            //ByteArrayInputStream byteArray = new ByteArrayInputStream(byteArrayImage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage,0, byteArrayImage.length);

            student.setImg(bitmap);
            student.setName(cursor.getString(2));
            student.setRoll_no(cursor.getString(3));
            student.setEmail(cursor.getString(4));
            student.setCourse(cursor.getString(5));
            student.setContact(cursor.getString(6));
            studentList.add(student);
        }
        return studentList;
    }

    public boolean deleteStudentWithID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            // String query = "DELETE * FROM table_book_issue where student_id ='"+id+"' ";

            db.delete(STUDENT_INFO_TABLE,KEY_Id +" = "+id,null);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Student getStudentWithEmail(String email){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM student_table where student_email ='"+email+"' ";
        Cursor cursor = db.rawQuery(query, null);
        //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();

        Student student=new Student();
        if(cursor.moveToNext())
        {

            student.setId(Integer.parseInt(cursor.getString(0)));

            byte byteArrayImage [] = cursor.getBlob(1);
            //ByteArrayInputStream byteArray = new ByteArrayInputStream(byteArrayImage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage,0, byteArrayImage.length);

            student.setImg(bitmap);
            student.setName(cursor.getString(2));
            student.setRoll_no(cursor.getString(3));
            student.setEmail(cursor.getString(4));
            student.setCourse(cursor.getString(5));
            student.setContact(cursor.getString(6));
            return student;
        }
        return null;
    }
}

