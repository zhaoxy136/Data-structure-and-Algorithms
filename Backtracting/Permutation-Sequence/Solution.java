//Version 0:
public class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for(int i = 1; i < n; i++){
            factorial[i] = factorial[i-1] * i;
        }
        StringBuilder res = new StringBuilder();
        ArrayList<Integer> num = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            num.add(i);
        }
        k = k - 1;
        for(int i = n; i > 0; i--){
            int temp = k/factorial[i-1];
            k = k % factorial[i-1];
            res.append(num.get(temp));
            num.remove(temp);
        }
        return res.toString();
    }
}

//Version 1: more efficient
public class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        int fact = 1;
        for(int i = 2; i <= n; i++){
            fact = fact * i;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            list.add(i);
        }
        k--;
        while(n > 0){
            fact /= n;
            res.append(list.remove(k/fact));
            k %= fact;
            n--;
        }
        return res.toString();
    }
}
