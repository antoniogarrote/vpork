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
    private String docKey;

    public Tester(com.fourspaces.couchdb.Session s, String db, String doc, Integer i) {
        this.session = s;
        this.dbName = db;
        this.docName = doc;
        this.docKey = "the_key";

        this.db = this.session.getDatabase(dbName);

        //this.document = this.db.getDocument(docName);

        //System.out.println("DB:" + dbName + " doc:" + doc + " Session:" + this.db.getName());
        this.numTries = i.intValue();
    }

    public Object get(String key) {
        try {
            //System.out.println("Retrieving"+ key);
            this.document = this.db.getDocument(key);
            //System.out.println("KEY:"+this.docKey);
            return this.document.get(this.docKey);
        } catch (Exception e) {
            System.out.println("ERROR");
            byte[] nullresult = new byte[0];
            return nullresult;
        }
    }

    void put(String key, byte[] value) throws Exception {
        //System.out.println("Writing doc"+key);

       // System.out.println("KEY:"+key);
        boolean successful = false;
        int tries = 0;
        try{
        this.document = this.db.getDocument(key);
        } catch(Exception ex) {
            //System.out.println("NUEVO DOC:"+key);
            //System.out.println("Document "+key+" does not exist");
            this.document = new Document();
        }
        while(!successful && tries<this.numTries) {
            try {
                this.document.put(this.docKey, value);
                this.db.saveDocument(this.document,key);
                successful = true;
            } catch(Exception ex) {
                System.out.print(".");
                this.document.refresh();
                tries++;
            }
        }

        if(successful == false) {
            throw new Exception("Unable to store value");
        }
    }
}
