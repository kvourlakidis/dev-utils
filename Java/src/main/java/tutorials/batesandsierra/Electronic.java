interface Device {
  public void doIt();
}

public class Electronic implements Device {
  public void doIt() {}
}

abstract class Phone1 extends Electronic {}

abstract class Phone2 extends Electronic {
  public void doIt(int x) {}
}

class Phone3 extends Electronic implements Device { // implements Device is implied
  public void doStuff() {}
}
