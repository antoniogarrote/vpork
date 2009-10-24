/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package couchdbtester;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;

/**
 *
 * @author antonio.garrote
 */
public class Tester {
    private com.fourspaces.couchdb.Session session;
    private String dbName;
    private String docName;
    private Database db;
    private Document document;
    
    public Tester(com.fourspaces.couchdb.Session s, String db, String doc) {
        
        this.session = s;
        this.dbName = db;
        this.docName = doc;
        
        this.db = this.session.getDatabase(dbName);

        this.document = this.db.getDocument(docName);        
    }

    public Object get(String key) {
        try {
            this.document.refresh();
            return this.document.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    void put(String key, byte[] value) {
        this.document.put(key,value);
        this.db.saveDocument(document);
    }
}
