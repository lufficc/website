package com.lufficc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by lcc_luffy on 2016/8/10.
 */
@Component
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * add other check to the user
     *
     * @param userDetails
     * @param token       from retrieveUser method
     * @throws AuthenticationException
     */

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        logger.info("> additionalAuthenticationChecks");
        logger.info("username:" + userDetails.getUsername());
        logger.info("password:" + userDetails.getPassword());
        logger.info("Credentials:" + token.getCredentials());
        if (token.getCredentials() == null || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }

        if (!passwordEncoder.matches((String) token.getCredentials(),
                userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }
    }

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        logger.info(">>lcc retrieveUser");

        //return username
        logger.info(">> :" + usernamePasswordAuthenticationToken.getName());
        //return password
        logger.info(">> :" + usernamePasswordAuthenticationToken.getCredentials());
        //return username
        logger.info(">> :" + usernamePasswordAuthenticationToken.getPrincipal());


        UserDetails userDetails = accountUserDetailsService
                .loadUserByUsername(username);

        logger.info("<< retrieveUser");
        return userDetails;
    }
}
