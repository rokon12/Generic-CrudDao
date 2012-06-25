/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 20, 2012	
 **************/

package org.codexplo.cruder.dao.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = { "classpath:/codexplo-dao-context.xml" }, inheritLocations = true)
public abstract class AbstractTest extends AbstractJUnit4SpringContextTests {

}
