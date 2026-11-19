# Java Streams exercises

10 practice exercises, from easy to interview-level, all using the same small `Item` list.

## Run it

Java 17 or newer, no build tool needed:

```bash
java StreamExercises.java
```

Each exercise prints `PASS`, `FAIL` (with the expected answer) or `not done yet`.

## How to practice

1. Open `StreamExercises.java`.
2. Pick an exercise and replace `return null;` with your stream.
3. Run the file and check your result.
4. Stuck? Look at `StreamSolutions.java`, but try first.

## The data

```java
List<Item> l1 = List.of(
    new Item("Java Guide", "Book", 40.0),
    new Item("Pen", "Stationery", 2.5),
    new Item("Novel", "Book", 25.0),
    new Item("Notebook", "Stationery", 5.0),
    new Item("Laptop", "Electronics", 900.0));
```

`Item`'s fields are `private`, so you read them through the getters `getName()`, `getCategory()` and `getPrice()`.

## The exercises

| # | Task | What it practices |
| --- | --- | --- |
| 1 | Names of items over 10 | `filter`, `map`, `toList` |
| 2 | Total price of books, as an `Integer` **(interview question)** | `mapToDouble`, `sum`, `(int)` cast |
| 3 | Average price of stationery | `average`, `orElse` |
| 4 | Names sorted by price, highest first | `sorted`, `Comparator`, `reversed` |
| 5 | Name of the cheapest item | `min`, `Optional` |
| 6 | Any item over 500? | `anyMatch` |
| 7 | All names in one string | `Collectors.joining` |
| 8 | Number of items per category | `groupingBy`, `counting` |
| 9 | Total price per category | `groupingBy`, `summingDouble` |
| 10 | All items across departments, cheapest first **(interview question)** | `flatMap` |

## More practice: flatMap

`FlatMapExercises.java` has 10 more exercises that only practice `flatMap`, from flattening a list of lists up to going two levels deep. Run it the same way:

```bash
java FlatMapExercises.java
```

Answers are in `FlatMapSolutions.java`.

| # | Task |
| --- | --- |
| 1 | Flatten `[[1, 2], [3, 4], [5]]` into one list |
| 2 | Sum every number in the nested list |
| 3 | All item names across all departments |
| 4 | Total price across all departments, as an `Integer` **(interview style)** |
| 5 | Items over 20 across all departments |
| 6 | Count all items across all departments |
| 7 | Split sentences into unique words **(classic interview question)** |
| 8 | Most expensive item across all departments |
| 9 | Build `"Department: Item"` for every item |
| 10 | Stores → departments → items (two levels deep) |

Tip: if VS Code suggests something like `StreamSolutions.Item::getName`, pick plain `Item::getName` instead. Each file has its own `Item` class.

## Cheat sheet

| Operation | What it does |
| --- | --- |
| `filter(i -> ...)` | Keeps only the items where the condition is true |
| `map(Item::getName)` | Turns each item into something else (here, its name) |
| `mapToDouble(Item::getPrice)` | Turns each item into a `double`, so you can use `sum()` and `average()` |
| `flatMap(d -> d.getItems().stream())` | Turns a stream of lists into one flat stream |
| `sorted(Comparator.comparingDouble(Item::getPrice))` | Sorts, cheapest first. Add `.reversed()` for highest first |
| `min(...)` / `max(...)` | Returns an `Optional`, because the list could be empty |
| `anyMatch(i -> ...)` | `true` if at least one item matches |
| `toList()` | Collects the result into a `List` |
| `collect(Collectors.groupingBy(...))` | Groups items into a `Map` by a key, like category |

`Item::getPrice` is a short way of writing `i -> i.getPrice()`.
