package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanTestBase {
    @BeforeAll
    public static void init() {
        //baseURI = "http://44.201.134.164:8000";
        baseURI="http://34.192.175.227:8000"; // spartan
         basePath = "/api"; //spartan

    }

    @AfterAll
    public static void destroy() {
        reset();

    }
}
