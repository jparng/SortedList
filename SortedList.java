public class SortedList<T extends Comparable<? super T>> {
	
	//Fields
	private Node<T> head;
	private int size;

	
	//Constructor
	public SortedList() {
		clear();
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		head = null;
	}
	
	public int getSize() {
		return size;
	}
	
	public void add(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> prevNode = getPrevNode(data);
		
		
		if(isEmpty() || prevNode == null) {    //if the list is empty, add node to the beginning
			newNode.next = head;
			head = newNode;
			size++;
		}else {
			Node<T> nextNode = prevNode.next;
			newNode.next = nextNode;
			prevNode.next = newNode;
			size++;
		}
		
	}
	
	public T removeAt(int index) {
		if(isEmpty()) {
			throw new EmptyCollectionException("Can't remove from an empty collection.");
		}
		validateIndex(index, 0 , size - 1);
		if (index == 0){
			return removeFirst();
		}else if(index == size - 1){
			return removeLast();
		}else {
			Node<T> toRemove = head;
			Node<T> previous = null;
			for(int i = 0; i < index; i++) {
				previous = toRemove;
				toRemove = toRemove.next;
			}
			previous.next = toRemove.next;
			toRemove.next = null;
			size -= 1;
			return toRemove.data;			
		}

	}
	
	private T removeFirst() {
		if(isEmpty()) {
			throw new EmptyCollectionException("Cannot remove from an empty collection");
		}else {
			Node<T> temp = head;
			head = head.next;
			temp.next = null;
			size -= 1;
			return temp.data;
		}

	}
	
	private T removeLast() {
		if(isEmpty()) {
			throw new EmptyCollectionException("Cannot remove from an empty collection");
		}else {
			Node<T> temp = getNode(size - 1);
			temp.next = null;
			size -= 1;
			return temp.data;
		}

	}
	
	public T get(int index) {
		if(isEmpty()) {
			throw new EmptyCollectionException("Can't remove from an empty collection.");
		}else {
			validateIndex(index, 0 , size - 1);
			Node<T> temp = getNode(index);
			return temp.data;
		}

	}
	
	public boolean contains(T data) {
		Node<T> temp = head;
		int i = 0;
		while(i < size) {
			if(temp.data.compareTo(data) == 0) {
				return true;
			}
			temp = temp.next;
			i++;
		}
		return false;
	}
	
	public int find(T data) {
		Node<T> temp = head;
		int i = 0;
		while(i < size) {
			if(temp.data.compareTo(data) == 0) {
				return i;
			}
			temp = temp.next;
			i++;
		}
		return -1;
	}
	
	public int count(T data) {
		Node<T> temp = head;
		int num = 0;
		int i = 0;
		while(i < size) {
			if(temp.data.compareTo(data) == 0) {     // If the data is equal to the node, add to num
				num++;
		}
			temp = temp.next;
			i++;
		}
		if(num > 0) {
			return num;
		}
		return 0;
	}
	
	public void removeAll(SortedList<T> otherList) {
		int i = 0;
		while(i < size) {
			if(otherList.contains(get(i))) {
				removeAt(i);
			}else {
				i++;
			}
		}

		
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if(!isEmpty()){
			Node<T> temp = head;
			for(int i = 0; i < size - 1; i++) {
				sb.append(temp.data.toString());
				sb.append(", ");
				temp = temp.next;
			}
			sb.append(temp.data.toString());
		}
		sb.append("]");
		return sb.toString();
	}
	
	private void validateIndex(int index, int lowerBound, int upperBound) {
		if(!(index >= lowerBound && index <= upperBound)) {
			throw new IndexOutOfBoundsException(String.format("Index must be between %d and %d", lowerBound,upperBound));
		}
			
	}
	
	private Node<T> getNode(int index){
		Node<T> temp = head;
		for(int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}
	
	private Node<T> getPrevNode(T data){
		Node<T> currentNode = head;
		Node<T> nodeBefore = null;
		while((currentNode != null) && (data.compareTo((T) currentNode.data) > 0)) {
			nodeBefore = currentNode;
			currentNode = currentNode.next;
		}
		return nodeBefore;
	}
	
	//Your node class.  You may move it to a different file but
	//you will need to change this to public class Node
	private class Node<E> {
		E data;
		Node<E> next;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
