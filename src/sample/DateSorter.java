package sample;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * <p>
 * package sample;
 * <p>
 * import java.time.LocalDate;
 * import java.util.Collection;
 * import java.util.List;
 * <p>
 * /**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {

    private static final int BEFORE = -1;
    private static final int AFTER = 1;

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        return unsortedDates.stream()
                .sorted(compareDatesWithR())
                .toList();
    }

    private Comparator<LocalDate> compareDatesWithR() {
        return (date1, date2) -> {
            boolean date1HasR = isMonthDateContainsR(date1);
            boolean date2HasR = isMonthDateContainsR(date2);

            if (date1HasR && date2HasR) {
                return sortInAscOrder(date1, date2);
            } else if (!date1HasR && !date2HasR) {
                return sortInDescOrder(date1, date2);
            } else if (date1HasR) {
                return BEFORE; // date1 has 'r' in the month, so it comes before date2
            } else {
                return AFTER;  // date2 has 'r' in the month, so it comes after date1
            }
        };
    }

    private boolean isMonthDateContainsR(LocalDate date) {
        return date.getMonth().toString().toLowerCase().contains("r");
    }

    private int sortInAscOrder(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    private int sortInDescOrder(LocalDate date1, LocalDate date2) {
        return date2.compareTo(date1);
    }

}