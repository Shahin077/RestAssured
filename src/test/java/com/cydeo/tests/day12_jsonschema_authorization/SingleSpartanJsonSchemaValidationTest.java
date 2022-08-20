package com.cydeo.tests.day12_jsonschema_authorization;

import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;;
import static org.hamcrest.Matchers.*;
import com.cydeo.utils.SpartanTestBase;

public class SingleSpartanJsonSchemaValidationTest extends SpartanTestBase {

    /**
     * given accept type is json
     * and path param id is 15
     * when I send GEt request to /spartans/{id}
     * then status code is 200
     * And json payload/body matches SingleSpartanSchema.json
     */
    @Test
    public void singleSpartanJsonSchemaValidationTest (){
        given().accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SingleSpartanSchema.json")))
                .and().log().all();
    }

    /**
     given accept type is json
     when I send GET request to /spartans
     Then status code is 200
     And json payload/body matches AllSpartansSchema.json
     */

    @DisplayName("GET /spartans and json schema validation")
    @Test
    public void allSpartansJsonSchemaValidationTest() {

        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/AllSpartansSchema.json")))
                .and().log().all();

    }

    /**
     given accept type is json
     And query params: nameContains "e" and gender "Female"
     when I send GET request to /spartans
     Then status code is 200
     And json payload/body matches SearchSpartansSchema.json
     */

    @Test
    public void searchSpartanSchemaValidationTest(){

        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SearchSpartanSchema.json")))
                .and().log().all();
    }
}
