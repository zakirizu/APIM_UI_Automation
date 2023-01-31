package com.informatica.web.util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.log4testng.Logger;

import com.informatica.tests.Launcher;

public class MailHelper {
	
	static Logger logger = Logger.getLogger(MailHelper.class);
	private Properties properties ;
	private String from,subject;
	private List<String> to = new ArrayList<>();
	//	private List<String> cc = new ArrayList<>();
	
	
	public MailHelper(){		
		from=ConfigPropertiesLoader.getProperty("from");

	}
	

	
	public void sendMail() 
			throws MessagingException, IOException{		
		properties = new Properties();
		properties.setProperty("mail.smtp.host", "mail.informatica.com");   
		properties.put("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(properties);    
		session=session.getInstance(properties);
		MimeMessage message = new MimeMessage(session);    

		subject = ConfigPropertiesLoader.getProperty("subject");
		subject = subject.concat("Results - Version: "+ConfigPropertiesLoader.getProperty("version")+"");
		// subject = subject.concat(ConfigPropertiesLoader.getProperty("buildNumber"));
		from= ConfigPropertiesLoader.getProperty("from");
		to = getListFromStringTokens(ConfigPropertiesLoader.getProperty("to"), ",");

		message.setFrom(new InternetAddress(from));
		message.setSubject(subject);
		for(String listOfTo:to){
			message.addRecipients(Message.RecipientType.TO, listOfTo);
		
		}

		StringBuilder mailBodyBuilder = new StringBuilder();
		mailBodyBuilder.append("Hi Team,");
//		mailBodyBuilder.append("<br><br>Automation Name : "+ConfigPropertiesLoader.getProperty("subject")+"<br>");
		mailBodyBuilder.append("<br><br> Please find the Automation Execution Status "+"<br>");
		mailBodyBuilder.append("<br>");


		String ldmAdministratorUrl = ConfigPropertiesLoader.getProperty("baseUrl");
		
		mailBodyBuilder.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  	POD Name  			 : "+PODFileManager.getProperty("Environment_Name"));
		mailBodyBuilder.append("<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Admin Url 			 : "+ PODFileManager.getProperty("Login_URL"));
		mailBodyBuilder.append("<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Version   			 : "+ConfigPropertiesLoader.getProperty("version"));
		// mailBodyBuilder.append("<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Build    			 : "+ConfigPropertiesLoader.getProperty("buildNumber"));
		mailBodyBuilder.append("<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Browser   			 : "+ConfigPropertiesLoader.getProperty("browser"));
		mailBodyBuilder.append("<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 	Total Execution Time : "+String.format("%.3f", ( Launcher.EndTime- Launcher.startTime)/1000/60F )+" Minutes");


		BufferedReader bufferedReader = null; 

		//Read the qafile and attach to mail
		try {			
			String sCurrentLine;
			bufferedReader = new BufferedReader(new FileReader("ResultMailNew.html"));

			while ((sCurrentLine = bufferedReader.readLine()) != null) {				
				mailBodyBuilder.append(sCurrentLine);
			}				

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		mailBodyBuilder.append("<br><br>Regards,<br>APIM QA");

		Multipart multipart = new MimeMultipart();

		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();		
		MimeBodyPart messageBodyPart3 = new MimeBodyPart();		
		messageBodyPart1.setContent(mailBodyBuilder.toString(), "text/html");
		messageBodyPart1.setHeader("Content-ID\\", "<image>");
		multipart.addBodyPart(messageBodyPart1);

	//	String emailableReport = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "reports.html";
		String emailableReport = Constants.REPORTS_PATH+File.separator+PODFileManager.getProperty("Environment_Name")+File.separator+"reports.html";
		
		DataSource qaXmlSource = new FileDataSource(emailableReport);  
		messageBodyPart2.setDataHandler(new DataHandler(qaXmlSource));  
		messageBodyPart2.setFileName("APIM_Automation_Report.html");
		multipart.addBodyPart(messageBodyPart2);

		message.setContent(multipart);
		Transport.send(message);
		logger.info("mail sent successfully");
		System.out.println("mail sent successfully");
	}

	public static String addColor(String msg, Color color) {
		String hexColor = String.format("#%06X",  (0xFFFFFF & color.getRGB()));
		String colorMsg = "<FONT COLOR=\"#" + hexColor + "\">" + msg + "</FONT>";
		return colorMsg;
	}
	
	public static List<String> getListFromStringTokens(String string, String delimiter) {

		List<String> list = new ArrayList<String>();

		if(!string.equalsIgnoreCase("NULL")){
			StringTokenizer tokens = new StringTokenizer(string, delimiter);
			list = new ArrayList<String>();

			while(tokens.hasMoreTokens()) {
				list.add(tokens.nextToken());
			}	
		}
		return list;
	}

}
