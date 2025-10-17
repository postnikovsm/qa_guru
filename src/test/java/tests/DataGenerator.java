package tests;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DataGenerator {
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String gender = faker.options().option("Male", "Female", "Other");
    String userNumber = faker.phoneNumber().subscriberNumber(10);
    Date birthdayDate = faker.date().birthday();
    LocalDate birthday = birthdayDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();


    int day = birthday.getDayOfMonth();
    String formattedDay = redirectDay(day);

    String month = birthday.getMonth().toString();
    String monthCorrect = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
    String year = String.valueOf(birthday.getYear());

    String subjects = faker.options().option(
            "Maths", "Biology", "Chemistry"
    );
    String hobby = faker.options().option("Sports", "Reading", "Music");
    String file = faker.options().option("1.png");
    String address = faker.address().fullAddress();
    String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = getRandomCity(state);

    String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
    private String redirectDay(int day) {
        String formattedDay;
        if (day < 10) {
            formattedDay = "0" + day;
        } else {
            formattedDay = String.valueOf(day);
        }
        return formattedDay;
    }

}
