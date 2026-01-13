package edu.basics7.Task3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class SynchronizedPersonDatabase implements PersonDatabaseInterface {

    private final List<Person> personList = new ArrayList<>();

    @Override
    public synchronized void add(Person person) {
        for (Person currPerson : personList) {
            if (person.id() == currPerson.id()) {
                throw new IllegalArgumentException("The user with this id already exists");
            }
        }
        personList.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        personList.removeIf(person -> id == person.id());
    }

    @Override
    public @Nullable synchronized List<Person> findByName(String name) {
        return personList.stream()
            .filter(person -> name.equals(person.name()))
            .toList();
    }

    @Override
    public @Nullable synchronized List<Person> findByAddress(String address) {
        return personList.stream()
            .filter(person -> address.equals(person.address()))
            .toList();
    }

    @Override
    public @Nullable synchronized List<Person> findByPhone(String phone) {
        return personList.stream()
            .filter(person -> phone.equals(person.phoneNumber()))
            .toList();
    }
}
