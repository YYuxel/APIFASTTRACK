package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class P01_Contains_DDT extends HRTestBase {
        /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains   Americas
     */

    @DisplayName("Contains Method")
    @Test
    public void containsMethod() {
        Response response = given().
                accept(ContentType.JSON).
                log().uri().
                pathParam("id", 2).
                when().
                get("/regions/{id}").
                prettyPeek();


        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.asString().contains("Americas"));

    }

    //DataDriven Test

@ParameterizedTest
@ValueSource(ints = {1,2,3,4})

    public void getOneRegionValueSource(int id){
        Response response = given().
                accept(ContentType.JSON).
                log().uri().
                pathParam("id", id).
                when().
                get("/regions/{id}").
                prettyPeek();


        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals( id, (Integer) response.path("region_id"));

    }

    // numLinsToskip  skip the header if it has a value as 2,
    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv", numLinesToSkip = 1)

    public void getOneRegionCsvFileSource(int id,String regionName){
        Response response = given().
                accept(ContentType.JSON).
                log().uri().
                pathParam("id", id).
                when().
                get("/regions/{id}").
                prettyPeek();


        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals( id, (Integer) response.path("region_id"));
        assertEquals(regionName, response.path("region_name").toString());


    }

}
