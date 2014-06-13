/**
 * 
 */
package org.own.ste.admin.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



/**
 * Here we are doing test for Spring security xml.
 * 
 * @author ramug
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
public class SecurityXmlTest {
	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

	/**
	 * This is the set up method to do set up.
	 */
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilter(this.springSecurityFilterChain, "/*").build();
	}
	
   /**
	 * Whenever we are hitting any url first we have to do authentication.
	 * 
	 * @throws Exception
	 */
	@Test
	public void successAuthentication() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/j_spring_security_check")
		            .param("j_username","ramu")
		            .param("j_password", "gadde")).andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}
	
	/**
	 * If we give wrong name and wrong password it redirects to error url.
	 * @throws Exception
	 */
	@Test
	public void failureRedirectUrlAuthentication() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/j_spring_security_check")
	            .param("j_username","ramu")
	            .param("j_password", "1234567")).andExpect(MockMvcResultMatchers.redirectedUrl("/spring_security_login?login_error"));
	}
	
	
	/**
	 * User name authentication.
	 * @throws Exception
	 */
	@Test
	public void userAuthenticates() throws Exception{
		final String userName="jeevana";
		mockMvc.perform(MockMvcRequestBuilders.post("/j_spring_security_check")
			   .param("j_username",userName)
			   .param("j_password","gadde"))
			   .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
			   .andExpect(new ResultMatcher() {
				
				@Override
				public void match(MvcResult result) throws Exception {
					HttpSession session=result.getRequest().getSession();
					SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
					Assert.assertEquals(userName,securityContext.getAuthentication().getName());
					
				}
			});
			   
     }
	
	
	/**
	 * If ROLE_USER,ROLE_M1,ROLE_M2 in roles(USER),roles("M1",M2").
	 * referred by http://spring.io/blog/2014/05/23/preview-spring-security-test-web-security,
	 * to work this method two jars added they are org.springframework:spring-test:4.0.2.RELEASE,
	 * org.springframework.security:spring-security-test:4.0.0.M1 and updated repositories in 
	 * gradle like below,
	 * maven {
     *       url "https://repo.spring.io/libs-milestone"
     *       url "https://repo.spring.io/libs-snapshot"
     *   }
	 * Roles Check.
	 * @throws Exception
	 */
	@Test
	public void rolesCheck() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/j_spring_security_check")
			   .with(user("ramu").password("gadde").roles("USER"))
			   .with(user("jeevana").password("gadde").roles("M1","M2")));
	}
	
	
}
