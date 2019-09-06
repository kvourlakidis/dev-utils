/*
 * Licensed Materials - Property of IBM
 * 5725-G22
 * (C) Copyright IBM Corp. 2012, 2019 All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.i2group.disco.test.tools;

public class PrintlnTest {
  public static void main(String[] args) {
    // the exception comes from the use of accessing the main thread
    /*
        Notice the user of print as opposed to println:
        the '\b' char cannot go over the new line char.
    */
    System.out.print("Start[          ]");
    System.out.flush(); // the flush method prints it to the screen

    // 11 '\b' chars: 1 for the ']', the rest are for the spaces
    System.out.print("\b\b\b\b\b\b\b\b\b\b\b");
    System.out.flush();
    try {
      Thread.sleep(500); // just to make it easy to see the changes
      for (int i = 0; i < 10; i++) {
        System.out.print("."); // overwrites a space
        System.out.flush();
        Thread.sleep(100);
      }

      System.out.print("] Done\n"); // overwrites the ']' + adds chars
      System.out.flush();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
