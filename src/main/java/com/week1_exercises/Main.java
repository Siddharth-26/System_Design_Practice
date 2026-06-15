package com.week1_exercises;

//import java.sql.Connection;
//import java.sql.DriverManager;

public class  Main {

    static long startTime = System.currentTimeMillis();
    public static void main(String[] args) {
//      ConnectionPoolBlocking connectionPool = new ConnectionPoolBlocking();
//      try {
//        for (int x = 0; x < 1000; x++) {
//          x += 1;
//          Connection conn = connectionPool.take();
//          Thread.sleep(200);
//          connectionPool.put(conn);
//        }
//        System.out.println(System.currentTimeMillis() - startTime);
//      }
//      catch (Exception e) {
//        System.out.println(e.getMessage());
//      }
      ShardingDriver shardingDriver = new ShardingDriver();
      try {
        shardingDriver.execute();
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
}
