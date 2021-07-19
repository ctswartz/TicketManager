package edu.ncsu.csc216.ticket_manager.model.manager;



import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.io.TicketReader;
import edu.ncsu.csc216.ticket_manager.model.io.TicketWriter;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Controls the creation and modification of TicketList
 * @author kaceyjohnson
 *
 */
public class TicketManager {
	/** list of tickets */
	private TicketList ticketList;
	/** singleton pattern */
	private static TicketManager ticketManager;


	/**
	 * constructor for the ticket manager
	 */
	public TicketManager() {
		ticketList = new TicketList();
	}
	/**
	 * Retrieves current instance of the ticket manager
	 * @return instance of the ticket manager
	 */
	public static TicketManager getInstance() {
		if (ticketManager == null) {
			ticketManager = new TicketManager();
		}
		return ticketManager;
	}

	/**
	 * Saves the list of tickets to the file 
	 * @param fileName name of the file
	 */
	public void saveTicketsToFile(String fileName) {
		try {
			TicketWriter.writeTicketFile(fileName, ticketList.getTickets());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}

	/**
	 * Loads tickets from the file
	 * @param fileName name of the file
	 */
	public void loadTicketsFromFile(String fileName) {
		ticketList.addTickets(TicketReader.readTicketFile(fileName));
	}

	/**
	 * Creates a new ticket list
	 */
	public void createNewTicketList() {
		ticketList = new TicketList();
	}

	/**
	 * Retrieves the list of tickets for display
	 * @return the tickets for the display
	 */
	public String[][] getTicketsForDisplay() {
		String [][] tix = new String[ticketList.getTickets().size()][6];
		for (int i = 0; i < ticketList.getTickets().size(); i++) {
			Ticket ticket = ticketList.getTickets().get(i);
			tix[i][0] = ticket.getTicketId() + "";
			tix[i][1] = ticket.getTicketTypeString();
			tix[i][2] = ticket.getState();
			tix[i][3] = ticket.getSubject();
			tix[i][4] = ticket.getCategory();
			tix[i][5] = ticket.getPriority();
		}
		return tix;
	}

	/**
	 * Retrieves the list of tickets for display by type
	 * @param ticketType type of the ticket
	 * @return the tickets for display by type
	 */
	public String[][] getTicketsForDisplayByType(TicketType ticketType) {
		if (ticketType == null) {
			throw new IllegalArgumentException();
		}

		String [][] tix = new String[ticketList.getTicketsByType(ticketType).size()][6];
		for (int i = 0; i < ticketList.getTicketsByType(ticketType).size(); i++) {
			Ticket ticket = ticketList.getTicketsByType(ticketType).get(i);
			tix[i][0] = ticket.getTicketId() + "";
			tix[i][1] = ticket.getTicketTypeString();
			tix[i][2] = ticket.getState();
			tix[i][3] = ticket.getSubject();
			tix[i][4] = ticket.getCategory();
			tix[i][5] = ticket.getPriority();
		}
		return tix;
	}

/**
 * retrieves the ticket by ticket id
 * @param ticketId id of the ticket
 * @return the ticket with the specified id
 */
public Ticket getTicketById(int ticketId) {
	return ticketList.getTicketById(ticketId);
}

/**
 * Executes the command on the specified ticket
 * @param ticketId id of the ticket 
 * @param commandValue command value being executed
 */
public void executeCommand(int ticketId, Command commandValue) {
	ticketList.executeCommand(ticketId, commandValue);
}

/**
 * Deletes the ticket with the specified id
 * @param ticketId id of the ticket
 */
public void deleteTicketById(int ticketId) {
	ticketList.deleteTicketById(ticketId);
}

/**
 * Adds a ticket the ticket list
 * @param ticketType type of the ticket
 * @param subject subject of the ticket
 * @param caller caller of the ticket
 * @param category category of the ticket
 * @param priority priority of the ticket
 * @param owner owner of the ticket
 * */
public void addTicketToList(TicketType ticketType, String subject, String caller, Category category, Priority priority, String owner) {
	ticketList.addTicket(ticketType, subject, caller, category, priority, owner);
}
}
