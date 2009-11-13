package redistester;

import org.jredis.JRedis;
import org.jredis.RedisException;
import org.jredis.ri.alphazero.JRedisClient;

/**
 *
 * @author antonio.garrote
 */
public class Tester {
    private JRedis client;

    public Tester(String host, int port) throws RedisException {
        this.client = new JRedisClient(host, port);
        client.ping();
    }

    public byte[] get(String key) {
        try {
            return client.get(key);
	} catch (Exception e) {
            return null;
	}
    }

    void put(String key, byte[] value) throws Exception {
        //System.out.println("Writing doc"+key);
        client.set(key,value);
    }
}
