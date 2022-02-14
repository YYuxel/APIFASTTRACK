package com.cydeo.day1;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class P01_SimpleGetRequest {
    /**
     * 1. Send request to HR url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *     - Headers
     *     - Content-Type
     *     - Status Code
     *     - Response
     *     - Date
     *     - Verify response body has "Europe"
     *     - Verify response has Date
     */


    @DisplayName("GET /regions")
    @Test
    public void simpleGetRequest(){

        System.out.println(RestAssured.get("http://44.201.134.164:1000/ords/hr/regions").headers());
        System.out.println("====================================================================");

        System.out.println(RestAssured.get("http://44.201.134.164:1000/ords/hr/regions").getContentType());
        System.out.println("====================================================================");

        System.out.println(RestAssured.get("http://44.201.134.164:1000/ords/hr/regions").getStatusCode());
        System.out.println("====================================================================");


        Response response = RestAssured.get("http://44.201.134.164:1000/ords/hr/regions");
        System.out.println(response.header("Date"));
        System.out.println("**********************************************");
        System.out.println(response.contentType());
        System.out.println("**********************************************");
        System.out.println(response.headers());
        System.out.println(response.header("Transfer-Encoding"));

        System.out.println("**********************************************");
        System.out.println(response.asString().contains("Europe"));

        assertTrue(response.asString().contains("Europe"));
        System.out.println("**********************************************");
        System.out.println(response.headers().getValue("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("**********************************************");
        System.out.println(response.prettyPeek());

    }

    /**
     * 1. Send request to HR url and save the response
     * 2. GET /employees/100
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *     - First Name
     *     - Last Name
     *     - Verify status code is 200
     *     - Verify First Name is "Steven"
     *     - Verify content-Type is application/json
     */

    @DisplayName("GET /employees/100")
    @Test
    public void test2(){

        Response response = RestAssured.get("http://44.201.134.164:1000/ords/hr/employees/100");
        System.out.println(response.prettyPeek());

        System.out.println(response.path("first_name").toString());
        System.out.println(response.path("last_name").toString());

        assertEquals("Steven", response.path("first_name"));
        assertEquals("application/json",response.contentType());
        assertEquals(ContentType.JSON.toString(),response.contentType());

    }

    /**
     * 1. Send request to Spartan url and save the response
     * 2. GET /spartans
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *     - response
     *     - Content-Type
     *     - Status Code
     *     - Get me first spartan gender
     *     - Get me first spartan name
     *     - Get me all spartan name
     */

    @Test
    public void test3(){

        Response response = RestAssured.get("http://34.192.175.227:8000/api/spartans");
        // System.out.println(response.prettyPeek());
        System.out.println("=================================");
        System.out.println(response.contentType());
        System.out.println("=================================");
        System.out.println(response.statusCode());
        System.out.println("=================================");



    }

}
