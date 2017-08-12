public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> st = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        pushList(nestedList);
    }

    @Override
    public Integer next() {
        return st.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            NestedInteger tmp = st.peek();
            if (tmp.isInteger()) {
                return true;
            }
            pushList(st.pop().getList());
        }
        return false;
    }
    
    private void pushList(List<NestedInteger> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            st.push(list.get(i));
        }
    }
}
