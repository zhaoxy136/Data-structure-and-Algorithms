//Using Stack or Deque

class Solution {
    public String simplifyPath(String path) {
        Deque<String> st = new LinkedList<>();
        List<String> directory = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) != '/') {
                int j = i + 1;
                while (j < path.length() && path.charAt(j) != '/') j++;
                directory.add(path.substring(i, j));
                i = j;
            }
        }
        for (String p : directory) {
            if (!p.equals(".") && !p.equals("..")) {
                st.push(p);
            } else if (p.equals("..") && !st.isEmpty()) {
                st.pop();
            }
        }
        if (st.isEmpty()) return new String("/");
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append('/').append(st.pollLast());
        }
        return new String(sb);
    }
}
