package serenity.spartan.editor;

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

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;

import static io.restassured.RestAssured.baseURI;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

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
                .prettyPrint();


    }


}
