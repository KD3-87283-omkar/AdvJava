package com.sunbeam.entities;

import java.sql.Date;


import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;

public class RegisterBean {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String birth;
	private int status;
	int cnt;
	
	private String role="voter";
	public RegisterBean()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public void saveData() 
	{
		Date d=Date.valueOf(birth);
		try(UserDao ud=new UserDaoImpl())
		{

			User u=new User(0,firstName,lastName,email,password,d,0,role);
		 cnt=ud.save(u);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
  
	
}
