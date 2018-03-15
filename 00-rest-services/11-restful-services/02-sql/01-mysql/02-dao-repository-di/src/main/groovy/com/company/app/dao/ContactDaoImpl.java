package com.company.app.dao;

import com.company.app.DataSourceConfig;
import com.company.app.model.Contact;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    DataSourceConfig config;

    public ContactDaoImpl(DataSourceConfig config){
        this.config = config;
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement select = conn.prepareStatement("SELECT id, name FROM webapi.contact")
        ) {
            select.executeQuery();

            try(ResultSet resultSet = select.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    Contact contact = new Contact();
                    contact.setId(id);
                    contact.setName(name);
                    contacts.add(contact);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public Contact getContact(int id) {
        Contact contact = null;
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement select = conn.prepareStatement("SELECT id, name FROM webapi.contact WHERE id = ?")
        ) {
            select.setInt(1, id);
            select.executeQuery();

            try (ResultSet resultSet = select.getResultSet()) {
                if (resultSet.next()) {
                    contact = new Contact();
                    contact.setId(resultSet.getInt(1));
                    contact.setName(resultSet.getString(2));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public int createContact(Contact contact) {
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement insert = conn.prepareStatement("INSERT INTO webapi.contact (name) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            insert.setString(1, contact.getName());
            int affectedRows = insert.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Create failed, no rows affected");
            }
            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
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
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement update = conn.prepareStatement("UPDATE webapi.contact SET name = ? WHERE id = ?")
        ) {
            update.setString(1, contact.getName());
            update.setInt(2, contact.getId());

            int affectedRows = update.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Create failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement delete = conn.prepareStatement("DELETE FROM webapi.contact WHERE id = ?")
        ) {
            delete.setInt(1, contact.getId());

            int affectedRows = delete.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Create failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
