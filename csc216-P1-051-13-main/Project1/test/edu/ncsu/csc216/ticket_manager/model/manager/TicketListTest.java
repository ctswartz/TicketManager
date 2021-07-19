/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for TicketList
 * @author kaceyjohnson
 *
 */
public class TicketListTest {
	/** an array of tickets */
	private  ArrayList<Ticket> tix = new ArrayList<Ticket>();
	

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketList#addTicket(edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType, java.lang.String, java.lang.String, edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category, edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority, java.lang.String)}.
	 */
	@Test
	public void testAddTicket() {
		TicketList tickets = new TicketList();
		tickets.addTicket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals(5, tickets.getTickets().size());
	}

	/**
	 * Test method for addTickets
	 */
	@Test
	public void testAddTickets() {
		TicketList tickets = new TicketList();
		assertEquals(0, tickets.getTickets().size());

		tix.add( new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note"));
		tix.add(new Ticket(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note"));
		tix.add(new Ticket(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note"));
		tix.add(new Ticket(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note"));
		tix.add(new Ticket(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note"));
		
		tickets.addTickets(tix);
		assertEquals(5, tickets.getTickets().size());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketList#getTickets()}.
	 */
	@Test
	public void testGetTickets() {
		TicketList tickets = new TicketList();
		
		
		tix.add(new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note"));
		tix.add(new Ticket(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note"));
		tix.add(new Ticket(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note"));
		tix.add(new Ticket(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note"));
		tix.add(new Ticket(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note")); 
		tickets.addTickets(tix);

		assertTrue(tix.equals(tickets.getTickets()));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketList#getTicketsByType(edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType)}.
	 */
	@Test
	public void testGetTicketsByType() {
		TicketList tickets = new TicketList();

		tickets.addTicket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		
		assertEquals(3, tickets.getTicketsByType(TicketType.INCIDENT).size());
	}

	/**
	 * Test method for getTicketsById
	 */
	@Test
	public void testGetTicketByID() {
		TicketList tickets = new TicketList();
//		Ticket t = new Ticket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "");
		
		tickets.addTicket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		
		assertEquals(1, tickets.getTicketById(1).getTicketId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketList#executeCommand(int, edu.ncsu.csc216.ticket_manager.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		TicketList tickets = new TicketList();

		tickets.addTicket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals("New", tickets.getTicketById(1).getState());

		Command c = new Command(CommandValue.PROCESS, "kaipres", null, null, null, "working on it");
		tickets.executeCommand(1, c);
		assertEquals("Working", tickets.getTicketById(1).getState());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketList#deleteTicketById(int)}.
	 */
	@Test
	public void testDeleteTicketById() {
		TicketList tickets = new TicketList();

		tickets.addTicket(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		tickets.addTicket(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		tickets.addTicket(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals(5, tickets.getTickets().size());

		tickets.deleteTicketById(1);
		assertEquals(4, tickets.getTickets().size());

	}

}
