package net.sziebert.tutorials.dao.hibernate;

import net.sziebert.tutorials.dao.ApplicationRepository;
import net.sziebert.tutorials.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * <code>HibernateApplicationDAO</code> is the default implementation
 * of <code>ApplicationDAO</code>.  It is responsible for querying
 * for application users.
 */
@Repository
public class HibernateApplicationRepository implements ApplicationRepository {
    
    private static final Logger logger = Red5LoggerFactory.getLogger(HibernateApplicationRepository.class, "hibernate");

    private SessionFactory sessionFactory;

    /**
     * @see net.sziebert.tutorials.dao.ApplicationRepository#getUser(String, String)
     */
    public User getUser(String user, String pass) {
        logger.debug("Retrieving user from the database.");
        // Ask Hibernate to query for the user based upon the specified user/pass.
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
        crit.add(Restrictions.eq("userName", user));
        crit.add(Restrictions.eq("password", pass));
        User u = (User) crit.uniqueResult();
        // Insure that we got something back from Hibernate.
        if (null == u) {
            logger.warn("User does not exist for credentials: {}/{}", user, pass);
            throw new ObjectRetrievalFailureException(User.class, user);
        }
        // Return the results.
        return u;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
