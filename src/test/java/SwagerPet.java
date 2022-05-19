import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class SwagerPet extends AbstractClass {
    List petId;
    int index = 0;
    String status="sold";
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

    @Test
    public void getFonk() {
        simpleGet("/pet/" + petId.get(index));
    }

    @Test
    public void getToWithStatus(){
        simpleGet("/pet/findByStatus?status="+status);
    }

    @Test
    public void postFonksiyonBodyFull(){
        String data="{\n" +
                "  \"id\": 2,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"itoğlu\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        simplePost("/pet",data);
    }

    @Test
    public void petIdStatusPostMethod() {
        String data = "{" +
                "\"petId\":"+petId.get(index).toString()+
                "\"status\":\"sold\""+
                "}";

        System.out.println(data);
        postWithFormParam("/pet/" + petId.get(index).toString(),data);
    }

    @Test
    public void putFonk() {
        String data = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        simplePut("/pet",data);
        controlToValue("status", "available");
    }

    @Test
    public void deleteFonk() {
        simpleDelete("/pet/" + petId.get(index));
    }

    @AfterMethod
    public void after() {
        statusCode(200);
        writeResult();
    }
}
