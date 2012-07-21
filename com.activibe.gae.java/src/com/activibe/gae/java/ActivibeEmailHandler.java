package com.activibe.gae.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
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
import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.mail.*;
import javax.mail.util.ByteArrayDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.activation.DataHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.Properties;

@SuppressWarnings("serial")
public class ActivibeEmailHandler  extends HttpServlet {

	private static final Logger log = Logger.getLogger(ActivibeEmailHandler.class.getName()); 

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		initialisemap();
		super.init();
	}

	private void initialisemap() {
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {

		
		int activibeOpcode = Integer.parseInt(req.getParameter("opcode"));
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		switch (activibeOpcode) {
		case Opcodes.EMAILTOSENDDATA :
			String response = Common.authenticateUserIDGetEmail(req);
			if(response.contains("@")){
				int mailresponse = sendMail(req,response);
				out.println(mailresponse);
				out.close();
			}else{

				out.println(response);
				out.close();
			}
			break;

		default:
			break;
		}


	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		int activibeOpcode = Integer.parseInt(req.getParameter("opcode"));
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		switch (activibeOpcode) {
		case Opcodes.EMAILTOSENDDATA :
			String response = Common.authenticateUserIDGetEmail(req);
			if(response.contains("@")){
				int mailresponse = sendMail(req,response);
				out.println(mailresponse);
				out.close();
			}else{

				out.println(response);
				out.close();
			}
			break;

		default:
			break;
		}


	}

	private int sendMail(HttpServletRequest req,String email) {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
        	String userid    = req.getParameter("userid");
        	String emailtosharewith = req.getParameter("emailtosharewith");
        	
        	String[] emails = emailtosharewith.split(",");

        	String link=generateRandomLink(userid,emailtosharewith);
        	String subject="Data Reports from Activibe ";
            String msgBody = "Hi, \n " +
            		"\t"+getUser(userid) +" has shared Activibe data with you." + " "+
            		"Please click on the link to access the report. "+
            		link + " \n\n\n"+
            		"Thanks \n"+
            		"Activibe Team \n";


            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@activibealpha.appspotmail.com", "Activibe Admin"));
            msg.addRecipient(Message.RecipientType.CC,
                             new InternetAddress(email, userid));
            for (int i = 0; i < emails.length; i++) {
            	msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(emails[i]));
       
			}
            
            msg.setSubject(subject);
            msg.setText(msgBody);
            Transport.send(msg);

            return 200;
        } catch (AddressException e) {
            // ...
        	return 201;
        } catch (MessagingException e) {
            // ...
        	return 202;
        }catch ( UnsupportedEncodingException e) {
			// TODO: handle exception
        	return 203;
		}
	}

	private String getUser(String userid) {
		// TODO Auto-generated method stub
		String upperuserid=userid.toUpperCase();
		return upperuserid.substring(0, 1)+userid.substring(1);
		
	}

	private String generateRandomLink(String userid,
			String emailtosharewith) {
		// TODO Auto-generated method stub
		BigInteger b = new BigInteger(128, new Random());
		String codevis=b.toString();
		ActivibeDataAccessObject.INSTANCE.updateDataForVisualization(userid,emailtosharewith,codevis);
		String link = "http://activibealpha.appspot.com/viz.jsp?code="+codevis;
		return link;
	}
}