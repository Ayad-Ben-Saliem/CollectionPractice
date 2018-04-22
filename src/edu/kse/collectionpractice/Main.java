package edu.kse.collectionpractice;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args){

        Range<Integer> ages = new Range<>(0, 1000000);

        for(Number n : ages){
            System.out.println(n);
        }
    }


















    private static void test1(){
        List<Student> students = new ArrayList<>();

        students.add(new Student("Eamon", 2311102));
        students.add(new Student("Aziz",  1007525));
        students.add(new Student("Belga", 1421556));
        students.add(new Student("Danya", 1752653));
        students.add(new Student("Ayad",  1011102));
        students.add(new Student("Ahmed", 912562));
        students.add(new Student("Ali",   1152522));
        students.add(new Student("Morad", 852232));
        students.add(new Student("Nezar", 525531));
        students.add(new Student("Rasheed",  1222321));
        students.add(new Student("Ismaeal",  1011103));

    }

    private static void test2(){
        Set<Integer> integers = new HashSet<>();
        Random r = new Random();
        integers.add(r.nextInt());
        integers.add(r.nextInt());
        integers.add(r.nextInt());
        integers.add(r.nextInt());
        integers.add(r.nextInt());

        for (Integer i : integers) {
            System.out.println(i);
        }
    }

    private static void test3(){
        Vector<Double> sin = new Vector<>();
        double f = 0.1;
        Range<Double> range = new Range<Double>(0d, 10d, 0.1);
        for (Number n : range){
            Double d = (Double) n;
            sin.add(Math.sin(2*Math.PI*f*d));
        }
        drawSin(sin);
    }


    private static void drawSin(Vector<Double> sin){
        scaleSin(sin, 25);
        Double min = Collections.min(sin);
        biasSin(sin, -min);
        for (Double v : sin) {
            for (int i = 0; i < v; i++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
    }
    private static void scaleSin(Vector<Double> sin, double scale){
        for (int i = 0; i < sin.size(); i++) {
            Double value = sin.get(i);
            value *= scale;
            sin.setElementAt(value, i);
        }
    }

    private static void biasSin(Vector<Double> sin, double bias){
        for (int i = 0; i < sin.size(); i++) {
            Double value = sin.get(i);
            value += bias;
            sin.setElementAt(value, i);
        }
    }

    private static void test4(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Ayad Mustafa");   print(buffer);
        buffer.ensureCapacity(35);     print(buffer);
        buffer.append(" Ayad");          print(buffer);
        buffer.append(" Ben Saleem");    print(buffer);
        buffer.delete(0, 5);        print(buffer);
        buffer.appendCodePoint(50);      print(buffer);
        buffer.reverse();                print(buffer);
        buffer.setLength(10);            print(buffer);
    }


    private static void test5(){
        StringBuilder buffer = new StringBuilder();
        buffer.append("Ayad Mustafa");   print(buffer);
        buffer.ensureCapacity(35);     print(buffer);
        buffer.append(" Ayad");          print(buffer);
        buffer.append(" Ben Saleem");    print(buffer);
        buffer.delete(0, 5);        print(buffer);
        buffer.appendCodePoint(50);      print(buffer);
        buffer.reverse();                print(buffer);
        buffer.setLength(10);            print(buffer);
    }

    private static void print(CharSequence string){
        System.out.printf("buffer = %s(@0x%x)\n", string, string.hashCode());
        System.out.println("Length : " + string.length());
        if(string instanceof StringBuilder)
            System.out.println("Capacity : " + ((StringBuilder)string).capacity());
        if(string instanceof StringBuffer)
            System.out.println("Capacity : " + ((StringBuffer)string).capacity());
    }

    private static final void test6(){

        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();
        Random r = new Random();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    int value = r.nextInt(1000);
                    String s = "T1(" + value + ")";
                    buffer.append(s);
                    builder.append(s);
                    System.out.println("T1 : buffer = " + buffer);
                    System.out.println("T1 : builder = " + builder);
                    try {
                        Thread.sleep(value);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };


        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    int value = r.nextInt(1000);
                    String s = "T2(" + value + ")";
                    buffer.append(s);
                    builder.append(s);
                    System.out.println("T2 : buffer = " + buffer);
                    System.out.println("T2 : builder = " + builder);
                    try {
                        Thread.sleep(value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Student implements Comparable{
    private String name;
    private int ID;
    private int yearCode;

    public Student(String name, int ID) {
        this.name = name;
        this.ID = ID;
        yearCode = ID / 100000;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getYearCode(){
        return yearCode;
    }

    @Override
    public String toString() {
        return name + "(" + ID + ")";
    }

    @Override
    public int compareTo(Object o) {
//        Student s2 = (Student) o;
//        if(yearCode > s2.getYearCode()){
//            return 1;
//        }else if(yearCode < s2.getYearCode()){
//            return -1;
//        }else {
//            return name.compareTo(s2.getName());
//        }
        return -1;
    }
}