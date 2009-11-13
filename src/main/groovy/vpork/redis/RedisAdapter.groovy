package vpork.redis

import redistester.Tester
import vpork.HashClient

/**
 * Adapts the Redis interface to the one used by VPork
 */
public class RedisAdapter implements HashClient {
	private Tester c;

	public RedisAdapter(String node, int port) {
            this.c = new Tester(node, port)
	}

	byte[] get(String key) {
            return c.get(key)
	}

	void put(String key, byte[] value) {
            c.put(key,value)
	}
}
