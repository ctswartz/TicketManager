/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 *  Test class for the command class
 * @author kaceyjohnson
 *
 */
public class CommandTest {


	/**
	 * Test method for getOwnerID
	 */
	@Test
	public void testGetOwnerId() {
		Command command = new Command(CommandValue.CONFIRM, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
		assertEquals("owner", command.getOwnerId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.command.Command#getNote()}.
	 */
	@Test
	public void testGetNote() {
		Command command = new Command(CommandValue.CONFIRM, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
		assertEquals("note", command.getNote());
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.command.Command#getFeedbackCode()}.
	 */
	@Test
	public void testGetFeedbackCode() {
		Command command = new Command(CommandValue.CONFIRM, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
		assertEquals(FeedbackCode.AWAITING_CALLER, command.getFeedbackCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.command.Command#getResolutionCode()}.
	 */
	@Test
	public void testGetResolutionCode() {
		Command command = new Command(CommandValue.CONFIRM, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
		assertEquals(ResolutionCode.CALLER_CLOSED, command.getResolutionCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.command.Command#getCancellationCode()}.
	 */
	@Test
	public void testGetCancellationCode() {
		Command command = new Command(CommandValue.CONFIRM, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
		assertEquals(CancellationCode.DUPLICATE, command.getCancellationCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.ticket_manager.model.command.Command#getCommand()}.
	 */
	@Test
	public void testGetCommand() {
		Command command = new Command(CommandValue.CONFIRM, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
		assertEquals(FeedbackCode.AWAITING_CALLER, command.getFeedbackCode());
		assertEquals(ResolutionCode.CALLER_CLOSED, command.getResolutionCode());
		assertEquals(CancellationCode.DUPLICATE, command.getCancellationCode());
		assertEquals("owner", command.getOwnerId());
		assertEquals(CommandValue.CONFIRM, command.getCommand());
		assertEquals("note", command.getNote());


		try {
			new Command(null, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}

		try {
			new Command(CommandValue.FEEDBACK, "owner", null, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}

		try {
			new Command(CommandValue.PROCESS, "", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "note");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}

		try {
			new Command(CommandValue.RESOLVE, "owner", FeedbackCode.AWAITING_CALLER, null, CancellationCode.DUPLICATE, "note");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}

		try {
			new Command(CommandValue.CANCEL, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, null, "note");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}
		try {
			new Command(CommandValue.CANCEL, "owner", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}
		
	}

}
