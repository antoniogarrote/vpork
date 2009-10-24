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
    //private com.fourspaces.couchdb.Document document
    //private com.fourspaces.couchdb.Database database

    public CouchDBAdapter(com.fourspaces.couchdb.Session session, String databaseName, String documentName) {
        println "\n----------------------------------\n"
        println "CouchDB client for database " + databaseName + " and document " + documentName
        println "----------------------------------\n"
        this.session = session

        this.tester = new Tester(session,databaseName,documentName)
        //this.database = this.session.getDatabase(databaseName);

        //this.document = this.database.getDocument(documentName);

    }

    byte[] get(String key) {
        try {
            //this.document.refresh()
            //return this.document.get(key)
            tester.get(key)
        } catch (Exception e) {
            return null
        }
    }

    void put(String key, byte[] value) {
        //this.document.put(key,value)
        //this.database.save(this.document)
        tester.put(key,value)
    }
}
