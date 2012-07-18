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
public class Common {
	
	static int authenticateUser(HttpServletRequest req) {
		// TODO Auto-generated method stub

		try{
			String username= req.getParameter("username");
			String password=req.getParameter("password");
			int response= ActivibeDataAccessObject.INSTANCE.verifiActivibeUser(username,password); 
			return response;
		}catch (Exception e) {
			// TODO: handle exception
			return Opcodes.INVALID_USER;
		}
	}

	static int authenticateUserID(HttpServletRequest req) {
		// TODO Auto-generated method stub

		try{
			String username= req.getParameter("userid");
			int response= ActivibeDataAccessObject.INSTANCE.verifiActivibeUserID(username); 
			return response;
		}catch (Exception e) {
			// TODO: handle exception
			return Opcodes.INVALID_USER;
		}
	}


	static String authenticateUserIDGetEmail(HttpServletRequest req) {
		// TODO Auto-generated method stub

			String username= req.getParameter("userid");
			String response= ActivibeDataAccessObject.INSTANCE.verifiActivibeUserIDGetEmail(username); 
			return response;
		
	}
	


}