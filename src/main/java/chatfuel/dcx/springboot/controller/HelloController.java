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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import chatfuel.dcx.springboot.model.errordata.SetErrorAttributes;
import chatfuel.dcx.springboot.utils.API;
import chatfuel.dcx.springboot.utils.API_CONST;
import chatfuel.dcx.springboot.utils.Utils;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String home(ModelMap modal) {
		ObjectMapper mapper = new ObjectMapper();
		String responsedata = null;

		SetErrorAttributes errorAttributes = new SetErrorAttributes("true", "No data found");
		try {
			responsedata = mapper.writeValueAsString(errorAttributes);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responsedata;
	}

	@RequestMapping("/callback")
	public String callback(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(API_CONST.GET_POLICY)
	public String getPolicyDetailsByPolicyNumber(
			@RequestParam(value = API_CONST.POLICYNUMBER, defaultValue = "") String policyNumber) {
		String responsedata = null;

		try {
			String authToken = getOAuthToken();
			ObjectMapper mapper = new ObjectMapper();

			JSONObject response = getData(API.GET_POLICY_BY_POLICYNO + policyNumber, authToken);
			if (response != null && response.length()>0) {
				PolicyDetailsModel policyDetails = new PolicyDetailsModel(response);
				SetAttributes attributes = new SetAttributes(policyDetails);
				//
				Utils.printLn(policyDetails.getTotal_Premium__c() + "," + policyDetails.getAttributes().getType());
				//
				// MessageBody messageBody = new MessageBody(policyDetails);

				SetAttributesData attributesData = new SetAttributesData();
				attributesData.setServiceresponsecomplete("true");

				attributes.setSet_attributes(attributesData);
				responsedata = mapper.writeValueAsString(attributes);
				return responsedata;
			} else {
				SetErrorAttributes errorAttributes = new SetErrorAttributes("true",
						"I could not find the details for the policy - " + policyNumber);
				responsedata = mapper.writeValueAsString(errorAttributes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responsedata;

	}

	@RequestMapping(API_CONST.GET_CLAIM)
	public String getClaimDetailsByClaimNumber(
			@RequestParam(value = API_CONST.CLAIMNUMBER, defaultValue = "") String claimNumber) {
		String responseData = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			String authToken = getOAuthToken();

			JSONObject response = getData(API.GET_CLAIM_BY_CLAIMNO + claimNumber, authToken);
			if (response != null && response.length()>0) {
				ClaimDetailsModel claimsDetails = new ClaimDetailsModel(response);
				SetAttributes attributes = new SetAttributes(claimsDetails);
				SetAttributesData attributesData = new SetAttributesData();
				attributesData.setServiceresponsecomplete("true");
				attributes.setSet_attributes(attributesData);
				responseData = objMapper.writeValueAsString(attributes);
			} else {
				SetErrorAttributes errorAttributes = new SetErrorAttributes("true", 
						"I could not find the details for the claim - " + claimNumber);

				responseData = objMapper.writeValueAsString(errorAttributes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseData;
	}

	@RequestMapping(API_CONST.GET_POLICY_DETAILS_BY_USER_DETAILS)
	public String getPolicyDetailsByUserDetails(
			@RequestParam(value = API_CONST.FIRST_NAME, defaultValue = "") String firstName,
			@RequestParam(value = API_CONST.LAST_NAME, defaultValue = "") String lastName,
			@RequestParam(value = API_CONST.DOB, defaultValue = "") String dob, HttpServletRequest request) {

		String responseData = null;

		try {
			String authToken = getOAuthToken();
			ObjectMapper objMapper = new ObjectMapper();
			JSONArray response = getDataInArray(
					API.GET_POLICY_BYPOLICY_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null  && response.length()>0) {
				String uri = Utils.getHostUrl(request);
				Utils.printLn("response data: " + response.toString());
				Utils.printLn("url: " + response.toString());
				Buttons[] btnList = new Buttons[response.length()];
				for (int i = 0; i < response.length(); i++) {
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject,
							uri + API_CONST.GET_POLICY + "?" + API_CONST.POLICYNUMBER.replace("/", ""));
					btnList[i] = buttons;
				}
				MultipleButtonMessageAttachments multipleMessageAttachments = new MultipleButtonMessageAttachments();
				ButtonPayload payload = new ButtonPayload(btnList,
						API_CONST.FOUND_MULTIPLE + API_CONST.POLICIES + API_CONST.PLEASE_CHOOSE);
				ButtonAttachment attachment = new ButtonAttachment(payload);

				ButtonMessageAttachment attachment2 = new ButtonMessageAttachment();
				attachment2.setAttachment(attachment);
				ButtonMessageAttachment[] messageAttachment = new ButtonMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleMessageAttachments.setMessages(messageAttachment);

				responseData = objMapper.writeValueAsString(multipleMessageAttachments);

			} else {
				SetErrorAttributes errorAttributes = new SetErrorAttributes("true",
						"I did not find any policy listed for " + firstName+" "+lastName +" and date of birth "+dob);

				responseData = objMapper.writeValueAsString(errorAttributes);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseData;
	}

	@RequestMapping(API_CONST.GET_CLAIM_DETAILS_BY_USER_DETAILS)
	public String getClaimDetailsByUserDetails(
			@RequestParam(value = API_CONST.FIRST_NAME, defaultValue = "") String firstName,
			@RequestParam(value = API_CONST.LAST_NAME, defaultValue = "") String lastName,
			@RequestParam(value = API_CONST.DOB, defaultValue = "") String dob, HttpServletRequest request) {

		String responseData = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String authToken = getOAuthToken();

			JSONArray response = getDataInArray(
					API.GET_CLAIM_BYCLAIM_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null && response.length()>0) {
				// Utils.printLn("response: "+response.toString());
				MultipleButtonMessageAttachments multipleMessageAttachments = new MultipleButtonMessageAttachments();
				String uri = Utils.getHostUrl(request);
				// Utils.printLn("response data: "+response.toString());
				// Utils.printLn("url: "+response.toString());
				Buttons[] btnList = new Buttons[response.length()];
				for (int i = 0; i < response.length(); i++) {
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject, uri + API_CONST.GET_CLAIM + "?" + API_CONST.CLAIMNUMBER);
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
				responseData = mapper.writeValueAsString(multipleMessageAttachments);
			} else {
				SetErrorAttributes errorAttributes = new SetErrorAttributes("true",
						"I did not find any claim listed for " + firstName+" "+lastName +" and date of birth "+dob);
				responseData = mapper.writeValueAsString(errorAttributes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseData;
	}

	@RequestMapping(API_CONST.GET_POLICYLIST)
	public String getPolicyLists(@RequestParam(value = API_CONST.FIRST_NAME, defaultValue = "") String firstName,
			@RequestParam(value = API_CONST.LAST_NAME, defaultValue = "") String lastName,
			@RequestParam(value = API_CONST.DOB, defaultValue = "") String dob, HttpServletRequest request) {

		String responseData = null;
		try {
			String authToken = getOAuthToken();
			ObjectMapper mapper = new ObjectMapper();
			MultipleListMessageAttachments multipleListMessageAttachments = new MultipleListMessageAttachments();

			JSONArray response = getDataInArray(
					API.GET_POLICY_BYPOLICY_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null && response.length()>0) {
				String uri = request.getScheme() + "://" + // "http" + "://
						request.getServerName() + // "myhost"
						":" + request.getServerPort();
				Utils.printLn("response data: " + response.toString());
				Utils.printLn("url: " + response.toString());
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

				ListPayload payloadList = new ListPayload(elementList);
				ListAttachment listAttachment = new ListAttachment(payloadList);

				ListMessageAttachment attachment2 = new ListMessageAttachment();
				attachment2.setAttachment(listAttachment);
				ListMessageAttachment[] messageAttachment = new ListMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleListMessageAttachments.setMessages(messageAttachment);
				responseData = mapper.writeValueAsString(multipleListMessageAttachments);
			} else {
				SetErrorAttributes errorAttributes = new SetErrorAttributes("true",
						"I did not find any policy listed for " + firstName+" "+lastName +" and date of birth "+dob);
				responseData = mapper.writeValueAsString(errorAttributes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseData;
	}

	@RequestMapping(API_CONST.GET_CLAIMLIST)
	public String getClaimList(@RequestParam(value = API_CONST.FIRST_NAME, defaultValue = "") String firstName,
			@RequestParam(value = API_CONST.LAST_NAME, defaultValue = "") String lastName,
			@RequestParam(value = API_CONST.DOB, defaultValue = "") String dob, HttpServletRequest request) {

		String responseData = null;
		try {
			String authToken = getOAuthToken();
			ObjectMapper mapper = new ObjectMapper();
			JSONArray response = getDataInArray(
					API.GET_CLAIM_BYCLAIM_FLD + API.FIRST_NAME + firstName + API.LAST_NAME + lastName + API.DOB + dob,
					authToken);
			if (response != null && response.length()>0) {
				// Utils.printLn("response: "+response.toString());
				MultipleListMessageAttachments multipleListMessageAttachments = new MultipleListMessageAttachments();

				String uri = Utils.getHostUrl(request);
				// Utils.printLn("response data: "+response.toString());
				// Utils.printLn("url: "+response.toString());
				Elements[] elementList = new Elements[response.length()];

				for (int i = 0; i < response.length(); i++) {
					Buttons[] btnList = new Buttons[1];
					JSONObject jsonObject = response.getJSONObject(i);
					Buttons buttons = new Buttons(jsonObject,
							uri + API_CONST.GET_CLAIM + "?" + API_CONST.CLAIMNUMBER.replace("/", ""));
					btnList[0] = buttons;
					Elements elements = new Elements(jsonObject, btnList);
					elementList[i] = elements;
				}

				ListPayload payloadList = new ListPayload(elementList);
				ListAttachment listAttachment = new ListAttachment(payloadList);

				ListMessageAttachment attachment2 = new ListMessageAttachment();
				attachment2.setAttachment(listAttachment);
				ListMessageAttachment[] messageAttachment = new ListMessageAttachment[1];
				messageAttachment[0] = attachment2;

				multipleListMessageAttachments.setMessages(messageAttachment);
				responseData = mapper.writeValueAsString(multipleListMessageAttachments);
			} else {
				SetErrorAttributes errorAttributes = new SetErrorAttributes("true",
						"I did not find any claim listed for " + firstName+" "+lastName +" and date of birth "+dob);
				responseData = mapper.writeValueAsString(errorAttributes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseData;
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

			Utils.printLn("token: " + token);

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
			Utils.printLn("Response Status line :" + closeableresponse.getStatusLine());

			if (closeableresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// Now lets use the standard java json classes to work with the
				// results
				try {
					// Do the needful with entity.
					HttpEntity entity = closeableresponse.getEntity();
					InputStream rstream = entity.getContent();
					JSONObject authResponse = new JSONObject(new JSONTokener(rstream));

					Utils.printLn(authResponse.toString());
					response = authResponse;

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				response = null;
			}

		} catch (URISyntaxException e1) {
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
			Utils.printLn("Response Status line :" + closeableresponse.getStatusLine());

			if (closeableresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// Now lets use the standard java json classes to work with the
				// results
				try {
					// Do the needful with entity.
					HttpEntity entity = closeableresponse.getEntity();
					InputStream rstream = entity.getContent();
					JSONArray authResponse = new JSONArray(new JSONTokener(rstream));
					Utils.printLn(authResponse.toString());
					response = authResponse;
					// response = authResponse.toString();

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				response = null;
			}

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} finally {
			httpclient.close();
		}
		return response;
	}

	// private String CreateErrorResponse(ObjectMapper mapper) {
	// SetErrorAttributes errorAttributes = new SetErrorAttributes("true","No
	// data found");
	// try {
	// return (mapper.writeValueAsString(errorAttributes));
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

}
