package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class P03_JsonPath extends HRTestBase {

    @Test
    public void getLocations(){
                 /*
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK
  */

        Response response = given().
                accept(ContentType.JSON).
                log().uri().
                when().
                get("/locations");

      //  response.prettyPeek();

        JsonPath jsonPath=response.jsonPath();
       // get the second city with JsonPath
        System.out.println("Second City: "+jsonPath.getString("items[1].city"));

       // get the last city with JsonPath
        System.out.println("Last City: "+jsonPath.getString("items[-1].city"));

        //get all country ids
        List<String> allCountryIDs = jsonPath.getList("items.country_id");
        System.out.println("All Country IDs: "+allCountryIDs);

        // get all city where their country id is UK
        List<String> list = jsonPath.getList("items.findAll{it.country_id=='UK'}.city");
        System.out.println("List: "+ list);

    }
              /*
    Given
             accept type is application/json
     When
             user sends get request to /employees
     Then
             response status code must be 200
            get me all employees first_name who is making salary more than 15000
  */

    @Test
    public void getALlEmployees(){

        JsonPath jsonPath = given().
                accept(ContentType.JSON).
                log().uri().
                when().
                get("/employees").jsonPath();


        System.out.println("======================================================");

        //get me all employees first_name who is making salary more than 15000
        System.out.println(jsonPath.get("items.findAll{it.salary>15000}.first_name").toString());

        List<String> list = jsonPath.getList("items.findAll{it.salary>15000}.first_name");
        System.out.println(list);

    }
}
