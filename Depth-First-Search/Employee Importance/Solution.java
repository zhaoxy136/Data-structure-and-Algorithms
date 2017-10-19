class Solution {
    public int getImportance(List<Employee> employees, int id) {
        //construct the map
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return helper(map, id);
    }
    private int helper(Map<Integer, Employee> map, int id) {
        if (!map.containsKey(id)) return 0;
        int importance = map.get(id).importance;
        for (int subId : map.get(id).subordinates) {
            importance += helper(map, subId);
        }
        return importance;
    }
}
