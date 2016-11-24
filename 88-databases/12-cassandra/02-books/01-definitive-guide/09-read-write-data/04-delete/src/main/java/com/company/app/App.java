package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;

public class App {
    public static void main(String... args) {

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
                //.withCredentials("jeff", "i6XJsj!k#9")
                .build();

        // create session on the "hotel" keyspace
        Session session = cluster.connect("hotel");

        // create a Hotel ID
        String hotelId = "AZ123";

        // create parameterized INSERT statement
        SimpleStatement hotelInsert = new SimpleStatement(
                "INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)",
                hotelId, "Super Hotel at WestWorld", "1-888-999-9999");

        session.execute(hotelInsert);

        SimpleStatement hotelSelect = new SimpleStatement("SELECT * FROM hotels WHERE id=?", hotelId);

        ResultSet hotelSelectResult = session.execute(hotelSelect);

        // result metadata
        System.out.println("# Print hotels");
        hotelSelectResult.forEach(r -> {
            System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                    r.getString("id"), r.getString("name"), r.getString("phone"));
        });

        SimpleStatement hotelDelete = new SimpleStatement("DELETE FROM hotels WHERE id=?", hotelId);

        ResultSet hotelDeleteResult = session.execute(hotelDelete);

        // result metadata
        System.out.println(hotelDeleteResult);
        System.out.println(hotelDeleteResult.wasApplied());
        System.out.println(hotelDeleteResult.getExecutionInfo());
        System.out.println("num results: " + hotelDeleteResult.all().size());

        System.out.println("# Print hotels after deleted");
        hotelSelectResult.forEach(r -> {
            System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                    r.getString("id"), r.getString("name"), r.getString("phone"));
        });

        session.close();
        cluster.close();

    }
}
/*
output:
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
ResultSet[ exhausted: true, Columns[]]
true
com.datastax.driver.core.ExecutionInfo@7e9131d5
num results: 0
# Print hotels again
 */
