package tutorials.general;

class InterfaceTest2 {
  interface Animal {
    void speak();
  }

  interface Doglike extends Animal {
    default void speak() {
      System.out.println("Woof");
    }
  }

  interface Catlike extends Animal {
    default void speak() {
      System.out.println("Miaow");
    }
  }

  static class Hybrid implements Doglike, Catlike {
    @Override
    public void speak() {
      System.out.println("Java is not purrfect");
    }
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
