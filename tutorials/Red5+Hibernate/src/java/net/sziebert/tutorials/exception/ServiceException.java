package net.sziebert.tutorials.exception;

/**
 * Base exception generated in service layer classes.
 */
public class ServiceException extends Exception
{
	/**
     * Constructor with message.
     *
     * @param message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructor with message and root cause.
     *
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
