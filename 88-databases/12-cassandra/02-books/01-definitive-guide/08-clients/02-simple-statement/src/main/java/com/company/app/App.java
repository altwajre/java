package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;

// SimpleStatement is designed for creating ad-hoc queries
public class App {
    public static void main(String... args){

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
                //.withCredentials("jeff", "i6XJsj!k#9")
                .build();

        // create session on the "hotel" keyspace
        Session session = cluster.connect("hotel");

        // create a Hotel ID
        String id = "AZ123";

        // create parameterized INSERT statement
        SimpleStatement hotelInsert = new SimpleStatement("INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)",
                id, "Super Hotel at WestWorld", "1-888-999-9999");

        ResultSet hotelInsertResult = session.execute(hotelInsert);

        System.out.println("# hotelInsertResult metadata");
        System.out.println(hotelInsertResult);
        System.out.println(hotelInsertResult.wasApplied());
        System.out.println(hotelInsertResult.getExecutionInfo());
        System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());


        SimpleStatement  hotelSelect = new SimpleStatement("SELECT * FROM hotels WHERE id=?", id);
        hotelSelect.enableTracing();

        ResultSet hotelSelectResult = session.execute(hotelSelect);

        // result metadata
        System.out.println("# hotelSelectResult metadata");
        System.out.println(hotelSelectResult);
        System.out.println(hotelSelectResult.wasApplied());
        System.out.println(hotelSelectResult.getExecutionInfo());
        System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());
        System.out.println(hotelSelectResult.getExecutionInfo().getQueryTrace());

        System.out.println("# Print hotels");
        for(Row row : hotelSelectResult){
            System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                    row.getString("id"), row.getString("name"), row.getString("phone"));
        }

        session.close();
        cluster.close();

    }
}
/*
output:
# hotelInsertResult metadata
ResultSet[ exhausted: true, Columns[]]
true
com.datastax.driver.core.ExecutionInfo@78ffe6dc
null
# hotelSelectResult metadata
ResultSet[ exhausted: false, Columns[id(varchar), address(frozen<hotel.address>), name(varchar), phone(varchar), pois(set<varchar>)]]
true
com.datastax.driver.core.ExecutionInfo@76f2bbc1
null
Execute CQL3 query [9b4f13b0-b072-11e6-93b9-65a40f13874c] - 778µs
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
 */
