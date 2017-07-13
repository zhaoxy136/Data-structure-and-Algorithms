//Version 0:
public class MedianFinder {
    PriorityQueue<Integer> leftQueue;
    PriorityQueue<Integer> rightQueue;
    
    //Constructor
    public MedianFinder() {
        leftQueue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        }); 
        rightQueue = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (leftQueue.isEmpty() || num <= leftQueue.peek()) {
            leftQueue.add(num);
            if (leftQueue.size() - rightQueue.size() > 1) {
                rightQueue.add(leftQueue.poll());
            }
        } else {
            rightQueue.add(num);
            if (rightQueue.size() > leftQueue.size()) {
                leftQueue.add(rightQueue.poll());
            }
        }
    }
    
    public double findMedian() {
        if (leftQueue.size() == rightQueue.size()) {
            return(leftQueue.peek() + rightQueue.peek()) / 2.0;
        } else {
            return leftQueue.peek() / 1.0;
        }
    }
}
