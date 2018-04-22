package edu.kse.collectionpractice;

import java.util.Iterator;

public class Array<T> implements Cloneable {

    private T[] values;

    public Array(){
        this(0);
    }

    public Array(int length) {
        if(length < 0) {
            throw new IllegalArgumentException("Array length shouldn't be negative");
        }
        values = (T[])new Object[length];
    }

    public Array<T> add(T object){

        int length = values.length;
        T[] temp = (T[])new Object[length + 1];
        System.arraycopy(values, 0, temp, 0, length);
        temp[length] = object;
        values = temp;

        return this;
    }

    public Array<T> setElementAt(int index, T object) {
        checkIndex(index);

        values[index] = object;
        return this;
    }

    public T getElementAt(int index) {
        checkIndex(index);

        return values[index];
    }

    public Array<T> replaceElementAt(int index, T object) {
        checkIndex(index);
        Array<T> result = this.copy();
        result.values[index] = object;
        return result;
    }

    public int remove(T value){
        int times = 0;

        Array<Integer> removedIndexes = new Array<>(0);
        for (int i=0; i<values.length; i++) {
            if(values[i] == value){
                removedIndexes.add(i);
            }
        }
        removeElementsAt(removedIndexes);

        return times;
    }

    public void removeElementAt(int index) {
        checkIndex(index);

        Array<Integer> removedIndexes = new Array<>(1);
        removedIndexes.setElementAt(0, index);
        removeElementsAt(removedIndexes);
    }

    public void removeElementsAt(Array<Integer> removedIndexes){
        int length = values.length - removedIndexes.length();
        T[] temp = (T[]) new Object[length];

        int n = 0;
        for (int i = 0; i < values.length ; i++) {
            if(!removedIndexes.isElementExists(i)){
                temp[n++] = values[i];
            }
        }
        values = temp;
    }

    public boolean isElementExists(T object) {
        for (T value : values) {
            if (value == object) {
                return true;
            }
        }
        return false;
    }

    public int length(){
        return values.length;
    }

    private void checkIndex(int index) {
        if(index < 0){
            throw new IllegalArgumentException("Array index shouldn't be negative");
        }else if(index > values.length-1){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString(){
        String result = "[";
        for(T value : values){
            result += value + ", ";
        }
        result = result.substring(0, result.length()-2);
        result += "]";

        return result;
    }

    @Override
    public boolean equals(Object object) {
        Array<T> temp;
        try {
            temp = (Array<T>)object;
        }catch (ClassCastException e){
            return false;
        }

        if(this.length() != temp.length()){
            return false;
        }

        for (int i = 0; i < this.length() ; i++) {
            if(this.getElementAt(i) != this.getElementAt(i)){
                return false;
            }
        }

        return true;
    }

    public Array<T> copy() {
        Array<T> result = new Array<T>(this.length());
        System.arraycopy(this.values, 0, result.values, 0, this.length());
        return result;
    }
}
