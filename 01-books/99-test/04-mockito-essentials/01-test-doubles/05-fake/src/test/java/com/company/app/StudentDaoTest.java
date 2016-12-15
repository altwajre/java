package com.company.app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class Student {
    private final String roleNumber;
    private final String name;

    public Student(String roleNumber, String name) {
        this.roleNumber = roleNumber;
        this.name = name;
    }

    public String getRoleNumber() {
        return roleNumber;
    }

    public String getName() {
        return name;
    }
}

class JdbcSupport {
    public int[] batchUpdate(String sql, List<Map<String, Object>> params) {
        // original db access code go here
        return null;
    }
}

interface StudentDao {
    void batchUpdate(List<Student> students);
}

class StudentDaoImpl implements StudentDao {
    @Override
    public void batchUpdate(List<Student> students) {
        List<Student> insertList = new ArrayList<>();
        List<Student> updateList = new ArrayList<>();
        for (Student student : students) {
            if (student.getRoleNumber() == null) {
                insertList.add(student);
            } else {
                updateList.add(student);
            }
        }
        int rowsInserted = 0;
        int rowsUpdated = 0;
        if (!insertList.isEmpty()) {
            List<Map<String, Object>> paramList = new ArrayList<>();
            for (Student std : insertList) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("name", std.getName());
                paramList.add(param);
            }
            int[] rowCount = update("insert", paramList);
            rowsInserted = sum(rowCount);
        }
        if (!updateList.isEmpty()) {
            List<Map<String, Object>> paramList = new ArrayList<>();
            for (Student std : updateList) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("roleId", std.getRoleNumber());
                param.put("name", std.getName());
                paramList.add(param);
            }
            int[] rowCount = update("update", paramList);
            rowsUpdated = sum(rowCount);
        }
        if (students.size() != (rowsInserted + rowsUpdated)) {
            throw new IllegalStateException("Database update error, expected "
                    + students.size() + " updates but actual "
                    + (rowsInserted + rowsUpdated));
        }
    }

    int[] update(String sql, List<Map<String, Object>> params) {
        return new JdbcSupport().batchUpdate(sql, params);
    }

    private int sum(int[] rows) {
        int sum = 0;
        for (int val : rows) {
            sum += val;
        }
        return sum;
    }
}

public class StudentDaoTest {
    Map<String, Integer> sqlCount = null;

    class TestableStudentDao extends StudentDaoImpl { // fake object
        int[] valuesToReturn;

        @Override
        int[] update(String sql, List<Map<String, Object>> params) {
            Integer count = sqlCount.get(sql);
            if (count == null) {
                sqlCount.put(sql, params.size());
            } else {
                sqlCount.put(sql, count + params.size());
            }

            if (valuesToReturn != null) {
                return valuesToReturn;
            }

            int[] val = new int[params.size()];
            for (int i = 0; i < params.size(); i++) {
                val[i] = 1;
            }
            return val;
        }
    }

    TestableStudentDao dao;

    @Before
    public void setup() {
        dao = new TestableStudentDao();
        sqlCount = new HashMap<>();
    }

    @Test(expected = IllegalStateException.class)
    public void when_row_count_does_not_match_then_rollbacks_transation() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(null, "Gautam Kohli"));
        int[] expect_update_fails_count = {0};
        dao.valuesToReturn = expect_update_fails_count;
        dao.batchUpdate(students);
    }

    @Test
    public void when_new_student_then_creates_student() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(null, "Gautam Kohli"));
        int[] expect_update_success = {1};
        dao.valuesToReturn = expect_update_success;
        dao.batchUpdate(students);
        int actualInsertCount = sqlCount.get("insert");
        int expectedInsertCount = 1;
        assertEquals(expectedInsertCount, actualInsertCount);
    }

    @Test
    public void when_existing_student_then_updates_student_successfully() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("001", "Mark Leo"));
        int[] expect_update_success = {1};
        dao.valuesToReturn = expect_update_success;

        dao.batchUpdate(students);
        int actualUpdateCount = sqlCount.get("update");
        int expectedUpdate = 1;
        assertEquals(expectedUpdate, actualUpdateCount);
    }

    @Test
    public void when_new_and_existing_students_then_creates_and_updates_students() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("001", "Mark Joffe"));
        students.add(new Student(null, "John Villare"));
        students.add(new Student("002", "Maria Rubinho"));

        dao.batchUpdate(students);
        int actualUpdateCount = sqlCount.get("update");
        int expectedUpdate = 2;
        assertEquals(expectedUpdate, actualUpdateCount);
    }
}