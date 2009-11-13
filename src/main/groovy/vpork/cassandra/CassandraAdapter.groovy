package vpork.cassandra

import cassandratester.Tester
import org.apache.cassandra.service.Cassandra
import vpork.HashClient

/**
 * Adapts the Cassandra interface to the one used by VPork
 */
public class CassandraAdapter implements HashClient {
	private Tester c;

	public CassandraAdapter(org.apache.cassandra.service.Cassandra.Client client, String tableName, String columnFamilyColumn) {
            this.c = new Tester(client, tableName, columnFamilyColumn)
	}

	byte[] get(String key) {
            return c.get(key)
	}

	void put(String key, byte[] value) {
            c.put(key,value)
	}
}
