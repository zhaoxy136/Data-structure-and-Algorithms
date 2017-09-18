public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> rowIte;
    Iterator<Integer> colIte;
    public Vector2D(List<List<Integer>> vec2d) {
        this.rowIte = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return colIte.next();
    }

    @Override
    public boolean hasNext() {
        while (rowIte.hasNext() && (colIte == null || !colIte.hasNext())) {
            colIte = rowIte.next().iterator();
        }
        return colIte != null && colIte.hasNext();
    }
}
