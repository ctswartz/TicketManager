/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * maintains a list of tickets. 
 * @author kaceyjohnson
 */
public class TicketList {
	/** Linked list containing tickets */
	ArrayList<Ticket> tickets;
	
	/** Constructor used for constructing a list of tickets */
	public TicketList () {
		tickets = new ArrayList<Ticket>();
		Ticket.setCounter(1);
	}
	
	/**
	 * Adds a ticket the ticket list
	 * @param ticketType type of the ticket
	 * @param subject subject of the ticket
	 * @param caller caller of the ticket
	 * @param category category of the ticket
	 * @param priority priority of the ticket
	 * @param note note for the ticket
	 * @return a number value of the ticket being added
	 */
	public int addTicket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note)  {
		Ticket t = new Ticket(ticketType, subject, caller, category, priority, note);
		tickets.add(t);
		return t.getTicketId();
	}
	
	/**
	 * Adds a group a tickets to the list
	 * @param listTickets tickets being added to the ticket list
	 */
	public void addTickets(ArrayList<Ticket> listTickets) {
		tickets = new ArrayList<Ticket>();
		tickets.addAll(listTickets);
		int max = tickets.get(0).getTicketId();
		for (Ticket ticket : tickets) {
			if (ticket.getTicketId() > max) {
				max = ticket.getTicketId();
			}
		}
		Ticket.setCounter(max + 1);
	}
	
	/**
	 * Retrieves the list of tickets
	 * @return list of tickets
	 */
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	/**
	 * Retrieves the tickets on list based on type
	 * @param ticketType type of ticket 
	 * @return list of ticket by type 
	 */
	public ArrayList<Ticket> getTicketsByType(TicketType ticketType) {
		if (ticketType == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Ticket> type = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (ticket.getTicketType().equals(ticketType)) {
				type.add(ticket);
			}
		}
		return type;
	}
	
	/**
	 * Retrieves the ticket by ticket id 
	 * @param ticketId id of the ticket
	 * @return the ticket with the specified id
	 */
	public Ticket getTicketById(int ticketId) {
		for (Ticket ticket : tickets) {
			if (ticket.getTicketId() == ticketId) {
				return ticket;
			}
		}
		return null;
	}
	
	/**
	 * Executes the desired command
	 * @param ticketId id of the ticket being executed on 
	 * @param commandValue command value of the command
	 */
	public void executeCommand(int ticketId, Command commandValue) {
		for (Ticket ticket : tickets) {
			if (ticket.getTicketId() == ticketId) {
				ticket.update(commandValue);
				return;
			}
		}
	}
	
	/**
	 * Deletes ticket with the specified id
	 * @param ticketId id of the ticket
	 */
	public void deleteTicketById(int ticketId) {
		for (Ticket ticket : tickets) {
			if (ticket.getTicketId() == ticketId) {
				tickets.remove(ticket);
				return;
			}
		}
	}
}
