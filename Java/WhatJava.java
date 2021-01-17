/*
 * Licensed Materials - Property of IBM
 * 5725-G22
 * (C) Copyright IBM Corp. 2012, 2019 All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

public class WhatJava {

  final String S = getC();
  final char C = "C".charAt(0);

  private String getC() {
    return String.valueOf(C);
  }

  public static void main(String[] args) {
    System.out.println(new WhatJava().S);
  }
}
