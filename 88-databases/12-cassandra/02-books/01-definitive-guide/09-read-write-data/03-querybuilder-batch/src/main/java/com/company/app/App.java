package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.querybuilder.Batch;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

// QueryBuilder.batch() is another way for batch statement
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

        Insert hotelsByPoiInsertBuilt = QueryBuilder.insertInto("hotels_by_poi")
                .value("poi_name", poiName)
                .value("hotel_id", hotelId)
                .value("name", hotelName)
                .value("phone", hotelPhone);
        batch.add(hotelsByPoiInsertBuilt);

        ResultSet hotelInsertResult = session.execute(batch);

        System.out.println("# hotelInsertResult metadata");
        System.out.println(hotelInsertResult);
        System.out.println(hotelInsertResult.wasApplied());
        System.out.println(hotelInsertResult.getExecutionInfo());
        System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());

        Select.Where hotelSelectBuilt = QueryBuilder.select()
                .all()
                .from("hotels")
                .where(eq("id", hotelId));

        ResultSet hotelSelectResult = session.execute(hotelSelectBuilt);

        System.out.println("# Print hotels");
        hotelSelectResult.forEach(r -> System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                r.getString("id"), r.getString("name"), r.getString("phone")));

        Select hotelsByPoiSelectBuilt = QueryBuilder.select()
                .all()
                .from("hotels_by_poi");

        ResultSet hotelsByPoiResult = session.execute(hotelsByPoiSelectBuilt);

        System.out.println("# Print hotels by poi");
        hotelsByPoiResult.forEach(r -> System.out.format("poi_name: %s, hotel_id: %s, name: %s, phone: %s\n",
                r.getString("poi_name"), r.getString("hotel_id"), r.getString("name"), r.getString("phone")));

        session.close();
        cluster.close();

    }

}
/*
output:
# hotelInsertResult metadata
ResultSet[ exhausted: true, Columns[]]
true
com.datastax.driver.core.ExecutionInfo@1ba9117e
null
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
# Print hotels by poi
poi_name: WestWorld, hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
 */
