package tests.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositiveTests {

    @Test
    void checkSumTest(){
        assertEquals(4, 2 + 2, "2 + 2 должно быть 4");
    }

    @Test
    void stringNotEqualsTest(){
        assertNotEquals(5, 2 + 2, "2 + 2 не равняется 5");
    }

    @Test
    void stringContainsTest(){
        String text = "Hello, Jenkins!";
        assertTrue(text.contains("Jenkins!"),"Строка должна содержать 'Jenkins'");
    }

    @Test
    void notContainsNullTest(){
        String result = "Hello, World!";
        assertNotNull(result, "Объект не должен быть null");
    }
}