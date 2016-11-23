package com.company.app;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;

/*
Batch mechanism allows you to group modifications to multiple partitions into a single statement
The semantics of the batch operation are as follows:
- Only modification statements (INSERT, UPDATE, or DELETE) may be included in a batch.
- Batches are atomic—that is, if the batch is accepted, all of the statements in a batch will succeed eventually. This is why Cassandra’s batches are sometimes referred to as atomic batches or logged batches.
- All updates in a batch belonging to a given partition key are performed in isolation, but there is no isolation guarantee across partitions. This means that modifications to different partitions may be read before the batch completes.
- Batches are not a transaction mechanism, but you can include lightweight transaction statements in a batch. Multiple lightweight transactions in a batch must apply to the same partition.
- Counter modifications are only allowed within a special form of batch known as a counter batch. A counter batch can only contain counter modifications.
 */
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

        // create parameterized INSERT statement
        SimpleStatement hotelInsert = new SimpleStatement(
                "INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)",
                hotelId, hotelName, hotelPhone);
        SimpleStatement hotelsByPoiInsert = new SimpleStatement(
                "INSERT INTO hotels_by_poi (poi_name, hotel_id, name, phone) VALUES (?, ?, ?, ?)",
                poiName, hotelId, hotelName, hotelPhone);

        BatchStatement hotelBatch = new BatchStatement();
        hotelBatch.add(hotelsByPoiInsert);
        hotelBatch.add(hotelInsert);

        ResultSet hotelInsertResult = session.execute(hotelBatch);

        System.out.println("# hotelInsertResult metadata");
        System.out.println(hotelInsertResult);
        System.out.println(hotelInsertResult.wasApplied());
        System.out.println(hotelInsertResult.getExecutionInfo());
        System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());


        SimpleStatement hotelSelect = new SimpleStatement("SELECT * FROM hotels WHERE id=?", hotelId);
        hotelSelect.enableTracing();

        ResultSet hotelSelectResult = session.execute(hotelSelect);

        System.out.println("# Print hotels");
        for (Row row : hotelSelectResult) {
            System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                    row.getString("id"), row.getString("name"), row.getString("phone"));
        }

        SimpleStatement hotelsByPoiSelect = new SimpleStatement("SELECT * FROM hotels_by_poi");
        ResultSet hotelsByPoiResult = session.execute(hotelsByPoiSelect);

        System.out.println("# Print hotels by poi");
        hotelsByPoiResult.forEach(row -> System.out.format("hotel_id: %s, name: %s, phone: %s\n",
                row.getString("hotel_id"), row.getString("name"), row.getString("phone")));

        session.close();
        cluster.close();

    }

}
/*
output:
# hotelInsertResult metadata
ResultSet[ exhausted: true, Columns[]]
true
com.datastax.driver.core.ExecutionInfo@37858383
null
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
# Print hotels by poi
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
 */