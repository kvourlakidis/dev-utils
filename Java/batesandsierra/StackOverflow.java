package batesandsierra;

public class StackOverflow {

    StackOverflow() {
        System.out.println("Calling StackOverflow()");
        final StackOverflow so = new StackOverflow();
    }

    public static void main(String[] args) {
        try {
            new StackOverflow();
        } catch (StackOverflowError error) {
            final StackTraceElement[] stackTrace = error.getStackTrace();
            System.out.println();
            System.out.println("Stack height: " + stackTrace.length);
        }
    }
}
