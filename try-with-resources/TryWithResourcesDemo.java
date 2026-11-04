public class TryWithResourcesDemo {

    // A resource is any class with a close() method (it implements AutoCloseable)
    static class DatabaseConnection implements AutoCloseable {
        DatabaseConnection() {
            System.out.println("1. Connection opened");
        }
        void query(String sql) {
            System.out.println("2. Running: " + sql);
        }
        @Override
        public void close() {
            System.out.println("3. Connection closed");
        }
    }

    static class Resource implements AutoCloseable {
        private final String name;
        private final boolean failOnClose;
        Resource(String name, boolean failOnClose) {
            this.name = name;
            this.failOnClose = failOnClose;
            System.out.println("open " + name);
        }
        @Override
        public void close() {
            System.out.println("close " + name);
            if (failOnClose) throw new IllegalStateException("could not close " + name);
        }
    }

    public static void main(String[] args) {

        System.out.println("--- Example 1: everything works ---");
        try (DatabaseConnection conn = new DatabaseConnection()) {
            conn.query("SELECT * FROM accounts");
        }   // Java calls conn.close() here for you

        System.out.println();
        System.out.println("--- Example 2: an error happens ---");
        try (DatabaseConnection conn = new DatabaseConnection()) {
            conn.query("SELECT * FROM accounts");
            throw new RuntimeException("query failed");
        } catch (RuntimeException e) {
            System.out.println("4. Caught: " + e.getMessage());
        }

        System.out.println();
        System.out.println("--- Example 3: two resources ---");
        try (Resource connection = new Resource("connection", false);
             Resource statement = new Resource("statement", false)) {
            System.out.println("using both");
        }

        System.out.println();
        System.out.println("--- Example 4: the code fails AND close() fails ---");
        try (Resource connection = new Resource("connection", true)) {
            throw new RuntimeException("query failed");
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Suppressed: " + e.getSuppressed()[0].getMessage());
        }
    }
}
