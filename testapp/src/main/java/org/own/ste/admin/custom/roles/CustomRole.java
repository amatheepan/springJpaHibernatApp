/**
 * 
 */
package org.own.ste.admin.custom.roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom Roles will be assigned.
 * @author ramug
 *
 */
public class CustomRole implements UserDetailsService{
   
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		//TODO:It will be a database call in future.
		List<GrantedAuthority> listOfGrantedAuthorities=new ArrayList<GrantedAuthority>();
		GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("ROLE_M1");
		GrantedAuthority grantedAuthority1=new SimpleGrantedAuthority("ROLE_M2");
		listOfGrantedAuthorities.add(grantedAuthority);
		listOfGrantedAuthorities.add(grantedAuthority1);
		UserDetails userDetails=new User("jeevana","gadde",listOfGrantedAuthorities);
		return userDetails;
	}
}
