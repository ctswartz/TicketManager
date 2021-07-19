/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command.CommandValue;

/**
 * Test class for the Command class 
 * @author kaceyjohnson
 */
public class CommandTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.command.Command#getCommandInformation()}.
	 */
	@Test
	public void testGetCommandInformation() {
		
		Command c = new Command(CommandValue.ASSIGN, "In Progress", "Test");
		assertEquals(c.getCommandInformation(), "In Progress");
		
		
		Command c2 = new Command(CommandValue.REOPEN, null, "Test");
		assertEquals("Test", c2.getCommandMessage());
		
		try {
			Command c1 = new Command(CommandValue.INVESTIGATE, null, "Test");
			assertEquals("Test", c1.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.service_wolf.model.command.Command#getCommandMessage()}.
	 */
	@Test
	public void testGetCommandMessage() {
		Command c = new Command(CommandValue.ASSIGN, "In Progress", "Test");
		assertEquals(c.getCommandMessage(), "Test");
		
		try {
			Command c1 = new Command(CommandValue.CANCEL, "Canceled", "");
			assertEquals("Test", c1.getCommandMessage());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid command message", e.getMessage());
		}
	}
 
	/**
	 * Test method for getCommandValue
	 */
	@Test
	public void testGetCommandValue() {
		try {
			Command cc = new Command(null, "what", "what");
			assertEquals(CommandValue.ASSIGN, cc.getCommand());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Command value cannot be null", e.getMessage());
		}
		Command c = new Command(CommandValue.ASSIGN, "In Progress", "Test");
		assertEquals(c.getCommand(), CommandValue.ASSIGN);
		
		Command c1 = new Command(CommandValue.CANCEL, "Canceled", "Test");
		assertNotEquals(c.getCommand(), c1.getCommand());
			
	}

}
