public class TryWithResourcesDemo {

    // Anything with a close() method can go inside try ( ... )
    static class Resource implements AutoCloseable {
        private final int number;

        Resource(int number) {
            this.number = number;
            System.out.println("Open " + number);
        }

        @Override
        public void close() {
            System.out.println("Close " + number);
        }
    }

    public static void main(String[] args) {

        System.out.println("--- Example 1 ---");
        try (Resource one = new Resource(1);
             Resource two = new Resource(2)) {
            System.out.println("Using 1 and 2");
        }   // Java closes them here: 2 first, then 1

        System.out.println();
        System.out.println("--- Example 2: with an error ---");
        try (Resource one = new Resource(1);
             Resource two = new Resource(2)) {
            System.out.println("Using 1 and 2");
            throw new RuntimeException("Something went wrong");
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
