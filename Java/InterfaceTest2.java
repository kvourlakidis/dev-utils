import static java.lang.System.out;

class InterfaceTest2 {
    interface Animal { void speak(); }
    interface Doglike extends Animal { default void speak() { out.println("Woof"); }; }
    interface Catlike extends Animal { default void speak() { out.println("Miaow"); }; }

    static class Hybrid implements Doglike, Catlike {
        @Override public void speak() { out.println("Java is not purrfect"); }
    }

    public static void main(String[] args) {
        Animal animal = new Hybrid();
        Doglike dog = new Hybrid();
        Catlike cat = new Hybrid();
        animal.speak();
        dog.speak();
        cat.speak();
    }
}