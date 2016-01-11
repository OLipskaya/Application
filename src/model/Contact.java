package model;

import java.io.Serializable;
import java.util.regex.*;
import java.util.*;

public class Contact implements Comparable<Contact>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ResourceBundle rb = null;
	private String name = "";
	private String surName = "";
	private String login = "";
	private String mail = "";
	private String phoneNumber = "";
	private String sortParam = "";
	private int id = 0;
	
	private static String COLUMN_TABLE_NAME    = "COLUMN_TABLE_NAME"; 
	private static String COLUMN_TABLE_SURNAME = "COLUMN_TABLE_SURNAME";
	private static String COLUMN_TABLE_LOGIN   = "COLUMN_TABLE_LOGIN";
	private static String COLUMN_TABLE_MAIL    = "COLUMN_TABLE_MAIL";
	private static String COLUMN_TABLE_PHONE   = "COLUMN_TABLE_PHONE";
	private static String paramsProperties     = "resources/params";
	
	public Contact(){}
	
	public Contact(String name,String surName,String login,
			String line,String phoneNumber,int id) {
		this.name = name;
		this.surName = surName;
		this.login = login;
		this.mail = line;
		this.phoneNumber = phoneNumber;
		this.id = id;
		sortParam = null;	
	}

	public String getName(){
		return name;
	}
	
	public String getSurName(){
		return surName;
	}
	
	public String getLogin(){
		return login;
	}
	
	public String getMail(){
		return mail;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setSurName(String surName){
		this.surName = surName;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public void setMail(String line){
		this.mail = line;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public void setId(int id){
		this.id = id;
	}
	
	/** Sort */
	public void setSortParam(String param){
		sortParam = param;
	}
	
	public int compareTo(Contact obj) {
		rb = ResourceBundle.getBundle(paramsProperties,new Locale("ru", "RU"));	
		if(sortParam.equals(rb.getString(COLUMN_TABLE_NAME))){
			return convertedNum(name) - convertedNum(obj.name);
		}
		else if(sortParam.equals(rb.getString(COLUMN_TABLE_SURNAME))){
			return convertedNum(surName) - convertedNum(obj.surName);
		} 
		else if(sortParam.equals(rb.getString(COLUMN_TABLE_LOGIN))){
			String mLogin = login.replaceAll(" ",  "");
			if(checkTypeParam(mLogin)){ return -1; }
			else { return 1; }
		} 
		else if(sortParam.equals(rb.getString(COLUMN_TABLE_MAIL))){
			return convertedNum(mail) - convertedNum(obj.mail);
		} 
		else if(sortParam.equals(rb.getString(COLUMN_TABLE_PHONE))){
			return convertedNum(phoneNumber) - convertedNum(obj.phoneNumber);
		} 
		return 0;
	}	
	
	private int convertedNum(String param){
		return param.replaceAll(" ",  "").length();
	}
	
	private boolean checkTypeParam(String name){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(name);
		return m.matches();
	}
}

