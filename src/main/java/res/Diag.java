package res;

import jdk.dynalink.linker.support.Lookup;
import pip.persons.Teacher;

import javax.swing.*;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

public class Diag {
    public static void printFields(Class<?> cl){
        Field[] fields = cl.getDeclaredFields();
        for (Field eachField : fields)
            System.out.printf("%s ", eachField.getName());
    }

    public static Object goodCopyOf(Object passedObject, int passedLength){
        var objectClass = passedObject.getClass();
        if (!objectClass.isArray()) return null;

        var lengthOfPassedObject = Array.getLength(passedObject);
        var tempArray = Array.newInstance(objectClass.componentType(), passedLength);
        System.arraycopy(passedObject, 0, tempArray, 0, Math.min(lengthOfPassedObject,
                passedLength));
        return tempArray;
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
        var arr = (int[]) goodCopyOf(new int[] {1, 2, 4}, 4);
        System.out.println(Arrays.toString(arr));
        var cl = arr.getClass();
        System.out.println(cl.arrayType());
    }
}
