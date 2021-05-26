/*
 * Licensed Materials - Property of IBM
 * 5725-G22
 * (C) Copyright IBM Corp. 2012, 2021 All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package misc;

public class StupidPenisFace {
  public static void main(String[] args) {
    //  f3(f2(f1(arg2, arg4)), f2(f1(arg1, arg3)))
    // arguments: arg1, arg2, arg3, arg4
    // functions: f1(2), f2(1), f3(2)
    // target: int f4(arg1, arg2, arg3, arg4)
    // f1(arg1, arg1) .. f1(arg4, arg4) > store
  }

  static int target(int arg1, int arg2, int arg3, int arg4) {
    final int f1r1 = f1(arg2, arg4);
    final int f1r2 = f1(arg1, arg3);
    final int f2r1 = f2(f1r1);
    final int f2r2 = f2(f1r2);
    final int f3r1 = f3(f2r1, f2r2);
    return f3r1;
  }

  static int f1(int arg1, int arg2) {
    return arg1 - arg2;
  }

  static int f2(int arg1) {
    return arg1 > 0 ? arg1 : -arg1;
  }

  static int f3(int arg1, int arg2) {
    return arg1 >= arg2 ? arg1 : arg2;
  }

  static int distance(int x, int y, int offsetX, int offsetY) {
    return Math.max(Math.abs(y - offsetY), Math.abs(x - offsetX));
  }
}
