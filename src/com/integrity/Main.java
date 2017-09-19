package com.integrity;

import com.integrity.model.Trie;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static Trie trie = new Trie();

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Enter path to file");
            return;
        }
        String fileName = args[0];
//        String fileName = "words.txt";

        List<String> dictionary = readFromFile(fileName);
        System.out.println("Total number of words in file: " + dictionary.size());
        Collections.sort(dictionary, (o1, o2) -> o2.length() - o1.length());

        for (String word : dictionary) {
            trie.insert(word);
        }


        // Find all concatenated words
        List<String> longestConcatWords = dictionary.stream()
                .filter(word -> isMadeOfWords(word, true))
                .collect(Collectors.toList());

        System.out.println("Longest concatenated word is: " + longestConcatWords.get(0) + " size: " + longestConcatWords.get(0).length());
        System.out.println("The second longest concatenated word is: " + longestConcatWords.get(1) + " size: " + longestConcatWords.get(1).length());
        System.out.println("The total count of concatenated words in the file: " + longestConcatWords.size());

    }


    public static boolean isMadeOfWords(String word, boolean concatWord) {
        // Remove the word so that the word is not matched to itself to find the longest word
        if (concatWord) {
            trie.delete(word);
        }
        // Loop over the length of the word
        for (int i = 0; i < word.length(); i++) {
            String firsPart = word.substring(0, i + 1);
            boolean search = trie.search(firsPart);
            if (search) {
                String SecondPart = word.substring(i + 1, word.length());
                boolean requiredWord = isMadeOfWords(SecondPart, false);
                if (i + 1 == word.length() || requiredWord) {
                    return true;
                }
            }
        }
        if (concatWord) {
            trie.insert(word);
        }
        return false;
    }

    public static List<String> readFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
