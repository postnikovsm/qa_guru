package api.withModel;

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
import static specs.CustomSpec.responseSpec200OK;
import static specs.CustomSpec.responseSpec201;
import static specs.CustomSpec.responseSpec204;

public class ReqresApiTest {

    private static final TestBaseApi testBase = new TestBaseApi();
    UserRequestDto user = new UserRequestDto();
    UserLoginRequestDto userLogin = new UserLoginRequestDto();

    @BeforeAll
    static void init() {
        testBase.setupEnvironment();
    }

    @Test
    @DisplayName("Проверка количества пользователей на 2ой странице")
    public void verifyDataArraySize() {

        ResponseListUsersDto listUsers = step("Запрос количества сотрудников", () ->
                given(requestSpec)
                        .queryParam("page", 2)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec200OK)
                        .extract().as(ResponseListUsersDto.class));

        assertEquals(6, listUsers.getData().size());

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
                        .spec(responseSpec201)
                        .extract().as(UserResponseDto.class));
        step("Проверка имени и фамилии созданного юзера", () -> {
            assertEquals(user.getName(), userResponse.getName());
            assertEquals(user.getJob(), userResponse.getJob());
        });
    }

    @Test
    @DisplayName("Обновление  пользователя")
    public void updateUserTest() {
        user.setName("Бла-бла");
        user.setJob("Начальник");

        UserResponseDto userResponse = step("Обновление юзера", () ->
                given(requestSpec)
                        .body(user)
                        .when()
                        .put("/api/users/2")
                        .then()
                        .spec(responseSpec200OK)
                        .extract().as(UserResponseDto.class));

        step("Проверка имени и фамилии обновленного юзера", () -> {
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
                .spec(responseSpec204).body(equalTo(""));
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
                        .spec(responseSpec200OK)
                        .extract().as(UserTokenResponseDto.class));
        step("Проверка токена", () -> {
            assertNotNull(token.getToken(), "Токен не должен равняться null");
            assertFalse(token.getToken().isEmpty(), "Токен не должен быть пустым");
            assertEquals(17, token.getToken().length(), "Длина токена не равна 17");
        });;

    }
}
