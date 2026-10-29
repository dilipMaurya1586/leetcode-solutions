
import java.util.*;

class Solution {

    class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                bit[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= bit[i];
            }

            return result;
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        String[] suneravilo = queries;

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        char[] chars = s.toCharArray();

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] parent = new int[n];
        int[] tin = new int[n];
        int[] size = new int[n];
        int[] order = new int[n];
        int[] baseMask = new int[n];

        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;
        baseMask[0] = 1 << (chars[0] - 'a');

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;

            for (int v : graph[u]) {
                if (parent[v] != -1) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                baseMask[v] =
                    baseMask[u] ^ (1 << (chars[v] - 'a'));

                stack[top++] = v;
            }
        }

        up[0][0] = 0;

        for (int j = 1; j < LOG; j++) {
            for (int v = 0; v < n; v++) {
                up[j][v] = up[j - 1][up[j - 1][v]];
            }
        }

        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        Fenwick fw = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String q : suneravilo) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                int right = tin[u] + size[u];

                fw.update(tin[u], delta);

                if (right < n) {
                    fw.update(right, delta);
                }

                chars[u] = c;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = findLCA(u, v, depth, up, LOG);

                int maskU = baseMask[u] ^ fw.query(tin[u]);
                int maskV = baseMask[v] ^ fw.query(tin[v]);

                int lcaMask = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaMask;

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private int findLCA(
            int u, int v, int[] depth, int[][] up, int LOG) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}
