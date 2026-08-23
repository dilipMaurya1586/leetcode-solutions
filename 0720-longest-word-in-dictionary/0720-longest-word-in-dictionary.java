class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode node = root;
        for(char ch : word.toCharArray()) {
            int index = ch - 'a';
            if(node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    private boolean canBuild(String word) {
        TrieNode node = root;
        for(char ch : word.toCharArray()) {
            int index = ch - 'a';
            if(node.children[index] == null) {
                return false;
            }
            node = node.children[index];
            if(!node.isEnd) {
                return false;
            }
        }
        return true;
    }

    public String longestWord(String[] words) {

        for(String word : words) {
            insert(word);
        }
        String answer = "";
        for(String word : words) {
            if(canBuild(word)) {
                if(word.length() > answer.length()) {
                    answer = word;
                }
                else if (word.length() == answer.length() && word.compareTo(answer) < 0) {
                    answer = word;
                }
            }
        }
        return answer;
    }
}