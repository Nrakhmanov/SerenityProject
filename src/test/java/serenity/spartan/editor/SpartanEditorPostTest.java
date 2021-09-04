package serenity.spartan.editor;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanNewBase;

import io.cucumber.java.af.En;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.SpartanUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;

import static io.restassured.RestAssured.baseURI;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;

@Disabled
@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @Test
    public void postSpartanAsEditor() {

        //create one spartan using Util

        Map<String, Object> bodyMap = SpartanUtil.getRandomSpartanMap();
        System.out.println("bodyMap = " + bodyMap);

        //send a post request as editor

        given().auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().all()
        .when()
                .post("/spartans")
                .then().log().all();

         /*
                status code is 201
                content type is Json
                success message is A Spartan is Born!
                id is not null
                name is correct
                gender is correct
                phone is correct

                check location header ends with newly generated id
         */


        Ensure.that("Status code is 201", vRes -> vRes.statusCode(201));

        Ensure.that("Content-type is JSON", vRes -> vRes.contentType(ContentType.JSON));

        Ensure.that("Success message is correct", x -> x.body("success", is("A Spartan is Born!")));

        Ensure.that("ID is not null", vRes -> vRes.body("data.id", notNullValue()));

        Ensure.that("Name is correct", vRes -> vRes.body("data.name", is(bodyMap.get("name"))));

        Ensure.that("Gender is correct", vRes -> vRes.body("data.gender", is(bodyMap.get("gender"))));

        Ensure.that("Phone is correct", vRes -> vRes.body("data.phone", is(bodyMap.get("phone"))));

        String id = lastResponse().jsonPath().getString("data.id");

        Ensure.that("Check location header ends with newly generated id",
                validatableResponse -> validatableResponse.header("Location", endsWith(id)));



        }

        /*
        we can give name to each execution using name = ""
        and if you want to get index of iteration we can use {index}
        {0}, {1}, {2} --> based on the order of variables given as argument
         */
    @ParameterizedTest(name = "New Spartan {index} - name: {0}")
    @CsvFileSource(resources = "/SpartanData.csv", numLinesToSkip = 1)
    public void postSpartanWithCsv(String name, String gender, long phone) {

          System.out.println("name = " + name);
          System.out.println("gender = " + gender);
          System.out.println("phone = " + phone);

          Map<String, Object> bodyMap = new LinkedHashMap<>();
          bodyMap.put("name", name);
          bodyMap.put("gender", gender);
          bodyMap.put("phone", phone);

            given()
                 .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
            .when()
                .post("/spartans")
                .then().log().all();



        Ensure.that("Status code is 201", vRes -> vRes.statusCode(201));

        Ensure.that("Content-type is JSON", vRes -> vRes.contentType(ContentType.JSON));

        Ensure.that("Success message is correct", x -> x.body("success", is("A Spartan is Born!")));

        Ensure.that("ID is not null", vRes -> vRes.body("data.id", notNullValue()));

        Ensure.that("Name is correct", vRes -> vRes.body("data.name", is(bodyMap.get("name"))));

        Ensure.that("Gender is correct", vRes -> vRes.body("data.gender", is(bodyMap.get("gender"))));

        Ensure.that("Phone is correct", vRes -> vRes.body("data.phone", is(bodyMap.get("phone"))));

        String id = lastResponse().jsonPath().getString("data.id");

        Ensure.that("Check location header ends with newly generated id",
                validatableResponse -> validatableResponse.header("Location", endsWith(id)));



    }


}
