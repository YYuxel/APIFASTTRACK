package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.List;

import static io.restassured.RestAssured.*;

public class P04_HamCrestMatchers extends HRTestBase {

    /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4,5
               ...
               ..
               .
    */
    @Test
    public void getAllRegions(){
      given().
                accept(ContentType.JSON).
                log().uri().
      when().
                get("/regions").prettyPeek().
      then().
              statusCode(200).
              header("Date",notNullValue()).
              body("items[0].region_name",is("Europe")).
              body("items[0].region_id", is(1)).
              body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa")).
              body("items", hasSize(5));


    }
}
