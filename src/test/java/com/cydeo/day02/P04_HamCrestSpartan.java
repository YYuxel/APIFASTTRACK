package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

public class P04_HamCrestSpartan extends SpartanTestBase {

    /**
     * - Send a request to GET /spartans/search
     * - Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> ea
     * - Log Everything
     * - Verify followings
     *       - Status Code is 200
     *       - ContentType is application/json
     *       - Total Element 2
     *       - jsonArray size hasSize 2
     *       - All Names hasItem "Meade1"
     *       - Every gender is Female
     */
    @Test
    public void searchSpartans(){

        given().
                queryParam("nameContains", "ea").
                queryParam("gender", "Female").
                accept(ContentType.JSON)
                .log().uri().
        when().
                get("/spartans/search").prettyPeek().
        then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("totalElement", is(2)).
                body("content", hasSize(2)).
                body("content.name", hasItem("Meade1")).
                body("content.gender", everyItem(is("Female")));


    }
}
