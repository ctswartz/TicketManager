/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.manager;

import static org.junit.Assert.*;


import java.util.ArrayList;
import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.command.Command.CommandValue;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ServiceWolfManager class 
 * @author kaceyjohnson
 */
public class ServiceWolfManagerTest {
	/** Valid test file */
	private static String validTestFile = "test-files/incidents1.txt";
	/** Invalid file */
	private static String invalidTestFile = "test-files/incidents.txt";
	/** Instance of the ServiceWolfManager */
	private static ServiceWolfManager manager;
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = ServiceWolfManager.getInstance();
		manager.resetManager();
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#saveToFile(java.lang.String)}.
	 */
	@Test
	public void testSaveToFile() {
		try {
			manager.saveToFile(validTestFile);
			fail();
		} catch(IllegalArgumentException e)	{
			assertEquals("Unable to save file.", e.getMessage());
		}
		ArrayList<String> list = new ArrayList<String>();
		list.add("Help needed.");	
		Incident incident1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		ServiceGroup s = new ServiceGroup("C");
		ServiceGroup s1 = new ServiceGroup("B");
		ServiceGroup s2 = new ServiceGroup("A");
		manager.addServiceGroupToListByName(s);
		manager.addServiceGroupToListByName(s1);
		manager.addServiceGroupToListByName(s2);
		manager.currentServiceGroup = s;
		manager.currentServiceGroup.addIncident(incident1);
		try {
			manager.saveToFile(validTestFile);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		
 	} 
	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#loadFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadFromFile() {
		manager.loadFromFile(validTestFile);
		assertEquals(3, manager.serviceGroup.size());
		
		try {
			manager.loadFromFile(invalidTestFile);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#getIncidentsAsArray()}.
	 */
	@Test
	public void testGetIncidentsAsArray() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Help needed.");	
		Incident incident1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		ServiceGroup sg = new ServiceGroup("CSC");
		sg.addIncident(incident1);
		manager.addServiceGroupToListByName(sg);
		manager.currentServiceGroup = sg;
		assertEquals("1", manager.getIncidentsAsArray()[0][0]);
		assertEquals("New", manager.getIncidentsAsArray()[0][1]);
		assertEquals("title", manager.getIncidentsAsArray()[0][2]);
		assertEquals("Awaiting Caller", manager.getIncidentsAsArray()[0][3]);
 	}
 
	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#getIncidentById(int)}.
	 */
	@Test
	public void testGetIncidentById() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Help needed.");	
		Incident incident1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		ServiceGroup sg = new ServiceGroup("CSC");
		sg.addIncident(incident1);
		manager.addServiceGroupToListByName(sg);
		manager.currentServiceGroup = sg;
		assertEquals(incident1, manager.getIncidentById(1));
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#executeCommand(int, edu.ncsu.csc216.service_wolf.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		ArrayList<String> list = new ArrayList<String>();
		Command c = new Command(CommandValue.CANCEL, "Unnecessary", "Test");
		list.add("Help needed.");	
		Incident incident1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		ServiceGroup sg = new ServiceGroup("CSC");
		sg.addIncident(incident1);
		manager.addServiceGroupToListByName(sg);
		manager.currentServiceGroup = sg;
		manager.executeCommand(1, c);
		assertEquals("Canceled", manager.currentServiceGroup.getIncidentById(1).getState());
//		assertEquals("Unnecessary", manager.currentServiceGroup.getIncidentById(1).getStatusDetails());
 	}

	/**
	 * Test method for deleteByIncidentId
	 */
	@Test
	public void testDeleteByIncidentId() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Help needed.");	
		Incident incident1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		ServiceGroup sg = new ServiceGroup("CSC");
		sg.addIncident(incident1);
		manager.addServiceGroupToListByName(sg);
		manager.currentServiceGroup = sg;
		assertEquals(1, manager.serviceGroup.get(0).getIncidentById(1).getId());
		manager.deleteIncidentById(1);
		assertEquals(null, manager.serviceGroup.get(0).getIncidentById(1));
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#addIncidentToServiceGroup(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddIncidentToServiceGroup() {
		ServiceGroup sg = new ServiceGroup("CSC");
		manager.addServiceGroupToListByName(sg);
		manager.currentServiceGroup = sg;
		manager.addIncidentToServiceGroup("title", "caller", "Test");
		assertEquals(sg.getIncidentById(2), manager.currentServiceGroup.getIncidentById(2));
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#loadServiceGroup(java.lang.String)}.
	 */
	@Test
	public void testLoadServiceGroup() {
		manager.loadServiceGroup("CSC");
		assertEquals("CSC", manager.serviceGroup.get(0).getServiceGroupName());
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#getServiceGroupName()}.
	 */
	@Test
	public void testGetServiceGroupName() {
		ServiceGroup sg = new ServiceGroup("CSC");
		manager.currentServiceGroup = sg;
		assertEquals("CSC", manager.getServiceGroupName());
 	}
	

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#getServiceGroupList()}.
	 */
	@Test
	public void testGetServiceGroupList() {
		manager.addServiceGroup("CSC");
		manager.addServiceGroup("CSC IT");
		String[] serviceGroupNames = {"CSC", "CSC IT"};
		assertEquals(serviceGroupNames[0], manager.getServiceGroupList()[0]);
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#clearServiceGroups()}.
	 */
	@Test
	public void testClearServiceGroups() {
		manager.addServiceGroup("CSC");
		manager.addServiceGroup("CSC IT");
		String[] serviceGroupNames = {"CSC", "CSC IT"};
		assertEquals(serviceGroupNames[0], manager.getServiceGroupList()[0]);
		assertEquals(serviceGroupNames[1], manager.getServiceGroupList()[1]);
		manager.clearServiceGroups();
		assertEquals(0, manager.serviceGroup.size());
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#editServiceGroup(java.lang.String)}.
	 */
	@Test
	public void testEditServiceGroup() {
		ServiceGroup s = new ServiceGroup("CSC");
		manager.currentServiceGroup = s;
		manager.editServiceGroup("CSC IT");
		assertEquals("CSC IT", manager.getServiceGroupName());
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#addServiceGroup(java.lang.String)}.
	 */
	@Test
	public void testAddServiceGroup() {
		manager.addServiceGroup("CSC");
		manager.addServiceGroup("CSC IT");
		String[] serviceGroupNames = {"CSC", "CSC IT"};
		assertEquals(serviceGroupNames[0], manager.getServiceGroupList()[0]);
		assertEquals(serviceGroupNames[1], manager.getServiceGroupList()[1]);
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#addServiceGroupToListByName(edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup)}.
	 */
	@Test
	public void testAddServiceGroupToListByName() {
		ServiceGroup s = new ServiceGroup("C");
		ServiceGroup s1 = new ServiceGroup("B");
		ServiceGroup s2 = new ServiceGroup("A");
		manager.addServiceGroupToListByName(s);
		manager.addServiceGroupToListByName(s1);
		manager.addServiceGroupToListByName(s2);

	    String[] serviceGroupNames = {"B", "A", "C"};
		assertEquals(serviceGroupNames[1], manager.getServiceGroupList()[0]);
		assertEquals(serviceGroupNames[0], manager.getServiceGroupList()[1]);
		assertEquals(serviceGroupNames[2], manager.getServiceGroupList()[2]);
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#checkDuplicateServiceName(java.lang.String)}.
	 */
	@Test
	public void testCheckDuplicateServiceName() {
		ServiceGroup s = new ServiceGroup("CSC");
		ServiceGroup s1 = new ServiceGroup("CSC");
		manager.addServiceGroupToListByName(s);
		try {
			manager.addServiceGroupToListByName(s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.manager.ServiceWolfManager#deleteServiceGroup()}.
	 */
	@Test
	public void testDeleteServiceGroup() {
		ServiceGroup s = new ServiceGroup("C");
		ServiceGroup s1 = new ServiceGroup("B");
		ServiceGroup s2 = new ServiceGroup("A");
		manager.addServiceGroupToListByName(s);
		manager.addServiceGroupToListByName(s1);
		manager.addServiceGroupToListByName(s2);
		manager.currentServiceGroup = s;
		manager.deleteServiceGroup();
		assertEquals("A", manager.currentServiceGroup.getServiceGroupName());
 	}

}
