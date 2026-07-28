package com.week0;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    UrlEncoder urlEncoder = new UrlEncoder();
    Random random = new Random();
    int num = random.nextInt(1000000);
    System.out.println("This is the string "+ urlEncoder.encode(num));
  }
}
