import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * flatMap exercises: solutions.
 *
 * Answers for FlatMapExercises.java. Try the exercises first!
 *
 * Run it:  java FlatMapSolutions.java   (every exercise should PASS)
 *
 * ---------------------------------------------------------------
 * What flatMap does, in one picture
 * ---------------------------------------------------------------
 *
 *   departments:   [ [Java Guide, Novel], [Pen, Notebook], [Laptop] ]
 *
 *   map(d -> d.getItems())
 *       -> a stream of 3 LISTS        (boxes still closed)
 *
 *   flatMap(d -> d.getItems().stream())
 *       -> a stream of 5 ITEMS        (boxes opened, items poured out)
 *
 * Use flatMap whenever each element holds a list, and you want to work
 * with what is INSIDE those lists. The lambda must return a stream.
 */
public class FlatMapSolutions {

    // ---------------------------------------------------------------
    // The data
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

    static class Store {
        private String name;
        private List<Department> departments;

        Store(String n, List<Department> d) { name = n; departments = d; }

        public String getName() { return name; }
        public List<Department> getDepartments() { return departments; }
    }

    // A list of lists of numbers
    static List<List<Integer>> numbers = List.of(
        List.of(1, 2),
        List.of(3, 4),
        List.of(5));

    // A list of sentences
    static List<String> sentences = List.of(
        "java is fun",
        "streams are fun",
        "java streams");

    // Departments, each with its own list of items
    static Department reading = new Department("Reading", List.of(
        new Item("Java Guide", "Book", 40.0),
        new Item("Novel", "Book", 25.0)));

    static Department office = new Department("Office", List.of(
        new Item("Pen", "Stationery", 2.5),
        new Item("Notebook", "Stationery", 5.0)));

    static Department tech = new Department("Tech", List.of(
        new Item("Laptop", "Electronics", 900.0)));

    static List<Department> departments = List.of(reading, office, tech);

    // Stores, each with its own list of departments (two levels deep)
    static List<Store> stores = List.of(
        new Store("Downtown", List.of(reading, office)),
        new Store("Airport", List.of(tech)));

    // ---------------------------------------------------------------
    // Exercise 1 (warm-up): flatten a list of lists
    // Turn [[1, 2], [3, 4], [5]] into one list.
    // Expected: [1, 2, 3, 4, 5]
    // Hint: flatMap(list -> list.stream()) -> toList
    // ---------------------------------------------------------------
    static List<Integer> exercise1(List<List<Integer>> numbers) {
        return numbers.stream()
            .flatMap(list -> list.stream())   // or: flatMap(List::stream)
            .toList();
    }

    // ---------------------------------------------------------------
    // Exercise 2: flatten, then sum
    // Add up every number in [[1, 2], [3, 4], [5]].
    // Expected: 15
    // Hint: flatMap -> mapToInt(Integer::intValue) -> sum
    // ---------------------------------------------------------------
    static Integer exercise2(List<List<Integer>> numbers) {
        return numbers.stream()
            .flatMap(list -> list.stream())
            .mapToInt(Integer::intValue)      // Integer -> int, so sum() works
            .sum();
    }

    // ---------------------------------------------------------------
    // Exercise 3: departments -> item names
    // Names of all items across all departments, in their original order.
    // Expected: [Java Guide, Novel, Pen, Notebook, Laptop]
    // Hint: flatMap(d -> d.getItems().stream()) -> map -> toList
    // ---------------------------------------------------------------
    static List<String> exercise3(List<Department> departments) {
        return departments.stream()
            .flatMap(d -> d.getItems().stream())   // Department -> its Items
            .map(Item::getName)
            .toList();
    }

    // ---------------------------------------------------------------
    // Exercise 4 (interview style): flatMap + sum + cast
    // Total price of every item in every department, stored in an
    // Integer called sum.
    // Expected: 972   (972.5 with the decimal cut off)
    // Hint: flatMap -> mapToDouble -> sum, then cast with (int)
    // ---------------------------------------------------------------
    static Integer exercise4(List<Department> departments) {
        Integer sum = (int) departments.stream()
            .flatMap(d -> d.getItems().stream())   // all 5 items
            .mapToDouble(Item::getPrice)
            .sum();                                // 972.5 -> (int) -> 972
        return sum;
    }

    // ---------------------------------------------------------------
    // Exercise 5: flatMap, then filter
    // Names of all items across all departments that cost more than 20.
    // Expected: [Java Guide, Novel, Laptop]
    // Hint: flatten first, then filter like you did in StreamExercises
    // ---------------------------------------------------------------
    static List<String> exercise5(List<Department> departments) {
        return departments.stream()
            .flatMap(d -> d.getItems().stream())
            .filter(i -> i.getPrice() > 20)
            .map(Item::getName)
            .toList();
    }

    // ---------------------------------------------------------------
    // Exercise 6: flatMap + count
    // How many items are there across all departments?
    // Expected: 5
    // Hint: count() returns a long, which is why this method returns Long
    // ---------------------------------------------------------------
    static Long exercise6(List<Department> departments) {
        return departments.stream()
            .flatMap(d -> d.getItems().stream())
            .count();
    }

    // ---------------------------------------------------------------
    // Exercise 7 (classic interview question): sentences -> words
    // Split every sentence into words, and keep each word only once.
    // Expected: [java, is, fun, streams, are]
    // Hint: flatMap(s -> Arrays.stream(s.split(" "))) -> distinct -> toList
    // ---------------------------------------------------------------
    static List<String> exercise7(List<String> sentences) {
        return sentences.stream()
            .flatMap(s -> Arrays.stream(s.split(" ")))   // sentence -> its words
            .distinct()                                    // drop repeated words
            .toList();
    }

    // ---------------------------------------------------------------
    // Exercise 8: flatMap + max
    // Name of the most expensive item across all departments.
    // Expected: Laptop
    // Hint: max(...) gives an Optional, so unwrap it with orElse("")
    // ---------------------------------------------------------------
    static String exercise8(List<Department> departments) {
        return departments.stream()
            .flatMap(d -> d.getItems().stream())
            .max(Comparator.comparingDouble(Item::getPrice))   // Optional<Item>
            .map(Item::getName)                                 // Optional<String>
            .orElse("");                                        // unwrap it
    }

    // ---------------------------------------------------------------
    // Exercise 9 (common follow-up): keep the department name
    // For every item, build "Department: Item".
    // Expected: [Reading: Java Guide, Reading: Novel, Office: Pen,
    //            Office: Notebook, Tech: Laptop]
    // Hint: after a normal flatMap you lose the department. So do the
    //       map INSIDE the flatMap, while you still have d:
    //       flatMap(d -> d.getItems().stream().map(i -> ...))
    // ---------------------------------------------------------------
    static List<String> exercise9(List<Department> departments) {
        // The map is INSIDE the flatMap, so d (the department) is still
        // available when we build each string.
        return departments.stream()
            .flatMap(d -> d.getItems().stream()
                           .map(i -> d.getName() + ": " + i.getName()))
            .toList();
    }

    // ---------------------------------------------------------------
    // Exercise 10 (hardest): two levels deep
    // Stores have departments, departments have items.
    // Names of all items in all stores.
    // Expected: [Java Guide, Novel, Pen, Notebook, Laptop]
    // Hint: flatMap twice: stores -> departments, then departments -> items
    // ---------------------------------------------------------------
    static List<String> exercise10(List<Store> stores) {
        return stores.stream()
            .flatMap(s -> s.getDepartments().stream())   // Store -> Departments
            .flatMap(d -> d.getItems().stream())         // Department -> Items
            .map(Item::getName)
            .toList();
    }

    // ===============================================================
    // You don't need to change anything below this line.
    // ===============================================================

    public static void main(String[] args) {
        check(1, List.of(1, 2, 3, 4, 5), () -> exercise1(numbers));
        check(2, 15, () -> exercise2(numbers));
        check(3, List.of("Java Guide", "Novel", "Pen", "Notebook", "Laptop"), () -> exercise3(departments));
        check(4, 972, () -> exercise4(departments));
        check(5, List.of("Java Guide", "Novel", "Laptop"), () -> exercise5(departments));
        check(6, 5L, () -> exercise6(departments));
        check(7, List.of("java", "is", "fun", "streams", "are"), () -> exercise7(sentences));
        check(8, "Laptop", () -> exercise8(departments));
        check(9, List.of("Reading: Java Guide", "Reading: Novel", "Office: Pen",
                         "Office: Notebook", "Tech: Laptop"), () -> exercise9(departments));
        check(10, List.of("Java Guide", "Novel", "Pen", "Notebook", "Laptop"), () -> exercise10(stores));

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
