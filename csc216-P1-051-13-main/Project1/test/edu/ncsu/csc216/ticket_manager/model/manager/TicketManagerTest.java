/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for TicketManager
 * @author kaceyjohnson
 *
 */
public class TicketManagerTest {
	/** valid file to load tickets from */
	private String validTestFile = "test-files/ticket1.txt";

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#saveTicketsToFile(java.lang.String)}.
	 */
	@Test
	public void testSaveTicketsToFile() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.loadTicketsFromFile(validTestFile);
		assertEquals(6, ticketManager.getTicketsForDisplay().length);
		
		ticketManager.saveTicketsToFile("test-files/ticket_manager.txt");
		checkFiles("test-files/ticket1.txt", "test-files/ticket_manager.txt");
	}
	
	/**
     * Helper method to compare two files for the same contents
     * @param expFile expected output
     * @param actFile actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
             Scanner actScanner = new Scanner(new FileInputStream(actFile));) {

            while (expScanner.hasNextLine()) {
                assertEquals(expScanner.nextLine(), actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }
	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#loadTicketsFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadTicketsFromFile() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.loadTicketsFromFile(validTestFile);
		assertEquals(6, ticketManager.getTicketsForDisplay().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#createNewTicketList()}.
	 */
	@Test
	public void testCreateNewTicketList() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Wolfare mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals(5, ticketManager.getTicketsForDisplay().length);
		ticketManager.createNewTicketList();
		assertEquals(0, ticketManager.getTicketsForDisplay().length);

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#getTicketsForDisplay()}.
	 */
	@Test
	public void testGetTicketsForDisplay() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Wolfware mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		
		//Row 1
		assertEquals("1", ticketManager.getTicketsForDisplay()[0][0]);
		assertEquals("Incident", ticketManager.getTicketsForDisplay()[0][1]);
		assertEquals("New", ticketManager.getTicketsForDisplay()[0][2]);
		assertEquals("Github down", ticketManager.getTicketsForDisplay()[0][3]);
		assertEquals("Software", ticketManager.getTicketsForDisplay()[0][4]);
		assertEquals("High", ticketManager.getTicketsForDisplay()[0][5]);

		//Row 2
		assertEquals("2", ticketManager.getTicketsForDisplay()[1][0]);
		assertEquals("Request", ticketManager.getTicketsForDisplay()[1][1]);
		assertEquals("New", ticketManager.getTicketsForDisplay()[1][2]);
		assertEquals("Wolfware mishap", ticketManager.getTicketsForDisplay()[1][3]);
		assertEquals("Database", ticketManager.getTicketsForDisplay()[1][4]);
		assertEquals("Urgent", ticketManager.getTicketsForDisplay()[1][5]);
		
		//Row 3
		assertEquals("3", ticketManager.getTicketsForDisplay()[2][0]);
		assertEquals("Incident", ticketManager.getTicketsForDisplay()[2][1]);
		assertEquals("New", ticketManager.getTicketsForDisplay()[2][2]);
		assertEquals("Buses down", ticketManager.getTicketsForDisplay()[2][3]);
		assertEquals("Hardware", ticketManager.getTicketsForDisplay()[2][4]);
		assertEquals("Medium", ticketManager.getTicketsForDisplay()[2][5]);
		
		//Row 4
		assertEquals("4", ticketManager.getTicketsForDisplay()[3][0]);
		assertEquals("Request", ticketManager.getTicketsForDisplay()[3][1]);
		assertEquals("New", ticketManager.getTicketsForDisplay()[3][2]);
		assertEquals("No more goldfish", ticketManager.getTicketsForDisplay()[3][3]);
		assertEquals("Network", ticketManager.getTicketsForDisplay()[3][4]);
		assertEquals("Low", ticketManager.getTicketsForDisplay()[3][5]);
		
		//Row 5
		assertEquals("5", ticketManager.getTicketsForDisplay()[4][0]);
		assertEquals("Incident", ticketManager.getTicketsForDisplay()[4][1]);
		assertEquals("New", ticketManager.getTicketsForDisplay()[4][2]);
		assertEquals("moodle down", ticketManager.getTicketsForDisplay()[4][3]);
		assertEquals("Software", ticketManager.getTicketsForDisplay()[4][4]);
		assertEquals("High", ticketManager.getTicketsForDisplay()[4][5]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#getTicketsForDisplayByType(edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType)}.
	 */
	@Test
	public void testGetTicketsForDisplayByType() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Wolfware mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		String[][] type = ticketManager.getTicketsForDisplayByType(TicketType.INCIDENT);
		//Row 1
		assertEquals("1", type[0][0]);
		assertEquals("Incident", type[0][1]);
		assertEquals("New", type[0][2]);
		assertEquals("Github down", type[0][3]);
		assertEquals("Software", type[0][4]);
		assertEquals("High", type[0][5]);

		//Row 3
		assertEquals("3", type[1][0]);
		assertEquals("Incident", type[1][1]);
		assertEquals("New", type[1][2]);
		assertEquals("Buses down", type[1][3]);
		assertEquals("Hardware", type[1][4]);
		assertEquals("Medium", type[1][5]);
		
		//Row 5
		assertEquals("5", type[2][0]);
		assertEquals("Incident", type[2][1]);
		assertEquals("New", type[2][2]);
		assertEquals("moodle down", type[2][3]);
		assertEquals("Software", type[2][4]);
		assertEquals("High", type[2][5]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#getTicketById(int)}.
	 */
	@Test
	public void testGetTicketById() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Wolfware mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		
		assertEquals(1, ticketManager.getTicketById(1).getTicketId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#executeCommand(int, edu.ncsu.csc216.ticket_manager.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		
		assertEquals("New", ticketManager.getTicketById(1).getState());

		Command c = new Command(CommandValue.PROCESS, "kaipres", null, null, null, "working on it");
		ticketManager.executeCommand(1, c);
		assertEquals("Working", ticketManager.getTicketById(1).getState());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#deleteTicketById(int)}.
	 */
	@Test
	public void testDeleteTicketById() {
		TicketManager ticketManager = new TicketManager();
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Wolfware mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		
		assertEquals(5, ticketManager.getTicketsForDisplay().length);
		ticketManager.deleteTicketById(1);
		assertEquals(4, ticketManager.getTicketsForDisplay().length);

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.manager.TicketManager#addTicketToList(edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType, java.lang.String, java.lang.String, edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category, edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority, java.lang.String)}.
	 */
	@Test
	public void testAddTicketToList() {
		TicketManager ticketManager = new TicketManager();
		assertEquals(0, ticketManager.getTicketsForDisplay().length);
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Github down", "kcjohns4", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Wolfware mishap", "kaipres", Ticket.Category.DATABASE, Ticket.Priority.URGENT, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "Buses down", "subns", Ticket.Category.HARDWARE, Ticket.Priority.MEDIUM, "note");
		ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "No more goldfish", "netsin4", Ticket.Category.NETWORK, Ticket.Priority.LOW, "note");
		ticketManager.addTicketToList(Ticket.TicketType.INCIDENT, "moodle down", "kjohns", Ticket.Category.SOFTWARE, Ticket.Priority.HIGH, "note");
		assertEquals(5, ticketManager.getTicketsForDisplay().length);
	}

}
