package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

// It is for cases where there is variation in the query structure (such as optional parameters)
public class App {

    public static void main(String... args){

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
                //.withCredentials("jeff", "i6XJsj!k#9")
                .build();

        // create session on the "hotel" keyspace
        Session session = cluster.connect("hotel");

        // create a Hotel ID
        String id = "AZ123";

        Insert hotelInsertBuilt = QueryBuilder.insertInto("hotels")
                .value("id", id)
                .value("name", "Super Hotel at WestWorld")
                .value("phone", "1-888-999-9999");

        ResultSet hotelInsertResult = session.execute(hotelInsertBuilt);

        System.out.println("# hotelInsertResult metadata");
        System.out.println(hotelInsertResult);
        System.out.println(hotelInsertResult.wasApplied());
        System.out.println(hotelInsertResult.getExecutionInfo());
        System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());


        Select.Where hotelSelectBuilt = QueryBuilder.select()
                .all()
                .from("hotels")
                .where(eq("id", id));

        ResultSet hotelSelectResult = session.execute(hotelSelectBuilt);

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

        Delete.Where hotelDeleteBuilt = QueryBuilder.delete()
                .all()
                .from("hotels")
                .where(eq("id", id));

        ResultSet hotelDeleteResult = session.execute(hotelDeleteBuilt);


        // result metadata
        System.out.println("# hotelDeleteResult metadata");
        System.out.println(hotelDeleteResult);
        System.out.println(hotelDeleteResult.wasApplied());
        System.out.println(hotelDeleteResult.getExecutionInfo());
        System.out.println(hotelDeleteResult.getExecutionInfo().getIncomingPayload());

        session.close();
        cluster.close();

    }

}
/*
output:
# hotelInsertResult metadata
ResultSet[ exhausted: true, Columns[]]
true
com.datastax.driver.core.ExecutionInfo@59af0466
null
# hotelSelectResult metadata
ResultSet[ exhausted: false, Columns[id(varchar), address(frozen<hotel.address>), name(varchar), phone(varchar), pois(set<varchar>)]]
true
com.datastax.driver.core.ExecutionInfo@62230c58
null
# Print hotels
hotel_id: AZ123, name: Super Hotel at WestWorld, phone: 1-888-999-9999
# hotelDeleteResult metadata
ResultSet[ exhausted: true, Columns[]]
true
com.datastax.driver.core.ExecutionInfo@67080771
null
 */
