import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FordBellmanAlgoTest {

    @Test
    void test() {
        AdjacencyMatrixFromFile matrix =
                new AdjacencyMatrixFromFile("src/main/java/test.txt");
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Владивосток","Хабаровск"),13);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Владивосток","Санкт-Петербург"),68);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Владивосток","Москва"),48);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Москва","Владивосток"),40);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Москва","Санкт-Петербург"),20);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Москва","Хабаровск"),34);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Хабаровск","Владивосток"),8);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Хабаровск","Санкт-Петербург"),55);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Хабаровск","Москва"),35);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Санкт-Петербург","Хабаровск"),14);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Санкт-Петербург","Владивосток"),20);
        assertEquals(FordBellmanAlgo.FordBellmanAlgo(matrix,
                "Санкт-Петербург","Москва"),10);
    }
}
