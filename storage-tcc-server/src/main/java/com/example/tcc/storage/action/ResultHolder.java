package com.example.tcc.storage.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultHolder {
    private static final Map<String, String> actionStorageResults = new ConcurrentHashMap<String, String>();

    /**
     * Set action one result.
     *
     * @param txId   the tx id
     * @param result the result
     */
    public static void setActionOrderResult(String txId, String result) {
        actionStorageResults.put(txId, result);
    }

    /**
     * Get action one result string.
     *
     * @param txId the tx id
     * @return the string
     */
    public static String getActionOrderResult(String txId) {
        return actionStorageResults.get(txId);
    }

    public static void removeResult(String xid) {
        actionStorageResults.remove(xid);
    }
}
