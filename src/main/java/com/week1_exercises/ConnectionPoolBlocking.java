package com.week1_exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolBlocking {
  private BlockingQueue<Connection> connections = new LinkedBlockingQueue<>();
  static String url = "jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false";
  static String password = "sidpass";
  static String username = "sid";
  public ConnectionPoolBlocking(){
    try {
      for (int x = 0; x < 100; x++) {
        Connection conn = DriverManager.getConnection(url, username, password);
        this.connections.add(conn);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  public Connection take(){
    try {
      return this.connections.take();
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public void put(Connection connection){
    try {
      this.connections.put(connection);
    }
    catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
