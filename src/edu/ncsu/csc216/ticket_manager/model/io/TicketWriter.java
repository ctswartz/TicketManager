/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Saves tickets to the file
 * @author kaceyjohnson
 *
 */
public class TicketWriter {

	/**
	 * Saves tickets to file 
	 * @param fileName name of the file 
	 * @param tickets list of tickets being saved
	 */
	public static void writeTicketFile(String fileName, List<Ticket> tickets)  {
		PrintStream fileWriter;
		
		try {
			fileWriter = new PrintStream(new File(fileName));
			
			for (int i = 0; i < tickets.size(); i++) {
				Ticket ticket = tickets.get(i);
				fileWriter.println(ticket.toString());				
			}
			fileWriter.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	
	}
}

