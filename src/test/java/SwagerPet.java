import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class SwagerPet extends AbstractClass {
    List petId;
    int index=0;

    @BeforeMethod
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        response = given().
                contentType(ContentType.JSON).
                get("/pet/findByStatus?status=sold");

        statusCode(200);
        petId = response.jsonPath().getList("id");

        Random random = new Random();

        index = random.nextInt(petId.size());
        System.out.println("id:\t" + petId.get(index));
    }

    @Test
    public void putFonk() {
        String data = "{\"status\": \"sold\"}";

        response = given().
                contentType(ContentType.JSON).
                body(data).
                when().
                put("/pet");


        controlToValue("status","available");
    }

    @Test
    public void getFonk() {
        response = given()
                .contentType(ContentType.JSON)
                .get("/pet/" + petId.get(index));
    }

    @Test
    public void postFonk() {

        String data = "{" +
                "\"petId\":" + petId.get(index) +
                "}";

        System.out.println(data);

        response = given()
                .post("/pet/" + petId.get(index));
    }

    @Test
    public void deleteFonk() {

        response = given()
                .contentType(ContentType.JSON)
                .delete("/pet/" + petId.get(index));
    }

    @AfterMethod
    public void after(){
        statusCode(200);
        writeResult();
    }
}
