/**
 * 
 */
package org.own.ste.admin.test;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test Controller test.
 * 
 * @author ramug
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
public class TestControllerTest {

	protected MockMvc mockMvc;

	/**
	 * wac.
	 */
	@Autowired
	public WebApplicationContext wac;

	protected ResultMatcher okStatus = MockMvcResultMatchers.status().isOk();

	protected ContentResultMatchers content = MockMvcResultMatchers.content();

	protected ResultMatcher redirectedStatus = MockMvcResultMatchers.status()
			.isMovedTemporarily();

	protected ViewResultMatchers viewResultMatcher = MockMvcResultMatchers
			.view();

	/**
	 * Before SetUp.
	 */
	@Before
	public void beforeSetUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * Test for view.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testControllerForView() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/testdata"))
				.andExpect(okStatus)
				.andExpect(viewResultMatcher.name("test")).andReturn();
        
		//In Test controller we stored key and value pairs in model.	
		Map<String,Object> keyValuePairs=result.getModelAndView().getModel();
		Assert.assertEquals("test data is injected",keyValuePairs.get("testdata"));
	}

}
