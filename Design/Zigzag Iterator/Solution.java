public class ZigzagIterator {
    List<Integer> l1;
    List<Integer> l2;
    int i, j;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.l1 = v1;
        this.l2 = v2;
        this.i = 0;
        this.j = 0;
    }

    public int next() {
        if (i >= l1.size()) {
            return l2.get(j++);
        } else if (j >= l2.size()) {
            return l1.get(i++);
        } else {
            return i <= j ? l1.get(i++) : l2.get(j++);
        }
    }

    public boolean hasNext() {
        return i < l1.size() || j < l2.size();
    }
}
