package com.week_6;

import java.util.Collections;
import java.util.List;

public class Trie implements ITrie {
  Node root = new Node();
  @Override
  public boolean store(String hash, String name) {
    int i = 0;
    Node node = root;
    while(i<hash.length()) {
      String curr = String.valueOf(hash.charAt(i));
      if(node.getNode(curr) == null) {
        Node newnode = new Node();
        node.addNode(curr, newnode);
        node = newnode;
      }
      else{
        node = node.getNode(curr);
      }
      i+=1;
    }
    node.addName(name);
    return true;
  }

  @Override
  public List<String> find(String hash) {
    int i = 0;
    Node node = root;
    Node lastNamedNode = null;

    while(i < hash.length()) {
      String curr = String.valueOf(hash.charAt(i));
      if(node.getNode(curr) == null) {
        break;
      }
      node = node.getNode(curr);
      if(!node.getNames().isEmpty()) {
        lastNamedNode = node;
      }
      i += 1;
    }

    // If we found a node with names stored, return those
    if(lastNamedNode != null) {
      return lastNamedNode.getNames();
    }

    // Otherwise collect all names reachable from the deepest matched node
    List<String> results = new java.util.ArrayList<>();
    collectAllNames(node, results);
    return results;
  }

  private void collectAllNames(Node node, List<String> results) {
    if(node == null) return;
    results.addAll(node.getNames());
    for(Node child : node.map.values()) {
      collectAllNames(child, results);
    }
  }
}
