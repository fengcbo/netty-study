package com.shiluns.netty.thrift;

import org.apache.thrift.TException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fengchuanbo
 */
public class PersonServiceImpl implements PersonService.Iface {

    public static Map<String, Person> PERSONS = new LinkedHashMap<>();

    static {
        Person person = new Person().setName("zhangsan").setAge(18).setMarried(false);
        PERSONS.put(person.getName(), person);
    }

    @Override
    public Person getPersonByUserName(String name) throws DataException, TException {
        return PERSONS.get(name);
    }

    @Override
    public void savePerson(Person person) throws TException {
        PERSONS.put(person.getName(), person);
        System.out.println(PERSONS);
    }
}
