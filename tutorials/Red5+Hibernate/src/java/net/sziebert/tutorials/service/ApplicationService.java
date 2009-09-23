package net.sziebert.tutorials.service;

import net.sziebert.tutorials.entity.User;
import net.sziebert.tutorials.exception.ServiceException;

/**
 * <code>ApplicationService</code> is a simple interface
 * used to retrieve application users.
 */
public interface ApplicationService
{
	/**
	 * Retrieve the <code>User</code> for the 
	 * specified user and pass.
	 * 
	 * @param user
	 * @param pass
	 * @return the <code>User</code> for the specified parameters.
	 * @throws ServiceException
	 */
	public User getUser(String user, String pass) throws ServiceException;
}
