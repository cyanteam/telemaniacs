package org.cyanteam.telemaniacs.core.utils;

import org.springframework.dao.DataAccessException;

/**
 * Exception which covers all issues during data access operations
 * in this application.
 *
 * @author Miroslav Kubus
 */
public class TvManagerDataAccessException extends DataAccessException {

    /**
     * Constructs an instance of <code>TvManagerDataAccessException</code> with
     * the default detail message.
     */
    public TvManagerDataAccessException() {
        super("Exception during data access");
    }

    /**
     * Constructs an instance of <code>TvManagerDataAccessException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public TvManagerDataAccessException(String msg) {
        super(msg);
    }

    public TvManagerDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
