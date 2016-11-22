package com.company.app;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

// PreparedStatement is designed to handle repeatedly queries more efficiently.
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
        PreparedStatement hotelInsertPrepared = session.prepare(
                "Insert INTO hotels (id, name, phone) VALUES (?, ?, ?)");

        BoundStatement hotelInsertBound = hotelInsertPrepared.bind(
                id, "Super Hotel at WestWorld", "1-888-999-9999");

        ResultSet hotelInsertResult = session.execute(hotelInsertBound);

        System.out.println("# hotelInsertResult metadata");
        System.out.println(hotelInsertResult);
        System.out.println(hotelInsertResult.wasApplied());
        System.out.println(hotelInsertResult.getExecutionInfo());
        System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());


        PreparedStatement hotelSelect = session.prepare("SELECT * FROM hotels WHERE id=?");
        BoundStatement hotelSelectBound = hotelSelect.bind(id);

        ResultSet hotelSelectResult = session.execute(hotelSelectBound);

        // result metadata
        System.out.println("# hotelSelectResult metadata");
        System.out.println(hotelSelectResult);
        System.out.println(hotelSelectResult.wasApplied());
        System.out.println(hotelSelectResult.getExecutionInfo());
        System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());

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
com.datastax.driver.core.ExecutionInfo@2beee7ff
null
# hotelSelectResult metadata
ResultSet[ exhausted: false, Columns[id(varchar), address(frozen<hotel.address>), name(varchar), phone(varchar), pois(set<varchar>)]]
true
com.datastax.driver.core.ExecutionInfo@e1de817
null
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
 */
