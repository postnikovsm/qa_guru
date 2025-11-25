package api.noModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class ReqresApiTest {

    private static final TestBaseApi testBase = new TestBaseApi();

    @BeforeAll
    static void init() {
        testBase.setupEnvironment();
    }

    @Test
    @DisplayName("Проверка количества пользователей на 2ой странице")
    public void verifyDataArraySize() {
        given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.size()", equalTo(6));
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void createUserTest() {
        String requestBody = """
                {
                    "name": "Бла-бла",
                    "job": "Начальник"
                }
                """;

        given()
                .header(testBase.header)
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("Бла-бла"))
                .body("job", equalTo("Начальник"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @Test
    @DisplayName("Обновление  пользователя")
    public void updateUserTest() {
        String requestBody = """
                {
                    "name": "Бла-бла",
                    "job": "Начальник"
                }
                """;

        given()
                .header(testBase.header)
                .contentType(JSON)
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo("Бла-бла"))
                .body("job", equalTo("Начальник"))
                .body("updatedAt", notNullValue());
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void deleteUserTest() {

        given()
                .header(testBase.header)
                .when()
                .delete("/api/users/2")
                .then()
                .log().body()
                .statusCode(204).body(equalTo(""));
    }

    @Test
    @DisplayName("Проверка токена")
    void successfulRegisterTest() {
        String regData = "{\"email\": \"eve.holt@reqres.in\",\n" + "\"password\": \"cityslicka\"}";
        given()
                .header(testBase.header)
                .body(regData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/register")
                .then()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token.length()", is(17));
        ;
    }
}
