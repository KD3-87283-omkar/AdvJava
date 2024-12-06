package com.sunbeam.entities;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;

public class LoginBean {
String email;
String password;
User u;
public LoginBean()
{
	
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
public User getU() {
	return u;
}
public void setU(User u) {
	this.u = u;
}

public void Login()
{
	try(UserDao ud=new UserDaoImpl())
	{
	     u=ud.findByEmail(email);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


}
