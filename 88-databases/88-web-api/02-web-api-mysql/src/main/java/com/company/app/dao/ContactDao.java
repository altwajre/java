package com.company.app.dao;

import com.company.app.models.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> getContacts();
    Contact getContact(int id);
    int createContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(Contact contact);
}
