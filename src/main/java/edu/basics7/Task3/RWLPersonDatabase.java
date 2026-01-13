package edu.basics7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class RWLPersonDatabase implements PersonDatabaseInterface {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final List<Person> personList = new ArrayList<>();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            for (Person currPerson : personList) {
                if (person.id() == currPerson.id()) {
                    throw new IllegalArgumentException("The user with this id already exists");
                }
            }
            personList.add(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            personList.removeIf(person -> id == person.id());
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        List<Person> listByName;
        readWriteLock.writeLock().lock();
        try {
            listByName = personList.stream()
                .filter(person -> name.equals(person.name()))
                .toList();
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return listByName;
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        List<Person> listByAddress;
        readWriteLock.writeLock().lock();
        try {
            listByAddress = personList.stream()
                .filter(person -> address.equals(person.address()))
                .toList();
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return listByAddress;
    }

    @Override
    public @Nullable List<Person> findByPhone(String phone) {
        List<Person> listByPhone;
        readWriteLock.writeLock().lock();
        try {
            listByPhone = personList.stream()
                .filter(person -> phone.equals(person.phoneNumber()))
                .toList();
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return listByPhone;
    }
}
