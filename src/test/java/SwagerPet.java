import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class SwagerPet extends AbstractClass {
    List petId;
    int index = 0;
    Random random = new Random();

    @BeforeMethod
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        simpleGet("/pet/findByStatus?status=sold");
        statusCode(200);
        petId = listToValue("id");
        index = random.nextInt(petId.size());
        System.out.println("id:\t" + petId.get(index));
    }

    @Test(priority = 1)
    public void getFonk() {
        simpleGet("/pet/" + petId.get(index));
    }

    @Test(priority = 2)
    public void postFonk() {
        String data = "{" +
                "\"petId\":" + petId.get(index) +
                "}";

        System.out.println(data);
        simplePost("/pet/" + petId.get(index));
    }

    @Test(priority = 3)
    public void putFonk() {
        String data = "{\"status\": \"available\"}";
        simplePut("/pet");
        controlToValue("status", "available");
    }

    @Test(priority = 4)
    public void deleteFonk() {
        simpleDelete("/pet/" + petId.get(index));
    }

    @AfterMethod
    public void after() {
        statusCode(200);
        writeResult();
    }
}
