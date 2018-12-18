package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class APITesting {

		private static final String USER_AGENT = "Chrome";

		public static void main(String[] args) throws IOException {
			
			Logger logger=Logger.getLogger("APITesting");
			PropertyConfigurator.configure("Log4j.properties");
			
			logger.info("Call the send get method");
			sendGET();
			logger.info("Get Done");
			logger.info("\nCall the send POST nethod");
			sendPOST();
			logger.info("Post Done");
		}

		private static void sendGET() throws IOException {
			
			Logger logger=Logger.getLogger("APITesting");
			PropertyConfigurator.configure("Log4j.properties");
			
			logger.info("Inside the SendGet Method");
		
			  URL url = new URL("URL");
			  logger.info("URL called");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        logger.info("Connection Successfull");
		        conn.setRequestMethod("GET");
		        logger.info("Requested Get Method");
		        conn.setRequestProperty("Content-Type","application/json");
		        logger.info("Requested Content-Type");
		        conn.setRequestProperty("Authorization","Bearer token");
		        logger.info("Authorization requested");
		        conn.setRequestProperty("Accept", "application/json, text/plain, */*");
		        
		        int responseCode = conn.getResponseCode();
	            logger.info("Get Response Code :: " +responseCode);
				
				  if (responseCode == HttpURLConnection.HTTP_OK){
					  BufferedReader in = new BufferedReader(new InputStreamReader(
					  conn.getInputStream()));
					  String inputLine;
					  StringBuffer response = new StringBuffer();
					  
					  while ((inputLine = in.readLine()) != null){
						  response.append(inputLine);
						}
					  in.close();
					  
					  
					  //print result 
					    System.out.println(response.toString());
				  }else {
					  logger.info("Get request not worked");
					   
					  
	      }
	}

		private static void sendPOST() throws IOException {
			
			Logger logger=Logger.getLogger("APITesting");
			PropertyConfigurator.configure("Log4j.properties");
			
			 URL url = new URL("URL");
			 logger.info("URL Called");
			    Map<String,Object> params = new LinkedHashMap<>();
			    params.put("year", "0");
			    params.put("month", "0");
			    params.put("sortfield", "latest");
             logger.info("Entered parameter");
			    StringBuilder postData = new StringBuilder();
			    for (Map.Entry<String,Object> param : params.entrySet()) {
			      if (postData.length() != 0) postData.append('&');
			      postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			      postData.append('=');
			      postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			    }
			    byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			    logger.info("Connection Seccessfull");
			    conn.setRequestMethod("POST");
			    logger.info("POST Requested");
			    conn.setRequestProperty("Content-Type", "application/json");
			    logger.info("Requested Content-Type");
			    conn.setRequestProperty("Authorization", "Bearer token");
			    logger.info("Authorization requested");
			    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
			    conn.setRequestProperty("Accept", "application/json, text/plain, */*");
			    conn.setDoOutput(true);
			    conn.getOutputStream().write(postDataBytes);

			    int responseCode = conn.getResponseCode();
			    logger.info("POST reponse Code ::" +responseCode);
				  
				  if (responseCode == HttpURLConnection.HTTP_OK){
					  BufferedReader in = new BufferedReader(new InputStreamReader(
					  conn.getInputStream()));
					  String inputLine;
					  StringBuffer response = new StringBuffer();
					  
					  while ((inputLine = in.readLine()) != null){
						  response.append(inputLine);
						}
					  in.close();
					  
					  
					  //print result 
					    System.out.println(response.toString());
				  }else {
					    logger.info("POST request not worked");
					  
	       }
		}
}	