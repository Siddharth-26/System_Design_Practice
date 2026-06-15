package com.week1_exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShardingAPI {
  MultipleConnectionPools multipleConnectionPools = new MultipleConnectionPools();

  public void storeUser(int userId) throws SQLException {
      int hash = this.getHash(userId);
    System.out.println(hash);
    Connection temp = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb", "sid", "sidpass");
    temp.prepareStatement("DROP TABLE USER").execute();
    temp.prepareStatement("CREATE TABLE USER (USER_ID INT  PRIMARY KEY)").execute();
    Connection temp2 = DriverManager.getConnection("jdbc:mysql://localhost:3308/testdb", "sid", "sidpass");
    temp2.prepareStatement("DROP TABLE USER").execute();
    temp2.prepareStatement("CREATE TABLE USER (USER_ID INT PRIMARY KEY)").execute();

      if(hash==1){
        // Store in DB1.
        Connection connection = multipleConnectionPools.getConnectionDB1();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO USER VALUES (?)");
        pstmt.setInt(1, userId);
        pstmt.executeUpdate();
        multipleConnectionPools.returnConnection1(connection);
        System.out.println(userId + "stored in the DB1");
      }
      else{
        Connection connection = multipleConnectionPools.getConnectionDB2();
        // Store in DB2.
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO USER VALUES (?)");
        pstmt.setInt(1, userId);
        pstmt.executeUpdate();
        multipleConnectionPools.returnConnection2(connection);
        System.out.println(userId + "stored in the DB2");

      }
  }
  public int getHash(int userId){
    return userId%2;
  }

}
