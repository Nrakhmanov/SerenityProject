package serenity.spartan.admin;

import io.cucumber.java.af.En;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static net.serenitybdd.rest.SerenityRest.given;

import static io.restassured.RestAssured.baseURI;
import static net.serenitybdd.rest.SerenityRest.lastResponse;


@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init() {
    baseURI = "http://44.195.19.167:7000";
}

@Test
    public void getAllspartan() {
        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
        .when()
                .get("/api/spartans");


}

    @Test
    public void getOnespartan() {

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");

        System.out.println("Status Code = " + lastResponse().statusCode());

        //print id

        System.out.println("lastResponse().path(\"id\") = " + lastResponse().path("id"));

        String name = lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);

        int phone = lastResponse().jsonPath().getInt("phone");
        System.out.println("phone = " + phone);


    }

    @Test
    public void getOneSpartanAssertion() {

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");

        Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200));

        Ensure.that("Content-type is JSON", vRes -> vRes.contentType(ContentType.JSON) );

        Ensure.that("Id is 15", vRes -> vRes.body("id", Matchers.is(15)));

    }
}
