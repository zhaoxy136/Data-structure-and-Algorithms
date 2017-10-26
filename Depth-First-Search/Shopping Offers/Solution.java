class Solution {
    Map<List<Integer>, Integer> map = new HashMap<>();
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        
        return helper(price, special, needs);
    }
    private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (map.containsKey(needs)) {
            return map.get(needs);
        }
        int res = noOffer(price, needs);
        for (List<Integer> spec : special) {
            boolean valid = true;
            for (int i = 0; i < needs.size(); i++) {
                needs.set(i, needs.get(i) - spec.get(i));
                valid = valid && (needs.get(i) >= 0);
            }
            if (valid) {
                res = Math.min(res, helper(price, special, needs) + spec.get(spec.size() - 1));
            }
            for (int i = 0; i < needs.size(); i++) {
                needs.set(i, needs.get(i) + spec.get(i));
            }
        }
        map.put(needs, res);
        return res;
    }
    private int noOffer(List<Integer> price, List<Integer> needs) {
        int nonSpec = 0;
        for (int i = 0; i < needs.size(); i++) {
            nonSpec += price.get(i) * needs.get(i);
        }
        return nonSpec;
    }
}
