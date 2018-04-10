package concurrent;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCllectionsTest {
    public static void main(String[] args) {
        class User{
            private String name;
            private int age;

            public User(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return "User{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }

//        Collection users = new ArrayList();
        Collection users = new CopyOnWriteArrayList();
        users.add(new User("tom1", 10));
        users.add(new User("tom2", 10));
        users.add(new User("tom3", 10));

        Iterator iterator = users.iterator();

        while (iterator.hasNext()){
            System.out.println("in the while");
//            try {
                User user = (User) iterator.next();
                if("tom1".equals(user.name)){
                    users.remove(user);
//                    iterator.remove();
                }else{
                    System.out.println(user);
                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }


        }
    }
}
