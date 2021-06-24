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

public class DiscussionDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DiscussionDB";

    //Student Table
    private static final String DISCUSSION_INFO_TABLE = "discussion_table";

    private static final String KEY_Id = "discussion_id";
    private static final String KEY_DISCUSSION_Image = "discussion_image";
    private static final String KEY_STUDENT_Name = "student_name";
    private static final String KEY_STUDENT_Roll = "student_roll_no";
    private static final String KEY_STUDENT_Email = "student_email";
    private static final String KEY_STUDENT_Course = "student_course";
    private static final String KEY_STUDENT_Contact = "student_contact";
    private static final String KEY_DISCUSSION_Category = "discussion_category";
    private static final String KEY_DISCUSSION_Topic = "discussion_topic";
    private static final String KEY_DISCUSSION_Response = "discussion_response";


    String queryDiscussion="CREATE TABLE "+ DISCUSSION_INFO_TABLE +" (" +
            KEY_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DISCUSSION_Image + " BLOB, " +
            KEY_STUDENT_Name + " TEXT, " +
            KEY_STUDENT_Roll + " TEXT, " +
            KEY_STUDENT_Email + " TEXT, " +
            KEY_STUDENT_Course + " TEXT, " +
            KEY_STUDENT_Contact + " TEXT, " +
            KEY_DISCUSSION_Category + " TEXT, " +
            KEY_DISCUSSION_Topic + " TEXT, " +
            KEY_DISCUSSION_Response + " TEXT " + ")";

    public DiscussionDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryDiscussion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DISCUSSION_INFO_TABLE);
        onCreate(db);
    }

    public long addDiscussion(Student student){

        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStored = student.getDisImage();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStored.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte imageInByte[] = byteArrayOutputStream.toByteArray();

        try {

            ContentValues contentValues=new ContentValues();
            contentValues.put(KEY_DISCUSSION_Image,imageInByte);
            contentValues.put(KEY_STUDENT_Name,student.getName());
            contentValues.put(KEY_STUDENT_Roll,student.getRoll_no());
            contentValues.put(KEY_STUDENT_Email,student.getEmail());
            contentValues.put(KEY_STUDENT_Course,student.getCourse());
            contentValues.put(KEY_STUDENT_Contact,student.getContact());
            contentValues.put(KEY_DISCUSSION_Category,student.getDisCategory());
            contentValues.put(KEY_DISCUSSION_Topic,student.getDisTopic());

            long result = db.insert(DISCUSSION_INFO_TABLE,null,contentValues);
            db.close();
            return result;
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return 0;
    }

    public List<Student> getDiscussionRecord(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM discussion_table";
        Cursor cursor = db.rawQuery(query, null);

        //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();

        List<Student> discussionList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Student discussion=new Student();
            discussion.setId(Integer.parseInt(cursor.getString(0)));

            byte byteArrayImage [] = cursor.getBlob(1);
            //ByteArrayInputStream byteArray = new ByteArrayInputStream(byteArrayImage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage,0, byteArrayImage.length);

            discussion.setDisImage(bitmap);
            discussion.setName(cursor.getString(2));
            discussion.setRoll_no(cursor.getString(3));
            discussion.setEmail(cursor.getString(4));
            discussion.setCourse(cursor.getString(5));
            discussion.setContact(cursor.getString(6));

            discussion.setDisCategory(cursor.getString(7));
            discussion.setDisTopic(cursor.getString(8));
            discussion.setDisResponse(cursor.getString(9));

            discussionList.add(discussion);
        }
        return discussionList;
    }

    public Student getDiscussionWithId(int id){

        SQLiteDatabase db = this.getReadableDatabase();

       // String query = "SELECT * FROM student_table where student_email ='"+email+"' ";
        String query = "SELECT * FROM discussion_table where discussion_id ='"+ id +"' ";
        Cursor cursor = db.rawQuery(query, null);

        //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();

        //List<Student> discussionList = new ArrayList<>();

        Student discussion=new Student();
        if(cursor.moveToNext())
        {
            discussion.setId(Integer.parseInt(cursor.getString(0)));

            byte byteArrayImage [] = cursor.getBlob(1);
            //ByteArrayInputStream byteArray = new ByteArrayInputStream(byteArrayImage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage,0, byteArrayImage.length);

            discussion.setDisImage(bitmap);
            discussion.setName(cursor.getString(2));
            discussion.setRoll_no(cursor.getString(3));
            discussion.setEmail(cursor.getString(4));
            discussion.setCourse(cursor.getString(5));
            discussion.setContact(cursor.getString(6));

            discussion.setDisCategory(cursor.getString(7));
            discussion.setDisTopic(cursor.getString(8));
            discussion.setDisResponse(cursor.getString(9));

           // discussionList.add(discussion);
        }
        return discussion;
    }

    public List<Student> getDiscussionWithEmail(String email){

        SQLiteDatabase db = this.getReadableDatabase();

        // String query = "SELECT * FROM student_table where student_email ='"+email+"' ";
        String query = "SELECT * FROM discussion_table where student_email ='"+ email +"' ";
        Cursor cursor = db.rawQuery(query, null);

        //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();

        List<Student> discussionList = new ArrayList<>();


        while(cursor.moveToNext())
        {
            Student discussion=new Student();
            discussion.setId(Integer.parseInt(cursor.getString(0)));

            byte byteArrayImage [] = cursor.getBlob(1);
            //ByteArrayInputStream byteArray = new ByteArrayInputStream(byteArrayImage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage,0, byteArrayImage.length);

            discussion.setDisImage(bitmap);
            discussion.setName(cursor.getString(2));
            discussion.setRoll_no(cursor.getString(3));
            discussion.setEmail(cursor.getString(4));
            discussion.setCourse(cursor.getString(5));
            discussion.setContact(cursor.getString(6));

            discussion.setDisCategory(cursor.getString(7));
            discussion.setDisTopic(cursor.getString(8));
            discussion.setDisResponse(cursor.getString(9));

             discussionList.add(discussion);
        }
        return discussionList;
    }


    public boolean deleteDiscussionWithId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            // String query = "DELETE * FROM table_book_issue where student_id ='"+id+"' ";

            db.delete(DISCUSSION_INFO_TABLE,KEY_Id +" = "+id,null);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
