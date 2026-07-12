package com.week_6;

import java.util.List;

public interface ITrie {
  public boolean store(String hash, String name);
  public List<String> find(String hash);
}
