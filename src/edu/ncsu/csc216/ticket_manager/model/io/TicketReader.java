/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Reads tickets from the file 
 * @author kaceyjohnson
 *
 */
public class TicketReader {

	/**
	 * Reads tickets from the specified file 
	 * @param fileName name of the file 
	 * @return an array of tickets from the file 
	 */
	public static ArrayList<Ticket> readTicketFile(String fileName) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		try {
			Scanner input = new Scanner(new FileInputStream(fileName));
			input.useDelimiter("\\r?\\n?[*]");

			while(input.hasNext()) {
				String info = input.next();
				//				System.out.println(info);
				Ticket t = processTicket(info);
				tickets.add(t);


			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		return tickets;
	}

	private static Ticket processTicket(String t) throws FileNotFoundException {
		ArrayList<String> notes = new ArrayList<String>();
		Scanner info = new Scanner(t);

		String n = info.nextLine();
		Scanner details = new Scanner(n);
		int id = 0;
		String state = "";
		String type = "";
		String subject = "";
		String caller = "";
		String category = "";
		String priority = "";
		String owner = "";
		String code = "";
		String note = "";

		// Used to break up the info needed to constructor the Ticket
		details.useDelimiter("#");

		id = Integer.parseInt(details.next());
		state = details.next();
		type = details.next();
		subject = details.next();
		caller = details.next();
		category = details.next();
		priority = details.next();

		if (details.hasNext()) {
			owner = details.next();
		}
		if (details.hasNext()) {
			code = details.next();
		}

		// Retrives the notes on the file
		info.useDelimiter("\\r?\\n?[-]");
		while(info.hasNext()) {
			note = info.next();
			notes.add(note);
		}
		info.close();
		details.close();
		return new Ticket(id, state, type, subject, caller, category, priority, owner, code, notes);

	}
}
