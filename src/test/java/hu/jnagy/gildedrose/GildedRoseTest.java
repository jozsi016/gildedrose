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
    Item item;

    @Before
    public void setUp() {
        item = new Item("Sulfuras, Hand of Ragnaros", 4, 5);
        items = new ArrayList<>();
        items.add(item);
        systemUnderTest = new GildedRose(items);
    }

    @Test
    public void shouldReplaceItemsArrayWithList() {
        //Given
        GildedRose gildedRoseSpy = Mockito.spy(systemUnderTest);
        //When
        gildedRoseSpy.updateQuality();
        //Then
        verify(gildedRoseSpy, Mockito.times(1)).updateQuality();
    }

    @Test
    public void shouldDecreaseQualityIfQualityPositiveAndNotSulfuras(){
        //Given
        String agedBrie = "Aged Brie";
        int expected = 4;
        item.name = agedBrie;
        //When
        systemUnderTest.decreaseQualityIfQualityPositiveAndNotSulfuras(item);
        //Then
        assertThat(item.quality, is(expected));
    }

    @Test
    public void shouldDecreaseSellInByOneWhenItemNotSulfuras(){
        //Given
        String agedBrie = "Aged Brie";
        int expected = 3;
        item.name = agedBrie;
        //When
        systemUnderTest.isNotSulfurasDecreaseSellInByOne(item);
        //Then
        assertThat(item.sellIn, is(expected));
    }

    @Test
    public void shouldIncreaseQuality() {
        //Given
        int sellIn = 11;
        int expected = 6;
        GildedRose gildedRoseSpy = Mockito.spy(systemUnderTest);
        //When
        gildedRoseSpy.checkSellInAndQualityIncreaseQualityByOne(item, sellIn);
        //Then
        assertThat(item.quality, is(expected));
        verify(gildedRoseSpy, Mockito.times(1)).checkSellInAndQualityIncreaseQualityByOne(item, sellIn);
        verify(gildedRoseSpy, Mockito.times(1)).increaseQualityByOne(item);
    }

    @Test
    public void shouldIsTheSameName() {
        //Given
        String itemName = "Sulfuras, Hand of Ragnaros";
        //When
        boolean actual = systemUnderTest.isTheSameName(item, itemName);
        //Then
        assertTrue(actual);

    }

    @Test
    public void shouldDecreaseQualityByOne() {
        //Given
        int expected = 4;
        //When
        systemUnderTest.decreaseQualityByOne(item);
        //Then
        assertThat(item.quality, is(expected));
    }

    @Test
    public void shouldIncreaseQualityByOne() {
        //Given
        int expected = 6;
        //When
        systemUnderTest.increaseQualityByOne(item);
        //Then
        assertThat(item.quality, is(expected));
    }

    @Test
    public void shouldDecreaseQualityByQuality() {
        //Given
        int expected = 0;
        //When
        systemUnderTest.decreaseQualityByQuality(item);
        //Then
        assertThat(item.quality, is(expected));
    }

    @Test
    public void shouldDecreaseSellInByOne() {
        //Given
        int expected = 3;
        //When
        systemUnderTest.decreaseSellInByOne(item);
        //Then
        assertThat(item.sellIn, is(expected));
    }
}
