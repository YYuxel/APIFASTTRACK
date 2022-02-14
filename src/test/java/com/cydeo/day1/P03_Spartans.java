package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class P03_Spartans {
    @BeforeAll
    public static void init() {
        //baseURI = "http://44.201.134.164:8000";
        baseURI="http://34.192.175.227:8000/";

        basePath = "/api";
    }

    @AfterAll
    public static void destroy() {
        reset();

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

    @DisplayName("GET allSpartans")
    @Test
    public void test3(){

        Response response = RestAssured.get("/spartans");
       // System.out.println(response.prettyPeek());
        System.out.println("=================================");
        System.out.println(response.contentType());
        System.out.println("=================================");
        System.out.println(response.statusCode());
        System.out.println("=================================");
        // Get me first spartan gender



        System.out.println(response.path("[0].gender").toString());
        System.out.println(response.path("[0]").toString());
        System.out.println(response.path("[1]").toString());



        //Get me first spartan name

        System.out.println(response.path("name[0]").toString());


        //Get me all spartan name
        List<String> names = response.path("name");

//        System.out.println(names.size());
//        for (int i = 0; i <names.size() ; i++) {
//            System.out.println(response.path("["+i+"]").toString());
//
//        }
        System.out.println(names);


    }
}
