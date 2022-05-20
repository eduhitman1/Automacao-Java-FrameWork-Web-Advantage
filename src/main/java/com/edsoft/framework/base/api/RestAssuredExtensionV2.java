package com.edsoft.framework.base.api;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtensionV2 {

	private RequestSpecBuilder builder = new RequestSpecBuilder();
	private String method;
	private String url;

	/**
	 * RestAssuredExtensionv2 construtor para passar as configurações iniciais para
	 * o seguinte método
	 * 
	 * @param uri
	 * @param method
	 * @param token
	 */
	public RestAssuredExtensionV2(String uri, String method, String token) {
		this.url = "http://localhost:3000" + uri;
		this.method = method;
		if (token != null) {
			builder.addHeader("Authorization", "Bearer " + token);
		}
	}

	/**
	 * ExecuteAPI executa as API GET/POST/DELETE
	 * 
	 * @return ResponseOptions<Response>
	 */
	private ResponseOptions<Response> ExecuteAPI() {
		RequestSpecification requestSpecification = builder.build();
		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.spec(requestSpecification);

		if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
			return request.post(this.url);
		else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
			return request.delete(this.url);
		else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
			return request.get(this.url);
		return null;
	}

	/**
	 * Authenticate retorna a variavel do token
	 * 
	 * @param body
	 * @return string token
	 */
	public String Authenticate(Object body) {
		builder.setBody(body);
		return ExecuteAPI().getBody().jsonPath().get("access_token");
	}

	/**
	 * Execução de API com parâmetros de consulta sendo passados como entrada dela
	 * 
	 * @param queryPath
	 * @return Reponse
	 */
	public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath) {
		builder.addQueryParams(queryPath);
		return ExecuteAPI();
	}

	/**
	 * ExecuteWithPathParams
	 * 
	 * @param pathParams
	 * @return
	 */
	public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParams) {
		builder.addPathParams(pathParams);
		return ExecuteAPI();
	}

	/**
	 * ExecuteWithPathParamsAndBody
	 * 
	 * @param pathParams
	 * @param body
	 * @return
	 */
	public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> pathParams,
			Map<String, String> body) {
		builder.setBody(body);
		builder.addPathParams(pathParams);
		return ExecuteAPI();
	}

}
