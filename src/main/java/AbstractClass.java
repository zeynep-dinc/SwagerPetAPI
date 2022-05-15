import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public abstract class AbstractClass {

    public Response response;

    public void statusCode(int code){
        response
                .then()
                .statusCode(code);
    }

    public void writeResult(){
        response
                .prettyPrint();
    }

    public void controlToValue(String matcher, String matchValue){
        response
                .then()
                .body(matcher,equalTo(matchValue));
    }

}
