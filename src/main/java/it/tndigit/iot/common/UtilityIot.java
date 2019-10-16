package it.tndigit.iot.common;

import it.tndigit.iot.web.util.JwtUser;
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



	/**
	 *
	 * @param String[] ruoloRichiesto
	 * @return Boolean (True se ha trovato il ruolo, false se non lo ha trovato)
	 *
	 * Check application roles
	 *
	 */
	@SuppressWarnings("unchecked")
	public static Boolean hasRole(String[] ruoloRichiesto ) {

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  boolean hasRole = false;

		  for (GrantedAuthority authority : authorities) {
		     for(String role : ruoloRichiesto){
		    	 hasRole = authority.getAuthority().equals(role);
		    	 if (hasRole) {
			    	 break;
			     }
		     }
		     if (hasRole) {
		    	 break;
		     }
		  }
		  return hasRole;
		}





}
