package org.raphets.todayinhistory.http;

/**
 * Created by RaphetS on 2016/10/19.
 */

public class GrilHttppResponse<T> {
    private boolean error;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
