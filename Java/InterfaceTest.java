import static java.lang.System.out;

class InterfaceTest {
    interface Animal { void speak(); }
    interface Doglike extends Animal { default void speak() { out.println("Woof"); }; }
    interface Catlike extends Animal { default void speak() { out.println("Miaow"); }; }

    static class Hybrid implements Doglike, Catlike {
        public final Doglike asDog() { return new Doglike() {}; }
        public final Catlike asCat() { return new Catlike() {}; }
        @Override public void speak() { out.println("Java is not purrfect"); }
    }

    public static void main(String[] args) {
        new Hybrid().asDog().speak();
        new Hybrid().asCat().speak();
        new Hybrid().speak();
    }
}