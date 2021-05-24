package me.calebclifton.utils;

public class RandomUtils {
    public static int getRandomPercent() {
        return (int)(Math.random() * 101);
    }

    public static String getRandomStringElementFrom(String[] array) {
        return array[(int)(Math.random() * array.length)];
    }

    public static boolean getRandomBool() {
        return Math.random() >= 0.5;
    }
}
