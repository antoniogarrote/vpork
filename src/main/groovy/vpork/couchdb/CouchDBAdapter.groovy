package vpork.couchdb

import com.fourspaces.couchdb.Session
import com.fourspaces.couchdb.Document
import vpork.HashClient

/**
 * Adapts the CouchDB4java interface to the one used by VPork
 */
public class CouchDBAdapter implements HashClient {
    private com.fourspaces.couchdb.Session session
    private com.fourspaces.couchdb.Document document
    private com.fourspaces.couchdb.Database database

    public CassandraAdapter(com.fourspaces.couchdb.Session session, String, databaseName, String documentName) {
        this.database = database
        this.session = session

        this.database = session.getDatabase(databaseName);

        this.document = db.getDocument(documentName);

    }

    byte[] get(String key) {
        try {
            document.refresh()
            return document.get(key)
        } catch (NotFoundException e) {
            return null
        }
    }

    void put(String key, byte[] value) {
        document.put(key,value)
        database.save(document)
    }
}
