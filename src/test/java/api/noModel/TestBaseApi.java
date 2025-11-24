package api.noModel;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class TestBaseApi {
    public final Header header = new Header("x-api-key", "reqres-free-v1");

    public void setupEnvironment() {
        RestAssured.baseURI = "https://reqres.in";
    }
}
