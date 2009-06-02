package vpork.cassandra


import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

import org.apache.cassandra.service.Cassandra
import vpork.HashClient;

/**
 * Handles setting up a client connection to Cassandra
 */
public class CassandraClientFactory {
   
    private def cfg
    private List<String> nodes
    
    private Random r = new Random();
    
    CassandraClientFactory(cfg, List<String> nodes) {
        this.cfg           = cfg
        this.nodes         = nodes
    }


    HashClient createClient() {
        String node = nodes[r.nextInt(nodes.size())]
        TTransport transport = new TSocket(node, cfg.storeFactory.storePort)
        TProtocol protocol = new TBinaryProtocol(transport)
        Cassandra.Client client = new Cassandra.Client(protocol)

        transport.open()
        
        return new CassandraAdapter(client, cfg.storeFactory.tableName, cfg.storeFactory.columnFamilyColumn)
    }
    
    void setup() {
    }
     
}