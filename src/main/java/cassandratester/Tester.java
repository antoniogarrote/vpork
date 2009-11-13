/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cassandratester;

import org.apache.cassandra.service.ColumnPath;
import org.apache.cassandra.service.ConsistencyLevel;
import org.apache.cassandra.service.Cassandra.Client;

/**
 *
 * @author antonio.garrote
 */
public class Tester {
    private Client client;
    private String tableName; //Keyspace1
    private String columnFamilyColumn; //Standard1

    public Tester(Client client, String tableName, String columnFamilyColumn) {
        this.client = client;
	this.tableName = tableName;
	this.columnFamilyColumn = columnFamilyColumn;
    }

    public byte[] get(String key) {
        try {

            return client.get(tableName, key, new ColumnPath(columnFamilyColumn, null, "test".getBytes("UTF-8")), ConsistencyLevel.ONE).getColumn().getValue();

	} catch (Exception e) {
            System.err.println("ERROR:"+e.getMessage());
            return null;
	}
    }

    void put(String key, byte[] value) throws Exception {
        //System.out.println("Writing doc"+key);

       client.insert(tableName,
                     key,
                     new ColumnPath(columnFamilyColumn,null,"test".getBytes("UTF-8")),
                     value,
                     System.currentTimeMillis(),
                     ConsistencyLevel.ONE);
    }
}