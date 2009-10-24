package vpork.couchdb

import com.fourspaces.couchdb.Session
import com.fourspaces.couchdb.Document
import vpork.HashClient
import couchdbtester.Tester

/**
 * Adapts the CouchDB4java interface to the one used by VPork
 */
public class CouchDBAdapter implements HashClient {
    private com.fourspaces.couchdb.Session session
    private couchdbtester.Tester tester

    public CouchDBAdapter(com.fourspaces.couchdb.Session session, String databaseName, String documentName, Integer retries) {
        this.session = session

        this.tester = new Tester(session,databaseName,documentName,retries)
    }

    byte[] get(String key) {
        try {
            tester.get(key)
        } catch (Exception e) {
            return null
        }
    }

    void put(String key, byte[] value) {
        tester.put(key,value)
    }
}
