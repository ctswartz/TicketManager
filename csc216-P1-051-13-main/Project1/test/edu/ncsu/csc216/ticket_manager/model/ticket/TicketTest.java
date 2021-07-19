/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for Ticket
 * @author kaceyjohnson
 *
 */
public class TicketTest {
	/** String of notes */
	private ArrayList<String> notes = new ArrayList<String>();

	/**
	 * Resets the counter
	 * @throws Exception exception thrown by the method.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset the Ticket counter at the beginning of every test.
		Ticket.setCounter(1);
	}	
	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getTicketId()}.
	 */
	@Test
	public void testGetTicketId() {

		Ticket t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note"); 
		assertEquals(1, t.getTicketId());
		Ticket t1 = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note"); 
		assertEquals(2, t1.getTicketId());
	}

	//	/**
	//	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#Ticket(edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType, java.lang.String, java.lang.String, edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category, edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority, java.lang.String)}.
	//	 */
	//	@Test
	//	public void testTicketTicketTypeStringStringCategoryPriorityString() {
	//		Ticket ticket = null;
	//		
	//	}



	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getSubject()}.
	 */
	@Test
	public void testGetSubject() {
		notes.add("test");
		Ticket t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note"); 
		assertEquals("Github down", t.getSubject());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getCaller()}.
	 */
	@Test
	public void testGetCaller() {
		Ticket t = new Ticket(1, "New", "Incident", "Github down", "kcjohns", "Software", "Urgent", "", "PROCESS", notes);
		assertEquals("kcjohns", t.getCaller());
		try {
			t = new Ticket(1, "New", "Incident", "Github down", "", "Software", "Urgent", "", "PROCESS", notes);
			fail();
		} catch (IllegalArgumentException e) {
			//
		}

		t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Urgent", "owner", "PROCESS", notes);
		Command c = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.SOLVED, null, "created accidentally");
		t.update(c);

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		Ticket t = null;

		// Empty owner field
		try {
			t = new Ticket(1, "New", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", null);
//			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid owner id.", e.getMessage());
		}

		t = new Ticket(1, "New", "Incident", "Github down", "kcjohns", "Software", "Urgent", "", "PROCESS", notes);
		Command c = new Command(CommandValue.PROCESS, "kaipres", null, null, null, "working on it");
		t.update(c);
		assertEquals("kaipres", t.getOwner());
		
		try {
			t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", null, "PROCESS", notes);
//			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid owner id.", e.getMessage());
		}
		
		try {
			t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", "", "PROCESS", notes);
//			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid owner id.", e.getMessage());
		}

		try {
			t = new Ticket(1, "Canceled", "Incident", "Github down", "kcjohns", "Software", "Low", "", "PROCESS", notes);
			assertEquals("", t.getOwner());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getNotes()}.
	 */
	@Test
	public void testGetNotes() {
		Ticket t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");

		try {
			Ticket t1 = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "");
			t1.getNotes();
			fail();
		} catch (IllegalArgumentException e) {
			//
		}
		notes.add("test");

		Command c = new Command(CommandValue.PROCESS, "kaipres", null, null, null, "working on it");
		t.update(c);
		assertEquals("-note\n-working on it\n", t.getNotes());
		Command c1 = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_CALLER, null, null, "still working on it");
		t.update(c1);
		assertEquals("-note\n-working on it\n-still working on it\n", t.getNotes());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getCancellationCode()}.
	 */
	@Test
	public void testGetCancellationCode() {
		Ticket t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		Command c = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.DUPLICATE, "created accidentally");
		t.update(c);
		assertEquals("Duplicate", t.getCancellationCode());

		Ticket t1 = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		Command c1 = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.INAPPROPRIATE, "inappropriate");
		t1.update(c1);
		assertEquals("Inappropriate", t1.getCancellationCode());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getFeedbackCode()}.
	 */
	@Test
	public void testGetFeedbackCode() {
		Ticket t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_CHANGE, null, null, "FBI changed mind");
		t.update(c);
		assertEquals("Awaiting Change", t.getFeedbackCode());

		Ticket t1 = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c1 = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_PROVIDER, null, null, "FBI changed mind");
		t1.update(c1);
		assertEquals("Awaiting Provider", t1.getFeedbackCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getResolutionCode()}.
	 */
	@Test
	public void testGetResolutionCode() {
		Ticket t = new Ticket(1, "Working", "Request", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.CALLER_CLOSED, null, "FBI changed mind");
		t.update(c);
		assertEquals("Caller Closed", t.getResolutionCode());

		Ticket t1 = new Ticket(1, "Working", "Request", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c1 = new Command(CommandValue.RESOLVE, "kaipres", null,  ResolutionCode.COMPLETED, null, "FBI changed mind");
		t1.update(c1);
		assertEquals("Completed", t1.getResolutionCode());

		Ticket t2 = new Ticket(1, "Working", "Request", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c2 = new Command(CommandValue.RESOLVE, "kaipres", null,  ResolutionCode.NOT_COMPLETED, null, "FBI changed mind");
		t2.update(c2);
		assertEquals("Not Completed", t2.getResolutionCode());

		Ticket t3 = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c3 = new Command(CommandValue.RESOLVE, "kaipres", null,  ResolutionCode.WORKAROUND, null, "FBI changed mind");
		t3.update(c3);
		assertEquals("Workaround", t3.getResolutionCode());

		Ticket t5 = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c5 = new Command(CommandValue.RESOLVE, "kaipres", null,  ResolutionCode.NOT_SOLVED, null, "FBI changed mind");
		t5.update(c5);
		assertEquals("Not Solved", t5.getResolutionCode());

		Ticket t6 = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c6 = new Command(CommandValue.RESOLVE, "kaipres", null,  ResolutionCode.SOLVED, null, "FBI changed mind");
		t6.update(c6);
		assertEquals("Solved", t6.getResolutionCode());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getTicketType()}.
	 */
	@Test
	public void testGetTicketType() {
		Ticket t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals("Incident", t.getTicketTypeString());
		assertEquals(TicketType.INCIDENT, t.getTicketType());
		t = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "ntoe");
		assertEquals("Request", t.getTicketTypeString());
		assertEquals(TicketType.REQUEST, t.getTicketType());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getState()}.
	 */
	@Test
	public void testGetState() {
		Ticket t = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals("New", t.getState());
		Command c = new Command(CommandValue.PROCESS, "kaipres", null, null, null, "working on it");
		t.update(c);
		assertEquals("Working", t.getState());

		Command c1 = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_CHANGE, null, null, "FBI changed mind");
		t.update(c1);
		assertEquals("Feedback", t.getState());

		Command c2 = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.COMPLETED, null, "FBI changed mind");
		t.update(c2);
		assertEquals("Resolved", t.getState());

		Command c3 = new Command(CommandValue.REOPEN, "kaipres", null, null, null, "FBI changed mind");
		t.update(c3);

		Command c4 = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.DUPLICATE, "FBI changed mind");
		t.update(c4);
		assertEquals("Canceled", t.getState());

		Ticket t3 = new Ticket(1, "Resolved", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c5 = new Command(CommandValue.CONFIRM, "kaipres", null, ResolutionCode.CALLER_CLOSED, null, "FBI changed mind");
		t3.update(c5);
		assertEquals("Closed", t3.getState());



		t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c6 = new Command(CommandValue.REOPEN, "kaipres", null, null, null, "FBI changed mind");
		t.update(c6);
		assertEquals("Working", t.getState());



		t = new Ticket(1, "Feedback", "Request", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c7 = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.NOT_COMPLETED, null, "FBI changed mind");
		t.update(c7);
		assertEquals("Resolved", t.getState());

		t = new Ticket(1, "Feedback", "Request", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c8 = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.CALLER_CLOSED, null, "FBI changed mind");
		t.update(c8);
		assertEquals("Resolved", t.getState());

		t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c9 = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.NOT_SOLVED, null, "FBI changed mind");
		t.update(c9);
		assertEquals("Resolved", t.getState());


		t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c10 = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.WORKAROUND, null, "FBI changed mind");
		t.update(c10);
		assertEquals("Resolved", t.getState());


		t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c11 = new Command(CommandValue.RESOLVE, "kaipres", null, ResolutionCode.CALLER_CLOSED, null, "FBI changed mind");
		t.update(c11);
		assertEquals("Resolved", t.getState());


		t = new Ticket(1, "Feedback", "Incident", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
		Command c12 = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.INAPPROPRIATE, "FBI changed mind");
		t.update(c12);
		assertEquals("Canceled", t.getState());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getPriority()}.
	 */
	@Test
	public void testGetPriority() {
		Ticket t = null;

		try {
			t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.URGENT, "note");
			assertEquals("Urgent", t.getPriority());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "High", "sesmith", "PROCESS", notes);
			assertEquals("High", t.getPriority());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Medium", "sesmith", "PROCESS", notes);
			assertEquals("Medium", t.getPriority());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {

			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "Low", "sesmith", "PROCESS", notes);
			assertEquals("Low", t.getPriority());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#getCategory()}.
	 */
	@Test
	public void testGetCategory() {
		Ticket t = null;

		try {
			t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.URGENT, "note");
			assertEquals("Software", t.getCategory());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Database", "Medium", "sesmith", "PROCESS", notes);
			assertEquals("Database", t.getCategory());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Hardware", "Medium", "sesmith", "PROCESS", notes);
			assertEquals("Hardware", t.getCategory());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Inquiry", "Medium", "sesmith", "PROCESS", notes);
			assertEquals("Inquiry", t.getCategory());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			t = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Network", "Medium", "sesmith", "PROCESS", notes);
			assertEquals("Network", t.getCategory());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.ticket.Ticket#update(edu.ncsu.csc216.ticket_manager.model.command.Command)}.
	 */
	@Test
	public void testUpdate() {
		Ticket t3 = new Ticket(1, "Resolved", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		Command c5 = new Command(CommandValue.CONFIRM, "kaipres", null, ResolutionCode.CALLER_CLOSED, null, "FBI changed mind");
		t3.update(c5);
		Command c6 = new Command(CommandValue.REOPEN, "kaipres", null, null, null, "FBI changed mind");
		t3.update(c6);
		assertEquals("Working", t3.getState());
		Command c7 = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.DUPLICATE, "FBI changed mind");
		t3.update(c7);
		try {
			Command c8 = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.DUPLICATE, "FBI changed mind");
			t3.update(c8);
			fail();
		} catch (UnsupportedOperationException e) {
			//
		}
		assertEquals("Canceled", t3.getState());
		
		
	}

	/**
	 * Test method for toString
	 */
	@Test 
	public void testToString() {
		notes.add("Test");
		Ticket t = new Ticket(20, "Resolved", "Incident", "Github down", "kcjohns", "Software", "Urgent", "sesmith", "PROCESS", notes);
		assertEquals("*20#Resolved#Incident#Github down#kcjohns#Software#Urgent#sesmith#\n-Test", t.toString());


		Command c1 = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_CHANGE, null, null, "FBI changed mind");
		t.update(c1);
		assertEquals("*20#Feedback#Incident#Github down#kcjohns#Software#Urgent#sesmith#Awaiting Change\n-Test\n-FBI changed mind", t.toString());

		Command c8 = new Command(CommandValue.CANCEL, "kaipres", null, null, CancellationCode.DUPLICATE, "FBI changed mind");
		t.update(c8);
		assertEquals("*20#Canceled#Incident#Github down#kcjohns#Software#Urgent#sesmith#Duplicate\n-Test\n-FBI changed mind\n-FBI changed mind", t.toString());
	}

	/**
	 * test method for working state transition
	 */
	@Test
	public void testWorkingStateTest() {
		Ticket t = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.URGENT, "note");
		assertEquals("Request", t.getTicketTypeString());
		assertEquals("New", t.getState());

		Command c = new Command(CommandValue.PROCESS, "owner", null, null, null, "working on it");
		t.update(c);
		assertEquals("Working", t.getState());
		assertEquals("owner", t.getOwner());

		Command c1 = new Command(CommandValue.RESOLVE, "owner", null, ResolutionCode.NOT_COMPLETED, null, "FBI changed mind");
		t.update(c1);
		assertEquals("Resolved", t.getState());
		assertEquals("Not Completed", t.getResolutionCode());

		//		Ticket ticketA = new Ticket(TicketType.REQUEST, "sesmith5", Category.DATABASE, Priority.URGENT, "Request", "a note");
		//		Ticket ticketB = new Ticket(TicketType.INCIDENT, "jdyoung2", Category.SOFTWARE, Priority.MEDIUM, "Incident", "a note");

		try {
			t = new Ticket(null, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.URGENT, "note");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Ticket cannot be created.", e.getMessage());
		}

		Ticket t2 = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.URGENT, "note");
		Command c2 = new Command(CommandValue.PROCESS, "owner", null, null, null, "working on it");
		t2.update(c2);
		assertEquals("Working", t2.getState());
		assertEquals("owner", t2.getOwner());

		try {
			Command c3 = new Command(CommandValue.RESOLVE, "owner", null, ResolutionCode.WORKAROUND, null, "working on it");
			t2.update(c3);
			fail();
		} catch (UnsupportedOperationException e) {
			//
		}
		
	}
	
	/**
	 * Test for ResolvedState
	 */
	@Test
	public void testResolvedState() {
		Ticket t = new Ticket(1, "Resolved", "Incident", "Github down", "kcjohns", "Software", "High", "sesmith", "PROCESS", notes);
		Command c1 = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_CALLER, null, null, "FBI changed mind");
		t.update(c1);
		assertEquals("Feedback", t.getState());
		assertEquals("Awaiting Caller", t.getFeedbackCode());
		
		Ticket t2 = new Ticket(1, "Resolved", "Incident", "Github down", "kcjohns", "Software", "High", "sesmith", "PROCESS", notes);
		Command c2 = new Command(CommandValue.FEEDBACK, "kaipres", FeedbackCode.AWAITING_PROVIDER, null, null, "FBI changed mind");
		t2.update(c2);
		assertEquals("Feedback", t2.getState());
		assertEquals("Awaiting Provider", t2.getFeedbackCode());
		
//		Ticket ticketA = new Ticket(TicketType.REQUEST, "subject", "sesmith5", Category.DATABASE, Priority.URGENT, "a note");
		Ticket t3 = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Ticket.Category.SOFTWARE, Ticket.Priority.URGENT, "note");
		assertEquals("", t3.getOwner());
		assertEquals("New", t3.getState());
		Command s = new Command(CommandValue.CANCEL, "", null, null, CancellationCode.INAPPROPRIATE, "working on it");
		t3.update(s);
		assertEquals("", t3.getOwner());
		assertEquals("Canceled", t3.getState());
		assertEquals("Inappropriate", t3.getCancellationCode());
		
		Ticket t4 = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Category.SOFTWARE, Priority.URGENT, "note");
		Command j = new Command(CommandValue.PROCESS, "kacey", null, null, null, "note");
		t4.update(j);
		assertEquals("Working", t4.getState());
		assertEquals("kacey", t4.getOwner());
		
		Command j1 = new Command(CommandValue.RESOLVE, "", null, ResolutionCode.SOLVED, null, "note");
		t4.update(j1);
		assertEquals("Resolved", t4.getState());
		assertEquals("kacey", t4.getOwner());
		

		Ticket p = new Ticket(1, "Working", "Incident", "Github down", "kcjohns", "Software", "High", "sesmith", "PROCESS", notes);
		Command z = new Command(CommandValue.REOPEN, "kaipres", null, null, null, "FBI changed mind");

		try {
			p.update(z);
			fail();
		} catch (UnsupportedOperationException e) {
			//
		}
		
		
		Ticket l = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Category.SOFTWARE, Priority.URGENT, "note");
		Command q = new Command(CommandValue.PROCESS, "kacey", null, null, null, "note");
		l.update(q);
		assertEquals("Working", l.getState());
		assertEquals("kacey", l.getOwner());
		
		
		Command g = new Command(CommandValue.RESOLVE, "owner", null, ResolutionCode.COMPLETED, null, "FBI changed mind");
		l.update(g);
		assertEquals("Resolved", l.getState());
		assertEquals("Completed", l.getResolutionCode());
		
		Command a = new Command(CommandValue.FEEDBACK, "", FeedbackCode.AWAITING_PROVIDER, null, null, "FBI changed mind");
		l.update(a);
		assertEquals("Feedback", l.getState());
		assertEquals("Awaiting Provider", l.getFeedbackCode());
		assertNull(l.getResolutionCode());
		
		
		Command s1 = new Command(CommandValue.CANCEL, "", null, null, CancellationCode.DUPLICATE, "working on it");
		l.update(s1);
		assertEquals("Canceled", l.getState());
		assertNull(l.getFeedbackCode());

		
		Ticket l1 = new Ticket(Ticket.TicketType.REQUEST, "Github down", "kacey", Category.SOFTWARE, Priority.URGENT, "note");
		Command q1 = new Command(CommandValue.PROCESS, "kacey", null, null, null, "note");
		l1.update(q1);
		
		Command g1 = new Command(CommandValue.RESOLVE, "owner", null, ResolutionCode.COMPLETED, null, "FBI changed mind");
		l1.update(g1);
		
		Command m = new Command(CommandValue.CONFIRM, "owner", null, null, null, "FBI changed mind");
		l1.update(m);
		
		Command n = new Command(CommandValue.PROCESS, "kacey", null, null, null, "note");
		try {
			l1.update(n);
			fail();
		} catch (UnsupportedOperationException e) {
			//
		}
		
		Ticket i = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Category.SOFTWARE, Priority.URGENT, "note");
		Command k = new Command(CommandValue.PROCESS, "kacey", null, null, null, "note");
		i.update(k);
		
		try {
			Command v = new Command(CommandValue.RESOLVE, "kacey", null, ResolutionCode.NOT_COMPLETED, null, "note");
			i.update(v);
		} catch (UnsupportedOperationException e) {
			//
		}
		
		Command v = new Command(CommandValue.RESOLVE, "kacey", null, ResolutionCode.CALLER_CLOSED, null, "note");
		i.update(v);
	
		
		try {
			Ticket i2 = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kacey", Category.SOFTWARE, Priority.URGENT, "note");
			Command k2 = new Command(CommandValue.PROCESS, "kacey", null, null, null, "note");
			i2.update(k2);
			Command e = new Command(CommandValue.CANCEL, "kacey", null, null, CancellationCode.INAPPROPRIATE, "note");
			i2.update(e);
		} catch (UnsupportedOperationException e) {
			fail();
		}
	}

}
