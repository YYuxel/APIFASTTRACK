package com.cydeo.day3;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P02_SpartanFlowWithPOJO extends SpartanTestBase {



                  /*
    Create a Spartan Flow to run below testCases dynamicly
    - POST     /spartans
            Create a spartan Map
                name = "FastTrack POST "
                gender="Male"
                phone=1231231231l
            - verfiy status code 201
            - success is "A Spartan is Born!"
            - take spartanID from response and save as a global variable
    - PUT  Spartan with spartanID    /spartans/{id}
             Create a spartan Map
                name = "FastTrack PUT "
                gender="Female"
                phone=3213213213l
             - verify status code 204
    - PATCH  Spartan with spartanID    /spartans/{id}
             Create a spartan Map
                name = "FastTrack PATCH "
             - verify status code 204
    - GET  Spartan with spartanID     /spartans/{id}
             - verify status code 200
             - verfiy name is FastTrack PUT
    - DELETE  Spartan with spartanID   /spartans/{id}
             - verify status code 204
     - GET  Spartan with spartanID   /spartans/{id}
             - verify status code 404
             - verfiy name is FastTrack PUT
     */

        static int spartanId;


    @Order(1)
    @Test
    public void postSpratan(){

        //first way
        Spartan spartan=new Spartan();
        spartan.setGender("Female");
        spartan.setName("FastTrack POJO1");
        spartan.setPhone(1231231231l);

        //second way

        Spartan spartanNew=new Spartan("FastTrack POJO2", "Female", 1231231231l);

        spartanId=given().
                log().uri()
                .contentType(ContentType.JSON)
                .body(spartanNew).
        when().
                post("/spartans").prettyPeek().
        then().
                statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath().getInt("data.id");  //burada olusturlan spartanin ID sini kaydediyoruz



    }

    @Order(2)
    @Test
    public void getSpratan(){
        JsonPath jsonPath = given().
                log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanId).
                when().
                get("/spartans/{id}").prettyPeek().
                then().
                statusCode(200)
                .body("name", is("FastTrack POJO2"))
                .extract().jsonPath();

        System.out.println(jsonPath.getString("gender"));


    }
}
