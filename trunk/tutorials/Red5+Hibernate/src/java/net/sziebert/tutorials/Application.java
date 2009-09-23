package net.sziebert.tutorials;

import net.sziebert.tutorials.entity.User;
import net.sziebert.tutorials.exception.ServiceException;
import net.sziebert.tutorials.service.ApplicationService;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * <code>Application</code> is a simple <code>ApplicationAdapter</code>
 * delegate which retrieves a <code>User</code> object when a connection
 * attempt is made.
 */
public class Application extends ApplicationAdapter {

    private static final Logger logger = Red5LoggerFactory.getLogger(Application.class, "hibernate");

    private ApplicationService service;

    /* ----- ApplicationAdapter delegate methods ----- */

    /**
     * Delegate method used to accept/reject incoming connection requests.
     *
     * @param conn
     * @param params
     * @return true/false
     */
    @Override
    public boolean roomConnect(IConnection conn, Object[] params) {
        logger.debug("New connection attempt from {}...", conn.getRemoteAddress());
        // Parse the user/pass out of the connection parameters
        String userName = (String) params[0];
        String password = (String) params[1];
        // Get the User object for this connection
        User user = null;
        // Insure that we have received the proper set of parameters.
        if (isNotBlank(userName) &&
                isNotBlank(password)) {
            user = authenticate(userName, password);
        }
        // If we got a valid user object, then allow the connection. Otherwise,
        // the user could not be found or something bad happened. In either
        // case, we do not want to allow the connection.
        return user != null && super.roomConnect(conn, params);
    }

    /**
     * Delegate method which logs connection/client/user disconnections.
     *
     * @param conn
     */
    @Override
    public void roomDisconnect(IConnection conn) {
        logger.debug("Connection closed by {}...", conn.getRemoteAddress());
        // Call the super class to insure that all listeners are properly dismissed.
        super.roomDisconnect(conn);
    }

    /* ----- Application utility methods ----- */

    /**
     * Simple utility method used to authenticate the
     * current connection/client/user.
     *
     * @param user
     * @param pass
     * @return the <code>User</code> object for this connection.
     */
    private User authenticate(String user, String pass) {
        logger.debug("Authenticating user for credentials: {}/{}", user, pass);
        User u = null;
        try {
            // Load the user object from Hibernate
            u = service.getUser(user, pass);
        } catch (ServiceException e) {
            log.error("Could not load user for credentials: {}/{}", user, pass);
        }
        return u;
    }

    /* ----- Spring injected dependencies ----- */

    /**
     * Store the <code>ApplicationService</code> for reuse.
     *
     * @param service
     */
    @Required
    public void setApplicationService(ApplicationService service) {
        this.service = service;
    }
}
