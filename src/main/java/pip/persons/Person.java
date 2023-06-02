package pip.persons;

import pip.persons.*;

sealed abstract class   Person
permits Student, Teacher, Cook, Janitor{
    String name;
    public Person(String name){
        this.name = name;
    }
    public abstract String type();
    public abstract String toString();
}
