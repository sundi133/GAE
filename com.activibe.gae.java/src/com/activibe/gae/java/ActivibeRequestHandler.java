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


public class ActivibeRequestHandler  extends HttpServlet {


	private static final Logger log = Logger.getLogger(ActivibeRequestHandler.class.getName()); 

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
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		switch (activibeOpcode) {
		case Opcodes.LOGIN :
			int response = Common.authenticateUser(req);
			out.println(response);
			out.close();
			break;

		case Opcodes.NEW_ACCOUNT:
			response= createNewAccountForUser(req);
			out.println(response);
			out.close();
			break;
		case Opcodes.UPDATE_STATUS:

			break;

		default:
			break;
		}


	}



	private int createNewAccountForUser(HttpServletRequest req) {
		// TODO Auto-generated method stub
		try{
			System.out.println("ok2");
			String username= req.getParameter("username");
			String password=req.getParameter("password");
			String email= req.getParameter("email");

			int response= ActivibeDataAccessObject.INSTANCE.createActivibeUser(username,password,email);
			if(response==Opcodes.SUCCESS)
				sendMail(email,username,password);
			return response;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ok3");
			return Opcodes.ERR_CREATION; //error creating account
		}

	}

	private void sendMail(String email, String username, String password) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);


		String SENDER="activibealpha@appspot.gserviceaccount.com";
		String msgBody = "Hi " + username +", \n Your account has been successfully created. Thanks for signing up for the alpha version.\n\t Activibe Team";

		String subject="Your Activibealpha account has been activated";
		//		try {
		//
		//			Message msg1 = new MimeMessage(session);
		//			msg1.setFrom(new InternetAddress("activibealpha@appspot.gserviceaccount.com", "Activibe Admin"));
		//			msg1.addRecipient(Message.RecipientType.TO,
		//					new InternetAddress(email.trim(), username.trim()));
		//			msg1.setSubject("Your Activibealpha account has been activated");
		//			msg1.setText(msgBody);
		//			Transport.send(msg1);
		//
		//		} catch (AddressException e) {
		//			// ...
		//			log.log(Level.SEVERE, "AddressException" + e.getMessage(), "");
		//		} catch (MessagingException e) {
		//			// ...
		//			log.log(Level.SEVERE, "MessagingException" + e.getMessage(), "");
		//		} catch (UnsupportedEncodingException e) {
		//			// TODO Auto-generated catch block
		//			log.log(Level.SEVERE, "MessagingException" + e.getMessage(), "");
		//		}

		int count=0;
		try {
			count++;
			MimeMessage message = new MimeMessage(session, null);
			count++;
			// Set the body with whatever text you want
			Multipart outboundMultipart = new MimeMultipart();
			count++;
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			count++;
			messageBodyPart.setText(msgBody);
			count++;
			message.setSubject(subject);
			count++;
			outboundMultipart.addBodyPart(messageBodyPart);
			count++;
			message.setContent(outboundMultipart);
			count++;
			message.setFrom(new InternetAddress(SENDER, "Activibe Account account"));
			count++;
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email, username));
			count++;
			Transport.send(message);
			count++;
		} catch (MessagingException e) {
			log.log(Level.SEVERE, "MessagingException" + count + e.getMessage(), "");
		}catch (UnsupportedEncodingException e) {
			log.log(Level.SEVERE, "UnsupportedEncodingException" + e.getMessage(), "");
		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		int activibeOpcode = Integer.parseInt(req.getParameter("opcode"));
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		System.out.println("ok1");
		switch (activibeOpcode) {
		case Opcodes.LOGIN :
			int response = Common.authenticateUser(req);
			out.println(response);
			out.close();
			break;

		case Opcodes.NEW_ACCOUNT:
			System.out.println("ok1");
			response= createNewAccountForUser(req);
			out.println(response);
			out.close();
			break;
		case Opcodes.UPDATE_STATUS:

			break;

		default:
			break;
		}


	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
