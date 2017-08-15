public class MovingAverage {
    private Queue<Integer> queue;
    private int size;
    private double sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0.0;
        queue = new LinkedList<>();
    }
    
    public double next(int val) {
        queue.offer(val);
        if (queue.size() > size) {
            sum += val - queue.poll();
        } else {
            sum += val;
        }
        return sum / queue.size();
    }
}
