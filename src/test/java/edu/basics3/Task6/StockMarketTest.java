package edu.basics3.Task6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockMarketTest {

    @Test
    @DisplayName("Проверка корректной работы биржи")
    void correctInput(){
        // given
        StockMarket stockMarket = new StockMarket();
        stockMarket.add(new Stock( "Stock1",30));
        stockMarket.add(new Stock( "Stock2",10));
        stockMarket.add(new Stock( "Stock3",20));
        // when
        Stock mostValuableStock = stockMarket.mostValuableStock();
        // then
        Assertions.assertThat(mostValuableStock).isEqualTo(new Stock("Stock1",30));
        stockMarket.remove(new Stock("Stock1",30));
        Stock mostValuableStockAfterRemove = stockMarket.mostValuableStock();
        Assertions.assertThat(mostValuableStockAfterRemove).isEqualTo(new Stock("Stock3",20));
        stockMarket.add(new Stock("Stock4",100));
        Stock mostValuableStockAfterAdd = stockMarket.mostValuableStock();
        Assertions.assertThat(mostValuableStockAfterAdd).isEqualTo(new Stock("Stock4",100));
    }

}
