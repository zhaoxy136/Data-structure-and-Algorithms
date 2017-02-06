//Version 0: two stack
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        min = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || x < min.peek()) min.push(x);
        else min.push(min.peek());
    }
    
    public void pop() {
        stack.pop();
        min.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

//Version 1:
//@copyright https://discuss.leetcode.com/topic/4953/share-my-java-solution-with-only-one-stack
public class MinStack {
    private long min;
    private Stack<Long> stack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Long>();
    }
    
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        } else {
            stack.push(x-min);
            min = Math.min(min, x);
        }
    }
    
    public void pop() {
        long tmp = (long)stack.pop();
        if (tmp < 0) {
            min -= tmp;
        }
    }
    
    public int top() {
        long tmp = stack.peek();
        if (tmp < 0) {
            return (int)min;
        } else {
            return (int)(min + tmp);
        }
    }
    
    public int getMin() {
        return (int)min;
    }
}
