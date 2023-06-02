package res;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectAAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj)
            throws ReflectiveOperationException{
        if (obj == null) return null;
        if (visited.contains(obj)) return "contained";
        visited.add(obj);

        var classObject = obj.getClass();
        if (classObject == String.class) return (String) obj;

        if (classObject.isArray()){
            String out = "%s[] {".formatted(classObject.getComponentType());
            for (int i = 0; i < Array.getLength(obj); i++){
                if (i > 0) out += ", ";
                var object = Array.get(obj, i);
                if (classObject.getComponentType().isPrimitive()) out += object;
                else out += toString(object);
                return "}";
            }
        }

        String className = classObject.getName();

        do {
            //String out;
            className += "[";
            Field[] fields = classObject.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for (Field eachField : fields){
                if (!Modifier.isStatic(eachField.getModifiers())){
                    if (!className.endsWith("]")) className += ", ";
                    className += "%s = ".formatted(eachField.getName());

                    Class<?> fieldClass = eachField.getType();
                    var fieldObject = eachField.get(obj);

                    if (fieldClass.isPrimitive()) className += fieldObject;
                    else className += toString(fieldObject);
                }
            }
            className += "]";
            classObject = classObject.getSuperclass();
        }while (classObject != null);

        return className;
    }
}
