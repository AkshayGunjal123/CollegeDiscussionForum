package com.example.collegediscussionforum;

import android.graphics.Bitmap;

public class Student {
    private int id;
    private Bitmap img;
    private String name;
    private String roll_no;
    private String email;
    private String course;
    private String contact;
    private String password;

    private String requestStudentName;
    private String requestStudentEmail;
    private String responseStudentName;
    private String responseStudentEmail;
    private Bitmap disImage;
    private String disCategoryId;
    private String disCategory;
    private String disTopic;
    private String disResponse;
    private String feedback;


    public Student() {
    }

    public Student(String requestStudentName, String requestStudentEmail, String responseStudentName, String responseStudentEmail, String disCategoryId, String disCategory, String disTopic, String disResponse) {
        this.requestStudentName = requestStudentName;
        this.requestStudentEmail = requestStudentEmail;
        this.responseStudentName = responseStudentName;
        this.responseStudentEmail = responseStudentEmail;
        this.disCategoryId = disCategoryId;
        this.disCategory = disCategory;
        this.disTopic = disTopic;
        this.disResponse = disResponse;
    }

    public Student(Bitmap disImage, String name, String roll_no, String email, String course, String contact, String disCategory, String disTopic) {
        this.disImage = disImage;
        this.name = name;
        this.roll_no = roll_no;
        this.email = email;
        this.course = course;
        this.contact = contact;
        this.disCategory = disCategory;
        this.disTopic = disTopic;
    }



    public Student(Bitmap img, String name, String roll_no, String email, String course, String contact, String password) {
        this.img = img;
        this.name = name;
        this.roll_no = roll_no;
        this.email = email;
        this.course = course;
        this.contact = contact;
        this.password = password;
    }

    public Student(int id, Bitmap img, String name, String roll_no, String email, String course, String contact, String password) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.roll_no = roll_no;
        this.email = email;
        this.course = course;
        this.contact = contact;
        this.password = password;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRequestStudentName() {
        return requestStudentName;
    }

    public void setRequestStudentName(String requestStudentName) {
        this.requestStudentName = requestStudentName;
    }

    public String getRequestStudentEmail() {
        return requestStudentEmail;
    }

    public void setRequestStudentEmail(String requestStudentEmail) {
        this.requestStudentEmail = requestStudentEmail;
    }

    public String getResponseStudentName() {
        return responseStudentName;
    }

    public void setResponseStudentName(String responseStudentName) {
        this.responseStudentName = responseStudentName;
    }

    public String getResponseStudentEmail() {
        return responseStudentEmail;
    }

    public void setResponseStudentEmail(String responseStudentEmail) {
        this.responseStudentEmail = responseStudentEmail;
    }

    public String getDisCategoryId() {
        return disCategoryId;
    }

    public void setDisCategoryId(String disCategoryId) {
        this.disCategoryId = disCategoryId;
    }

    public Bitmap getDisImage() {
        return disImage;
    }

    public void setDisImage(Bitmap disImage) {
        this.disImage = disImage;
    }

    public String getDisCategory() {
        return disCategory;
    }

    public void setDisCategory(String disCategory) {
        this.disCategory = disCategory;
    }

    public String getDisTopic() {
        return disTopic;
    }

    public void setDisTopic(String disTopic) {
        this.disTopic = disTopic;
    }

    public String getDisResponse() {
        return disResponse;
    }

    public void setDisResponse(String disResponse) {
        this.disResponse = disResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
