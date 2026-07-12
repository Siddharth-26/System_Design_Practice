package com.week_6;

import java.util.List;

public class GeoHash implements IGeoHash {
  ITrie trie;
  public GeoHash(ITrie trie) {
    this.trie = trie;
  }
  public GeoHash() {
   this.trie = new Trie();
  }

  @Override
  public boolean addCoordinate(float lat, float lon, String name) {
    String hash = this.calculateHash(lat, lon);
    trie.store(hash, name);
    return true;
  }

  @Override
  public List<String> findNearest(float lat, float lon) {
    String hash = this.calculateHash(lat, lon);
    List<String> lst =  trie.find(hash);
    return lst;
  }
  private String calculateHash(float lat, float lon) {
    String latHash = "";
    String longHash = "";
    int minLong = -180;
    int maxLong = 180;
    int i = 0;
    String finalBinary = "";
    while(i<32){
      int mid = (minLong+maxLong)/2;
      if(mid<lon){
        longHash+="1";
        minLong = mid;
      }
      else{
        longHash+="0";
        maxLong = mid;
      }
      i+=1;
    }
    int minLat = -90;
    int maxLat = 90;
    i = 0;
    while(i<32){
      int mid = (minLat+maxLat)/2;
      if(mid<lat){
        latHash+="1";
        minLat = mid;
      }
      else{
        latHash+="0";
        maxLat = mid;
      }
      i+=1;
    }
    i = 0;
    while(i<32){
      finalBinary+=latHash.charAt(i);
      finalBinary+=longHash.charAt(i);
      i+=1;
    }
    return calculateString(finalBinary);
  }
  private String calculateString(String charList){
    int i = 0;
    String base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
    String ans = "";
    while (i + 5 <= 64) {
      String fiveBits = charList.substring(i, i + 5);
      int index = Integer.parseInt(fiveBits, 2);
      ans += base32.charAt(index);
      i += 5;
    }
    // Handle remaining bits (64 % 5 = 4 remaining bits)
    if (i < 64) {
      String remaining = charList.substring(i);
      // Pad with zeros on the right to make 5 bits
      while (remaining.length() < 5) {
        remaining += "0";
      }
      int index = Integer.parseInt(remaining, 2);
      ans += base32.charAt(index);
    }
    return ans;
  }
}
