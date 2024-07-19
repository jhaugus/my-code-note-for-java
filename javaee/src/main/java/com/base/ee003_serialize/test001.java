package com.base.ee003_serialize;

import org.junit.Test;

import java.io.*;

public class test001 implements java.io.Serializable{

    // 序列化
    public static void serializePerson(Person person) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            oos.writeObject(person);
            System.out.println(oos);
            System.out.println("对象已序列化成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 反序列化
    public static void deserializePerson() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Person person = (Person) ois.readObject();
            System.out.println("反序列化后的对象: " + person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test1(){
        // 创建要序列化的对象
        Person person = new Person("张三", 25);

        serializePerson(person);


        deserializePerson();
    }


}



