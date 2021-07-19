/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.incident;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.command.Command.CommandValue;

/**
 * Test class for the incident class 
 * @author kaceyjohnson
 *
 */
public class IncidentTest {
	

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getId()}.
	 */
	@Test
	public void testGetId() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getId(), 1);
		Incident i1 = new Incident(3, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(3, i1.getId()); 
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getTitle(), i1.getTitle());
		try {
			Incident i2 = new Incident(1, "", "caller", "owner", 0, "Awaiting Caller", "New", list);
			assertEquals("caller", i2.getCaller());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
		
		try {
			Incident i3 = new Incident(1, null, "caller", "owner", 0, "Awaiting Caller", "New", list);
			assertEquals("owner", i3.getCaller());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getCaller()}.
	 */
	@Test
	public void testGetCaller() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getCaller(), i1.getCaller());
		try {
			Incident i2 = new Incident(1, "title", null, "owner", 0, "Awaiting Caller", "New", list);
			assertEquals("Awaiting Caller", i2.getStatusDetails());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
		
		try {
			Incident i3 = new Incident(1, "title", "", "owner", 0, "Awaiting Caller", "New", list);
			assertEquals("Awaiting Caller", i3.getStatusDetails());

			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getReopenCount()}.
	 */
	@Test
	public void testGetReopenCount() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getReopenCount(), i1.getReopenCount());
		Incident i2 = new Incident(1, "title", "caller", "owner", 6, "Awaiting Caller", "New", list);
		assertEquals(6, i2.getReopenCount()); 
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#setReopenCount(int)}.
	 */
	@Test
	public void testSetReopenCount() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getReopenCount(), i1.getReopenCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getOwner(), i1.getOwner());
		try {
			Incident i2 = new Incident(1, "title", "caller", "", 0, "Awaiting Caller", "New", list);
			assertEquals("New", i2.getState());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
		
		try {
			Incident i3 = new Incident(1, "title", "caller", null, 0, "Awaiting Caller", "New", list);
			assertEquals("New", i3.getState());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getStatusDetails()}.
	 */
	@Test
	public void testGetStatusDetails() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getStatusDetails(), i1.getStatusDetails());
		
//		try { 
//			Incident i11 = new Incident(1, "title", "caller", "owner", 0, "", "New", list);
//			fail();
//		} catch(IllegalArgumentException e) {
//			assertEquals("Incident cannot be created.", e.getMessage());
//		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getState()}.
	 */
	@Test
	public void testGetState() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getState(), i1.getState());
		try {
			Incident i2 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "", list);
			assertEquals("Resolved", i2.getState());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid state", e.getMessage());
		}
		
		try {
			Incident i3 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", null, list);
			assertEquals("Resolved", i3.getState());

			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid state", e.getMessage());
		}
		
		try {
			Incident i4 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Resolved", list);
			assertEquals("Resolved", i4.getState());

		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			Incident i5 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			assertEquals("Canceled", i5.getState());
		} catch (IllegalArgumentException e) {
			fail();
		}		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#setState(java.lang.String)}.
	 */
	@Test
	public void testSetState() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Incident i1 = new Incident(2, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(i.getState(), i1.getState());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#incrementCounter()}.
	 */
	@Test
	public void testIncrementCounter() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(3, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(3, i.getId());
		Incident i1 = new Incident("title", "caller", "messages");
		Incident.incrementCounter();
		assertEquals(3, i1.getId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#setCounter(int)}.
	 */
	@Test
	public void testSetCounter() {
		ArrayList<String> list = new ArrayList<String>();
		Incident i = new Incident(3, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		assertEquals(3, i.getId());
		Incident i1 = new Incident("title", "caller", "messages");
		Incident.setCounter(Incident.counter);
		assertEquals(6, Incident.counter);
		assertEquals(5, i1.getId());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#getIncidentLogMessages()}.
	 */
	@Test
	public void testGetIncidentLogMessages() {
		ArrayList<String> list = new ArrayList<String>();
		Incident e = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		list.add("incident help needed");
		assertEquals(e.getIncidentLogMessages(), "\r- incident help needed");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.incident.Incident#update(edu.ncsu.csc216.service_wolf.model.command.Command)}.
	 */
	@Test
	public void testUpdate() {
		ArrayList<String> list = new ArrayList<String>();
		Incident e = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		list.add("incident help needed");
		Command c = new Command(CommandValue.ASSIGN, "Awaiting Change", "Test"); 
		e.update(c);	
		assertEquals("In Progress", e.getState());
		Command c1 = new Command(CommandValue.HOLD, "Awaiting Change", "Test");
		e.update(c1);
		assertEquals("On Hold", e.getState());
		
		Incident e1 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "New", list);
		Command c7 = new Command(CommandValue.CANCEL, "Caller Canceled", "Ended");
		e1.update(c7);
		assertEquals("Canceled", e1.getState());
		Incident e2 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "In Progress", list);
		Command c8 = new Command(CommandValue.RESOLVE, "Workaround", "Ended");
		e2.update(c8);
		assertEquals("Resolved", e2.getState());
		Command reopen = new Command(CommandValue.REOPEN, null, "Reopened");
		e2.update(reopen);
		assertEquals("In Progress", e2.getState());
		
		Command ccc = new Command(CommandValue.CANCEL, "Caller Canceled", "Ended");
		e2.update(ccc);
		assertEquals("Canceled", e2.getState());
		
		Incident e3 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "In Progress", list);
		Command c9 = new Command(CommandValue.CANCEL, "Duplicate", "Ended");
		e3.update(c9);
		assertEquals("Canceled", e3.getState());
	
		try {
			Command cc = new Command(CommandValue.REOPEN, null, "Fail");
			e3.update(cc);
			fail();
		} catch (UnsupportedOperationException es) {
			assertEquals(null, es.getMessage());
		}
		
		Incident e4 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "On Hold", list);
		Command c10 = new Command(CommandValue.INVESTIGATE, null, "Ended");
		e4.update(c10);
		assertEquals("In Progress", e4.getState());
		
//		Incident e5 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Resolved", list);
//		Command c11 = new Command(CommandValue.REOPEN, null, "Ended");
//		e5.update(c11);
//		assertEquals("In Progress", e5.getState()); 
		
		Incident e90 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Resolved", list);
		Command c90 = new Command(CommandValue.CANCEL, "Caller Canceled", "Ended");
		e90.update(c90);
		assertEquals("Canceled", e90.getState()); 
		
		Incident e91 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "In Progress", list);
		Command c91 = new Command(CommandValue.ASSIGN, "Awaiting Vendor", "Ended");
		e91.update(c91);
		assertEquals("In Progress", e91.getState()); 
		
		try {
			Incident e8 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			Command c12 = new Command(CommandValue.INVESTIGATE, null, "Ended");
			e8.update(c12);
			fail();
		} catch (UnsupportedOperationException s) {
			assertEquals(null, s.getMessage());
		}
		
		try {
			Incident e9 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			Command c12 = new Command(CommandValue.REOPEN, null, "Ended");
			e9.update(c12);
			fail();
		} catch (UnsupportedOperationException s) {
			assertEquals(null, s.getMessage());
		}
		
		try {
			Incident e9 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			Command c12 = new Command(CommandValue.CANCEL, "Caller Caneled", "Ended");
			e9.update(c12);
			fail();
		} catch (UnsupportedOperationException s) {
			assertEquals(null, s.getMessage());
		}
		
		try {
			Incident e9 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			Command c12 = new Command(CommandValue.ASSIGN, "No status", "Ended");
			e9.update(c12);
			fail();
		} catch (UnsupportedOperationException s) {
			assertEquals(null, s.getMessage());
		}
		
		try {
			Incident e9 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			Command c12 = new Command(CommandValue.HOLD, "Awaiting Caller", "Ended");
			e9.update(c12);
			fail();
		} catch (UnsupportedOperationException s) {
			assertEquals(null, s.getMessage());
		}
		
		try {
			Incident e9 = new Incident(1, "title", "caller", "owner", 0, "Awaiting Caller", "Canceled", list);
			Command c12 = new Command(CommandValue.RESOLVE, "Workaround", "Ended");
			e9.update(c12);
			fail();
		} catch (UnsupportedOperationException s) {
			assertEquals(null, s.getMessage());
		}
	}

}
