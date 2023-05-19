package io.seata.tcc.order.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultHolder {
    private static Map<String, String> actionOrderResults = new ConcurrentHashMap<String, String>();

    /**
     * Set action one result.
     *
     * @param txId   the tx id
     * @param result the result
     */
    public static void setActionOrderResult(String txId, String result) {
        actionOrderResults.put(txId, result);
    }

    /**
     * Get action one result string.
     *
     * @param txId the tx id
     * @return the string
     */
    public static String getActionOrderResult(String txId) {
        return actionOrderResults.get(txId);
    }

    public static void removeResult(String xid) {
        actionOrderResults.remove(xid);
    }
}
