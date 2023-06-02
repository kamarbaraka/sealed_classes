package res;

import jdk.dynalink.linker.support.Lookup;
import pip.persons.Teacher;

import javax.swing.*;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.stream.Stream;

public class Diag {
    public static void printFields(Class<?> cl){
        Field[] fields = cl.getDeclaredFields();
        for (Field eachField : fields)
            System.out.printf("%s ", eachField.getName());
    }
    public static void main(String... args)
            throws NoSuchFieldException, IllegalAccessException{
        var teacher = new Teacher("kahindi", "winners", 2564);
        var teacherClass = teacher.getClass();
        printFields(teacherClass);
        var teachName = teacherClass.getDeclaredField("room");
        teachName.setAccessible(true);

        var lookup = MethodHandles.privateLookupIn(teacherClass, MethodHandles.lookup());
        System.out.println(teachName.get(teacher));
    }
}
