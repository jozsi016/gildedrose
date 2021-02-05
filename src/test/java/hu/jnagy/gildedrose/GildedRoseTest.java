package hu.jnagy.gildedrose;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class GildedRoseTest {
    GildedRose systemUnderTest;
    List<Item> items;

    @Before
    public void setUp() {
        items = new ArrayList<>();
        systemUnderTest = new GildedRose(items);
    }

    @Test
    public void shouldNotIncreaseQualityByOne() {
        //Given
        // Item[] items = new Item[1];
        Item item = new Item("Sulfuras, Hand of Ragnaros", 4, 5);
        items.add(item);
        Item expected = new Item("Sulfuras, Hand of Ragnaros", 4, 5);
        //When
        systemUnderTest.updateQuality();
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldIncreaseQualityByOne() {
        //Given
        //Item[] items = new Item[1];
        Item item = new Item("Aged Brie", 4, 5);
        items.add(item);
        Item expected = new Item("Aged Brie", 3, 6);
        //When
        systemUnderTest.updateQuality();
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void whenNotSulfurasDecreaseSellInByOne() {
        //Given
        //Item[] items = new Item[1];
        Item item = new Item("Bon Jovi", -1, 5);
        items.add(item);
        Item expected = new Item("Bon Jovi", -2, 3);
        //When
        systemUnderTest.updateQuality();
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void whenItemIsBackstageConcert() {
        //Given
        //Item[] items = new Item[1];
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 5);
        items.add(item);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", -2, 0);
        //When
        systemUnderTest.updateQuality();
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldIncreaseQualityByOneWhenAgedBrieAndSellInMinus() {
        //Given
        //Item[] items = new Item[1];
        Item item = new Item("Aged Brie", -4, 5);
        items.add(item);
        Item expected = new Item("Aged Brie", -5, 7);
        //When
        systemUnderTest.updateQuality();
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldDecreaseQualityIfQualityPositiveAndNotSulfuras() {
        //Given
        Item item = new Item("Aged Brie", -1, 5);
        items.add(item);
        Item expected = new Item("Aged Brie", -1, 4);
        //When
        systemUnderTest.decreaseQualityIfQualityPositiveAndNotSulfuras(item);
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldDecreaseSellInByOneWhenItemNotSulfuras() {
        //Given
        Item item = new Item("Aged Brie", -1, 5);
        items.add(item);
        Item expected = new Item("Aged Brie", -2, 5);
        //When
        systemUnderTest.isNotSulfurasDecreaseSellInByOne(item);
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldIncreaseQuality() {
        //Given
        int sellIn = 11;
        Item item = new Item("Aged Brie", 6, 5);
        items.add(item);
        GildedRose gildedRoseSpy = Mockito.spy(systemUnderTest);
        Item expected = new Item("Aged Brie", 6, 6);
        //When
        gildedRoseSpy.checkSellInAndQualityIncreaseQualityByOne(item, sellIn);
        //Then
        assertThat(item, is(expected));
        verify(gildedRoseSpy, Mockito.times(1)).checkSellInAndQualityIncreaseQualityByOne(item, sellIn);
        verify(gildedRoseSpy, Mockito.times(1)).increaseQualityByOne(item);
    }

    @Test
    public void shouldIsTheSameName() {
        //Given
        String itemName = "Sulfuras, Hand of Ragnaros";
        Item item = new Item("Sulfuras, Hand of Ragnaros", 6, 5);
        items.add(item);
        //When
        boolean actual = systemUnderTest.isTheSameName(item, itemName);
        //Then
        assertTrue(actual);
    }

    @Test
    public void shouldDecreaseQualityByOne() {
        //Given
        Item item = new Item("Sulfuras, Hand of Ragnaros", 6, 5);
        items.add(item);
        Item expected = new Item("Sulfuras, Hand of Ragnaros", 6, 4);
        //When
        systemUnderTest.decreaseQualityByOne(item);
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldDecreaseQualityByQuality() {
        //Given
        Item item = new Item("Sulfuras, Hand of Ragnaros", 6, 5);
        items.add(item);
        Item expected = new Item("Sulfuras, Hand of Ragnaros", 6, 0);
        //When
        systemUnderTest.decreaseQualityByQuality(item);
        //Then
        assertThat(item, is(expected));
    }

    @Test
    public void shouldDecreaseSellInByOne() {
        //Given
        Item item = new Item("Sulfuras, Hand of Ragnaros", 4, 5);
        items.add(item);
        Item expected = new Item("Sulfuras, Hand of Ragnaros", 3, 5);
        //When
        systemUnderTest.decreaseSellInByOne(item);
        //Then
        assertThat(item, is(expected));
    }
}
