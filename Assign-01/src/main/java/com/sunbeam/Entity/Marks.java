package com.sunbeam.Entity;

public class Marks {

	double mark;
	String subject;
	public Marks()
	{
		
	}
	public Marks(String s,double m)
	{
		mark=m;
		subject=s;
		
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Marks [mark=" + mark + ", subject=" + subject + "]";
	}
	
}
