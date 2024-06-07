package converter;

public class HexConverter {
    public static String decToHex(int number) {
        return Integer.toHexString(number).toUpperCase();
    }
}