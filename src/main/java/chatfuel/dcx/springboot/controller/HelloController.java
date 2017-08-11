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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chatfuel.dcx.springboot.model.ButtonAttachment;
import chatfuel.dcx.springboot.model.ButtonMessageAttachment;
import chatfuel.dcx.springboot.model.ButtonPayload;
import chatfuel.dcx.springboot.model.Buttons;
import chatfuel.dcx.springboot.model.Elements;
import chatfuel.dcx.springboot.model.ListAttachment;
import chatfuel.dcx.springboot.model.ListMessageAttachment;
import chatfuel.dcx.springboot.model.ListPayload;
import chatfuel.dcx.springboot.model.MultipleButtonMessageAttachments;
import chatfuel.dcx.springboot.model.MultipleListMessageAttachments;
import chatfuel.dcx.springboot.model.SetAttributes;
import chatfuel.dcx.springboot.model.SetAttributesData;
import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;
import chatfuel.dcx.springboot.utils.API;
import chatfuel.dcx.springboot.utils.API_CONST;

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

	@RequestMapping(API_CONST.GET_POLICY)
	public SetAttributes getPolicyDetailsByPolicyNumber(
			@RequestParam(value = API_CONST.POLICYNUMBER, defaultValue = "506-8823987") String policyNumber) {
		SetAttributes attributes = null;

		try {
			String authToken = getOAuthToken();

			JSONObject response = getData(API.GET_POLICY_BY_POLICYNO + policyNumber, authToken);
			if (response != null) {
				PolicyDetailsModel policyDetails = new PolicyDetailsModel(response);
				attributes = new SetAttributes(policyDetails);
				//
				System.out.println(policyDetails.getTotal_Premium__c() + "," + policyDetails.getAttributes().getType());
				//
				// MessageBody messageBody = new MessageBody(policyDetails);

				SetAttributesData attributesData = new SetAttributesData();
				attributesData.setServiceresponsecomplete("true");

				attributes.setSet_attributes(attributesData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return attributes;
	}

	@RequestMapping(API_CONST.GET_CLAIM)
	public SetAttributes getClaimDetailsByClaimNumber(
			@RequestParam(value = API_CONST.CLAIMNUMBER, defaultValue = "454545") String claimNumber) {

		SetAttributes attributes = null;

		try {
			String authToken = getOAuthToken();

			JSONObject response = getData(API.GET_CLAIM_BY_CLAIMNO + claimNumber, authToken);
			if (response != null) {
				ClaimDetailsModel claimsDetails = new ClaimDetailsModel(response);
				attributes = new SetAttributes(claimsDetails);
				// MessageBody messageBody = new MessageBody(claimsDetails);
				SetAttributesData attributesData = new SetAttributesData();
				attributesData.setServiceresponsecomplete("true");

				attributes.setSet_attributes(attributesData);
				// attributes.setMessages(messageBody);

				// System.out.println("response:==>>" + response.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return attributes;
	}

	@RequestMapping("/getpolicydetailsbyuserdetails")
	public MultipleButtonMessageAttachments getPolicyDetailsByUserDetails(
			@RequestParam(value = "firstname", defaultValue = "") String firstName,
			@RequestParam(value = "lastname", defaultValue = "") String lastName,
			@RequestParam(value = "dob", defaultValue = "") String dob, HttpServletRequest request) {

		MultipleButtonMessageAttachments multipleMessageAttachments = new MultipleButtonMessageAttachments();

		try {
			String authToken = getOAuthToken();

			JSONArray response = getDataInArray(
					API.GET_POLICY_BYPOLICY_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null) {
				String uri = request.getScheme() + "://" + // "http" + "://
						request.getServerName() + // "myhost"
						":" + request.getServerPort();
				System.out.println("response data: " + response.toString());
				System.out.println("url: " + response.toString());
				Buttons[] btnList = new Buttons[response.length()];
				for (int i = 0; i < response.length(); i++) {
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject,
							uri + API_CONST.GET_POLICY + "?" + API_CONST.POLICYNUMBER.replace("/", ""));
					btnList[i] = buttons;
				}

				ButtonPayload payload = new ButtonPayload(btnList,
						API_CONST.FOUND_MULTIPLE + API_CONST.POLICIES + API_CONST.PLEASE_CHOOSE);
				ButtonAttachment attachment = new ButtonAttachment(payload);

				ButtonMessageAttachment attachment2 = new ButtonMessageAttachment();
				attachment2.setAttachment(attachment);
				ButtonMessageAttachment[] messageAttachment = new ButtonMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleMessageAttachments.setMessages(messageAttachment);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return multipleMessageAttachments;
	}

	@RequestMapping("/getpolicylist")
	public MultipleListMessageAttachments getPolicyLists(
			@RequestParam(value = "firstname", defaultValue = "") String firstName,
			@RequestParam(value = "lastname", defaultValue = "") String lastName,
			@RequestParam(value = "dob", defaultValue = "") String dob, HttpServletRequest request) {

		MultipleListMessageAttachments multipleListMessageAttachments = new MultipleListMessageAttachments();

		try {
			String authToken = getOAuthToken();

			JSONArray response = getDataInArray(
					API.GET_POLICY_BYPOLICY_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null) {
				String uri = request.getScheme() + "://" + // "http" + "://
						request.getServerName() + // "myhost"
						":" + request.getServerPort();
				System.out.println("response data: " + response.toString());
				System.out.println("url: " + response.toString());
				Elements[] elementList = new Elements[response.length()];
				for (int i = 0; i < response.length(); i++) {
					
					Buttons[] btnList = new Buttons[1];
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject, uri + API_CONST.GET_POLICY + "?" + API_CONST.POLICYNUMBER,
							API_CONST.VIEW_POLICY);
					btnList[0] = buttons;
					Elements elements = new Elements(jsonObject, btnList);
					elementList[i] = elements;
				}

				ListPayload payloadList = new ListPayload (elementList);
				ListAttachment listAttachment = new ListAttachment(payloadList);

				ListMessageAttachment attachment2 = new ListMessageAttachment();
				attachment2.setAttachment(listAttachment);
				ListMessageAttachment[] messageAttachment = new ListMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleListMessageAttachments.setMessages(messageAttachment);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return multipleListMessageAttachments;
	}
	


	@RequestMapping("/getclaimlist")
	public MultipleListMessageAttachments getClaimList(
			@RequestParam(value = "firstname", defaultValue = "") String firstName,
			@RequestParam(value = "lastname", defaultValue = "") String lastName,
			@RequestParam(value = "dob", defaultValue = "") String dob, HttpServletRequest request) {

		MultipleListMessageAttachments multipleListMessageAttachments = new MultipleListMessageAttachments();

		try {
			String authToken = getOAuthToken();

			JSONArray response = getDataInArray(
					API.GET_CLAIM_BYCLAIM_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null) {
				// System.out.println("response: "+response.toString());

				String uri = request.getScheme() + "://" + // "http" + "://
						request.getServerName() + // "myhost"
						":" + request.getServerPort();
				// System.out.println("response data: "+response.toString());
				// System.out.println("url: "+response.toString());
				Elements[] elementList = new Elements[response.length()];
				
				for (int i = 0; i < response.length(); i++) {
					Buttons[] btnList = new Buttons[1];
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject,
							uri + API_CONST.GET_CLAIM + "?" + API_CONST.CLAIMNUMBER.replace("/", ""));
					btnList[0] = buttons;
					Elements elements = new Elements(jsonObject, btnList);
					elementList[i]= elements;
				}

				ListPayload payloadList = new ListPayload (elementList);
				ListAttachment listAttachment = new ListAttachment(payloadList);

				ListMessageAttachment attachment2 = new ListMessageAttachment();
				attachment2.setAttachment(listAttachment);
				ListMessageAttachment[] messageAttachment = new ListMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleListMessageAttachments.setMessages(messageAttachment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return multipleListMessageAttachments;
	}
	
	
	@RequestMapping("/getclaimdetailsbyuserdetails")
	public MultipleButtonMessageAttachments getClaimDetailsByUserDetails(
			@RequestParam(value = "firstname", defaultValue = "") String firstName,
			@RequestParam(value = "lastname", defaultValue = "") String lastName,
			@RequestParam(value = "dob", defaultValue = "") String dob, HttpServletRequest request) {

		MultipleButtonMessageAttachments multipleMessageAttachments = new MultipleButtonMessageAttachments();

		try {
			String authToken = getOAuthToken();

			JSONArray response = getDataInArray(
					API.GET_CLAIM_BYCLAIM_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null) {
				// System.out.println("response: "+response.toString());

				String uri = request.getScheme() + "://" + // "http" + "://
						request.getServerName() + // "myhost"
						":" + request.getServerPort();
				// System.out.println("response data: "+response.toString());
				// System.out.println("url: "+response.toString());
				Buttons[] btnList = new Buttons[response.length()];
				for (int i = 0; i < response.length(); i++) {
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject,
							uri + API_CONST.GET_CLAIM + "?" + API_CONST.CLAIMNUMBER.replace("/", ""));
					btnList[i] = buttons;
				}

				ButtonPayload payload = new ButtonPayload(btnList,
						API_CONST.FOUND_MULTIPLE + API_CONST.CLAIMS + API_CONST.PLEASE_CHOOSE);
				ButtonAttachment attachment = new ButtonAttachment(payload);

				ButtonMessageAttachment attachment2 = new ButtonMessageAttachment();
				attachment2.setAttachment(attachment);
				ButtonMessageAttachment[] messageAttachment = new ButtonMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleMessageAttachments.setMessages(messageAttachment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return multipleMessageAttachments;
	}

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

	private JSONObject getData(String url, String accessToken) throws IOException {

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

	private JSONArray getDataInArray(String url, String accessToken) throws IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		JSONArray response = null;
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
					JSONArray authResponse = new JSONArray(new JSONTokener(rstream));
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
