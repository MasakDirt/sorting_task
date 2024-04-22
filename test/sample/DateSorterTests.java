package sample;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DateSorterTests {

    private DateSorter dataSorter;

    @Before
    public void setUp() {
        dataSorter = new DateSorter();
    }

    @Test
    public void testSortDatesWithNull() {
        assertThrows(NullPointerException.class, () ->
                dataSorter.sortDates(null));
    }

    @Test
    public void testSortingEmptyCollection() {
        List<LocalDate> expected = List.of();
        Collection<LocalDate> actual = dataSorter.sortDates(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testSortingOneDate() {
        List<LocalDate> expected = List.of(
                LocalDate.parse("2004-07-01"));
        Collection<LocalDate> actual = dataSorter.sortDates(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testSortedDatesWhichContainsR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.parse("2005-01-02"),
                LocalDate.parse("2007-01-01"),
                LocalDate.parse("2032-11-03"));

        Collection<LocalDate> expected = List.of(
                LocalDate.parse("2005-01-02"), // February
                LocalDate.parse("2007-01-01"), // February
                LocalDate.parse("2032-11-03")); // November
        Collection<LocalDate> actual = dataSorter.sortDates(unsortedDates);

        assertEquals(expected, actual);
    }

    @Test
    public void testSortedDatesWhichNotContainsR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.parse("2005-06-02"),
                LocalDate.parse("2007-07-01"),
                LocalDate.parse("2032-08-03"),
                LocalDate.parse("1999-05-03")
        );

        Collection<LocalDate> expected = List.of(
                LocalDate.parse("2032-08-03"), // August
                LocalDate.parse("2007-07-01"), // July
                LocalDate.parse("2005-06-02"), // June
                LocalDate.parse("1999-05-03")); // May
        Collection<LocalDate> actual = dataSorter.sortDates(unsortedDates);

        assertEquals(expected, actual);
    }

    @Test
    public void testSortedDates() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.parse("2004-07-01"),
                LocalDate.parse("2005-01-02"),
                LocalDate.parse("2007-01-01"),
                LocalDate.parse("2032-05-03"),
                LocalDate.parse("2040-11-03"));

        Collection<LocalDate> expected = List.of(
                LocalDate.parse("2005-01-02"), // January
                LocalDate.parse("2007-01-01"), // January
                LocalDate.parse("2040-11-03"), // November
                LocalDate.parse("2032-05-03"), // May
                LocalDate.parse("2004-07-01")); // July
        Collection<LocalDate> actual = dataSorter.sortDates(unsortedDates);

        assertEquals(expected, actual);
    }

    @Test
    public void testSortedDates2() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.parse("2004-07-01"),
                LocalDate.parse("2005-01-02"),
                LocalDate.parse("2007-01-01"),
                LocalDate.parse("2032-07-03"),
                LocalDate.parse("2040-11-03"),
                LocalDate.parse("2100-10-11"),
                LocalDate.parse("2020-12-22"),
                LocalDate.parse("1980-08-08"),
                LocalDate.parse("2990-01-01"),
                LocalDate.parse("1890-05-05"),
                LocalDate.parse("2268-06-09"));

        Collection<LocalDate> expected = List.of(
                LocalDate.parse("2005-01-02"), // January
                LocalDate.parse("2007-01-01"), // January
                LocalDate.parse("2020-12-22"), // December
                LocalDate.parse("2040-11-03"), // November
                LocalDate.parse("2100-10-11"), // October
                LocalDate.parse("2990-01-01"), // January
                LocalDate.parse("2268-06-09"), // June
                LocalDate.parse("2032-07-03"), // July
                LocalDate.parse("2004-07-01"), // July
                LocalDate.parse("1980-08-08"), // August
                LocalDate.parse("1890-05-05")); // May
        Collection<LocalDate> actual = dataSorter.sortDates(unsortedDates);

        assertEquals(expected, actual);
    }

}
