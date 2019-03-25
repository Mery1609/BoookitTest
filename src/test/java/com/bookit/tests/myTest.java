package com.bookit.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;



public class myTest {
	
	String baseurl = "http://3.80.189.197:1000/ords/hr";
	
	/*When I send the GET request to url/countries
	 * then response SttusCode should be 200
	 */
	
  @Test (enabled= false)
  public void singleStatusCode200() { //we will verify simple status code 200 when using GET request
	  
	  when().get("http://3.80.189.197:1000/ords/hr/countries").then().statusCode(200); //when -condition to start 
	  //.get (whatever request we have on Postman via string . then --> expecting statusCode -->built in method (parameters 200)
	  
  }
  
     /*When I send the GET request to url/employees
	 * then I should see the json response
	 */
	
  @Test (enabled= false)
  public void printResponse() {
	  
	  when().get("http://3.80.189.197:1000/ords/hr/employees").body().prettyPrint(); //when condition. get(our parameters in string ""
	  //we don't use then before body. --> body()our json body is requesting here. -->prettyPrint built in method to print our json file
	  
  }
  
  /*When I send GET request to url/countries
   * And Accept type is "application/json"
   * Then i should be able to see the JSON response
   */
  
  @Test (enabled= false)
  public void getWithHeaders() {
	  
	  given().accept(ContentType.JSON)
	  .when().get("http://3.80.189.197:1000/ords/hr/employees")
	  .then().statusCode(200);
  }
  
    /*When I send the GET request to url/regions/1234
	 * then response StatusCode should be 404
	 */
  
  @Test (enabled= false)
  public void negativeGetStatus404() {
	  
	  when().get("http://3.80.189.197:1000/ords/regions/1234").then().statusCode(404); //because I know there is no regions/id1234
	  
	  
  }
  
  /*When I send a GET request to url/regions/1234
   * Then status code is 404
   * And Response Body error message should includes "Not Found"
   */
  
  @Test (enabled= false)
  public void negativeGetWithBody() { //want to get the whole body (html)
	  
	  Response response = when().get("http://3.80.189.197:1000/ords/regions/1234"); //we put our request into response type object
	  // Response (import from Iio.restassured.response it's a response type object
	  
	  //statusCode assertion
	  assertEquals(response.statusCode(), 404);
	  
	  //check body includes "Not Found"
	  assertTrue(response.asString().contains("Not Found")); //asString method() we want to verify that text "Not Found" is included in the body
	  
	  response.prettyPrint(); //returns all html codes 
	  
	 
  }
  
   /* When I send a GET request to url/employees/110
    * And Accept type Json
    * Then status code is 200
    * And Response content should be json
    */
  
  @Test (enabled = false)
  public void verifyContentType() {
	  
	  given().accept(ContentType.JSON)
	  .when().get("http://3.80.189.197:1000/ords/hr/employees")
	  .then().statusCode(200).and().contentType(ContentType.JSON); //contentType(ContentType.JSON) -->assertion if content type is Json
	  
	 
  }
  
  /*When I send GET request to url/employees/130
   * Then status Code is 200
   * And Response content should be Json 
   * And first name  should be "Mozhe"
   */
  
  @Test (enabled = false)
  public void verifyFirstName() {
	  
	  given().accept(ContentType.JSON)
	  .when().get(baseurl +"/employees/130")
	  .then().statusCode(200).and().contentType(ContentType.JSON)
	  .and().body("first_name", Matchers.equalTo("Mozhe")); //Hamcrest Matchers is an assertion class in REST assured (simple assertion)
  }
  
  /*When I send GET request to url/employees/161
   * Then status Code is 200
   * And Response content should be Json 
   * And employee_id should be 161
   * And last name  should be "Sewall"
   * And last name  should be "Sarath"
   */
  
  @Test (enabled = false)
  public void verifyFullName() {
	  
	  given().accept(ContentType.JSON)
	  .when().get(baseurl +"/employees/161")
	  .then().statusCode(200).and().contentType(ContentType.JSON)
	  .and().body("last_name", Matchers.equalTo("Sewall"))
	  .and().body("first_name", Matchers.equalTo("Sarath"))
	  .and().body("employee_id", Matchers.equalTo(161));;
	  
	  
  }
  
   /*When I send GET request to url/regions
   * Then status Code is 200
   * And Response content should be Json 
   * And employee_id should be 161
   * And 4 regions should return
   */
  
  @Test
  public void verifyRegionCount() {
	  
	  given().accept(ContentType.JSON).when().get(baseurl+"/regions")
	  .then().assertThat().statusCode(200)   //assertThat() --> is syntactic sugar
	  .assertThat().contentType(ContentType.JSON)
	  .assertThat().body("items.region_id", Matchers.hasSize(16))
	  .and().assertThat().body("items.region_name", Matchers.hasItem("Asia"))
	  .and().assertThat().body("items.region_name", Matchers.hasItems("Asia", "Europe", "mynewregion"));
  }
  
  
}



















