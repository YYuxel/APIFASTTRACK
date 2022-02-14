package com.cydeo.day1;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_MethodChaining extends SpartanTestBase {

    /**
     *1- Given accept type is Json
     *2- Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> e
     *3- When user sends GET request to /spartans/search
     *4- Print out Followings
     *     - Total Element Number
     *     - Get me first spartan name
     *     - Get me second spartan id
     *     - Get me last spartan name
     *     - Get me all Spartan Names
     * 5- Verify followings
     *     - Status code should be 200
     */

    @DisplayName("Query Param")
    @Test
    public void queryParam(){

        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "e").
                when()
                .get("/spartans/search");

    //   response.prettyPeek();

         //     - Total Element Number

//        List<String> names = response.path("name");
//        System.out.println(names.size());
//        for (String name : names) {
//            System.out.println(name.toString());
//
//        }
        System.out.println(response.path("totalElement").toString());

          // Get me first spartan name
        System.out.println("Get me first spartan name: "+response.path("content.name[0]").toString());
        // Get me second spartan id
        System.out.println("Get me second spartan id: "+response.path("content.id[0]").toString());
        // Get me last spartan name
        System.out.println("Get me last spartan name: "+response.path("content.name[-1]").toString());

        System.out.println(response.path("content[-1]").toString());
        // Get me all Spartan Names
        List<String> names = response.path("content.name");
        System.out.println(names.toString());
        System.out.println(names.size());

    }
    /**
     *1- Given accept type is Json
     *2- Path Parameters values are
     *     - id —> 5
     *3- When user sends GET request to /spartans/{id}
     *5- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - ID is 5
     *     - Name is "Blythe"
     *     - gender is "Female"
     *     - phone is 3677539542
     *
     */
    @DisplayName("Path Param")
    @Test
    public void pathParam(){
        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .pathParam("id", 5).
                when()
                .get("/spartans/{id}");
    response.prettyPeek();

//    - Verify followings
//                *     - Status code should be 200
        assertEquals(200, response.statusCode());
//                *     - Content Type is application/jsons
        assertEquals("application/json",response.contentType().toString());
//                *     - ID is 5
        int id=response.path("id");
        assertEquals(5,id);

//                *     - Name is "Blythe"
        String name=response.path("name");
        assertEquals("FastTrack03",name);
//                *     - gender is "Female"
        assertEquals("Female", response.path("gender"));
//                *     - phone is 3677539542
        String phone="3677539542";
        assertEquals("3677539542",phone) ;
    }


    @DisplayName("Query Params -Map")
    @Test
    public void queryParams(){

        Map<String, String> queryMap=new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .queryParams(queryMap).
                when()
                .get("/spartans/search");

        //   response.prettyPeek();

        //     - Total Element Number

//        List<String> names = response.path("name");
//        System.out.println(names.size());
//        for (String name : names) {
//            System.out.println(name.toString());
//
//        }
        System.out.println(response.path("totalElement").toString());

        // Get me first spartan name
        System.out.println("Get me first spartan name: "+response.path("content.name[0]").toString());
        // Get me second spartan id
        System.out.println("Get me second spartan id: "+response.path("content.id[0]").toString());
        // Get me last spartan name
        System.out.println("Get me last spartan name: "+response.path("content.name[-1]").toString());

        System.out.println(response.path("content[-1]").toString());
        // Get me all Spartan Names
        List<String> names = response.path("content.name");
        System.out.println(names.toString());
        System.out.println(names.size());
    }
}
