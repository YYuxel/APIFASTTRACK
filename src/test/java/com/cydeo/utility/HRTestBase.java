package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class HRTestBase {
    @BeforeAll
    public static void init() {


        //baseURI="http://44.201.134.164:1000";
        baseURI="http://34.192.175.227:1000";
        basePath="/ords/hr";
    }

    @AfterAll
    public static void destroy() {
        reset();

    }
}
