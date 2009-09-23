package net.sziebert.tutorials.service.impl;

import net.sziebert.tutorials.dao.ApplicationRepository;
import net.sziebert.tutorials.entity.User;
import net.sziebert.tutorials.exception.ServiceException;
import net.sziebert.tutorials.service.ApplicationService;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <code>ApplicatonServiceImpl</code> is a simple implementation
 * of the <code>ApplicationService</code> interface.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger logger = Red5LoggerFactory.getLogger(ApplicationServiceImpl.class, "hibernate");

    private ApplicationRepository repository;

    /**
     * @see ApplicationService#getUser(String, String)
     */
    @Transactional(readOnly = true)
    public User getUser(String user, String pass) throws ServiceException {
        logger.debug("Looking up user for user/pass of: {}/{}", user, pass);
        try {
            // Return the result of the data access call.
            return repository.getUser(user, pass);
        }
        catch (Exception e) {
            logger.error("Could not load user with credentials: {}/{}", user, pass);
            throw new ServiceException("Could not load user!", e);
		}
	}

    /**
     * Store the <code>ApplicationDAO</code> for reuse.
     *
     * @param repository
     */
    @Required
    public void setApplicationRepository(ApplicationRepository repository) {
        this.repository = repository;
    }
}
