package vpork.redis


import vpork.HashClient
import vpork.HashClientFactory
import vpork.StatsLogger
import vpork.NodesUtil;

/**
 * Handles setting up a client connection to Cassandra
 */
public class RedisClientFactory implements HashClientFactory {

    private ConfigObject cfg
    private List<String> nodes

    private Random r = new Random();

    void setup(ConfigObject cfg, StatsLogger logger, List<String>factoryArgs) {
        this.cfg = cfg
        this.nodes = NodesUtil.loadNodes(logger, factoryArgs)
    }

    HashClient createClient() {
        String node = nodes[r.nextInt(nodes.size())]
        int port = cfg.storeFactory.storePort

        return new RedisAdapter(node, port)
    }
}
