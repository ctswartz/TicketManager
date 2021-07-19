/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Test class for TicketReader
 * @author kaceyjohnson
 *
 */
public class TicketReaderTest {
	/** file to test ReadTicketFile */
	private final String validTestFile = "test-files/ticket1.txt";


	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.io.TicketReader#readTicketFile(java.lang.String)}.
	 */
	@Test
	public void testReadTicketFile() {
		ArrayList<Ticket> tickets = TicketReader.readTicketFile(validTestFile);
		assertEquals(6, tickets.size());
		assertEquals("New", tickets.get(0).getState());
		assertEquals("Working", tickets.get(1).getState());
		assertEquals("Feedback", tickets.get(2).getState());
		assertEquals("Resolved", tickets.get(3).getState());
		assertEquals("Closed", tickets.get(4).getState());

		try {
			ArrayList<Ticket> tix = TicketReader.readTicketFile("");
			assertEquals(0, tix.size());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		
		TicketReader tr = new TicketReader();
		
		try {
			ArrayList<Ticket> tix = tr.readTicketFile("");
			assertEquals(0, tix.size());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}

	}

}
