package com.activibe.gae.java;

import javax.servlet.http.HttpServletRequest;

import com.activibe.gae.java.dao.ActivibeDataAccessObject;

/*
 * hex value of activibe , last four digits,defines in opcodes
 * opcodes starts with 6265 
 * 6265
 * 6266
 * 6267
 * 6268
 * 6269
 */
public class Opcodes {
	public final static int LOGIN = 6265; 
	public final static int NEW_ACCOUNT = 6266;
	public final static int UPDATE_STATUS = 6267;
	public final static int GET_UPDATE_STATUS = 6268;
	public static final int EMAILTOSENDDATA = 6269;
	public static final int EMAILFROMCLIENT = 6270;
	
	public static final int SUCCESS = 200;
	
	//ERRORS
	public static final int USER_EXISTS = 1002;
	public static final int ERR_CREATION = 1003;
	public static final int USER_ALREADY_EXISTS = 1004;
	public static final int INVALID_USER = 1005;
	public static final int USER_DONOT_EXISTS = 1006;
	
}