package util;

/**
 * Декоратор с палитрой из 5 цветов
 */
public class Decorator {
    public static final String RESET = colour(0);
    public static final String RED = colour(31);
    public static final String GREEN = colour(32);
    public static final String YELLOW = colour(33);
    public static final String BLUE = colour(34);

    public static String colour(int num) {
        return "\u001B[" + num + "m";
    }
}
