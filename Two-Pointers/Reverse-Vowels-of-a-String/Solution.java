//Version 0: original (14 ms)
public class Solution {
    public String reverseVowels(String s) {
        if (s.length() == 0) {
            return s;
        }
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        StringBuilder sb = new StringBuilder(s);
        int start = 0;
        int end = sb.length() - 1;
        while (start < end) {
            while (!vowels.contains(sb.charAt(start)) && start < end) {
                start++;
            }
            while (!vowels.contains(sb.charAt(end)) && start < end) {
                end--;
            }
            if (start >= end) {
                break;
            }
            char tmp = sb.charAt(start);
            sb.setCharAt(start, s.charAt(end));
            sb.setCharAt(end, tmp);
            start++;
            end--;
        }
        return sb.toString();
    }
}
//Version 0'
public class Solution {
    public String reverseVowels(String s) {
        List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] chars = s.toCharArray();
        int i = 0, j = s.length()-1;
        while (i < j) {
            while (i < j && !vowels.contains(chars[i])) i++;
            while (i < j && !vowels.contains(chars[j])) j--;
            char ch = chars[i];
            chars[i++] = chars[j];
            chars[j--] = ch;
        }
        return String.valueOf(chars);
    }
}

//Version 1: (5 ms)
public class Solution {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        String vowels = "aeiouAEIUO";
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            while (start < end && vowels.indexOf(chars[start]) == -1) {
                start++;
            }
            while (start < end && vowels.indexOf(chars[end]) == -1) {
                end--;
            }
            if (start >= end) {
                break;
            }
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
