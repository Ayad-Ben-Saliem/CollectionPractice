package edu.kse.collectionpractice;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;

public class Range<T extends Number> implements Iterable<Number>{

    private T from;
    private T to;
    private Number value;
    private Number step;
    
    public Range(@NotNull T from, @NotNull T to){
        this(from, to, null);
    }
    
    public Range(@NotNull T from, @NotNull T to, @NotNull T step){
        this.from = from;
        this.to = to;
        this.value = from;
        if(step == null)
            this.step = 1;
        else
            this.step = step;
    }

    class MyIterator implements Iterator<Number>{

        @Override
        public boolean hasNext() {
            if(step.doubleValue() > 0){
                return (value.doubleValue() + step.doubleValue() <= to.doubleValue());
            }else{
                return (value.doubleValue() + step.doubleValue() >= to.doubleValue());
            }
        }

        @Override
        public Number next() {
            if(!hasNext()){
                throw new RuntimeException();
            }

            Number result = value;

            if(from instanceof Double){
                value = value.doubleValue() + step.doubleValue();
            }else if(from instanceof Float){
                value = value.floatValue() + step.floatValue();
            }else if(from instanceof Long){
                value = value.longValue() + step.longValue();
            }else if(from instanceof Integer){
                value = value.intValue() + step.intValue();
            }else if(from instanceof Short){
                value = value.shortValue() + step.shortValue();
            }else if(from instanceof Byte){
                value = value.byteValue() + step.byteValue();
            }

            return result;
        }
    }
    
    @Override
    public Iterator<Number> iterator() {
        MyIterator iterator = new MyIterator();
        return iterator;
    }

    @Override
    public String toString() {
        Iterator i = iterator();
        StringBuilder result = new StringBuilder("[");
        while (i.hasNext()){
            result.append(i.next()).append(", ");
        }
        return result.substring(0, result.length() - 2) + "]";
    }
}