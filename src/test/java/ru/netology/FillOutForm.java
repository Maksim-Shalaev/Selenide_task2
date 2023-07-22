package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FillOutForm {

    String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldFillOutForm() {
        open("http://localhost:9999/");

        $("[data-test-id = city] input").setValue("Москва").pressTab();
        $("[data-test-id = date] input").setValue(generateDate(3, "dd")).pressTab();
        $("[data-test-id = name] input").setValue("Иванов Иван");
        $("[data-test-id = phone] input").setValue("+71112223344");
        $("[data-test-id = agreement").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15000));


    }
}
