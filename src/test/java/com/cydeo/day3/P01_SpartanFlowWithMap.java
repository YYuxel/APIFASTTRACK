package com.cydeo.day3;

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
public class P01_SpartanFlowWithMap extends SpartanTestBase {
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

    static int spartanID;
    @Order(1)
    @Test
    public void postSpartan(){
        Map<String,Object> spartanMap=new HashMap<>();

        spartanMap.put("name", "FastTrack POST");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1231231231l);


        JsonPath jp = given().log().uri()
                .contentType(ContentType.JSON)
                .body(spartanMap).
                when()
                .post("/spartans").prettyPeek().
                then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();


        spartanID = jp.getInt("data.id");

    }

    @Order(2)
    @Test

    public void putSpartan(){
        /*
            - PUT  Spartan with spartanID    /spartans/{id}
             Create a spartan Map
                name = "FastTrack PUT "
                gender="Female"
                phone=3213213213l
             - verify status code 204
             * */

        Map<String,Object> spartanMap=new HashMap<>();

        spartanMap.put("name", "FastTrack PUT");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1231231231l);

        given().
                log().uri()
                .pathParam("id", spartanID)
                .contentType(ContentType.JSON)
                .body(spartanMap).
        when().
                put("/spartans/{id}").prettyPeek().
        then().
                statusCode(204)   ;

    }
    @Order(3)
    @Test
    public void patchSpartan(){

        /*
        *     - PATCH  Spartan with spartanID    /spartans/{id}
             Create a spartan Map
                name = "FastTrack PATCH "
             - verify status code 204
        *
        * */

        Map<String,Object> spartanMap=new HashMap<>();

        spartanMap.put("name", "FastTrack PATCH");

        given().
                log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(spartanMap).
        when().
                patch("/spartans/{id}").prettyPeek().
        then().
                statusCode(204);

    }
    @Order(4)
    @Test
    public void getSpartan(){

        /*
        *     - GET  Spartan with spartanID     /spartans/{id}
             - verify status code 200
             - verfiy name is FastTrack PATCH*/

        given().
                log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanID).
        when().
                get("/spartans/{id}").prettyPeek().
        then().
                statusCode(200)
                .body("name", is("FastTrack PATCH"));

    }
    @Order(5)
    @Test
    public void deleteSpartan(){
        /*
          - DELETE  Spartan with spartanID   /spartans/{id}
             - verify status code 204
        * */

        given().
                log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanID).
        when().
                delete("/spartans/{id}").prettyPeek().
        then().
                statusCode(204);

    }
    @Order(6)
    @Test
    public void getAfterDelete(){

        /*
        *         - GET  Spartan with spartanID   /spartans/{id}
             - verify status code 404
             - verfiy name is FastTrack PUT
     */

        given().
                log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanID).
        when().
                get("/spartans/{id}").prettyPeek().
        then().
                statusCode(404);


    }

}
