package com.adg.model;

import java.sql.Date;

/**
 * UserModel represents the user data structure used across the application.
 * It stores personal and login-related information of a user.
 */
public class UserModel {
    private int userId;
    private String userName;
    private String contact;
    private String address;
    private String email;
    private String password;
    private int age;
    private String gender;
    private String repass; // used during registration to confirm password
    private Date dob;

    // ========================= Constructors =========================

    /**
     * Full constructor including all fields (used for complete user registration).
     */
    public UserModel(int userId, String userName, String contact, String address, String email,
                     String password, int age, String gender, String repass, Date dob) {
        this.userId = userId;
        this.userName = userName;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.repass = repass;
        this.dob = dob;
    }

    /**
     * Constructor without repass (used for login or simplified views).
     */
    public UserModel(int userId, String userName, String contact, String address, String email,
                     String password, int age, String gender, Date dob) {
        this.userId = userId;
        this.userName = userName;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.dob = dob;
    }

    /**
     * Constructor used for authentication (username and password only).
     */
    public UserModel(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    /**
     * Generic constructor —
     */
    public UserModel() {
        // Default constructor
    }

    /**
     * Placeholder constructor (incomplete) — should be implemented or removed if unnecessary.
     */
    public UserModel(int id, String name, String contact, String address, String email, Date dob, int age) {
        // TODO: Implement this constructor as needed
    }

    // ========================= Getters & Setters =========================

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
