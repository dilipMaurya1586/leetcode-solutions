class Solution {
    int[] parent = new int[26];

    public boolean equationsPossible(String[] equations) {

        // Step 1: Each variable starts in its own group
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Step 2: Process all equality equations
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                int a = eq.charAt(0) - 'a';
                int b = eq.charAt(3) - 'a';

                union(a, b);
            }
        }

        // Step 3: Check all inequality equations
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                int a = eq.charAt(0) - 'a';
                int b = eq.charAt(3) - 'a';

                if (find(a) == find(b)) {
                    return false;
                }
            }
        }

        return true;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}