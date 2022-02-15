package org.acme;

import org.infinispan.query.api.continuous.ContinuousQueryListener;

public class QueryListener implements ContinuousQueryListener<String, Object[]> {

    public QueryListener() {
      
    }
    
    public void resultJoining(String key, Object[] value) {
        System.out.println("resultJoining "+key);
    }
 
    /**
     * Receives notification that a cache entry from the matching set was updated and continues to match the query. The
     * modified attributes causing this update are not necessarily part of the query.
     *
     * @param key   the key of the joining entry
     * @param value the joining entry or the Object[] projection if specified
     */
     public void resultUpdated(String key, Object[] value) {
       System.out.println("resultUpdated "+key);
    }
 
    /**
     * Receives notification that a cache entry has left the matching set. This can happen due to an update or removal.
     *
     * @param key the key of the leaving entry
     */
     public void resultLeaving(String key) {
       System.out.println("resultLeaving "+key);
    }
}
