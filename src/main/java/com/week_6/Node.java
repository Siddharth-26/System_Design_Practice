package com.week_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {
  HashMap<String, Node> map = new HashMap<>();
  List<String> names = new ArrayList<>();

  public boolean addNode(String s, Node node) {
    map.put(s, node);
    return true;
  }
  public Node getNode(String s) {
    return map.get(s);
  }
  public boolean addName(String name) {
    this.names.add(name);
    return true;
  }
  public List<String> getNames() {
    return names;
  }
}
