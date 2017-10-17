class Solution {
    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        int res = 0;
        int i = -1;
        for (String s : ops) {
            int num = 0;
            if (s.equals("D")) {
                //check index out of bound
                num = 2 * list.get(i);
                list.add(num);
                i++;
            } else if (s.equals("C")) {
                num = -1 * list.get(i);
                list.remove(i--);
            } else if (s.equals("+")) {
                //check index out of bound
                num = list.get(i-1) + list.get(i);
                list.add(num);
                i++;
            } else {
                num = Integer.parseInt(s);
                list.add(num);
                i++;
            }
            res += num;
        }
        return res;
    }
}
