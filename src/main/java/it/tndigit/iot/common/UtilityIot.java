package it.tndigit.iot.common;

import it.tndigit.iot.web.utils.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Mirko Pianetti
 * Utility class for the project
 *
 *
 */
public class UtilityIot {


	/**
	 *
	 * @return String
	 *
	 * get the username from the principal
	 * see JwtAuthenticationTokenBeforeFilter class for the specific string
	 *
	 */

	private UtilityIot() {
		throw new IllegalStateException("Utility class");
	}

	public static String getUserName() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getPrincipal() instanceof User) {
				User user = (User) authentication.getPrincipal();
				return user.getUsername();
			}else if (authentication.getPrincipal() instanceof JwtUser) {
				JwtUser user = (JwtUser) authentication.getPrincipal();
				return user.getUsername();
			} else {
				return (String) authentication.getPrincipal();
			}

		} catch (Exception e) {
			return "";
		}
	}


}
