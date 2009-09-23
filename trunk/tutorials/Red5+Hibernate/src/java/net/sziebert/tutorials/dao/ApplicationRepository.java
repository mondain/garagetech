package net.sziebert.tutorials.dao;

import net.sziebert.tutorials.entity.User;

/**
 * <code>ApplicationDAO</code> is a simple interface
 * representing the typical application data access
 * object.
 */
public interface ApplicationRepository {
    
    /**
     * Retrieve the <code>User</code> for the
     * specified user and pass.
     *
     * @param user
     * @param pass
     * @return the <code>User</code> for the specified parameters.
     */
    public User getUser(String user, String pass);
}
