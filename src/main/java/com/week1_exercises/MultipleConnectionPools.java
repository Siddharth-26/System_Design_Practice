package com.week1_exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultipleConnectionPools {
  BlockingQueue<Connection> queue1 = new LinkedBlockingQueue<>();
  BlockingQueue<Connection> queue2 = new LinkedBlockingQueue<>();
  public MultipleConnectionPools() {
    try{
      for(int i=0;i<10;i++){
        queue1.add(DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb", "sid", "sidpass"));
        queue2.add(DriverManager.getConnection("jdbc:mysql://localhost:3308/testdb", "sid", "sidpass"));
      }
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
  public Connection getConnectionDB1()  {
    try {
      return queue1.take();
    }
    catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public Connection getConnectionDB2() {
    try {
      return queue2.take();
    }
    catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public void returnConnection1(Connection connection){
    try {
      queue1.put(connection);
    }
    catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
  public void returnConnection2(Connection connection){
    try {
      queue2.put(connection);
    }
    catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
