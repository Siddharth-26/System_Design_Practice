package com.week1_exercises;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class ShardingDriver {
  public void execute() throws SQLException {
    int i = 1;
    ShardingAPI shardingAPI = new ShardingAPI();
    while(i<1000){
      int userId = ThreadLocalRandom.current().nextInt(1, 1000);
      try {
        shardingAPI.storeUser(userId);
      }
      catch (Exception e) {
        System.out.println(i);
      }
      i+=1;
    }
    System.out.println("Completed");
  }
}
