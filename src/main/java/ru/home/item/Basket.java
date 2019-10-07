package ru.home.item;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private static Basket b = null;

    private static List<Item> basket;

    private Basket() {
        basket = new ArrayList<>();
    }

    public static Basket getBasket() {
        if (b == null) {
            b = new Basket();
        }
        return b;
    }

    public static Item get(String name) {
        int i = 0;
        for (int y = 0; y < basket.size(); y++) {
            if (basket.get(y).getName().equals(name)) {
                i = y;
            }
        }
        return basket.get(i);
    }

    public static void add(Item item) {
        basket.add(item);
    }

    public static void delete(String name) {
        int i = 0;
        for (int y = 0; y < basket.size(); y++) {
            if (basket.get(y).getName().equals(name)) {
                i = y;
            }
        }
        basket.remove(i);
    }

    public static int getGuarantee(String nameItem) {
        for (Item i : basket) {
            if (i.getName().equals(nameItem)) {
                return i.getGuarantee();
            }
        }
        return 0;
    }

    public static double getPrice() {
        double sum = 0;
        for (Item i : basket) {
            sum += i.getPrice();
        }
        String str = "";
        return sum;
    }

    public static boolean equalsPrice(String name, double price) {
        boolean answer = false;
        for (Item i : basket) {
            if (name.equals(i.getName())) {
                answer = price == i.getPrice();
            }
        }
        return answer;
    }

    public static boolean equalsSumPrice(String str) {
        boolean answer = false;
        Double secondPrice = Double.parseDouble(str.replaceAll(" ", ""));
        if (secondPrice == getPrice()) {
            answer = true;
        }
        return answer;
    }

    public static void addNumber(String name) {
        for (Item i : basket) {
            if (i.getName().equals(name)) {
                i.plusNumber();
            }
        }
    }

    public static int getNumber(String nameItem) {
        for (Item i : basket) {
            if (i.getName().equals(nameItem)) {
                return i.getNumber();
            }
        }
        return 0;
    }
}
