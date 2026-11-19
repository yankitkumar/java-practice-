import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Java Stream exercises.
 *
 * How to use:
 *   1. Pick an exercise and replace "return null;" with your stream.
 *   2. Run it:  java StreamExercises.java
 *   3. Each exercise prints PASS, FAIL (with the expected answer) or "not done yet".
 *
 * Stuck? The answers are in StreamSolutions.java. Try first!
 */
public class StreamExercises {

    // ---------------------------------------------------------------
    // The data: same Item class as the interview question
    // ---------------------------------------------------------------

    static class Item {
        private String name, category;
        private double price;

        Item(String n, String c, double p) { name = n; category = c; price = p; }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
    }

    static class Department {
        private String name;
        private List<Item> items;

        Department(String n, List<Item> i) { name = n; items = i; }

        public String getName() { return name; }
        public List<Item> getItems() { return items; }
    }

    static List<Item> l1 = List.of(
        new Item("Java Guide", "Book", 40.0),
        new Item("Pen", "Stationery", 2.5),
        new Item("Novel", "Book", 25.0),
        new Item("Notebook", "Stationery", 5.0),
        new Item("Laptop", "Electronics", 900.0));

    static List<Department> departments = List.of(
        new Department("Reading", List.of(
            new Item("Java Guide", "Book", 40.0),
            new Item("Novel", "Book", 25.0))),
        new Department("Office", List.of(
            new Item("Pen", "Stationery", 2.5),
            new Item("Notebook", "Stationery", 5.0))),
        new Department("Tech", List.of(
            new Item("Laptop", "Electronics", 900.0))));

    // ---------------------------------------------------------------
    // Exercise 1 (easy): filter + map
    // Names of all items that cost more than 10.
    // Expected: [Java Guide, Novel, Laptop]
    // Hint: filter -> map -> toList
    // ---------------------------------------------------------------
    static List<String> exercise1(List<Item> l1) {
        return null; // TODO: write your stream here
    }

    // ---------------------------------------------------------------
    // Exercise 2 (the interview question): sum + cast
    // Total price of all "Book" items, stored in an Integer called sum.
    // Expected: 65
    // Hint: filter -> mapToDouble -> sum, then cast with (int)
    // ---------------------------------------------------------------
    static Integer exercise2(List<Item> l1) {
        Integer sum = null; // TODO: sum = (int) l1.stream() ...
        return sum;
    }

    // ---------------------------------------------------------------
    // Exercise 3: average
    // Average price of all "Stationery" items.
    // Expected: 3.75
    // Hint: average() gives an OptionalDouble, so finish with orElse(0)
    // ---------------------------------------------------------------
    static Double exercise3(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 4: sorting
    // Names of all items, sorted by price, most expensive first.
    // Expected: [Laptop, Java Guide, Novel, Notebook, Pen]
    // Hint: sorted(Comparator.comparingDouble(...)), and look at reversed()
    // ---------------------------------------------------------------
    static List<String> exercise4(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 5: min
    // Name of the cheapest item.
    // Expected: Pen
    // Hint: min(...) gives an Optional<Item>, so you need to unwrap it
    // ---------------------------------------------------------------
    static String exercise5(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 6: anyMatch
    // Is there any item that costs more than 500?
    // Expected: true
    // Hint: anyMatch(...)
    // ---------------------------------------------------------------
    static Boolean exercise6(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 7: joining
    // All item names in one String, separated by ", ".
    // Expected: Java Guide, Pen, Novel, Notebook, Laptop
    // Hint: map -> collect(Collectors.joining(...))
    // ---------------------------------------------------------------
    static String exercise7(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 8: groupingBy + counting
    // How many items are in each category?
    // Expected: {Book=2, Stationery=2, Electronics=1}  (order may differ)
    // Hint: Collectors.groupingBy(..., Collectors.counting())
    // ---------------------------------------------------------------
    static Map<String, Long> exercise8(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 9: groupingBy + summing
    // Total price for each category.
    // Expected: {Book=65.0, Stationery=7.5, Electronics=900.0}  (order may differ)
    // Hint: Collectors.groupingBy(..., Collectors.summingDouble(...))
    // ---------------------------------------------------------------
    static Map<String, Double> exercise9(List<Item> l1) {
        return null; // TODO
    }

    // ---------------------------------------------------------------
    // Exercise 10 (the other interview question): flatMap
    // Each department has its own list of items. Get the names of ALL
    // items across all departments, sorted by price, cheapest first.
    // Expected: [Pen, Notebook, Novel, Java Guide, Laptop]
    // Hint: flatMap(d -> d.getItems().stream()) -> sorted -> map -> toList
    // ---------------------------------------------------------------
    static List<String> exercise10(List<Department> departments) {
        return null; // TODO
    }

    // ===============================================================
    // You don't need to change anything below this line.
    // ===============================================================

    public static void main(String[] args) {
        check(1, List.of("Java Guide", "Novel", "Laptop"), () -> exercise1(l1));
        check(2, 65, () -> exercise2(l1));
        check(3, 3.75, () -> exercise3(l1));
        check(4, List.of("Laptop", "Java Guide", "Novel", "Notebook", "Pen"), () -> exercise4(l1));
        check(5, "Pen", () -> exercise5(l1));
        check(6, true, () -> exercise6(l1));
        check(7, "Java Guide, Pen, Novel, Notebook, Laptop", () -> exercise7(l1));
        check(8, Map.of("Book", 2L, "Stationery", 2L, "Electronics", 1L), () -> exercise8(l1));
        check(9, Map.of("Book", 65.0, "Stationery", 7.5, "Electronics", 900.0), () -> exercise9(l1));
        check(10, List.of("Pen", "Notebook", "Novel", "Java Guide", "Laptop"), () -> exercise10(departments));

        System.out.println();
        System.out.println(passed + " / 10 passed");
    }

    static int passed = 0;

    static void check(int number, Object expected, Supplier<Object> exercise) {
        String label = "Exercise " + number + ": ";
        try {
            Object actual = exercise.get();
            if (actual == null) {
                System.out.println(label + "not done yet");
            } else if (expected.equals(actual)) {
                System.out.println(label + "PASS");
                passed++;
            } else {
                System.out.println(label + "FAIL - expected " + expected + " but got " + actual);
            }
        } catch (Exception e) {
            System.out.println(label + "ERROR - " + e);
        }
    }
}
