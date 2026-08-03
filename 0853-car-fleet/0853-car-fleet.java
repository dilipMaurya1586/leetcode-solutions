import java.util.*;

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {

        int n = position.length;

        // position -> speed
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(position[i], speed[i]);
        }

        // Sort positions in decreasing order
        Integer[] positions = new Integer[n];

        for (int i = 0; i < n; i++) {
            positions[i] = position[i];
        }

        Arrays.sort(positions, Collections.reverseOrder());

        int fleets = 0;
        double lastTime = 0;

        for (int pos : positions) {

            double time = (double) (target - pos) / map.get(pos);

            if (time > lastTime) {
                fleets++;
                lastTime = time;
            }
        }

        return fleets;
    }
}