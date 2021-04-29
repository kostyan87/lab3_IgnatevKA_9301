import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getPriceOfWayTest {

    @Test
    void test() {
        FlightRepository repo = new FlightRepository("src/main/java/test.txt");

        assertEquals(repo.getPriceOfWay("Санкт-Петербург", "Москва"),10);
        assertEquals(repo.getPriceOfWay("Москва", "Санкт-Петербург"),20);
        assertEquals(repo.getPriceOfWay("Хабаровск", "Москва"),35);
        assertEquals(repo.getPriceOfWay("Москва", "Хабаровск"),40);
        assertEquals(repo.getPriceOfWay("Санкт-Петербург", "Хабаровск"),14);
        assertEquals(repo.getPriceOfWay("Санкт-Петербург", "Владивосток"),20);
        assertEquals(repo.getPriceOfWay("Владивосток", "Хабаровск"),13);
        assertEquals(repo.getPriceOfWay("Хабаровск", "Владивосток"),8);
        assertEquals(repo.getPriceOfWay("Хабаровск", "Санкт-Петербург"), Integer.MAX_VALUE);
    }
}
