//Version 0:
public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> res = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int sum = i + map.get(list2[i]);
                if (sum < minSum) {
                    minSum = sum;
                    res = new ArrayList<>();
                    res.add(list2[i]);
                } else if (sum == minSum) {
                    res.add(list2[i]);
                }
            }
        }
        String[] restaurants = new String[res.size()];
        for (int i = 0; i < restaurants.length; i++) {
            restaurants[i] = res.get(i);
        }
        return restaurants;
    }
}
