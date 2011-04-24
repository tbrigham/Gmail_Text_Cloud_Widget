package com.tsb.gmailinboxtextcloud;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/21/11
 * Time: 7:15 PM
 *
 * Implements a fixed length queue where the most recent object added is in the [0] position and
 * the object added last is in the [maxSize - 1] position.  objects that hang off the end are removed
 * from the queue.
 *
 *
 *
 */
public class GITextQueue<E> {

    private LinkedList<E> mainQ;
    private int maxSize;

    public GITextQueue() {
        maxSize = GITextCloud.DEFAULT_MAX_EMAIL_COUNT;
        mainQ = new LinkedList<E>();
        for (int i = 0; i<maxSize; i++) {

        }
    }

    public GITextQueue(int maxSize) {
        this.maxSize = maxSize;
        mainQ = new LinkedList<E>();
    }

    /*
       int size()

       returns:
               -1 if no array is instantiated
               the number of objects in the array otherwise
     */
    public int size() {
        if (mainQ == null) {
            return -1;
        } else {
            return mainQ.size();
        }
    }

    public boolean isEmpty() {
        return (mainQ.size() == 0);
    }

    public boolean contains(E object) {
        for (E aMainQ : mainQ) {
            if (aMainQ.equals(object)) {
                return true;
            }
        }
        return false;
    }

    public E[] toArray() {
        return (E[])mainQ.toArray();
    }


    public boolean add(E e) {
        if (mainQ.size() == maxSize) {
            mainQ.removeLast();
            mainQ.add(0, e);
            return true;
        } else {
            mainQ.add(0, e);
            return true;
        }
    }

    public boolean remove(E object) {
        for (int i = 0; i < mainQ.size(); i++) {
            if (mainQ.get(i).equals(object)) {
                mainQ.remove(i);
                return true;
            }
        }
        return false;
    }


    public void clear() {
        mainQ.clear();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public E get(int i) {
        return mainQ.get(i);
    }

}
