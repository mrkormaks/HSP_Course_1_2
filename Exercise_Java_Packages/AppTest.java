import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import mathoperations.MathUtils;
import converter.HexConverter;

import java.util.Random;

public class AppTest {

    private final Random random = new Random();

    @Test
    public void testIsEven() {
        // Проверка на четность
        assertTrue(MathUtils.isEven(2), "2 должно быть четным");
        assertFalse(MathUtils.isEven(3), "3 должно быть нечетным");

        // Проверка на четность для отрицательных чисел и нуля
        assertTrue(MathUtils.isEven(-4), "-4 должно быть четным");
        assertFalse(MathUtils.isEven(-5), "-5 должно быть нечетным");
        assertTrue(MathUtils.isEven(0), "0 должно быть четным");
    }

    @Test
    public void testSquareInt() {
        // Проверка возведения в квадрат
        assertEquals(4, MathUtils.squareInt(2), "Квадрат 2 должен быть 4");
        assertEquals(9, MathUtils.squareInt(3), "Квадрат 3 должен быть 9");

        // Проверка возведения в квадрат для отрицательных чисел и нуля
        assertEquals(16, MathUtils.squareInt(-4), "Квадрат -4 должен быть 16");
        assertEquals(25, MathUtils.squareInt(-5), "Квадрат -5 должен быть 25");
        assertEquals(0, MathUtils.squareInt(0), "Квадрат 0 должен быть 0");
    }

    @Test
    public void testDecToHex() {
        // Проверка преобразования в шестнадцатеричное представление
        assertEquals("A", HexConverter.decToHex(10), "10 должно быть 'A' в шестнадцатеричной системе");
        assertEquals("F", HexConverter.decToHex(15), "15 должно быть 'F' в шестнадцатеричной системе");
    }

    @Test
    public void testSquareIntToHex() {
        // Проверка возведения в квадрат и преобразования в шестнадцатеричное представление
        assertEquals("4", HexConverter.squareIntToHex(2), "Квадрат 2 должен быть '4' в шестнадцатеричной системе");
        assertEquals("9", HexConverter.squareIntToHex(3), "Квадрат 3 должен быть '9' в шестнадцатеричной системе");

        // Проверка возведения в квадрат и преобразования для отрицательных чисел и нуля
        assertEquals("10", HexConverter.squareIntToHex(-4), "Квадрат -4 должен быть '10' в шестнадцатеричной системе");
        assertEquals("19", HexConverter.squareIntToHex(-5), "Квадрат -5 должен быть '19' в шестнадцатеричной системе");
        assertEquals("0", HexConverter.squareIntToHex(0), "Квадрат 0 должен быть '0' в шестнадцатеричной системе");
    }

    @Test
    public void testNullHandling() {
        // Проверка на null в методе decToHex
        try {
            HexConverter.decToHex(null);
        } catch (NullPointerException e) {
            // Проверка, что было выброшено NullPointerException
            assertEquals(NullPointerException.class, e.getClass(), "Преобразование null должно вызывать NullPointerException");
        }

        // Проверка на null в методе squareIntToHex
        try {
            HexConverter.squareIntToHex(null);
        } catch (NullPointerException e) {
            // Проверка, что было выброшено NullPointerException
            assertEquals(NullPointerException.class, e.getClass(), "Преобразование null должно вызывать NullPointerException");
        }
    }

    @Test
    public void testRandomValues() {

        // Тестирование с 1000 случайных значений
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt();
            int squared = MathUtils.squareInt(randomNumber);
            String hex = HexConverter.decToHex(randomNumber);
            String hexSquared = HexConverter.squareIntToHex(randomNumber);

            // Проверка возведения в квадрат
            assertEquals(squared, randomNumber * randomNumber, "Квадрат " + randomNumber + " должен быть " + (randomNumber * randomNumber));

            // Проверка преобразования в шестнадцатеричное представление
            assertEquals(hex, Integer.toHexString(randomNumber).toUpperCase(), "Шестнадцатеричное представление " + randomNumber + " должно быть " + Integer.toHexString(randomNumber).toUpperCase());

            // Проверка возведения в квадрат и преобразования в шестнадцатеричное представление
            assertEquals(hexSquared, Integer.toHexString(squared).toUpperCase(), "Шестнадцатеричное представление квадрата " + randomNumber + " должно быть " + Integer.toHexString(squared).toUpperCase());
        }
    }
}
