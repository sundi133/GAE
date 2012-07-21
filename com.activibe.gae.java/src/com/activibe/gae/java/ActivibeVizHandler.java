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


public class ActivibeVizHandler  extends HttpServlet {
	private static final Logger log = Logger.getLogger(ActivibeVizHandler.class.getName()); 
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
		resp.sendRedirect("/viz.jsp");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		resp.sendRedirect("/viz.jsp");
	}
}
