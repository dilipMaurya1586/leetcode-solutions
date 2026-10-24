class MyCircularDeque {
    int[] arr;
    int front;
    int rear;
    int count;
    int capacity;

    public MyCircularDeque(int k) {
        arr = new int[k];
        front = 0;
        rear = -1;
        count = 0;
        capacity = k;
    }
    
    public boolean insertFront(int value) {
        if(isFull()) {
            return false;
        }
        if(isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }
        arr[front] = value; 
        count++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if(isFull()) {
            return false;
        }
        if(isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        arr[rear] = value;
        count++;
        return true;
    }
    
    public boolean deleteFront() {
        if(isEmpty()) {
            return false;
        }
        if(count == 1) {
            count = 0;
            front = 0;
            rear = -1;
            return true;
        }
        front = (front + 1) % capacity;
        count--;
        return true;
    }
    
    public boolean deleteLast() {
        if(isEmpty()) {
            return false;
        }
        if(count == 1) {
            count =0;
            front = 0;
            rear = -1;
            return true;
        }
        rear = (rear - 1 + capacity) % capacity;
        count--;
        return true;
    }
    
    public int getFront() {
        if(isEmpty()) {
            return -1;
        }
        return arr[front];
    }
    
    public int getRear() {
        if(isEmpty()) {
            return -1;
        }
        return arr[rear];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */