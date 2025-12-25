package edu.basics3.Task6;

public class Stock {
    private final String name;
    private final Integer price;

    public Stock(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Stock comparingObj = (Stock) obj;
        return this.name.equals(comparingObj.name) && this.price.equals(comparingObj.price);
    }

    @Override
    public int hashCode() {
        return this.price * 3 + this.name.length();
    }
}
