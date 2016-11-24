package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.*;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

public class App {

    public static void main(String... args) {

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
                //.withCredentials("jeff", "i6XJsj!k#9")
                .build();

        // create session on the "hotel" keyspace
        Session session = cluster.connect("hotel");

        // create a Hotel ID
        String hotelId = "AZ123";
        String hotelName = "Super Hotel at WestWorld";
        String hotelPhone = "1-888-999-9999";
        String poiName = "WestWorld";

        Batch batch = QueryBuilder.batch();

        Insert hotelInsertBuilt = QueryBuilder.insertInto("hotels")
                .value("id", hotelId)
                .value("name", hotelName)
                .value("phone", hotelPhone);
        batch.add(hotelInsertBuilt);

        Select.Where hotelSelectBuilt = QueryBuilder.select()
                .all()
                .from("hotels")
                .where(eq("id", hotelId));

        ResultSet hotelSelectResult = session.execute(hotelSelectBuilt);

        System.out.println("# Print hotels");
        hotelSelectResult.forEach(r -> System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                r.getString("id"), r.getString("name"), r.getString("phone")));

        Delete.Where hotelDeleteBuilt = QueryBuilder.delete()
                .all()
                .from("hotels")
                .where(eq("id", hotelId));

        ResultSet hotelDeleteResult = session.execute(hotelDeleteBuilt);

        System.out.println("# Print hotels after deleted");
        hotelSelectResult.forEach(r -> System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                r.getString("id"), r.getString("name"), r.getString("phone")));

        session.close();
        cluster.close();

    }

}
/*
output:
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
# Print hotels after deleted
 */
