package ink.pog.algorithm.leetcode;

import ink.pog.algorithm.LinkList;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DFSAndBFS {
    List<String> res = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    String[] alphabets = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {


        if (digits.length() == 0) {
            return res;
        }
        letterCombinationsDFS(0, digits);
        return res;
    }

    public void letterCombinationsDFS(int index, String digits) {

        if (index == digits.length()) {
            res.add(temp.toString());
            return;
        }

        if (temp.indexOf(String.valueOf(digits.charAt(index))) == -1) {

            String map = alphabets[digits.charAt(index)-'2'];
            for (char c : map.toCharArray()) {
                temp.append(c);
                letterCombinationsDFS(index + 1, digits);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        DFSAndBFS method = new DFSAndBFS();
        method.letterCombinations("234");
        System.out.println(method.res);
        LinkedList<Object> list = new LinkedList<>();
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put()
    }

}
