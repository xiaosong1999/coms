package com.dfbz.service.impl;

import org.apache.tomcat.jni.Local;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

enum Shrubbery{GROUND,CRAWLING,HANGING}
enum ComsDataType{

}
class Person{
    private int id;
    private String name;
    public Person(int id,String name){
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class Test {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1,"张三"));
        persons.add(new Person(2,"李四"));
        Object o = persons.get(0);
        Class<?> clazz = o.getClass();
        String simpleName = clazz.getSimpleName();
//        System.out.println(simpleName);
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field.getName());
        }


/*
        for(Shrubbery s : Shrubbery.values()){
            System.out.println(s+" ordinal:"+s.ordinal());
            System.out.print(s.compareTo(Shrubbery.CRAWLING)+" ");
            System.out.print(s.equals(Shrubbery.CRAWLING)+" ");
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("-----------------------------");
        }
        for(String s : "HANGING CRAWLING GROUND".split(" ")){
            Shrubbery shrub = Enum.valueOf(Shrubbery.class,s);
            System.out.println(shrub);
        }*/
/*        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime beforeWeekDate = LocalDateTime.of(nowDate.minusDays(7).toLocalDate(), LocalTime.MIN);
        System.out.println(nowDate+":"+beforeWeekDate);
        LocalDate nowDate1 = LocalDate.now();
        System.out.println(nowDate1);
        System.out.println(nowDate.toLocalDate());*/
/*        LocalDate now = LocalDate.now();
        LocalDate yestoday = now.minus(1, ChronoUnit.DAYS);
        System.out.println(now.minus(1,ChronoUnit.DAYS));
        System.out.println(now);
        System.out.println(yestoday);*/
/*
        LocalDate date = LocalDate.now();
        System.out.println(date.getMonthValue());
        System.out.println(LocalDate.now().getMonthValue());
        int startMonth = LocalDate.now().getMonthValue()+1;
        System.out.println(startMonth);
        LocalDate date = LocalDate.of(LocalDate.now().getYear()-1,LocalDate.now().getMonthValue()+1,1);
        System.out.println(date);*/

    }

}
