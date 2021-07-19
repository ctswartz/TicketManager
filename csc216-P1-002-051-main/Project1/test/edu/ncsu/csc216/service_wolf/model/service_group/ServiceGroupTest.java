/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.service_group;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.command.Command.CommandValue;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * Test class for the ServiceGroup class 
 * @author kaceyjohnson
 *
 */
public class ServiceGroupTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#setIncidentCounter()}.
	 */
	@Test
	public void testSetIncidentCounter() {
		ServiceGroup s = new ServiceGroup("CSC");
		ArrayList<String> list = new ArrayList<String>();
		list.add("test");
		list.add("test2");
		Incident i3 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		s.addIncident(i3);
		s.setIncidentCounter();
		assertEquals(2, Incident.counter);
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#setServiceGroupName(java.lang.String)}.
	 */
	@Test
	public void testSetServiceGroupName() {
		ServiceGroup s = new ServiceGroup("CSC");

		try {
			s.setServiceGroupName("");
			fail(); 
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		
		try {
			s.setServiceGroupName("CSCIT");
		} catch (IllegalArgumentException e) {
			fail();
		}
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#getServiceGroupName()}.
	 */
	@Test
	public void testGetServiceGroupName() {
		ServiceGroup s = new ServiceGroup("CSC");
		assertEquals(s.getServiceGroupName(), "CSC");

		try {
			s.setServiceGroupName("");
			fail();
		} catch (IllegalArgumentException e) 	{
			assertEquals("Invalid service group name.", e.getMessage());
		}
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#addIncident(edu.ncsu.csc216.service_wolf.model.incident.Incident)}.
	 */
	@Test
	public void testAddIncident() {
		ServiceGroup s = new ServiceGroup("CSC");
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(6, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i2 = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i3 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		s.addIncident(i);
		s.addIncident(i2);
		s.addIncident(i3);
		assertEquals(1, s.getIncidents().get(0).getId());
 	}

	/**
	 * Test method for getIncidents
	 */
	@Test
	public void testGetIncidents() {
		ServiceGroup s = new ServiceGroup("CSC");
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(6, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i2 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		List<Incident> incidents = new ArrayList<Incident>();
		incidents.add(i2);
		incidents.add(i);
		s.addIncident(i2);
		s.addIncident(i);
		assertEquals(incidents.get(0), s.getIncidents().get(0));
 	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#getIncidentById(int)}.
	 */
	@Test
	public void testGetIncidentById() {
		ServiceGroup s = new ServiceGroup("CSC");
		ArrayList<String> list = new ArrayList<String>();
		list.add("test");
		list.add("test2");
		Incident i2 = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i3 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		s.addIncident(i3);
		s.addIncident(i2);
		Incident e = s.getIncidentById(i2.getId());
		assertEquals(e.getId(), i2.getId());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#executeCommand(int, edu.ncsu.csc216.service_wolf.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		ServiceGroup s = new ServiceGroup("CSC");
		ArrayList<String> list = new ArrayList<String>();
		list.add("test");
		list.add("test2");
		Incident i2 = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "In Progress", list);
		s.addIncident(i2);
		

		Command c = new Command(CommandValue.HOLD, "Awaiting Vendor", "Test");
		s.executeCommand(2, c);


		assertEquals("Awaiting Vendor", s.getIncidentById(2).getStatusDetails());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup#deleteIncidentById(int)}.
	 */
	@Test
	public void testDeleteIncidentById() {
		ServiceGroup s = new ServiceGroup("CSC");
		ArrayList<String> list = new ArrayList<String>();
		list.add("test");
		list.add("test2");
		assertEquals(0, s.getIncidents().size());
		Incident i2 = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		s.addIncident(i2);
		assertEquals(1, s.getIncidents().size());
		s.deleteIncidentById(2);
		assertEquals(0, s.getIncidents().size());

		
	}
}
