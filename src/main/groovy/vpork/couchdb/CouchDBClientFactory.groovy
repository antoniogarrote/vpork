package vpork.couchdb

import vpork.HashClient

import java.util.Map
import java.util.concurrent.ConcurrentHashMap
import vpork.HashClientFactory
import vpork.StatsLogger
import com.fourspaces.couchdb.Session
import com.fourspaces.couchdb.Document


class CouchDBClientFactory implements HashClientFactory {
    private Map hash = new ConcurrentHashMap()
    private ConfigObject cfg
    private List<String> nodes
    private Random r = new Random();

    HashClient createClient() {
        String databaseName = cfg.storeFactory.database
        String documentName = cfg.storeFactory.document


        String node = nodes[r.nextInt(nodes.size())]
        Session s = new Session(node,cfg.storeFactory.storePort)
        return new vpork.couchdb.CouchDBAdapter(s,databaseName,documentName)
    }

    void setup(ConfigObject cfg, StatsLogger logger, List<String>factoryArgs) {
        this.cfg = cfg
        this.nodes = NodesUtil.loadNodes(logger, factoryArgs)
    }
}
