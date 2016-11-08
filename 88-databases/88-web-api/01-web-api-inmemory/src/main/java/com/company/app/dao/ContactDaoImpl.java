package com.company.app.dao;

import com.company.app.models.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDaoImpl implements ContactDao {
    Map<Integer, Contact> students;

    public ContactDaoImpl(){
        students = new HashMap<>();
        Contact tom = new Contact(0, "Tom");
        students.put(tom.getId(), tom);
        Contact dick = new Contact(1, "Dick");
        students.put(dick.getId(), dick);
        Contact harry = new Contact(2, "Harry");
        students.put(harry.getId(), harry);
    }

    @Override
    public List<Contact> getContacts() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Contact getContact(int id) {
        return students.get(id);
    }

    @Override
    public int createContact(Contact contact) {
        students.put(contact.getId(), contact);
        return contact.getId();
    }

    @Override
    public void updateContact(Contact contact) {
        students.put(contact.getId(), contact);
        System.out.println("Contact: id=" + contact.getId() + ", updated in the database");
    }

    @Override
    public void deleteContact(Contact contact) {
        students.remove(contact.getId());
        System.out.println("Contact: id=" + contact.getId() + ", deleted from database");
    }
}
