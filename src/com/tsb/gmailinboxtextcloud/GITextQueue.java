package com.tsb.gmailinboxtextcloud;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/21/11
 * Time: 7:15 PM
 */
public class GITextQueue<E> {

    private E mainQ[];
    private int maxSize;
    private int frontOfQueue;
    private int size;

    public GITextQueue(Class<E> e) {
        maxSize = GITextCloud.DEFAULT_MAX_EMAIL_COUNT;
        frontOfQueue = 0;
        this.size = 0;
        mainQ = (E[])Array.newInstance(e, maxSize);
    }

    public GITextQueue(Class<E> e, int maxSize) {
        this.maxSize = maxSize;
        frontOfQueue = 0;
        this.size = 0;
        mainQ = (E[])Array.newInstance(e, maxSize);
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
            return mainQ.length;
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(Object o) {
        for (E aMainQ : mainQ) {
            if (aMainQ.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public E[] toArray() {
        return null;
    }


    public boolean add(E e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean remove(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean offer(E e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public E remove() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public E poll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public E element() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public E peek() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
