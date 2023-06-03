package pip.persons;

import pip.persons.*;

sealed abstract class   Person
permits Student, Teacher, Cook, Janitor{
    String name;
    public Person(String name){
        this.name = name;
    }
    protected abstract String type();
    @Override
    public abstract String toString();
}
