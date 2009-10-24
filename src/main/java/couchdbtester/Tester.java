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
    private int numTries;
    
    public Tester(com.fourspaces.couchdb.Session s, String db, String doc, Integer i) {
        
        this.session = s;
        this.dbName = db;
        this.docName = doc;
        
        this.db = this.session.getDatabase(dbName);

        this.document = this.db.getDocument(docName);

        this.numTries = i.intValue();
    }

    public Object get(String key) {
        try {
            this.document.refresh();
            return this.document.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    void put(String key, byte[] value) throws Exception {
        boolean successful = false;
        int tries = 0;

        while(!successful && tries<this.numTries) {
            try {
                this.document.put(key, value);
                this.db.saveDocument(document);
                successful = true;
            } catch(Exception ex) {
                this.document.refresh();
                tries++;
            }
        }

        if(successful == false) {
            throw new Exception("Unable to store value");
        }
    }
}
