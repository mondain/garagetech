package com.lewdlistings.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class BCryptPasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(BCryptPasswordEncoder.class);

    /**
     * Hash the supplied password using jBCrypt.
     *
     * @param rawPass <p>the user supplied plain text password</p>
     * @param salt <p>ignored, uses a random salt instead</p>
     * @return the hashed password
     * @throws DataAccessException
     */
    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        logger.debug("Encoding password.");
        return BCrypt.hashpw(rawPass, BCrypt.gensalt());
    }

    /**
     * Check the validity of the supplied password.
     *
     * @param encPass <p>the hashed password stored in the database</p>
     * @param rawPass <p>the user supplied plain text password</p>
     * @param salt <p>ignored</p>
     * @return true if the passwords match, false otherwise
     * @throws DataAccessException
     */
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
        logger.debug("Validating password.");
        return BCrypt.checkpw(rawPass, encPass);
    }
}