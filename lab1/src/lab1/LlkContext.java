package lab1;

import java.util.LinkedList;

public class LlkContext {
    private LinkedList<int[]> llkContext = new LinkedList();

    public boolean wordInContext(int[] word) {
        for (int[] word1 : this.llkContext) {
            int len;
            if (word.length != word1.length) continue;
            for (len = 0; len < word.length && word[len] == word1[len]; ++len) {
            }
            if (len != word.length) continue;
            return true;
        }
        return false;
    }

    public int[] getWord(int index) {
        if (this.llkContext == null) {
            return null;
        }
        if (index >= this.llkContext.size() || index < 0) {
            return null;
        }
        return this.llkContext.get(index);
    }

    public int minLengthWord() {
        int minlen = 99;
        for (int[] word : this.llkContext) {
            if (minlen <= word.length) continue;
            minlen = word.length;
        }
        return minlen;
    }

    public int calcWords() {
        return this.llkContext.size();
    }

    public boolean addWord(int[] word) {
        int len = word.length;
        for (int[] tmp : this.llkContext) {
            int ii;
            if (tmp.length != len) continue;
            for (ii = 0; ii < tmp.length && tmp[ii] == word[ii]; ++ii) {
            }
            if (ii != tmp.length) continue;
            return false;
        }
        this.llkContext.add(word);
        return true;
    }
}