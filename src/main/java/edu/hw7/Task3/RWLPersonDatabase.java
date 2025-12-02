package edu.hw7.Task3;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class RWLPersonDatabase implements PersonDatabaseInterface {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final HashMap<Integer, Person> personById = new HashMap<>();
    private final HashMap<String, Person> personByName = new HashMap<>();
    private final HashMap<String, Person> personByAddress = new HashMap<>();
    private final HashMap<String, Person> personByPhone = new HashMap<>();

    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            personById.put(person.id(), person);
            personByName.put(person.name(), person);
            personByAddress.put(person.address(), person);
            personByPhone.put(person.phoneNumber(), person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = personById.get(id);

            personById.remove(id);
            personByName.remove(person.name(), person);
            personByAddress.remove(person.address(), person);
            personByPhone.remove(person.phoneNumber(), person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Nullable
    public Person findByName(String name) {
        Person ans;

        readWriteLock.readLock().lock();
        try {
            ans = personByName.get(name);
        } finally {
            readWriteLock.readLock().unlock();
        }

        return ans;
    }

    @Nullable
    public Person findByAddress(String address) {
        Person ans;

        readWriteLock.readLock().lock();
        try {
            ans = personByAddress.get(address);
        } finally {
            readWriteLock.readLock().unlock();
        }

        return ans;
    }

    @Nullable
    public Person findByPhone(String phone) {
        Person ans;

        readWriteLock.readLock().lock();
        try {
            ans = personByPhone.get(phone);
        } finally {
            readWriteLock.readLock().unlock();
        }

        return ans;
    }
}
