package com.integrity.model;

import java.util.Collection;
import java.util.LinkedList;

public class Node {
    protected char character;
    protected boolean endMarker;
    protected int prefix;  // Number of words having prefix as this character
    protected Collection<Node> childNode;

    public Node(char character) {
        childNode = new LinkedList<Node>();
        endMarker = false;
        this.character = character;
        prefix = 1;
    }

    // subNode function returns the child node of a given node having character c
    public Node subNode(char ch) {
        if (this.childNode != null) {
            for (Node child : this.childNode) {
                if (child.character == ch) {
                    return child;
                }
            }
        }
        return null;
    }

}