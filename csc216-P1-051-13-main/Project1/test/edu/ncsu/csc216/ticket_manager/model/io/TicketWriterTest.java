/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;


import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

//import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
//import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
//import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Test class for TicketWriter
 * @author kaceyjohnson
 *
 */
public class TicketWriterTest {

	
	/**
	 * Test method for writerTicketFile
	 */
	@Test
	public void testWriteTicketFile() {
		List<Ticket> tix = new ArrayList<Ticket>();
		
		ArrayList<String> n = new ArrayList<String>();
		ArrayList<String> n1 = new ArrayList<String>();
		ArrayList<String> n2 = new ArrayList<String>();
		ArrayList<String> n3 = new ArrayList<String>();
		ArrayList<String> n4 = new ArrayList<String>();
		ArrayList<String> n5 = new ArrayList<String>();
		
		// Notes for ticket 1
		n.add("GitHub is not responding when I navigate to github.ncsu.edu");
		
		// Notes for ticket 2
		n1.add("Create a workshop account for access to a GitHub repo");
		n1.add("Assigned to ccgurley.");
		n1.add("How long is the account needed for?");
		n1.add("Until November 1");
		
		// Notes for ticket 3
		n2.add("Add Gradescope plugin to Moodle to import grades");
		n2.add("Checking with plugin provider");
		
		// Notes for ticket 4
		n3.add("Lights are not working in EBI 1011.");
		n3.add("Cannot install dimmer switch.  Will leave on.");
		
		// Notes for ticket 5
		n4.add("I would like to request a new VM for my class");
		n4.add("Assigned to jtking");
		n4.add("VM created");
		n4.add("Request completed");
		
		// Notes for ticket 6
		n5.add("Deliver a large pizza to EBII 1221!");
		n5.add("No!");
		
		tix.add(new Ticket(1, "New", "Incident", "GitHub down", "sesmith5", "Software", "Urgent", "", "", n));
		tix.add(new Ticket(2, "Working", "Request", "Workshop account", "sesmith5", "Inquiry", "Low", "ccgurley", "", n1));
		tix.add(new Ticket(3, "Feedback", "Request", "Add Gradescope plugin to Moodle", "ahoward", "Software", "Medium", "itecs", "Awaiting Provider", n2));
		tix.add(new Ticket(4, "Resolved", "Incident", "Lights not working in EBI 1011", "jtking", "Hardware", "Medium", "facilities", "Workaround", n3));
		tix.add(new Ticket(5, "Closed", "Request", "New VM", "sesmith5", "Inquiry", "High", "jtking", "Completed", n4));
		tix.add(new Ticket(6, "Canceled", "Request", "Pizza", "wpack", "Inquiry", "Urgent", "", "Inappropriate", n5));

		try {
			TicketWriter.writeTicketFile("test-files/actual_tickets.txt", tix);
		} catch (IllegalArgumentException e) {
			fail("Cannot write to tickets records file");
		}
		
		checkFiles("test-files/ticket1.txt", "test-files/actual_tickets.txt");
		
		List<Ticket> tix1 = new ArrayList<Ticket>();
		TicketWriter tw = new TicketWriter();
		try {
			tw.writeTicketFile("", tix1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to save file.", e.getMessage());
		}
	
//		List<Ticket> tix2 = new ArrayList<Ticket>();
//		Ticket t = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.NETWORK, Priority.LOW, "note");
//		tix2.add(t);
//		Command c = new Command(CommandValue.CANCEL, "owner", null, null, CancellationCode.DUPLICATE, "note");
//		t.update(c);
//		System.out.println(t.toString());
//		try {
//			TicketWriter.writeTicketFile("test-files/exp_tickets.txt", tix2);
//		} catch (IllegalArgumentException e) {
//			fail("Cannot write to tickets records file");
//		}
//		
//		checkFiles("test-files/exp_tickets.txt", "test-files/act_tickets.txt");
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

}
