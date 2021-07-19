/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Test class for the ServiceGroupsReader class 
 * @author kaceyjohnson
 *
 */
public class ServiceGroupReaderTest {
	/** Test file */
	private final String validTestFile = "test-files/incidents1.txt";
	/** Valid ServiceGroups **/
	private final ArrayList<String> serviceGroupNames = new ArrayList<String>();
	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.io.ServiceGroupsReader#readServiceGroupsFile(java.lang.String)}.
	 */
	@Test
	public void testReadServiceGroupsFile() {
		serviceGroupNames.add("CSC IT"); 
		serviceGroupNames.add("ITECS");
		serviceGroupNames.add("OIT");
		List<ServiceGroup> sg = ServiceGroupsReader.readServiceGroupsFile(validTestFile);
		assertEquals(3, sg.size());
		for (int i = 0; i < serviceGroupNames.size(); i++) {
			assertEquals(serviceGroupNames.get(i), sg.get(i).getServiceGroupName());
		}

 	}

}
