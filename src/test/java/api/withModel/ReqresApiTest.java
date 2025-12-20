package api.withModel;

import io.restassured.RestAssured;
import model.createUser.UserRequestDto;
import model.createUser.UserResponseDto;
import model.listUser.ResponseListUsersDto;
import model.loginUser.UserLoginRequestDto;
import model.loginUser.UserTokenResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.CustomSpec.requestSpec;
import static specs.CustomSpec.responseSpec;


public class ReqresApiTest {

    UserRequestDto user = new UserRequestDto();
    UserLoginRequestDto userLogin = new UserLoginRequestDto();

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath="/api";
    }

    @Test
    @DisplayName("Проверка количества пользователей на 2ой странице")
    public void verifyDataArraySizeTest() {
        ResponseListUsersDto listUsers = step("Запрос количества сотрудников", () ->
                given()
                        .spec(requestSpec)
                        .queryParam("page", 2)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(ResponseListUsersDto.class)
        );

        step("Проверка количества сотрудников", () ->
                assertEquals(6, listUsers.getData().size()));
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void createUserTest() {
        user.setName("Бла-бла");
        user.setJob("Начальник");

        UserResponseDto userResponse = step("Создание нового юзера", () ->
                given(requestSpec)
                        .body(user)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec(201))
                        .extract().as(UserResponseDto.class));
        step("Проверка имени и фамилии созданного юзера", () -> {
            assertEquals(user.getName(), userResponse.getName());
            assertEquals(user.getJob(), userResponse.getJob());
        });
    }

    @Test
    @DisplayName("Обновление пользователя")
    public void updateUserTest() {
        user.setName("Бла-бла");
        user.setJob("Начальник");

        UserResponseDto userResponse = step("Обновление юзера", () ->
                given()
                        .spec(requestSpec)
                        .body(user)
                        .when()
                        .put("/api/users/2")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(UserResponseDto.class)
        );

        step("Проверка имени и должности обновленного юзера", () -> {
            assertEquals(user.getName(), userResponse.getName());
            assertEquals(user.getJob(), userResponse.getJob());
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void deleteUserTest() {

        given(requestSpec)
                .when()
                .delete("/api/users/2")
                .then()
                .spec(responseSpec(204)).body(equalTo(""));
    }

    @Test
    @DisplayName("Проверка токена")
    void successfulRegisterTest() {
        userLogin.setEmail("eve.holt@reqres.in");
        userLogin.setPassword("cityslicka");

        UserTokenResponseDto token = step("Получение токена", () ->
                given(requestSpec)
                        .body(userLogin)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(UserTokenResponseDto.class));
        step("Проверка токена", () -> {
            assertNotNull(token.getToken(), "Токен не должен равняться null");
            assertFalse(token.getToken().isEmpty(), "Токен не должен быть пустым");
            assertEquals(17, token.getToken().length(), "Длина токена не равна 17");
        });
    }
}
