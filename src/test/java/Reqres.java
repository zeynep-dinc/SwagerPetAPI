import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Reqres {


    private String domain;

    @Test
    public void singleUserBDDTest(){
        domain="https://reqres.in/api/users/2";
        when().
                get(domain).
                then().
                statusCode(200).
                body("data.email",equalTo("janet.weaver@reqres.in")).
                time(lessThan(4000L));
    }


    @Test
    public void singleUserTest(){
        baseURI="https://reqres.in";
        RequestSpecification httpRequest=given();
        Response response=httpRequest.get("/api/users=2");
        ResponseBody body=response.getBody();
        String bodyAsString=body.asString();
        System.out.println(bodyAsString);
        Assert.assertTrue(bodyAsString.contains("janet.weaver@reqres.in"),"Pass");
    }

    @Test
    public void createData(){
        String posData="{\n" +
                "    \"name\": \"zeynep\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        baseURI="https://reqres.in";
        given().
                contentType(ContentType.JSON).
                body(posData).
                when().
                post("/api/users").
                then().
                statusCode(201).
                body("name",equalTo("zeynep"));
    }

    @Test
    public void delete(){
        baseURI="https://reqres.in";
        given().
                contentType(ContentType.JSON).
                when().
                delete("/api/users=2").
                then().
                statusCode(204);
    }

    @Test
    public void put(){
        String updateData="{\n" +
                "    \"name\": \"zeynep\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        baseURI="https://reqres.in";

        Response response=given().
                contentType(ContentType.JSON).
                body(updateData).
                when().
                put("/api/user=2");

        response.then().statusCode(200);
        response.prettyPrint();
    }
}
