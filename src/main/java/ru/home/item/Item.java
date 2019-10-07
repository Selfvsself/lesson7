package ru.home.item;

public class Item {

    private String name;
    private double price;
    private int guarantee;
    private int number;

    public Item() {
        guarantee = -1;
        number = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price * number;
    }

    public void setPrice(String str) {
        this.price = Double.parseDouble(str.replaceAll(" ", ""));
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public void plusNumber() {
        number++;
    }

    public int getNumber() {
        return number;
    }
}
