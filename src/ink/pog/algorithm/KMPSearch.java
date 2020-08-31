package ink.pog.algorithm;

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

import java.util.Arrays;

public class KMPSearch {


    public int[] prefix(String pattern) {
        int len = pattern.length();
        int prefix[] = new int[len];
        prefix[0] = -1;
        for (int i = 1, j = 0; i < len; i++) {
            // AABCAAB
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
                if (j == -1) {
                    j = 0;
                }
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }

        return prefix;
    }

    public void kmpSearch(String text, String pattern){
        int n = text.length();
        int m = pattern.length();
        int prefix[] = prefix(pattern);

        for(int i = 0, j = 0; i < n; i ++){
            while (j > 0 && text.charAt(i) != pattern.charAt(j)){
                j = prefix[j - 1];
                if(j == -1){
                    j = 0;
                }
            }
            if(text.charAt(i) == pattern.charAt(j)){
                j++;
            }
            if(j == m && text.charAt(i) == pattern.charAt( j - 1)){
                System.out.println("pattern at " + (i - j + 1));
                j = prefix[j - 1];
            }
        }

    }

    public static void main(String[] args) {
        String pattern = "AABCAAB";
        String text = "AABDAABCAABBAACAABCAAB";
        KMPSearch kmp = new KMPSearch();
        kmp.kmpSearch(text,pattern);

    }
}
