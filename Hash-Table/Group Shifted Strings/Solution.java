class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        // List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String s = convert(str);
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }
    public String convert(String s) {
        char[] str = s.toCharArray();
        int dif = str[0] - 'a';
        for (int i = 0; i < str.length; i++) {
            if (str[i] - 'a' < dif) {
                str[i] = (char)(str[i] - dif + 26);
            } else {
                str[i] = (char)(str[i] - dif); 
            }
        }
        return new String(str);
    }
}
