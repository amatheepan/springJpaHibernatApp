/**
 * 
 */
package org.own.ste.admin.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Integration test suite will be place here.
 * @author ramug
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/spring_app_context.xml"})
@WebAppConfiguration
public @interface IntegrationTest {
    //EMPTY
}
