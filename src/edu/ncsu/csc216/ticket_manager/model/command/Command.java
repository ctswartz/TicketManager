/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.command;

/**
 * Command class to help facilitate the transitions between the FSM design for the TicketManager. 
 * Provides the codes needed for transitioning. 
 * @author kaceyjohnson
 *
 */
public class Command {
	/** Awaiting caller feedback code */
	public static final String F_CALLER = "Awaiting Caller";
	/** Awaiting change feedback code */
	public static final String F_CHANGE = "Awaiting Change";
	/** Awaiting provider feedback code */
	public static final String F_PROVIDER = "Awaiting Provider";
	/** Resolved completed resolution code */
	public static final String RC_COMPLETED = "Completed";
	/** Resolved not completed resolution code */
	public static final String RC_NOT_COMPLETED = "Not Completed";
	/** Resolved solved resolution code */
	public static final String RC_SOLVED = "Solved";
	/** Resolved workaround resolution code */
	public static final String RC_WORKAROUND = "Workaround";
	/** Resolved not solved resolution code */
	public static final String RC_NOT_SOLVED = "Not Solved";
	/** Resolved caller closed resolution code */
	public static final String RC_CALLER_CLOSED = "Caller Closed";
	/** Canceled duplicate cancellation code */
	public static final String CC_DUPLICATE = "Duplicate";
	/** Canceled inappropriate cancellation code */
	public static final String CC_INAPPROPRIATE = "Inappropriate";
	/** id of the owner of the ticket */
	private String ownerId;
	/** notes for the ticket */
	private String note;
	/** FeedbackCode for the command */
	private FeedbackCode feedbackCode;
	/** ResolutionCode for the command */
	private ResolutionCode resolutionCode;
	/** CancellationCode for the command */
	private CancellationCode cancellationCode;
	/** Command value for the command */
	private CommandValue command;

	/**
	 * Constructor for the command
	 * @param command command for changing states
	 * @param ownerId id of the owner of the ticket
	 * @param feedbackCode feedbackCode of the command
	 * @param resolutionCode resolutionCode of the command
	 * @param cancellationCode cancellationCode of the command 
	 * @param note notes for the command
	 */
	public Command(CommandValue command, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException("Invalid command.");
		}
		if (command == null) {
			throw new IllegalArgumentException();
		} else if (command == CommandValue.PROCESS) {
			if (ownerId == null || ownerId.isEmpty()) {
				throw new IllegalArgumentException();
			}
		} else if (command == CommandValue.FEEDBACK && feedbackCode == null) {
			throw new IllegalArgumentException();
		} else if (command == CommandValue.RESOLVE && resolutionCode == null) {
			throw new IllegalArgumentException();
		} else if (command == CommandValue.CANCEL && cancellationCode == null) {
			throw new IllegalArgumentException();
		} else if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}

		this.command = command;
		this.feedbackCode = feedbackCode;
		this.resolutionCode = resolutionCode;
		this.cancellationCode = cancellationCode;
		this.note = note;
		this.ownerId = ownerId;
	}


	/**
	 * Retrieves the id of the owner of the ticket
	 * @return the ownerId of the ticket
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Retrieves notes pertaining to the ticket
	 * @return the notes pertaining to the ticket
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Retrieves the feedbackCode for the ticket
	 * @return the feedbackCode of the ticket
	 */
	public FeedbackCode getFeedbackCode() {
		return feedbackCode;
	}



	/**
	 * Retrieves the resolutionCode for the ticket
	 * @return the resolutionCode of the ticket
	 */
	public ResolutionCode getResolutionCode() {
		return resolutionCode;
	}



	/**
	 * Retrieves the cancellationCode for the ticket
	 * @return the cancellationCode for the ticket
	 */
	public CancellationCode getCancellationCode() {
		return cancellationCode;
	}



	/**
	 * Retrieves the command for the ticket
	 * @return the command for the ticket
	 */
	public CommandValue getCommand() {
		return command;
	}

	/**
	 * Enumerations for the CommandValues
	 * @author kaceyjohnson
	 *
	 */
	public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }

	/**
	 * Enumerations for the feedbackCodes
	 * @author kaceyjohnson
	 *
	 */
	public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }

	/**
	 * Enumerations for the resolutionCodes
	 * @author kaceyjohnson
	 *
	 */
	public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }

	/**
	 * Enumerations for the cancellationCodes
	 * @author kaceyjohnson
	 *
	 */
	public enum CancellationCode { DUPLICATE, INAPPROPRIATE }
}
