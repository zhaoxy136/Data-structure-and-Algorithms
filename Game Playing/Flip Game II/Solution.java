//Version 0: backtracking
class Solution {
    public boolean canWin(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                if (!canWin(s.substring(0, i) + "--" + s.substring(i+2))) return true;
            }
        }
        return false;
    }
}

//Version 1: with memorization
class Solution {
    Map<String, Boolean> map = new HashMap<>();
    public boolean canWin(String s) {
        return helper(s);
    }
    public boolean helper(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        boolean win = false;
        int i = 0;
        while (i < s.length() - 1 && !win) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                win |= !helper(s.substring(0, i) + "--" + s.substring(i+2));
            }
            i++;
        }
        map.put(s, win);
        return win;
    }
}
