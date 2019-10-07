package ru.home.item;

import java.util.ArrayList;
import java.util.List;

public class Trash {
    private static Trash trash = null;

    private static List<Item> listTrash;

    private Trash() {
        listTrash = new ArrayList<>();
    }

    public static Trash getTrash() {
        if (trash == null) {
            trash = new Trash();
        }
        return trash;
    }

    public static void add(Item item) {
        listTrash.add(item);
    }

    public static List<Item> restore() {
        ArrayList<Item> temp = new ArrayList<>();
        listTrash.forEach(item -> temp.add(item));
        listTrash.clear();
        return temp;
    }

    public static int size() {
        return listTrash.size();
    }
}
