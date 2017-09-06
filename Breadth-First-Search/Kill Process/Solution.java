//Version 0:
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            List<Integer> list = map.getOrDefault(ppid.get(i), new ArrayList<>());
            list.add(pid.get(i));
            map.put(ppid.get(i), list);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int process = queue.poll();
            res.add(process);
            if (map.containsKey(process)) {
                for (int p : map.get(process)) {
                    queue.add(p);
                }
            }
        }
        return res;
    }
}
