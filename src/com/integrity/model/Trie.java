package com.integrity.model;

public class Trie {
    private Node head;

    public Trie() {
        head = new Node('#');  // Root indicated by Hash
    }

    public void insert(String s) {
        Node current = head;
        if (s.isEmpty())
            current.endMarker = true;
        for (int i = 0; i < s.length(); i++) {
            Node child = current.subNode(s.charAt(i));
            if (child != null) {
                current = child;
                current.prefix++;
            } else {
                current.childNode.add(new Node(s.charAt(i)));
                current = current.subNode(s.charAt(i));
            }
            if (i == s.length() - 1)
                current.endMarker = true;
        }
    }

    public boolean search(String s) {
        Node current = head;
        while (current != null) {
            for (int i = 0; i < s.length(); i++) {
                if (current.subNode(s.charAt(i)) == null)
                    return false;
                else
                    current = current.subNode(s.charAt(i));
            }

            if (current.endMarker == true)
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean delete(String s) {
        if (search(s))   // Check for string is already present, if not it cannot be deleted
        {
            Node current = head;
            Node temp;  // To keep track of parent
            while (current != null) {
                for (int i = 0; i < s.length(); i++) {
                    temp = current;
                    current = current.subNode(s.charAt(i));
                    current.prefix--;
                    if (current.prefix == 0) {
                        temp.childNode.remove(current);
                        break;
                    }
                }
                current.endMarker = false;
                return true;
            }
        }
        return false;
    }
}
