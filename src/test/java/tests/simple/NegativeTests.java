package tests.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeTests {

    @Test
    void wrongAdditionTest() {
        assertEquals(5, 2 + 2, "Специально неверный тест: 2 + 2 ≠ 5");
    }

    @Test
    void failedTrueTest() {
        boolean isActive = false;
        assertTrue(isActive, "Специально падающий тест: ожидалось true, но false");
    }

    @Test
    void failedNotNullTest() {
        String nullString = null;
        assertNotNull(nullString, "Специально падающий тест: объект null");
    }
}
