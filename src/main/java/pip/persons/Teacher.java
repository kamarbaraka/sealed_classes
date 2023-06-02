package pip.persons;

import res.ObjectAAnalyzer;

import java.util.HashMap;
import java.util.*;
import java.lang.reflect.Constructor;

public final class Teacher
extends Person {
    private String room;
    private int id;

    public Teacher(String name, String room, int id){
        super(name);
        this.room = room;
        this.id = id;
    }
    @Override
    public String type() {;
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        try {
            return new ObjectAAnalyzer().toString(this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String... args){
        var details = new HashMap<String, Object>();
        details.put("name", "kamar");
        System.out.println(details.get("name"));
        System.out.println(HashMap.class);

        var teacher = new Teacher("kahindi", "winners", 2446);
        System.out.println(teacher.toString());
    }
}
