package io.exercism;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class GigasecondTest {

    @Disabled("Remove to run test")
    @Test
    public void modernTime() {
        Gigasecond gigaSecond = new Gigasecond(LocalDate.of(2011, Month.APRIL, 25));

        Assertions.assertEquals(LocalDateTime.of(2043, Month.JANUARY, 1, 1, 46, 40), gigaSecond.getDateTime());
    }

    @Disabled("Remove to run test")
    @Test
    public void afterEpochTime() {
        Gigasecond gigaSecond = new Gigasecond(LocalDate.of(1977, Month.JUNE, 13));

        Assertions.assertEquals(LocalDateTime.of(2009, Month.FEBRUARY, 19, 1, 46, 40), gigaSecond.getDateTime());
    }

    @Disabled("Remove to run test")
    @Test
    public void beforeEpochTime() {
        Gigasecond gigaSecond = new Gigasecond(LocalDate.of(1959, Month.JULY, 19));

        Assertions.assertEquals(LocalDateTime.of(1991, Month.MARCH, 27, 1, 46, 40), gigaSecond.getDateTime());
    }

    @Disabled("Remove to run test")
    @Test
    public void withFullTimeSpecified() {
        Gigasecond gigaSecond = new Gigasecond(LocalDateTime.of(2015, Month.JANUARY, 24, 22, 0, 0));

        Assertions.assertEquals(LocalDateTime.of(2046, Month.OCTOBER, 2, 23, 46, 40), gigaSecond.getDateTime());
    }

    @Disabled("Remove to run test")
    @Test
    public void withFullTimeSpecifiedAndDayRollover() {
        Gigasecond gigaSecond = new Gigasecond(LocalDateTime.of(2015, Month.JANUARY, 24, 23, 59, 59));

        Assertions.assertEquals(LocalDateTime.of(2046, Month.OCTOBER, 3, 1, 46, 39), gigaSecond.getDateTime());
    }
}
