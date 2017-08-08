package chatfuel.dcx.springboot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HelloController {

	// @RequestMapping("/")
	// String home(ModelMap modal) {
	// modal.addAttribute("title", "Dear Learner");
	// modal.addAttribute("message", "Welcome to SpringBoot");
	// return "hello";
	// }
	//
	// @Autowired
	// @Qualifier("monkeymanRestTemplate")
	// private RestTemplate monkeymanRestTemplate;

	@RequestMapping("/callback")
	public String callback(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("/getmessage")
	public MessageBody greeting(@RequestParam(value = "policynum", defaultValue = "3254546") String policyNumber)  {

		
		MessageBody messageBody = new MessageBody();
		
//				try{
				//	String authToken = oauthToken();
				//	JSONObject response = getPolicyDetails(authToken,policyNumber);
//				  if(response!=null){
//					  PolicyDetailsModel policyDetails = new PolicyDetailsModel(response);
// 
////					  ObjectMapper mapper = new ObjectMapper();
////					
////						PolicyDetailsModel policyDetails = mapper.readValue(response, PolicyDetailsModel.class);
////						
//						System.out.println(policyDetails.getAmount()+","+policyDetails.getAttributes().getType());
////						
//						
//						List<TextMessages> tmList = new ArrayList<TextMessages>();
//						
//						TextMessages textMessage1 = new TextMessages();
//						textMessage1.setText("Total amount is: "+ policyDetails.getAmount());
//						
//						TextMessages textMessage2 = new TextMessages();
//						textMessage2.setText("Policy type is: "+ policyDetails.getAttributes().getType());
//							tmList.add(textMessage1);
//							tmList.add(textMessage2);
//							messageBody.setMessages(tmList);
//					}
//				}catch(IOException e){
//					e.printStackTrace();
//				}
				
		
		TextMessages tm = new TextMessages();
		tm.setText("hi zakaiter");
		
		TextMessages tm2 = new TextMessages();
		tm2.setText("hey mohsin");
		
		List<TextMessages> tmList = new ArrayList<TextMessages>();
		tmList.add(tm);
		tmList.add(tm2);
		messageBody.setMessages(tmList);
		
		return messageBody;
	}

	private String oauthToken() {
		String token = null;
		try {

			OAuthClientRequest request = OAuthClientRequest
					.tokenLocation("https://ysurancedemo-dev-ed.my.salesforce.com/services/oauth2/token")
					.setRedirectURI("https://chatfuel1.herokuapp.com/callback").setGrantType(GrantType.PASSWORD)

					.setClientId(
							"3MVG9d8..z.hDcPL8Cc73Am729Id_dHpSGA5idrmc4gdH0LtXpe8p1Eew6jwE6T6yY5txmXRxQPQL0RjF8jjB")
					.setClientSecret("613084951392011949").setUsername("ysuranceadmin@capgemini.com")
					.setPassword("capgemini@123")
					// .setScope() here if you want to set the token scope
					.buildQueryMessage();

			request.addHeader("content-type", "application/x-www-form-urlencoded");
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			token =oAuthClient.accessToken(request, OAuthJSONAccessTokenResponse.class).getAccessToken();

			System.out.println("token: " + token);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;

	}
	
	private JSONObject getPolicyDetails (String accessToken,String policynumber) throws IOException {
			
			CloseableHttpClient  httpclient = HttpClients.createDefault();
			JSONObject response = null;
			HttpGet httpGet = new HttpGet();

			//add key and value
			httpGet.addHeader("Authorization", "Bearer " + accessToken);

			try {
					
				URIBuilder builder = new URIBuilder("https://ysurancedemo-dev-ed.my.salesforce.com/"+ "services/apexrest/AXA_ChatbotDetailsV2?policynum="+policynumber);

				httpGet.setURI(builder.build());

				CloseableHttpResponse  closeableresponse = httpclient.execute(httpGet);
				System.out.println("Response Status line :" + closeableresponse.getStatusLine());
				
				if (closeableresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// Now lets use the standard java json classes to work with the results
					try {
						// Do the needful with entity.
						HttpEntity entity = closeableresponse.getEntity();
						InputStream rstream = entity.getContent();
						JSONObject authResponse = new JSONObject(new JSONTokener(rstream));

						System.out.println(authResponse.toString());
						response= authResponse;	
						//response = authResponse.toString();	
						
					}
					catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
			} catch (URISyntaxException  e1) {
				// TODO Auto­generated catch block
				e1.printStackTrace();
			} finally {
				httpclient.close();
			}
		return response;
	}

}
