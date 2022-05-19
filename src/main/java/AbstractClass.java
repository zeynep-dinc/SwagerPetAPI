import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public abstract class AbstractClass {

    public Response response;

    public Response simpleGet(String url) {
        response = given()
                .contentType(ContentType.JSON)
                .get(url);
        return response;
    }

    public Response simpleDelete(String url) {
        response = given()
                .contentType(ContentType.JSON)
                .delete(url);
        return response;
    }

    public Response simplePost(String url) {
        response = given().
                contentType(ContentType.JSON).
                when().
                post(url);
        return response;
    }

    public Response simplePut(String url) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .put(url);
        return response;
    }

    public Response simpleGet(String url, String bodyData) {
        response = given()
                .contentType(ContentType.JSON)
                .body(bodyData)
                .when()
                .get(url);
        return response;
    }

    public Response simpleDelete(String url, String bodyData) {
        response = given()
                .contentType(ContentType.JSON)
                .body(bodyData)
                .when()
                .delete(url);
        return response;
    }

    public Response simplePost(String url, String bodyData) {
        response = given().
                contentType(ContentType.JSON).
                body(bodyData).
                when().
                post(url);
        return response;
    }

    public Response simplePut(String url, String bodyData) {
        response = given()
                .contentType(ContentType.JSON)
                .body(bodyData)
                .when()
                .put(url);
        return response;
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

    public List<String> listToValue(String filterName){
        return response.jsonPath().getList(filterName);
    }
}
