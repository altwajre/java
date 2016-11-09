package com.company.app.dao;

import com.company.app.models.Contact;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    Connection conn = null;
    String url = "jdbc:mysql://localhost/phonebook";
    String userName = "phonebookuser";
    String password = "phonebookpassword";

    public ContactDaoImpl(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            PreparedStatement select = conn.prepareStatement("SELECT id, name FROM phonebook.contact");
            select.executeQuery();
            ResultSet resultSet = select.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Contact contact = new Contact();
                contact.setId(id);
                contact.setName(name);
                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public Contact getContact(int id) {
        Contact contact = null;
        try {
            PreparedStatement select = conn.prepareStatement("SELECT id, name FROM phonebook.contact WHERE id = ?");
            select.setInt(1, id);
            select.executeQuery();
            ResultSet resultSet = select.getResultSet();
            if (resultSet.next()){
                contact = new Contact();
                contact.setId(resultSet.getInt(1));
                contact.setName(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public int createContact(Contact contact) {
        try {
            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO phonebook.contact (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, contact.getName());
            int affectedRows = insert.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Create failed, no rows affected");
            }
            try(ResultSet generatedKeys = insert.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                }
                else{
                    throw new SQLException("Creating failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateContact(Contact contact) {
        try {
            PreparedStatement update = conn.prepareStatement("UPDATE phonebook.contact SET name = ? WHERE id = ?");
            update.setString(1, contact.getName());
            update.setInt(2, contact.getId());

            int affectedRows = update.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Create failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        try {
            PreparedStatement delete = conn.prepareStatement("DELETE FROM phonebook.contact WHERE id = ?");
            delete.setInt(1, contact.getId());

            int affectedRows = delete.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Create failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
