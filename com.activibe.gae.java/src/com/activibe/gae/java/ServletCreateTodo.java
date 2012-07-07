package com.activibe.gae.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import com.activibe.gae.java.dao.Dao;
import com.activibe.gae.java.model.Cliques;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ServletCreateTodo extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		initialisemap();
		super.init();
	}

	private void initialisemap() {
		// TODO Auto-generated method stub
		jobKeyVals = Dao.getJobsMap();

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// System.out.println("Creating new todo ");
		int resume = 0;
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		// try{
		//
		// resp.setContentType("text/xml");
		// PrintWriter out = resp.getWriter();
		//
		// int clq= Integer.parseInt(req.getParameter("shoutLoud"));
		// if(clq==101){
		//
		//
		// String title = checkNull(req.getParameter("title"));
		// String desc = checkNull(req.getParameter("desc"));
		// String category = checkNull(req.getParameter("category"));
		// String location = checkNull(req.getParameter("location"));
		// String userid = checkNull(req.getParameter("userid"));
		// String username = checkNull(req.getParameter("username"));
		// Dao.INSTANCE.add(title,desc,category,location,userid,username);
		//
		//
		//
		//
		// out.println("<?xml version=\"1.0\"?>");
		// out.println("<success/>");
		// out.println("</xml>");
		// out.close();
		//
		// }else if(clq==102){
		// cliques = Dao.getCliques();
		// out.println("<?xml version=\"1.0\"?>");
		// out.println("<cliques>");
		//
		// for (int i = 0; i < cliques.size(); i++) {
		// out.println("<clique>");
		// out.println("<title>" + cliques.get(i).getTitle() + "</title>");
		// out.println("<desc>" + cliques.get(i).getDesText() + "</desc>");
		// out.println("<category>" + cliques.get(i).getCategory() +
		// "</category>");
		// out.println("<location>" + cliques.get(i).getLocation() +
		// "</location>");
		// out.println("<username>" + cliques.get(i).getUsername() +
		// "</username>");
		// out.println("<userid>" + cliques.get(i).getUserid() + "</userid>");
		// out.println("</clique>");
		//
		// }
		// out.println("</cliques>");
		// out.println("</xml>");
		// out.close();
		//
		//
		//
		// }
		//
		// }catch (Exception e) {
		// // TODO: handle exception
		// }

		try {

			int clq = Integer.parseInt(req.getParameter("shoutLoud"));
			if (clq == 101) {

				resp.setContentType("text/xml");
				PrintWriter out = resp.getWriter();

				String title = checkNull(req.getParameter("title"));
				String desc = checkNull(req.getParameter("desc"));
				String category = checkNull(req.getParameter("category"));
				String location = checkNull(req.getParameter("location"));
				String userid = checkNull(req.getParameter("userid"));
				String username = checkNull(req.getParameter("username"));
				Dao.INSTANCE.add(title, desc, category, location, userid,
						username);

				out.println("<?xml version=\"1.0\"?>");
				out.println("<success/>");
				out.println("</xml>");
				out.close();

			} else if (clq == 102) {

				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();

				cliques = Dao.getCliques();
				// out.println("<?xml version=\"1.0\"?>");
				// out.println("<cliques>");

				for (int i = 0; i < cliques.size(); i++) {
					if (i == cliques.size() - 1) {
						out.println(cliques.get(i).getTitle() + ":"
								+ cliques.get(i).getDesText() + ":"
								+ cliques.get(i).getCategory() + ":"
								+ cliques.get(i).getLocation().split(",")[0]
								+ ":"
								+ cliques.get(i).getLocation().split(",")[1]);
					} else {
						out.println(cliques.get(i).getTitle() + ":"
								+ cliques.get(i).getDesText() + ":"
								+ cliques.get(i).getCategory() + ":"
								+ cliques.get(i).getLocation().split(",")[0]
								+ ":"
								+ cliques.get(i).getLocation().split(",")[1]
								+ ",");
					}

					// out.println("<clique>");
					// out.println("<title>" + cliques.get(i).getTitle() +
					// "</title>");
					// out.println("<desc>" + cliques.get(i).getDesText() +
					// "</desc>");
					// out.println("<category>" + cliques.get(i).getCategory() +
					// "</category>");
					// out.println("<location>" + cliques.get(i).getLocation() +
					// "</location>");
					// out.println("<username>" + cliques.get(i).getUsername() +
					// "</username>");
					// out.println("<userid>" + cliques.get(i).getUserid() +
					// "</userid>");
					// out.println("</clique>");

				}
				// out.println("</cliques>");
				// out.println("</xml>");
				out.close();

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {

			int jb = Integer.parseInt(req.getParameter("jobdata"));
			if (jb == 100) {

				resp.setContentType("text/xml");
				PrintWriter out = resp.getWriter();

				String key = checkNull(req.getParameter("key"));
				String url = checkNull(req.getParameter("url"));
				Dao.INSTANCE.add(key, url);

				out.println("<?xml version=\"1.0\"?>");
				out.println("<success/>");
				out.println("</xml>");
				out.close();

			} else if (jb == 200) {
				jobKeyVals = Dao.getJobsMap();
				// Iterator it = jobKeyVals.entrySet().iterator();
				// while (it.hasNext()) {
				// Map.Entry pairs = (Map.Entry)it.next();
				// }

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {

			createjobslist();
			initLocations();

			String url = "http://www.salary.com/category/salary/";
			Profile p = new Profile();
			ArrayList<String> jobobjects = new ArrayList<String>();
			String[] types = new String[20];
			String[] state = new String[20];
			String[] country = new String[20];
			String stopwords = "a,able,about,across,after,all,almost,also,am,among,an,and,any,are,as,at,be,because,been,but,by,can,cannot,could,dear,did,do,does,either,else,ever,every,for,from,get,got,had,has,have,he,her,hers,him,his,how,however,i,if,in,into,is,it,its,just,least,let,like,likely,may,me,might,most,must,my,neither,no,nor,not,of,off,often,on,only,or,other,our,own,rather,said,say,says,she,should,since,so,some,than,that,the,their,them,then,there,these,they,this,tis,to,too,twas,us,wants,was,we,were,what,when,where,which,while,who,whom,why,will,with,would,yet,you,your";

			// try{
			//
			// FileInputStream fstream = new FileInputStream("stopwords.txt");
			// // Get the object of DataInputStream
			// DataInputStream in = new DataInputStream(fstream);
			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(in));
			// String strLine;
			// //Read File Line By Line
			// while ((strLine = br.readLine()) != null) {
			// // Print the content on the console
			// //System.out.println (strLine);
			//
			// if(strLine!=null && !strLine.trim().equalsIgnoreCase(""))
			// try{
			// stopwords+=strLine.split(",")[0]+",";
			// }catch (Exception e) {
			// // TODO: handle exception
			// }
			// }
			// //Close the input stream
			// in.close();
			// }catch (Exception e) {
			// // TODO: handle exception
			// e.printStackTrace();
			// }

			ArrayList<String> contents = new ArrayList<String>();
			// resume= Integer.parseInt(req.getParameter("resume"));

			if (true) {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				// out.println("error uploading, try a bit later. Thank You." +
				// req.getParameter("resume") );

				// Part filePart = req.getPart("datafile"); // Retrieves <input
				// type="file" name="file">
				// String filename = getFilename(filePart);
				// InputStream filecontent = filePart.getInputStream();
				//
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(req.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					// out.println(line);
					// out.println("done");
					// System.out.println (strLine);
					for (int i = 0; i < joblocations.size(); i++) {
						// System.out.println(strLine);
						if (line.indexOf(joblocations.get(i).trim()) != -1) {

							userLocation = joblocations.get(i);
							break;
						}

					}
					// System.out.println(userLocation);

					// System.out.println(strLine);
					String[] res = line.split("[ ,]");
					for (int i = 0; i < res.length; i++) {
						if (Pattern.matches("(\\w*[a-zA-Z0-9|-|#|@|.|]+\\w*)",
								res[i])) {
							for (int j = 0; j < joblocations.size(); j++) {
								if (editDistance(res[i], joblocations.get(j)) <= 4)
									userLocation = joblocations.get(j);
							}

						}
					}
					for (int i = 0; i < res.length; i++) {
						// if(Pattern.matches("\\w* | \\d* | \\+*", res[i]))
						if (stopwords.toLowerCase().indexOf(
								res[i].toLowerCase().trim()) == -1)
							if (contents.indexOf(res[i].toLowerCase().trim()) == -1)
								if (matched(res[i].toLowerCase().trim(), 1)) {
									String alphabets = "abcdefghijklmnopqrstuvwxyz";
									if (alphabets.indexOf(res[i].toLowerCase()
											.charAt(0)) != -1)
										contents.add(res[i].toLowerCase()
												.trim());
								}

					}
					for (int i = 0; i < res.length - 1; i++) {
						String tmp = res[i] + " " + res[i + 1];
						if (Pattern.matches("(\\w* \\w*)", tmp)) {
							// System.out.println(tmp);
							if (stopwords.toLowerCase().indexOf(
									tmp.toLowerCase().trim()) == -1)
								if (contents.indexOf(tmp.toLowerCase().trim()) == -1)
									if (matched(tmp.toLowerCase().trim(), 2))
										contents.add(tmp.toLowerCase().trim());
						}
					}

				}// end of while

				userLocation = "new%20york";
				// for (int i = 0; i < contents.size(); i++) {
				// out.println(contents.get(i));
				// }
				sendRequestTostartupapply(contents);

				Iterator<Entry<String, String>> iterator = tfidlist.entrySet()
						.iterator();

				out.println("<html>");
				out.println("<body bgcolor=\"white\">");
				out.println("<head>");

				out.println("<title>Quality Sturtps... </title>");
				out.println("</head>");
				out.println("<body>");
				int k = 0;
				while (iterator.hasNext()) {
					String[] res = iterator.next().toString().split(",");
					for (int i = 0; i < res.length; i++) {
						// out.println(res[i]);
						if (res[i].indexOf("job") != -1) {
							k++;
							out.println("<A HREF=" + res[i]
									+ " TARGET=\"_blank\">" + k + " : "
									+ res[i] + "</A><br/>");
						}

					}

				}

				out.println(skilltested);
				// Close the input stream

				out.println("</body>");
				out.println("</html>");

				out.close();
			}
			// else{
			// resp.setContentType("text/plain");
			// PrintWriter out = resp.getWriter();
			// out.println("error uploading, try a bit later.");
			// out.close();
			// }

		} catch (Exception e) {
			// TODO: handle exception
			resp.setContentType("text/plain");
			PrintWriter out = resp.getWriter();
			out.println("error uploading, try a bit later. Thank You." + resume);
			e.printStackTrace();
			out.close();
		}
		try {
			int gc = Integer.parseInt(req.getParameter("gcext"));
			if (gc == 1) {

				String urls = req.getParameter("urls");

				resp.setContentType("text/xml");

				PrintWriter out = resp.getWriter();

				out.println("<?xml version=\"1.0\"?>");
				out.println("<urls>");
				String[] url = urls.split("----");
				for (int k = 0; k < url.length; k++) {
					out.println("<url>" + url[k] + "</url>");
				}
				out.println("<ip>" + req.getRemoteAddr() + "</ip>");

				out.println("</urls>");

				out.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		//
		// User user = (User) req.getAttribute("user");
		// if (user == null) {
		// UserService userService = UserServiceFactory.getUserService();
		// user = userService.getCurrentUser();
		// }
		//
		// String summary = checkNull(req.getParameter("summary"));
		// String longDescription = checkNull(req.getParameter("description"));
		// String url = checkNull(req.getParameter("url"));
		//
		// Dao.INSTANCE.add(user.getUserId(), summary, longDescription, url);
		//
		// resp.sendRedirect("/TodoApplication.jsp");
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	private static String getValue(Part part) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				part.getInputStream(), "UTF-8"));
		StringBuilder value = new StringBuilder();
		char[] buffer = new char[1024];
		for (int length = 0; (length = reader.read(buffer)) > 0;) {
			value.append(buffer, 0, length);
		}
		return value.toString();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();

		}

		try {

			String activibe = checkNull(req.getParameter("activibe"));
			String userid = checkNull(req.getParameter("user"));

			resp.setContentType("text/plain");
			PrintWriter out = resp.getWriter();

			if (Integer.parseInt(activibe) == 2) {

				String pressure = checkNull(req.getParameter("pressure"));

				String blood = checkNull(req.getParameter("blood"));

				String time = checkNull(req.getParameter("time"));
				Dao.INSTANCE.addactivibe(activibe, userid, pressure, blood,
						time);

				out.println("success");
			} else if (Integer.parseInt(activibe) == 3) {
				// pressure fetch request

				String[] pressure = Dao.INSTANCE.getActivibePressure(userid);
				for (int i = 0; i < pressure.length; i++) {
					if (i == pressure.length - 1) {
						out.println(pressure[i]);
					} else {
						out.println(pressure[i] + ",");
					}

				}
			}
			return;

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			int cnt = 0;
			String tweet_param = checkNull(req.getParameter("userTweet"));
			if (tweet_param != null) {
				resp.setContentType("text/plain");
				PrintWriter out = resp.getWriter();
				try {

					// tweet =tweet.split(",")[1].split("@")[0];
					String tweet = tweet_param.split(",")[0] + " twitter";
					String APPID = "73FBA04BA3B5DA1A28909E7CE8916F8034376378";
					tweet = replacespace(tweet);

					String url1 = "http://api.bing.net/json.aspx?AppId="
							+ APPID + "&Version=2.2&Market=en-US&Query="
							+ tweet + "&Sources=web+spell&Web.Count=20";
					// String url =
					// "http://api.bing.net/json.aspx?AppId=73FBA04BA3B5DA1A28909E7CE8916F8034376378&Version=2.2&Market=en-US&Query="+"jyotirmoy%20sundi"+"&Sources=web+spell&Web.Count=20";
					URL yahoo = new URL(url1);
					URLConnection yc = yahoo.openConnection();
					// yc.setRequestProperty("User-Agent",
					// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
					BufferedReader in = new BufferedReader(
							new InputStreamReader(yc.getInputStream()));
					String inputLine = "";
					String response = "";
					while ((inputLine = in.readLine()) != null) {
						// count++;
						response += inputLine;
					}
					// in.close();
					// JsonParser jParser = jfactory.createJsonParser(response);
					JsonFactory jfactory = new JsonFactory();
					JsonParser jParser = jfactory.createJsonParser(response);
					ArrayList<String> list1 = new ArrayList<String>();
					while (jParser.nextToken() != null) {

						String fieldname = jParser.getCurrentName();
						String url = "";
						String desc = "";
						if ("Description".equals(fieldname)) {
							jParser.nextToken();
							desc = jParser.getText();
							jParser.nextToken();
							fieldname = jParser.getText();
						}

						if ("Url".equals(fieldname)) {

							// current token is "name",
							// move to next, which is "name"'s value
							jParser.nextToken();
							// System.out.println(jParser.getText()); // display
							// mkyong
							url = jParser.getText();

						}

						// System.out.println("res1" +url+","+desc); // display
						// mkyong

						if (!url.trim().equalsIgnoreCase("")
								&& !desc.trim().equalsIgnoreCase("")) {
							if (url.indexOf("linkedin") != -1
									|| url.indexOf("twitter") != -1) {
								list1.add(url + "," + desc);
							}
							// System.out.println("res11" +url+","+desc); //
							// display mkyong
						}

					}
					// jParser.close();

					// tweet =tweet.split(",")[1];
					// tweet=replacespace(tweet);
					//
					// url1="http://api.bing.net/json.aspx?AppId="+APPID
					// +"&Version=2.2&Market=en-US&Query="+tweet+"&Sources=web+spell&Web.Count=20";
					// //String url =
					// "http://api.bing.net/json.aspx?AppId=73FBA04BA3B5DA1A28909E7CE8916F8034376378&Version=2.2&Market=en-US&Query="+"jyotirmoy%20sundi"+"&Sources=web+spell&Web.Count=20";
					// yahoo = new URL(url1);
					// String response2="";
					// yc = yahoo.openConnection();
					// //yc.setRequestProperty("User-Agent",
					// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
					// in = new BufferedReader(
					// new InputStreamReader(
					// yc.getInputStream()));
					// while ((inputLine = in.readLine()) != null){
					// //count++;
					// response2+=inputLine;
					// }
					// in.close();
					// out.print(response + response2);
					// for (int i = 0; i < list1.size(); i++) {
					// out.println(list1.get(i));
					// }
					//

					tweet = tweet_param.split(",")[0] + " linkedin";

					tweet = replacespace(tweet);

					url1 = "http://api.bing.net/json.aspx?AppId=" + APPID
							+ "&Version=2.2&Market=en-US&Query=" + tweet
							+ "&Sources=web+spell&Web.Count=20";
					// String url =
					// "http://api.bing.net/json.aspx?AppId=73FBA04BA3B5DA1A28909E7CE8916F8034376378&Version=2.2&Market=en-US&Query="+"jyotirmoy%20sundi"+"&Sources=web+spell&Web.Count=20";
					yahoo = new URL(url1);
					yc = yahoo.openConnection();
					// yc.setRequestProperty("User-Agent",
					// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
					in = new BufferedReader(new InputStreamReader(
							yc.getInputStream()));
					inputLine = "";
					response = "";
					while ((inputLine = in.readLine()) != null) {
						// count++;
						response += inputLine;
					}
					// System.out.println(response);
					in.close();
					// JsonParser jParser = jfactory.createJsonParser(response);
					jfactory = new JsonFactory();
					jParser = jfactory.createJsonParser(response);
					while (jParser.nextToken() != null) {

						String fieldname = jParser.getCurrentName();
						String url = "";
						String desc = "";
						if ("Description".equals(fieldname)) {
							jParser.nextToken();
							desc = jParser.getText();
							jParser.nextToken();
							fieldname = jParser.getText();
						}

						if ("Url".equals(fieldname)) {

							// current token is "name",
							// move to next, which is "name"'s value
							jParser.nextToken();
							// System.out.println(jParser.getText()); // display
							// mkyong
							url = jParser.getText();

						}

						// System.out.println("res2" +url+","+desc); // display
						// mkyong

						if (!url.trim().equalsIgnoreCase("")
								&& !desc.trim().equalsIgnoreCase("")) {
							if (url.indexOf("linkedin") != -1
									|| url.indexOf("twitter") != -1) {
								list1.add(url + "," + desc);
							}
							// System.out.println("res22" +url+","+desc); //
							// display mkyong
						}

					}
					// jParser.close();

					String email = tweet_param.split(",")[1];
					// Set the email pattern string
					Pattern p = Pattern.compile("(.+)@(.+)(\\.[a-z]+)");

					// Match the given string with the pattern
					Matcher m = p.matcher(email);
					// check whether match is found
					boolean matchFound = m.matches();
					tweet = tweet_param.split(",")[0] + " " + m.group(2);

					tweet = replacespace(tweet);

					url1 = "http://api.bing.net/json.aspx?AppId=" + APPID
							+ "&Version=2.2&Market=en-US&Query=" + tweet
							+ "&Sources=web+spell&Web.Count=20";
					// String url =
					// "http://api.bing.net/json.aspx?AppId=73FBA04BA3B5DA1A28909E7CE8916F8034376378&Version=2.2&Market=en-US&Query="+"jyotirmoy%20sundi"+"&Sources=web+spell&Web.Count=20";
					yahoo = new URL(url1);
					yc = yahoo.openConnection();
					// yc.setRequestProperty("User-Agent",
					// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
					in = new BufferedReader(new InputStreamReader(
							yc.getInputStream()));
					inputLine = "";
					response = "";
					while ((inputLine = in.readLine()) != null) {
						// count++;
						response += inputLine;
					}
					// System.out.println(response);
					in.close();
					// JsonParser jParser = jfactory.createJsonParser(response);
					jfactory = new JsonFactory();
					jParser = jfactory.createJsonParser(response);
					while (jParser.nextToken() != null) {

						String fieldname = jParser.getCurrentName();
						String url = "";
						String desc = "";
						if ("Description".equals(fieldname)) {
							jParser.nextToken();
							desc = jParser.getText();
							jParser.nextToken();
							fieldname = jParser.getText();
						}

						if ("Url".equals(fieldname)) {

							// current token is "name",
							// move to next, which is "name"'s value
							jParser.nextToken();
							// System.out.println(jParser.getText()); // display
							// mkyong
							url = jParser.getText();

						}

						// System.out.println("res3" +url+","+desc); // display
						// mkyong
						if (!url.trim().equalsIgnoreCase("")
								&& !desc.trim().equalsIgnoreCase("")) {
							if (url.indexOf("linkedin") != -1
									|| url.indexOf("twitter") != -1) {
								list1.add(url + "," + desc);

								// System.out.println("res33" +url+","+desc); //
								// display mkyong

							}

						}

					}

					jParser.close();

					// System.out.println(list1);
					// out.println("<html><body>");
					for (int i = 0; i < list1.size(); i++) {
						// out.println(list1.get(i));
						out.println("<a href=\"" + list1.get(i).split(",")[0]
								+ "\">" + list1.get(i).split(",")[0] + "</a>");
						out.println();
					}
					// out.println("</body></html>");
					return;
					// out.println(list1);
				} catch (Exception e) {
					// System.out.println("not working" + e.g );
					out.println("sorry some error occured, try again...");
					e.printStackTrace();
				}

			} else {
				int clq = Integer.parseInt(req.getParameter("shoutLoud"));
				if (clq == 101) {

					resp.setContentType("text/xml");
					PrintWriter out = resp.getWriter();

					String title = checkNull(req.getParameter("title"));
					String desc = checkNull(req.getParameter("desc"));
					String category = checkNull(req.getParameter("category"));
					String location = checkNull(req.getParameter("location"));
					String userid = checkNull(req.getParameter("userid"));
					String username = checkNull(req.getParameter("username"));
					Dao.INSTANCE.add(title, desc, category, location, userid,
							username);

					out.println("<?xml version=\"1.0\"?>");
					out.println("<success/>");
					out.println("</xml>");
					out.close();

				} else if (clq == 102) {

					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();

					cliques = Dao.getCliques();
					// out.println("<?xml version=\"1.0\"?>");
					// out.println("<cliques>");

					for (int i = 0; i < cliques.size(); i++) {
						// if(i==cliques.size()-1){
						// out.println(cliques.get(i).getTitle());
						// }else{
						// out.println(cliques.get(i).getTitle()+",");
						// }

						if (i == cliques.size() - 1) {
							out.println(cliques.get(i).getTitle()
									+ ":"
									+ cliques.get(i).getDesText()
									+ ":"
									+ cliques.get(i).getCategory()
									+ ":"
									+ cliques.get(i).getLocation().split(",")[0]
									+ ":"
									+ cliques.get(i).getLocation().split(",")[1]);
						} else {
							out.println(cliques.get(i).getTitle()
									+ ":"
									+ cliques.get(i).getDesText()
									+ ":"
									+ cliques.get(i).getCategory()
									+ ":"
									+ cliques.get(i).getLocation().split(",")[0]
									+ ":"
									+ cliques.get(i).getLocation().split(",")[1]
									+ ",");
						}

						// out.println("<clique>");
						// out.println("<title>" + cliques.get(i).getTitle() +
						// "</title>");
						// out.println("<desc>" + cliques.get(i).getDesText() +
						// "</desc>");
						// out.println("<category>" +
						// cliques.get(i).getCategory() + "</category>");
						// out.println("<location>" +
						// cliques.get(i).getLocation() + "</location>");
						// out.println("<username>" +
						// cliques.get(i).getUsername() + "</username>");
						// out.println("<userid>" + cliques.get(i).getUserid() +
						// "</userid>");
						// out.println("</clique>");

					}
					// out.println("</cliques>");
					// out.println("</xml>");
					out.close();

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Creating new todo ");
		try {
			int gc = Integer.parseInt(req.getParameter("gcext"));
			if (gc == 1) {

				String urls = req.getParameter("urls");

				resp.setContentType("text/plain");

				PrintWriter out = resp.getWriter();

				out.println("<?xml version=\"1.0\"?>");
				out.println("<urls>");
				String[] url = urls.split("----");
				for (int k = 0; k < url.length; k++) {
					out.println("<url>" + url[k] + "</url>");
				}
				out.println("<ip>" + req.getRemoteAddr() + "</ip>");

				out.println("</urls>");

				out.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			resp.setContentType("text/plain");

			PrintWriter out = resp.getWriter();

			out.println("<?xml version=\"1.0\"?>");
			out.println("<urls>1</urls>");
			out.close();

		}

	}

	private static String replacespace(String tweet) {
		// TODO Auto-generated method stub
		return tweet.replaceAll(" ", "%20");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	private static void fetchLinksFromMemcache(ArrayList<String> contents) {

		Iterator it = jobKeyVals.entrySet().iterator();

		int pnt = 0;// contents pointer
		int page_num = 1;
		// while (pnt < contents.size()) {
		int mindist = 100;
		int minind = 0;
		String tmp = "";
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();

			// System.out.println(pairs.getKey() + " = " + pairs.getValue());
			for (int j = 0; j < contents.size(); j++) {
				int dist = editDistance(pairs.getKey().toString(),
						contents.get(j));
				if (dist <= minind) {
					if (dist == minind) {
						mindist = dist;

					} else {
						mindist = dist;

					}

				}
			}

		}

	}

	private static void sendRequestTostartupapply(ArrayList<String> contents) {
		// TODO Auto-generated method stub
		// http://www.startuply.com/#/java%20in%20california/1

		int pnt = 0;// contents pointer
		int page_num = 1;
		// while (pnt < contents.size()) {
		int mindist = 100;
		int minind = 0;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String tmp = "";
		for (int i = 0; i < jobskills.size(); i++) {
			for (int j = 0; j < contents.size(); j++) {
				int dist = editDistance(jobskills.get(i), contents.get(j));
				if (dist <= minind) {
					if (dist == minind) {
						mindist = dist;
						minind = i;
						tmp += Integer.toString(i) + ",";
					} else {
						mindist = dist;
						minind = i;
						tmp = Integer.toString(i);
					}

				}
			}

		}
		pnt = minind;
		// while (pnt < contents.size()) {

		String[] search = tmp.split(" ");
		pnt = 0;
		// while (pnt < minind+1) {//run one time
		while (pnt < 2) {// run one time
			System.out.println("pnt " + pnt);
			int skill_index = -1;
			// for (int i = 0; i < jobskills.size(); i++) {
			// if(Math.abs(editDistance(jobskills.get(i).trim(),contents.get(pnt).trim()))<=2){
			// //System.out.println("s :" + contents.get(pnt).trim());
			// skill_index=i;
			// break;
			//
			// }else{
			//
			// }
			// }
			// System.out.println(skill_index);
			// if(skill_index==-1){
			// pnt++;
			// continue;
			// }

			int cnt = 1;// page_num
			skilltested = jobskills.get(pnt);
			while (cnt <= 1) {
				// String url= "http://www.startuply.com/#/" +
				// jobskills.get(jobskills.indexOf(contents.get(pnt).toLowerCase().trim()))
				// + "%20in%20" + userLocation + "/" + cnt;
				String furl = "http://www.startuply.com/#/"
						+ jobskills.get(pnt) + "%20in%20" + userLocation + "/"
						+ "undefined";

				furl = "http://www.startuply.com/#/" + jobskills.get(pnt)
						+ "/1";
				furl = "http://www.indeed.com/jobs?q=startup+%2B+"
						+ jobskills.get(pnt) + "&l=new+york";

				skilltested += jobskills.get(pnt);
				String response = "";
				int breaking = 0;
				try {
					// System.out.println(furl);
					URL url = new URL(furl);

					URLConnection yc = url.openConnection();
					yc.setRequestProperty(
							"User-Agent",
							"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
					BufferedReader in = new BufferedReader(
							new InputStreamReader(yc.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null) {
						response += inputLine;
					}
					// System.out.print(response);
					in.close();

					ArrayList<String> urls = findallUrl(response);

					skilltested += response;

					skilltested += "<null>" + urls.size() + urls.get(0);

					for (int i = 0; i < urls.size(); i++) {
						parseContents(urls.get(i));
					}

					// parseContents(link.attr("abs:href"));

					// Elements doc =
					// Jsoup.connect(url).get().getElementsByClass("DataTable");
					// Elements doc = Jsoup.connect(url).header("User-Agent",
					// "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get().getElementsByClass("DataTable");
					// //System.out.println(Jsoup.connect(url).get().text());
					// Elements links = doc.select("a[href]");
					// System.out.println(url + " size" + links.size());
					//
					// //System.out.println(doc.text());
					// for (Element link : links) {
					// // JobList j = new
					// JobList(link.attr("abs:href"),link.text());
					// // joblist.add(j);
					// //System.out.println(link.attr("abs:href") +
					// link.text());
					// parseContents(link.attr("abs:href"));
					// //System.out.println();
					// }

				} catch (Exception e) {

					e.printStackTrace();
					System.out.println("error ");
				}
				cnt++;
			}

			pnt++;

		}

	}

	private static ArrayList<String> findallUrl(String response) {
		// TODO Auto-generated method stub

		ArrayList<String> crawledUrlList = new ArrayList<String>();

		String lowValue = new String(response);

		Pattern pattern = Pattern
				.compile("\\(?\\b(https??://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]");

		Matcher matcher = pattern.matcher(lowValue);

		while (matcher.find()) {

			String url = matcher.group();

			crawledUrlList.add(url);

		}

		return crawledUrlList;
	}

	private static void parseContents(String furl) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(url);
			URL url = new URL(furl);
			URLConnection yc = url.openConnection();
			yc.setRequestProperty(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;

			String response = "";
			while ((inputLine = in.readLine()) != null) {
				response += inputLine;
			}

			in.close();

			String[] text = response.split(" ");
			for (int i = 0; i < text.length; i++) {
				for (int j = 0; j < jobskills.size(); j++) {
					System.out.println(text[i]);
					if (Math.abs(editDistance(text[i], jobskills.get(j))) == 0) {
						putinMap(text[i], furl);
						// tfidlist.put(key, value)
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();

		}

	}

	private static void putinMap(String key, String url) {
		// TODO Auto-generated method stub
		// tfidlist.put(key, value)
		String val = tfidlist.get(key) + "," + url;
		tfidlist.put(key, val);
		System.out.println("key : " + key + " val : " + url);

	}

	private static void initLocations() {
		// TODO Auto-generated method stub

		joblocations.add("new york");
		joblocations.add("california");
		joblocations.add("san francisco");
		joblocations.add("palo alto");
		joblocations.add("carolina");
		joblocations.add("florida");
		joblocations.add("colorado");
		joblocations.add("bangalore");
		joblocations.add("hyderabad");
		joblocations.add("pune");
		joblocations.add("mumbai");
		joblocations.add("usa");
		joblocations.add("india");

	}

	private static boolean matched(String lowerCase, int key) {
		// TODO Auto-generated method stub
		switch (key) {
		case 1:
			for (int i = 0; i < jobskills.size(); i++) {
				if (editDistance(jobskills.get(i), lowerCase) <= 2)
					return true;

			}
			break;

		case 2:
			String[] tmp = lowerCase.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp.length; j++) {
					if (editDistance(jobskills.get(i).trim(), tmp[j].trim()) <= 2)
						return true;

				}
			}

			break;
		default:
			break;
		}

		return false;
	}

	public static int editDistance(String s, String t) {
		int m = s.length();
		int n = t.length();
		int[][] d = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			d[i][0] = i;
		}
		for (int j = 0; j <= n; j++) {
			d[0][j] = j;
		}
		for (int j = 1; j <= n; j++) {
			for (int i = 1; i <= m; i++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					d[i][j] = min((d[i - 1][j] + 1), (d[i][j - 1] + 1),
							(d[i - 1][j - 1] + 1));
				}
			}
		}
		return (d[m][n]);
	}

	public static int min(int a, int b, int c) {
		return (Math.min(Math.min(a, b), c));
	}

	private static void createjobslist() {
		// TODO Auto-generated method stub
		// grepcourses("http://www.cs.sunysb.edu/graduate/GraduateCourses.html");
		jobskills.add("java");
		jobskills.add("web");
		jobskills.add("data");
		jobskills.add("hadoop");
		jobskills.add("machinelearning");
		jobskills.add("nlp");
		jobskills.add("natural language procesing");
		jobskills.add("language procesing");
		jobskills.add("nlp");
		jobskills.add("datamining");
		jobskills.add("android");
		jobskills.add("operating systems");
		jobskills.add("systems");
		jobskills.add("kernel");
		jobskills.add("javascript");
		jobskills.add("artificial intelligence");
		jobskills.add("server");
		jobskills.add("networks");
		jobskills.add("security");
		jobskills.add("secure");
		jobskills.add("compiler");
		jobskills.add("maps");
		jobskills.add("database");
		jobskills.add("ios");
		jobskills.add("app");
		jobskills.add("c++");
		jobskills.add("client");
		jobskills.add("server");
		jobskills.add("xmpp");
		jobskills.add("calender");
		jobskills.add("iphone");
		jobskills.add("operating");

		jobskills.add("algorithms");
		jobskills.add("debug");
		jobskills.add("html");
		jobskills.add("ui");
		jobskills.add("ajax");

	}

	private static void grepcourses(String url) {
		// TODO Auto-generated method stub
		String response = "";
		try {

			Document doc = Jsoup.connect(url).get();
			// System.out.println(doc.text());
			Elements links = doc.select("a[href]");

			for (Element link : links) {
				// print(" Job Links ", link.attr("abs:href"), trim(link.text(),
				// 35));
				// System.out.println();
				// System.out.println(link.text());

				try {

					if (link.attr("abs:href").indexOf("courses") != -1
							&& link.attr("abs:href").matches(("^.*\\d.html"))) {
						System.out.println(link.attr("abs:href"));
						try {

							Document indoc = Jsoup.connect(
									link.attr("abs:href")).get();
							Elements elem1 = indoc
									.getElementsByClass("justify");

							// System.out.println(doc.text());
							for (int i = 0; i < elem1.size(); i++) {
								System.out.println(elem1.get(i).text());
							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

				// String[] jobs = parsedata(link.text());
				// for (int i = 0; i < jobs.length; i++) {
				// jobskills.add(jobs[i]);
				// }

				// System.out.println();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void sendstartuply(String url) {

		String response = "";
		try {

			Elements doc = Jsoup.connect(url).get()
					.getElementsByClass("DataTable");
			// System.out.println(doc.text());
			Elements links = doc.select("a[href]");
			// Elements media = doc.select("[src]");
			// Elements imports = doc.select("link[href]");
			// //
			// print("\nMedia: (%d)", media.size());
			// for (Element src : media) {
			// if (src.tagName().equals("img"))
			// print(" * %s: <%s> %sx%s (%s)",
			// src.tagName(), src.attr("abs:src"), src.attr("width"),
			// src.attr("height"),
			// trim(src.attr("alt"), 20));
			// else
			// print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			// }
			//
			// print("\nImports: (%d)", imports.size());
			// for (Element link : imports) {
			// print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"),
			// link.attr("rel"));
			// }

			// print("\nLinks: (%d)", links.size());
			for (Element link : links) {
				// print(" Job Links ", link.attr("abs:href"), trim(link.text(),
				// 35));
				// System.out.println();
				// System.out.println(link.text());

				JobList j = new JobList(link.attr("abs:href"), link.text());
				joblist.add(j);
				// System.out.println();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void sendDataMonster(String[] types, String[] state,
			String[] country) {
		// TODO Auto-generated method stub

		for (int i = 0; i < types.length; i++) {

			String url = "http://jobsearch.monster.co.uk/jobs/?q=" + types[i]
					+ "&where=" + state[0] + "&cy=" + country[0];
			// url=
			String response = "";
			try {

				Elements doc = Jsoup.connect(url).get()
						.getElementsByClass("multiline");
				System.out.println(doc.text());
				Elements links = doc.select("a[href]");
				Elements media = doc.select("[src]");
				Elements imports = doc.select("link[href]");

				print("\nMedia: (%d)", media.size());
				for (Element src : media) {
					if (src.tagName().equals("img"))
						print(" * %s: <%s> %sx%s (%s)", src.tagName(),
								src.attr("abs:src"), src.attr("width"),
								src.attr("height"), trim(src.attr("alt"), 20));
					else
						print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
				}

				print("\nImports: (%d)", imports.size());
				for (Element link : imports) {
					print(" * %s <%s> (%s)", link.tagName(),
							link.attr("abs:href"), link.attr("rel"));
				}

				print("\nLinks: (%d)", links.size());
				for (Element link : links) {
					print(" * a: <%s>  (%s)", link.attr("abs:href"),
							trim(link.text(), 35));
					// System.out.println( link.text());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			// return tmp_res.replace("&", "");
			// System.out.println(tmp_res);

		}

	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}

	public static ArrayList<JobList> joblist = new ArrayList<JobList>();
	public static ArrayList<String> jobskills = new ArrayList<String>();
	public static ArrayList<String> joblocations = new ArrayList<String>();
	public static HashMap<String, String> tfidlist = new HashMap<String, String>();
	public static HashMap<String, String> jobKeyVals = new HashMap<String, String>();
	public static List<Cliques> cliques = new ArrayList<Cliques>();
	private static String userLocation = "";
	private static String skilltested = "";

}

class Profile {

	String skills = "";
	String address = "";

	public void addSkills(String text) {
		skills += text + ",";

	}

	public String getSkills() {
		return skills;
	}

	public void addAdress(String str) {
		address += str + ",";
	}

	public String getAddress() {
		return address;
	}
}

class JobList {

	String link = "";
	String type = "";

	public JobList(String attr, String text) {
		// TODO Auto-generated constructor stub
		link = attr;
		type = text;
	}
}
