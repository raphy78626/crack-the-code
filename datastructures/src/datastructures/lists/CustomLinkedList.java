/**
 * 
 */
package datastructures.lists;

import datastructures.util.ListUtil;

/**
 * CustomLinkedList: Simple LinkedList implementation.
 * 
 * @author Sudharsanan Muralidharan
 */
public class CustomLinkedList<T> extends AbstractList<T> {

  public CustomLinkedList() {

  }

  public CustomLinkedList(CustomLinkedList<T> copyList) {
    LinkedListNode<T> current = copyList.head;
    while (current != null) {
      this.add(current.data);
      current = current.next;
    }
  }
  
  public CustomLinkedList(LinkedListNode<T> head) {
    this.head = head;
  }

  /**
   * Returns whether the list contains the object o
   * 
   * @param o
   * @return contains
   */
  public boolean contains(Object o) {
    LinkedListNode<T> current = head;

    while (current != tail.next) {
      if (current.data.equals(o)) {
        return true;
      }
      current = current.next;
    }

    return false;
  }

  /**
   * Convert the list toArray and return Object[]
   * 
   * @return orray[]
   */
  public Object[] toArray() {
    Object[] array = new Object[size];
    int i = 0;
    LinkedListNode<T> node = head;

    while (node != null) {
      array[i] = node.data;
      node = node.next;
      i++;
    }

    return array;
  }

  /**
   * Add new object to LinkedList
   * 
   * @param e
   * @return added
   */
  public boolean add(T e) {
    boolean added = false;

    /*
     * create head if null i.e. list if empty
     */
    if (head == null) {
      head = new LinkedListNode<T>();
      head.data = e;
      head.next = null;
      tail = head;
      added = true;
    } else {
      // add element to the tail and increment size
      LinkedListNode<T> current = new LinkedListNode<T>();
      current.data = e;
      current.next = null;
      tail.next = current;
      tail = current;
      added = true;
    }

    size++;

    return added;
  }
  
  public boolean addToFront(T e) {
    boolean added = false;
    
    if(head == null) {
      head = new LinkedListNode<T>();
      head.data = e;
      head.next = null;
      tail = head;
      added = true;
    } else {
      LinkedListNode<T> node = new LinkedListNode<T>();
      node.data = e;
      node.next = head;
      head = node;
      added = true;
    }
    
    return added;
  }

  /**
   * Removes the object from the list
   * 
   * @param o
   * @return removed
   */
  public boolean remove(Object o) {
    LinkedListNode<T> current = head;
    LinkedListNode<T> runner = current.next;
    
    /*
     * If head is to be removed
     */
    if (head.data.equals(o)) {
      head = head.next;
      size--;
      return true;
    }

    while (runner != null) {
      if (runner.data.equals(o)) {
        current.next = runner.next;
        size--;
        if (runner.equals(tail)) {
          tail = current;
        }
        return true;
      } else {
        current = current.next;
      }
      runner = runner.next;
    }

    return false;
  }
  
  public LinkedListNode<T> removeNode(Object o) {
    LinkedListNode<T> current = head;
    LinkedListNode<T> runner = current.next;
    LinkedListNode<T> removedNode = null;
    
    if(head.data.equals(o)) {
      removedNode = head;
      head = head.next;
      size--;
      return removedNode;
    }
    
    while(runner != null) {
      if(runner.data.equals(o)) {
        current.next = runner.next;
        size--;
        
        if(runner.equals(tail)) {
          tail = current;
        }
        removedNode = runner;
        return runner;
      } else {
        current = current.next;
      }
      
      runner = runner.next;
    }
    
    return removedNode;
  }

  public void removeAll(Object o) {
    LinkedListNode<T> current = head;
    LinkedListNode<T> runner = current.next;
    
    if(head.data.equals(o)) {
      head = head.next;
    }
    
    while(runner != null) {
      if(runner.data.equals(o)) {
        current.next = runner.next;
        
        if(runner.equals(tail)) {
          tail = current;
        }
        size--;
      } else {
        current = current.next;
      }
      
      runner = runner.next;
    }
  }
  
  /**
   * Removes the object at index from list
   * 
   * @param index
   * @return removed
   */
  public T remove(int index) {
    LinkedListNode<T> current = head;
    int i = 1;
    T removed = null;

    if (index == 0) {
      T data = head.data;
      head = head.next;
      size--;
      return data;
    }

    while (current.next != null) {
      if (i == index) {
        removed = current.next.data;
        current.next = current.next.next;
        size--;

        if (index == size) {
          tail = current;
        }

        return removed;
      } else {
        current = current.next;
      }

      i++;
    }

    return null;
  }

  /**
   * Get the element value at the index
   * 
   * @param index
   * @return found.data
   */
  public T get(int index) {
    LinkedListNode<T> found = find(index);
    if (found != null) {
      return found.data;
    }

    return null;
  }

  /**
   * Set the element value at the specified index
   * 
   * @param index
   * @param element
   * @return found.data
   */
  public T set(int index, T element) {
    LinkedListNode<T> found = find(index);
    if (found != null) {
      found.data = element;
      return element;
    }
    return null;
  }

  /**
   * Add an element at the index
   * 
   * @param index
   * @param element
   */
  public void add(int index, T element) {
    LinkedListNode<T> current = head;
    int i = 0;

    /*
     * if index is 0 then add it to front and update head
     */
    if (index == 0) {
      LinkedListNode<T> node = new LinkedListNode<T>();
      node.data = element;
      node.next = head;
      head = node;
      size++;
      return;
    }

    while (current != null) {
      if (index - 1 == i) {
        LinkedListNode<T> node = new LinkedListNode<T>();
        node.data = element;
        node.next = current.next;
        current.next = node;
        if (index == size - 1) {
          tail = node;
        }
        size++;
      }

      current = current.next;
      i++;
    }
  }

  public void addNode(int index, LinkedListNode<T> node) {
    LinkedListNode<T> current = head;
    int i = 1;

    if (index == 0) {
      head = node;
    }

    if (index == size - 1) {
      tail = node;
    }

    while (current != null) {
      if (i == index) {
        node.next = current.next;
        current.next = node;
        break;
      }
      i++;
      current = current.next;
    }
    
    while(node != null) {
      node = node.next;
    }
    
    size += 1;
  }

  /**
   * Prints the list
   */
  public void print() {
    ListUtil.printList(this.head);
  }

  /**
   * Returns the Node<T> at index
   * 
   * @param index
   * @return Node<T>
   */
  public LinkedListNode<T> getNode(int index) {
    return find(index);
  }

  /**
   * Returns the Node<T> at the index
   * 
   * @param index
   * @return node
   */
  public LinkedListNode<T> find(int index) {
    LinkedListNode<T> current = head;
    int i = 0;

    if (index == 0) {
      return current;
    }

    while (current != null) {
      if (i == index) {
        return current;
      }
      i++;
      current = current.next;
    }

    return null;
  }
  
  public LinkedListNode<T> find(Object o) {
    LinkedListNode<T> current = head;

    if (head.data.equals(o)) {
      return current;
    }

    while (current != null) {
      if (current.data.equals(o)) {
        return current;
      }
      current = current.next;
    }

    return null;
  }
}
