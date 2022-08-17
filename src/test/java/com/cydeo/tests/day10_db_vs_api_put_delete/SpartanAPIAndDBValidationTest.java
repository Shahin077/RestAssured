package com.cydeo.tests.day10_db_vs_api_put_delete;

import com.cydeo.utils.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cydeo.utils.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanAPIAndDBValidationTest extends SpartanTestBase {
    /**
     given accept is json and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "PostVSDatabase"
     "phone": 1234567425
     }
     When I send POST request to /spartans
     Then status code is 201
     And content type is json
     And "success" is "A Spartan is Born!"
     When I send database query
     SELECT name, gender, phone
     FROM spartans
     WHERE spartan_id = newIdFrom Post request;
     Then name, gender , phone values must match with POST request details
     */

    @DisplayName("POST /api/spartan -> then validate in DB")
    @Test
    public void postNewSpartanThenValidateInDBTest() {

        Map<String, Object> postRequestMap = new HashMap<>();
        postRequestMap.put("gender", "Male");
        postRequestMap.put("name" , "PostVSDatabase");
        postRequestMap.put("phone", 1234567425L );

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                .when().post("/spartans");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_CREATED));
        assertThat(response.contentType(), equalTo(ContentType.JSON.toString()));
        assertThat(response.jsonPath().getString("success"), equalTo("A Spartan is Born!"));
        assertThat(response.path("success"), equalTo("A Spartan is Born!"));

        int newId = response.path("data.id");
//
//        Response response1 = given().accept(ContentType.JSON)
//                .and().contentType(ContentType.JSON)
//                .and().pathParam("id", newId)
//                .when().get("/spartans/{id}");

   //     assertThat(response1.statusCode(),is(HttpStatus.SC_OK));

        String query = "select NAME , gender, PHONE from SPARTANS\n" +
                "where SPARTAN_ID = "+newId;

        DBUtils.createConnection(
                ConfigurationReader.getProperty("spartan.db.url"),
                ConfigurationReader.getProperty("spartan.db.userName"),
                ConfigurationReader.getProperty("spartan.db.password"));

        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        assertThat(dbMap.get("NAME"), equalTo(jsonPath.getString("data.name")));
        assertThat(dbMap.get("GENDER"), equalTo(jsonPath.getString("data.gender")));
        assertThat(dbMap.get("PHONE"), equalTo(jsonPath.getInt("data.phone")+""));

        DBUtils.destroy();


    }

}
