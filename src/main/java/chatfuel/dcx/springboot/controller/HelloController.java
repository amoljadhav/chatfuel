package chatfuel.dcx.springboot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chatfuel.dcx.springboot.model.MessageBody;
import chatfuel.dcx.springboot.model.SetAttributes;
import chatfuel.dcx.springboot.model.SetAttributesData;
import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;
import chatfuel.dcx.springboot.utils.API;

@RestController
public class HelloController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		return "Welcome to chatfuel middleware, please go to the APIs you wish to go";
	}

	@RequestMapping("/callback")
	public String callback(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("/getpolicydetailsbypolicynumber")
	public SetAttributes getPolicyDetailsByPolicyNumber(
			@RequestParam(value = "policynum", defaultValue = "506-8823987") String policyNumber) {
		SetAttributes attributes = new SetAttributes();


		try {
			String authToken = getOAuthToken();

			JSONObject response = getPolicyDetailsDataByPolicyNumber(API.GET_POLICY_BY_POLICYNO + policyNumber,
					authToken);
			if (response != null) {
				PolicyDetailsModel policyDetails = new PolicyDetailsModel(response);

				//
				System.out.println(policyDetails.getTotal_Premium__c()+ "," + policyDetails.getAttributes().getType());
				//
				MessageBody messageBody = new MessageBody(policyDetails);
				
				SetAttributesData attributesData = new SetAttributesData();
				attributesData.setServiceresponsecomplete("true");
				
				attributes.setSet_attributes(attributesData);
				attributes.setMessages(messageBody);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return attributes;
	}

	@RequestMapping("/getclaimdetailsbyclaimnumber")
	public SetAttributes getClaimDetailsByClaimNumber(
			@RequestParam(value = "claimnumber", defaultValue = "454545") String claimNumber) {

		SetAttributes attributes = new SetAttributes();

		try {
			String authToken = getOAuthToken();

			JSONObject response = getPolicyDetailsDataByPolicyNumber(API.GET_CLAIM_BY_CLAIMNO + claimNumber, authToken);
			if (response != null) {
				ClaimDetailsModel claimsDetails = new ClaimDetailsModel(response);
				MessageBody messageBody = new MessageBody(claimsDetails);
				SetAttributesData attributesData = new SetAttributesData();
				attributesData.setServiceresponsecomplete("true");
				
				attributes.setSet_attributes(attributesData);
				attributes.setMessages(messageBody);
				
//				System.out.println("response:==>>" + response.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return attributes;
	}

/*	@RequestMapping("/getpolicydetailsbyuserdetails")
	public MessageBody getPolicyDetailsByUserDetails(
			@RequestParam(value = "firstname", defaultValue = "") String firstName,
			@RequestParam(value = "lastname", defaultValue = "") String lastName,
			@RequestParam(value = "dob", defaultValue = "") String dob) {

		MessageBody messageBody = new MessageBody();

		try {
			String authToken = getOAuthToken();

			JSONObject response = getPolicyDetailsDataByPolicyNumber(API.GET_POLICY_BYPOLICY_FLD + API.FIRST_NAME+firstName+API.LAST_NAME+lastName+API.DOB+dob,
					authToken);
			if (response != null) {
				PolicyDetailsModel policyDetails = new PolicyDetailsModel(response);

				//
				System.out.println(policyDetails.getAmount() + "," + policyDetails.getAttributes().getType());
				//

				List<TextMessages> tmList = new ArrayList<TextMessages>();

				TextMessages textMessage1 = new TextMessages();
				textMessage1.setText("Total amount is: " + policyDetails.getAmount());

				TextMessages textMessage2 = new TextMessages();
				textMessage2.setText("Policy type is: " + policyDetails.getAttributes().getType());
				tmList.add(textMessage1);
				tmList.add(textMessage2);
				messageBody.setMessages(tmList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageBody;
	}
	
	@RequestMapping("/getclaimdetailsbyuserdetails")
	public MessageBody getClaimDetailsByUserDetails(
			@RequestParam(value = "firstname", defaultValue = "") String firstName,
			@RequestParam(value = "lastname", defaultValue = "") String lastName,
			@RequestParam(value = "dob", defaultValue = "") String dob) {

		MessageBody messageBody = new MessageBody();

		try {
			String authToken = getOAuthToken();

			JSONObject response = getPolicyDetailsDataByPolicyNumber(API.GET_CLAIM_BYCLAIM_FLD + API.FIRST_NAME+firstName+API.LAST_NAME+lastName+API.DOB+dob,
					authToken);
			if (response != null) {
				PolicyDetailsModel policyDetails = new PolicyDetailsModel(response);

				//
				System.out.println(policyDetails.getAmount() + "," + policyDetails.getAttributes().getType());
				//

				List<TextMessages> tmList = new ArrayList<TextMessages>();

				TextMessages textMessage1 = new TextMessages();
				textMessage1.setText("Total amount is: " + policyDetails.getAmount());

				TextMessages textMessage2 = new TextMessages();
				textMessage2.setText("Policy type is: " + policyDetails.getAttributes().getType());
				tmList.add(textMessage1);
				tmList.add(textMessage2);
				messageBody.setMessages(tmList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageBody;
	}*/

	private String getOAuthToken() {
		String token = null;
		try {

			OAuthClientRequest request = OAuthClientRequest.tokenLocation(API.TOKEN_URL)
					.setRedirectURI(API.CALLBACK_URL).setGrantType(GrantType.PASSWORD)

					.setClientId(API.CONSUMER_KEY).setClientSecret(API.CONSUMER_SECRET).setUsername(API.USERNAME)
					.setPassword(API.PASSWORD)
					// .setScope() here if you want to set the token scope
					.buildQueryMessage();

			request.addHeader("content-type", "application/x-www-form-urlencoded");
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			token = oAuthClient.accessToken(request, OAuthJSONAccessTokenResponse.class).getAccessToken();

			System.out.println("token: " + token);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;

	}

	private JSONObject getPolicyDetailsDataByPolicyNumber(String url, String accessToken)
			throws IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		JSONObject response = null;
		HttpGet httpGet = new HttpGet();

		// add key and value
		httpGet.addHeader("Authorization", "Bearer " + accessToken);

		try {

			URIBuilder builder = new URIBuilder(url);

			httpGet.setURI(builder.build());

			CloseableHttpResponse closeableresponse = httpclient.execute(httpGet);
			System.out.println("Response Status line :" + closeableresponse.getStatusLine());

			if (closeableresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// Now lets use the standard java json classes to work with the
				// results
				try {
					// Do the needful with entity.
					HttpEntity entity = closeableresponse.getEntity();
					InputStream rstream = entity.getContent();
					JSONObject authResponse = new JSONObject(new JSONTokener(rstream));

					System.out.println(authResponse.toString());
					response = authResponse;
					// response = authResponse.toString();

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		} catch (URISyntaxException e1) {
			// TODO Auto­generated catch block
			e1.printStackTrace();
		} finally {
			httpclient.close();
		}
		return response;
	}

}
