import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public abstract class AbstractClass {

    public Response response;

    public Response simpleGet(String url){
       response=
               given()
                       .contentType(ContentType.JSON)
                       .get(url);
       return response;
    }

    public Response simpleDelete(String url)
    {
        response = given()
                .contentType(ContentType.JSON)
                .delete(url);
        return response;
    }

    public Response simplePost(String url){
        return  given().
                contentType(ContentType.JSON).
                when().
                post(url);
    }

    public Response simplePut(String url){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .put(url);
    }

    public void statusCode(int code) {
        response
                .then()
                .statusCode(code);
    }


    public void writeResult() {
        response
                .prettyPrint();
    }

    public void controlToValue(String matcher, String matchValue) {
        response
                .then()
                .body(matcher, equalTo(matchValue));
    }

}
