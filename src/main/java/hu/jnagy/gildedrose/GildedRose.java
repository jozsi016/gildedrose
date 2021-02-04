package hu.jnagy.gildedrose;

import java.util.List;

public class GildedRose {

    public static final String BACKSTAG_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            isAgedBrieAndBackstageConcert(item);
            isNotSulfurasDecreaseSellInByOne(item);
            isSellInNegative(item);
        }
    }

    private void isAgedBrieAndBackstageConcert(Item item) {
        if (!isTheSameName(item, AGED_BRIE) && !isTheSameName(item, BACKSTAG_CONCERT)) {
            decreaseQualityIfQualityPositiveAndNotSulfuras(item);
        } else if (item.quality < 50) {
            increaseQualityByOne(item);
            if (isTheSameName(item, BACKSTAG_CONCERT)) {
                checkSellInAndQualityIncreaseQualityByOne(item, 11);
                checkSellInAndQualityIncreaseQualityByOne(item, 6);
            }
        }
    }

    public void isNotSulfurasDecreaseSellInByOne(Item item) {
        if (!isTheSameName(item, SULFURAS))
            decreaseSellInByOne(item);
    }

    private void isSellInNegative(Item item) {
        if (item.sellIn < 0) {
            if (!isTheSameName(item, AGED_BRIE) && !isTheSameName(item, BACKSTAG_CONCERT)) {
                decreaseQualityIfQualityPositiveAndNotSulfuras(item);
            } else if (!isTheSameName(item, AGED_BRIE)) {
                decreaseQualityByQuality(item);
            } else if (item.quality < 50) {
                increaseQualityByOne(item);
            }
        }
    }

    public void decreaseQualityIfQualityPositiveAndNotSulfuras(Item item) {
        if (item.quality > 0 && !isTheSameName(item, SULFURAS))
            decreaseQualityByOne(item);
    }

    public void checkSellInAndQualityIncreaseQualityByOne(Item item, int sellIn) {
        if (item.sellIn < sellIn && item.quality < 50)
            increaseQualityByOne(item);
    }

    public boolean isTheSameName(Item item, String s) {
        return item.name.equals(s);
    }

    public void decreaseSellInByOne(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public void decreaseQualityByQuality(Item item) {
        item.quality = item.quality - item.quality;
    }

    public void decreaseQualityByOne(Item item) {
        item.quality = item.quality - 1;
    }

    public void increaseQualityByOne(Item item) {
        item.quality = item.quality + 1;
    }
}
