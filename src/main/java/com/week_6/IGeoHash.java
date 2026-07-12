package com.week_6;

import java.util.List;

public interface IGeoHash {
  public boolean addCoordinate(float lat, float lon, String name);
  public List<String> findNearest(float lat, float lon);
}
