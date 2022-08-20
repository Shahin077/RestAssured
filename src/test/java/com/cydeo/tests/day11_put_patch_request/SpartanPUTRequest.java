package com.cydeo.tests.day11_put_patch_request;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;;
import static org.hamcrest.Matchers.*;

public class SpartanPUTRequest extends SpartanTestBase {
    /**
     * Given accept type is json
     * And content type is json
     * And path param id is 15
     * And json body is
     * {
     * "gender": "Male",
     * "name": "PutRequest"
     * "phone": 1234567425
     * }
     * When i send PUT request to /spartans/{id}
     * Then status code 204
     */
    @Test
    public void updateSpartanTest() {

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "PutRequest");
        requestMap.put("phone", 1234567425L);


        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 15)
                .and().body(requestMap)
                .when().put("/spartans/{id}");

        response.prettyPrint();

    }

    @DisplayName("PATCH /api/spartans/{id}")
    @Test
    public void spartanPatchTest() {
        Map<String, Long> requestMap = new HashMap<>();
        requestMap.put("phone", 4444444444L);

        int spartanId = 15;

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .and().body(requestMap)
                .when().patch("/spartans/{id}")
                .then().statusCode(204)
                .and().body(emptyString());

        Map<String, Object> spartanMap = SpartanRestUtils.getSpartan(15);




    }
}