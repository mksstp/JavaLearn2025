package edu.basics3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket implements StockMarketInterface {

    private final PriorityQueue<Stock> marketQueue =
            new PriorityQueue<>(Comparator.comparingInt(Stock::getPrice).reversed());


    public void add(Stock stock) {
        marketQueue.add(stock);
    }

    public void remove(Stock stock) {
        marketQueue.remove(stock);
    }

    public Stock mostValuableStock() {
        return marketQueue.peek();
    }
}
