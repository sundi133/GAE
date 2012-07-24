package com.activibe.gae.java;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.activibe.gae.java.dao.ActivibeDataAccessObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@SuppressWarnings("serial")
public class ActivibeStatusRequestHandler  extends HttpServlet {
	private static final Logger log = Logger.getLogger(ActivibeStatusRequestHandler.class.getName());
	private final int PAGE_LIMIT = 4; 
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		initialisemap();
		super.init();
	}

	private void initialisemap() {
		// TODO Auto-generated method stub


	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {

		int activibeOpcode = Integer.parseInt(req.getParameter("opcode"));
		
		switch (activibeOpcode) {
		case Opcodes.UPDATE_STATUS :
			resp.setContentType("text/plain");
			PrintWriter out = resp.getWriter();

			int response = Common.authenticateUserID(req);
			if(response==200){
				int statusupdate=updateStatus(req);
				out.println(statusupdate);
			}else{
				out.println(Opcodes.INVALID_USER);
			}
			
			out.close();
			break;
			
		case Opcodes.GET_UPDATE_STATUS :
			resp.setContentType("application/json");
			out = resp.getWriter();

			response = Common.authenticateUserID(req);
			log.log(Level.SEVERE, "Status Response" + response, "");
			
			
			if(response==200){
				String statusupdate=getUpdateStatus(req);
				out.println(statusupdate);
			}else{
				out.println(Opcodes.INVALID_USER);
			}
			
			out.close();
			break;

		default:
			break;
		}




	}
	
	private String getUpdateStatus(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		String devicetype = req.getParameter("devicetype");
		String pnum = req.getParameter("pnum");
		String response="";
		if(devicetype.equalsIgnoreCase("iose")){
			response= ActivibeDataAccessObject.INSTANCE.getActivibeUserStatus(userid,pnum,PAGE_LIMIT);
		}else{
			response= ActivibeDataAccessObject.INSTANCE.getActivibeUserStatus(userid);
		}
		
		log.log(Level.SEVERE, "Status Response" + response, "");
		
		return response; 
	
	}

	private int updateStatus(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		String moodlevel = req.getParameter("moodlevel");
		String energylevel = req.getParameter("energylevel");
		String time = req.getParameter("time");
		String location = req.getParameter("location");
		String locationType = req.getParameter("locationType");
		String lat = req.getParameter("lat");
		String lon = req.getParameter("lon");
		int response= ActivibeDataAccessObject.INSTANCE.createActivibeUserStatus(userid,moodlevel,energylevel,time,location,locationType,lat,lon);
		return response;
	
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {

		int activibeOpcode = Integer.parseInt(req.getParameter("opcode"));
		
		switch (activibeOpcode) {
		case Opcodes.UPDATE_STATUS :
			resp.setContentType("text/plain");
			PrintWriter out = resp.getWriter();

			int response = Common.authenticateUserID(req);
			if(response==200){
				int statusupdate=updateStatus(req);
				out.println(statusupdate);
			}else{
				out.println(Opcodes.INVALID_USER);
			}
			
			out.close();
			break;
			
		case Opcodes.GET_UPDATE_STATUS :
			resp.setContentType("application/json");
			out = resp.getWriter();

			response = Common.authenticateUserID(req);
			log.log(Level.SEVERE, "0Status Response" + response, "");
			
			
			if(response==200){
				String statusupdate=getUpdateStatus(req);
				out.println(statusupdate);
			}else{
				out.println(Opcodes.INVALID_USER);
			}
			
			out.close();
			break;

		default:
			break;
		}

	}
}