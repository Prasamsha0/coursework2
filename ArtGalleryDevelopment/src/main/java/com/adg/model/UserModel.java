package com.adg.model;

import java.sql.Date;




public class UserModel {
    private int userId;
    private String userName;
    private String contact;
    private String address;
    private String email;
    private String password;
    private int age;
    private String gender;
    private String repass;
    private Date dob;

    // Constructor
    public UserModel(int userId, String userName, String contact, String address, String email,
                String password, int age, String gender, String repass , Date dob) {
        this.userId = userId;
        this.userName = userName;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.password = password;
        this.age= age;
        this.gender = gender;
        this.repass = repass;
        this.dob = dob;
    }
    public UserModel(int userId, String userName, String contact, String address, String email,
            String password, int age, String gender,  Date dob) {
    this.userId = userId;
    this.userName = userName;
    this.contact = contact;
    this.address = address;
    this.email = email;
    this.password = password;
    this.age= age;
    this.gender = gender;
    this.dob = dob;
}

	public UserModel(String username, String pasword) {
		this.userName = username;
		this.password = pasword;
	}


	
	public UserModel(int int1, String string, String string2, String string3, String string4, Date date, int int2) {
		// TODO Auto-generated constructor stub
	}
	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the repass
	 */
	public String getrepass() {
		return repass;
	}

	/**
	 * @param repass the repass to set
	 */
	public void setrepass(String repass) {
		this.repass = repass;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

    
}