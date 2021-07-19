/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 * Ticket class use to modify and create tickets. Also used to gather ticket info for output. 
 * @author kaceyjohnson
 *
 */
public class Ticket {
	/** Request ticket type */
	public static final String TT_REQUEST = "Request";
	/** Incident ticket type */
	public static final String TT_INCIDENT = "Incident";
	/** Category type */
	public static final String C_INQUIRY = "Inquiry";
	/** Category type */
	public static final String C_SOFTWARE = "Software";
	/** Category type */
	public static final String C_HARDWARE = "Hardware";
	/** Category type */
	public static final String C_NETWORK = "Network";
	/** Category type */
	public static final String C_DATABASE = "Database";
	/** Ticket urgency: Urgent */
	public static final String P_URGENT = "Urgent";
	/** Ticket urgency: High */
	public static final String P_HIGH = "High";
	/** Ticket urgency: Medium */
	public static final String P_MEDIUM = "Medium";
	/** Ticket urgency: Low */
	public static final String P_LOW = "Low";
	/** State of the ticket: New */
	public static final String NEW_NAME = "New";
	/** State of the ticket: Working */
	public static final String WORKING_NAME = "Working";
	/** State of the ticket: Feedback */
	public static final String FEEDBACK_NAME = "Feedback";
	/** State of the ticket: Resolved */
	public static final String RESOLVED_NAME = "Resolved";
	/** State of the ticket: Closed */
	public static final String CLOSED_NAME = "Closed";
	/** State of the ticket: Canceled */
	public static final String CANCELED_NAME = "Canceled";
	/** A static field that keeps track of the id value that should be given to the next ticket created */
	private static int counter = 1;
	/** Unique id for the ticket */
	private int ticketId;
	/** Tickets subject information for when the ticket is created */
	private String subject;
	/** User id of person who reported the ticket */
	private String caller;
	/** User id of the ticket owner or "" */
	private String owner;
	/** An arraylist of notes */
	private ArrayList<String> notes;
	/** cancellation code */
	private CancellationCode cancellationCode;
	/** feedback code */
	private FeedbackCode feedbackCode;
	/** resolution code */
	private ResolutionCode resolutionCode;
	/** Ticket type */
	private TicketType ticketType;
	/** State of ticket */
	private TicketState state;
	/** priority of the ticket */
	private Priority priority;
	/** category of the ticket */
	private Category category;
	/** New state for the ticket */
	private NewState newState = new NewState();
	/** Working state for the ticket */
	private WorkingState workingState = new WorkingState();
	/** Feedback state for the ticket */
	private FeedbackState feedbackState = new FeedbackState();
	/** Resolved state for the ticket */
	private ResolvedState resolvedState = new ResolvedState();
	/** closed state for the ticket */
	private ClosedState closedState = new ClosedState();
	/** Canceled state for the ticket */
	private CanceledState canceledState = new CanceledState();

	/** Constructer intended to create ticket when reading from a ticket file 
	 * @param id id of the ticket
	 * @param state state of the ticket
	 * @param ticketType type of the ticket
	 * @param subject subject of the ticket
	 * @param caller caller of the ticket
	 * @param category category of the ticket
	 * @param priority priority of the ticket
	 * @param owner owner of the ticket
	 * @param code cancellation code of the ticket
	 * @param notes notes pertaining to the ticket
	 */
	public Ticket(int id, String state, String ticketType, String subject, String caller, String category, String priority, String owner, String code, ArrayList<String> notes) {
		if (id > counter) {
			setCounter(id + 1);
		}
		this.ticketId = id;
		setState(state);
		setTicketType(ticketType);
		setSubject(subject);
		setCaller(caller);
		setCategory(category);
		setPriority(priority);
		setOwner(owner);
		if ("Awaiting Caller".equals(code) || "Awaiting Change".equals(code) || "Awaiting Provider".equals(code)) {
			setFeedbackCode(code);
		} else if ("Completed".equals(code) || "Not Completed".equals(code) || "Solved".equals(code) || "Not Solved".equals(code) 
				|| "Workaround".equals(code) || "Caller Closed".equals(code)) {
			setResolutionCode(code);
		} else if ("Duplicate".equals(code) || "Inappropriate".equals(code)) {
			setCancellationCode(code);
		}

		setNotes(notes);
	}

	/**
	 * Constructor intended for ticket constructed from parameters 
	 * @param ticketType type of the ticket
	 * @param subject subject of the ticket
	 * @param caller caller of the ticket
	 * @param category category of the ticket
	 * @param priority priority of the ticket
	 * @param note not for the new ticket
	 */
	public Ticket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
		
		this.ticketId = counter;
		
		if (ticketType == null || category == null || priority == null) {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}

		this.ticketType = ticketType;
		this.category = category;
		this.priority = priority;

		setState(NEW_NAME);
		setSubject(subject);
		setCaller(caller);
		this.owner = "";
		
		notes = new ArrayList<String>();
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}

		notes.add(note);
		incrementCounter();
	}

	/**
	 * increments the counter 
	 */
	public static void incrementCounter() {
		counter = counter + 1;
	}

	/**
	 * Sets the counter 
	 * @param count counter value 
	 */
	public static void setCounter(int count) {
		if (count == 0) {
			throw new IllegalArgumentException("Ticket id must be a value greater than 0.");
		}
		counter = count;
	}
	/**
	 * Retrieves the unique id for the ticket
	 * @return unique id for the ticket
	 */
	public int getTicketId() {
		return ticketId;
	}


	/**
	 * Retrieves the subject information of the ticket
	 * @return the subject of the subject 
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject information of the ticket
	 * @param subject subject of the ticket
	 */
	private void setSubject(String subject) {
		if (subject == null || subject.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.subject = subject;
	}

	/**
	 * Retrieves the id of the person who reported the ticket
	 * @return id of the person who reported the ticket
	 */
	public String getCaller() {
		return caller;
	}

	/**
	 * Sets the id of the person who reported the ticket
	 * @param caller id of the person who reported the ticket
	 */
	private void setCaller(String caller) {
		if (caller == null || caller.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.caller = caller;
	}

	/**
	 * Retrieves the id of the ticket owner
	 * @return the id of the owner of the ticket
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the id of the ticket owner
	 * @param owner the owner to set to the ticket
	 */
	private void setOwner(String owner) {
		if (owner == null) {
			throw new IllegalArgumentException("Invalid owner id.");
		}
		
		if ("New".equals(getState()) && !"".equals(owner)) {
			throw new IllegalArgumentException("Invalid owner id.");
		} else if ("New".equals(getState()) && "".equals(owner)){
			this.owner = "";
		}

		if ("Working".equals(getState()) && "".equals(owner)) {
			throw new IllegalArgumentException("Invalid owner id.");
		} else if ("Working".equals(getState()) && !"".equals(owner)) {
			this.owner = owner;
		}

		if ("Feedback".equals(getState()) && "".equals(owner)) {
			throw new IllegalArgumentException("Invalid owner id.");
		} else if ("Feedback".equals(getState()) && !"".equals(owner)){
			this.owner = owner;
		}

		if ("Resolved".equals(getState()) && "".equals(owner)) {
			throw new IllegalArgumentException("Invalid owner id.");
		} else if ("Resolved".equals(getState()) && !"".equals(owner)){
			this.owner = owner;
		}

		if ("Closed".equals(getState()) && "".equals(owner)) {
			throw new IllegalArgumentException("Invalid owner id.");
		} else if ("Closed".equals(getState()) && !"".equals(owner) ){
			this.owner = owner;
		}

		if ("Canceled".equals(getState()) && "".equals(owner)) {
			this.owner = "";
		} else {
			this.owner = owner;
		}
		
		
	}

	/**
	 * Retrieves notes pertaining to the ticket
	 * @return the notes pertaining to the ticket
	 */
	public String getNotes() {
		if (notes.size() <= 0) {
			throw new IllegalArgumentException();
		} 
		String fullNotes = "";
		for (int i = 0; i < notes.size(); i++) {
			if (i == 0) {
				fullNotes += "-" + notes.get(i);
			} else {
				fullNotes += "\n-" + notes.get(i);
			}

		}

		return fullNotes + "\n";
	}

	/**
	 * Sets the notes pertaining to the ticket
	 * @param notes the notes to add to the ticket
	 */
	private void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}

	/**
	 * Retrieves the reason for canceling a ticket
	 * @return the reason for cancellation
	 */
	public String getCancellationCode() {
		if (cancellationCode == null) {
			return null;
		}
		if (cancellationCode.equals(CancellationCode.DUPLICATE)) {
			return "Duplicate";
		} else {
			return "Inappropriate";
		} 

	}

	/**
	 * Sets the cancellatoin code for the ticket 
	 * @param cancellationCode cancellation code for the ticket
	 */
	private void setCancellationCode(String cancellationCode) {
		if (state.equals(canceledState)) {
			switch(cancellationCode) {
			case "Duplicate":
				this.cancellationCode = CancellationCode.DUPLICATE;
				break;
			case "Inappropriate":
				this.cancellationCode = CancellationCode.INAPPROPRIATE;
				break;
			default:
				this.cancellationCode = null;
				break;
			}
		}
	}

	/**
	 * Retrieves the feedbackCode for the ticket 
	 * @return the feedbackCode reported for the ticket 
	 */
	public String getFeedbackCode() {
		if (feedbackCode == null) {
			return null;
		}
		
		if (feedbackCode.equals(FeedbackCode.AWAITING_CALLER)) {
			return "Awaiting Caller";
		} else if (feedbackCode.equals(FeedbackCode.AWAITING_CHANGE)) {
			return "Awaiting Change";
		} else if (feedbackCode.equals(FeedbackCode.AWAITING_PROVIDER)) {
			return "Awaiting Provider";
		} 
		
		return null;

	}

	/**
	 * Sets the feedbackCode for the ticket
	 * @param feedbackCode the feedbackCode to set for the ticket
	 */
	private void setFeedbackCode(String feedbackCode) {
		if ("Feedback".equals(getState())) {
			switch (feedbackCode) {
			case "Awaiting Caller":
				this.feedbackCode = FeedbackCode.AWAITING_CALLER;
				return;
			case "Awaiting Change":
				this.feedbackCode = FeedbackCode.AWAITING_CHANGE;
				return;
			case "Awaiting Provider":
				this.feedbackCode = FeedbackCode.AWAITING_PROVIDER;
				return;
			default:
				break;
			}	
		} else {
			this.feedbackCode = null;
		}

	}

	/**
	 * Retrieves the resolutionCode for the ticket 
	 * @return the resolutionCode reported for the ticket
	 */
	public String getResolutionCode() {
		if (resolutionCode == null) {
			return null;
		}
		if (resolutionCode.equals(ResolutionCode.COMPLETED)) {
			return "Completed";
		} else if (resolutionCode.equals(ResolutionCode.NOT_COMPLETED)) {
			return "Not Completed";
		} else if (resolutionCode.equals(ResolutionCode.SOLVED)) {
			return "Solved";
		} else if (resolutionCode.equals(ResolutionCode.WORKAROUND)) {
			return "Workaround";
		} else if (resolutionCode.equals(ResolutionCode.NOT_SOLVED)) {
			return "Not Solved";
		} else if (resolutionCode.equals(ResolutionCode.CALLER_CLOSED)) {
			return "Caller Closed";
		} 

		return null;

	}

	/**
	 * Sets the resolutionCode for the ticket
	 * @param resolutionCode the resolutionCode to set
	 */
	private void setResolutionCode(String resolutionCode) {
		if ("Resolved".equals(getState()) || "Closed".equals(getState())) {
			if ("Completed".equals(resolutionCode)) {
				this.resolutionCode = ResolutionCode.COMPLETED;
				return;
			} else if ("Not Completed".equals(resolutionCode)) {
				this.resolutionCode = ResolutionCode.NOT_COMPLETED;
				return;

			} else if ("Solved".equals(resolutionCode)) {
				this.resolutionCode = ResolutionCode.SOLVED;
				return;

			} else if ("Workaround".equals(resolutionCode)) {
				this.resolutionCode = ResolutionCode.WORKAROUND;
				return;

			} else if ("Not Solved".equals(resolutionCode)) {
				this.resolutionCode = ResolutionCode.NOT_SOLVED;
				return;

			} else if ("Caller Closed".equals(resolutionCode)) {
				this.resolutionCode = ResolutionCode.CALLER_CLOSED;
				return;

			} 
		}
		this.resolutionCode = null;
	}

	/**
	 * Retrieves the type of ticket (incident or request)
	 * @return the ticketType string type of the ticket 
	 */
	public TicketType getTicketType() {
		if (TicketType.REQUEST.equals(ticketType)) {
			return Ticket.TicketType.REQUEST;
		} else if (TicketType.INCIDENT.equals(ticketType)) {
			return Ticket.TicketType.INCIDENT;
		} else {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}

	}

	/**
	 * Retrieves the type of ticket (incident or request)
	 * @return the ticketType type of the ticket 
	 */
	public String getTicketTypeString() {
		if (TicketType.REQUEST.equals(ticketType)) {
			return TT_REQUEST;
		} else if (TicketType.INCIDENT.equals(ticketType)) {
			return TT_INCIDENT;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Sets the type of the ticket 
	 * @param ticketType the type of the ticket 
	 */
	private void setTicketType(String ticketType) {
		if (ticketType == null || ticketType.isEmpty()) {
			throw new IllegalArgumentException();
		} else if (TT_REQUEST.equals(ticketType)) {
			this.ticketType = TicketType.REQUEST;
		} else if (TT_INCIDENT.equals(ticketType)) {
			this.ticketType = TicketType.INCIDENT;
		} else {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}
	}

	/**
	 * Retrieves the state that ticket is currently in
	 * @return the state
	 */
	public String getState() {
		String s = "";
		if (state.equals(newState)) {
			s = NEW_NAME;
		} else if (state.equals(workingState)) {
			s = WORKING_NAME;
		} else if (state.equals(feedbackState)) {
			s = FEEDBACK_NAME;
		} else if (state.equals(resolvedState)) {
			s = RESOLVED_NAME;
		} else if (state.equals(closedState)) {
			s = CLOSED_NAME;
		} else if (state.equals(canceledState)) {
			s = CANCELED_NAME;
		} else {
			throw new IllegalArgumentException();
		}
		return s;
	}

	/**
	 * Sets the state of the ticket
	 * @param state the state to set the tstate to 
	 */
	private void setState(String state) {
		if (state == null || state.isEmpty()) {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}

		switch(state) {
		case NEW_NAME:
			this.state = newState;
			break;
		case WORKING_NAME:
			this.state = workingState;
			break;
		case FEEDBACK_NAME:
			this.state = feedbackState;
			break;
		case RESOLVED_NAME:
			this.state = resolvedState;
			break;
		case CLOSED_NAME:
			this.state = closedState;
			break;
		case CANCELED_NAME:
			this.state = canceledState;
			break;
		default:
			throw new IllegalArgumentException("Ticket cannot be created.");
		}
	}

	/** 
	 * Retrieves the priority level of the ticket 
	 * @return the priority of the ticket 
	 */
	public String getPriority() {
		String p = "";
		if (priority == Priority.URGENT) {
			p = P_URGENT;
		} else if (priority == Priority.HIGH) {
			p = P_HIGH;
		} else if (priority == Priority.MEDIUM) {
			p = P_MEDIUM;
		} else if (priority == Priority.LOW) {
			p = P_LOW;
		}
		return p;
	}

	/**
	 * Sets the priority of the ticket 
	 * @param priority the priority to set the ticket to 
	 */
	private void setPriority(String priority) {
		if (priority == null || priority.isEmpty()) {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}

		switch (priority) {
		case P_URGENT:
			this.priority = Priority.URGENT;
			break;
		case P_HIGH: 
			this.priority = Priority.HIGH;
			break;
		case P_MEDIUM:
			this.priority = Priority.MEDIUM;
			break;
		case P_LOW:
			this.priority = Priority.LOW;
			break;
		default:
			throw new IllegalArgumentException("Ticket cannot be created.");
		}

	}

	/**
	 * Retrieves the category that the ticket falls into 
	 * @return the category of the ticket 
	 */
	public String getCategory() {
		String categoryS = "";
		if (category == Category.INQUIRY) {
			categoryS = C_INQUIRY;
		} else if (category == Category.SOFTWARE) {
			categoryS = C_SOFTWARE;
		} else if (category == Category.HARDWARE) {
			categoryS = C_HARDWARE;
		} else if (category == Category.NETWORK) {
			categoryS = C_NETWORK;
		} else if (category == Category.DATABASE) {
			categoryS = C_DATABASE;
		} else {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}
		return categoryS;
	}

	/**
	 * Category to set the ticket to 
	 * @param category the category to set the ticket to 
	 */
	private void setCategory(String category) {
		if (category == null || category.isEmpty()) {
			throw new IllegalArgumentException("Ticket cannot be created.");
		}
		switch(category) {
		case C_INQUIRY:
			this.category = Category.INQUIRY;
			break;
		case C_SOFTWARE:
			this.category = Category.SOFTWARE;
			break;
		case C_HARDWARE:
			this.category = Category.HARDWARE;
			break;
		case C_NETWORK:
			this.category = Category.NETWORK;
			break;
		case C_DATABASE:
			this.category = Category.DATABASE;
			break;
		default:
			throw new IllegalArgumentException("Ticket cannot be created.");
		}

	}

	/**
	 * updates the state and codes for tickets
	 * @param commandValue command value for the ticket
	 */
	public void update(Command commandValue) {
		if ("New".equals(getState())) {
			newState.updateState(commandValue);
			return;
		}
		if ("Working".equals(getState())) {
			workingState.updateState(commandValue);
			return;
		}
		if ("Feedback".equals(getState())) {
			feedbackState.updateState(commandValue);
			return;
		}
		if ("Resolved".equals(getState())) {
			resolvedState.updateState(commandValue);
			return;
		}
		if ("Closed".equals(getState())) {
			closedState.updateState(commandValue);
			return;
		}
		if ("Canceled".equals(getState())) {
			canceledState.updateState(commandValue);
			return;
		} 
		throw new UnsupportedOperationException();
	}

	/**
	 * String representation of the ticket
	 * @return string representation of the ticket 
	 */
	@Override
	public String toString() {
		String toString = "";
		if (feedbackCode != null) { 
			toString = "*" + ticketId + "#" + getState() + "#" + getTicketTypeString() + "#" + subject + "#" + caller + "#" + getCategory() + "#" + getPriority() +
					"#" + owner + "#" + getFeedbackCode();
		} else if (cancellationCode != null) {
			toString = "*" + ticketId + "#" + getState() + "#" + getTicketTypeString() + "#" + subject + "#" + caller + "#" + getCategory() + "#" + getPriority() +
					"#" +  owner + "#" + getCancellationCode();
		} else if (resolutionCode != null) {
			toString =  "*" + ticketId + "#" + getState() + "#" + getTicketTypeString() + "#" + subject + "#" + caller + "#" + getCategory() + "#" + getPriority() +
					"#" + owner + "#" + getResolutionCode();
		} else {
			if (owner == null) {
				toString =  "*" + ticketId + "#" + getState() + "#" + getTicketTypeString() + "#" + subject + "#" + caller + "#" + getCategory() + "#" + getPriority() +
						"#" + "#";
			} else {
				toString =  "*" + ticketId + "#" + getState() + "#" + getTicketTypeString() + "#" + subject + "#" + caller + "#" + getCategory() + "#" + getPriority() +
						"#" + owner + "#";
			}
		}

		for (int i = 0; i < notes.size(); i++) {
			toString += "\n-" + notes.get(i).strip();
		}

		return toString;

	}


	/**
	 * Class for when a ticket enters the ticket manager state model 
	 * @author kaceyjohnson
	 *
	 */
	public class NewState implements TicketState {



		/**
		 * Update the Ticket based on the given Command.
		 * An UnsupportedOperationException is thrown if the CommandValue
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Ticket's
		 * state.
		 * @throws UnsupportedOperationException if the CommandValue is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {

			if (command.getCommand().equals(CommandValue.PROCESS)) {
				setState(WORKING_NAME);
				setOwner(command.getOwnerId());
			} else if (command.getCommand().equals(CommandValue.CANCEL)) {
				if (command.getCancellationCode().equals(CancellationCode.DUPLICATE)) {
					setState(CANCELED_NAME);
					setCancellationCode(Command.CC_DUPLICATE);
					setResolutionCode(null);
				} else if (command.getCancellationCode().equals(CancellationCode.INAPPROPRIATE)) {
					setState(CANCELED_NAME);
					setCancellationCode(Command.CC_INAPPROPRIATE);
					setResolutionCode(null);
				} else {
					throw new UnsupportedOperationException();
				}

			} else {
				throw new UnsupportedOperationException();
			}
			
			if (getState().equals(getStateName())) {
				throw new UnsupportedOperationException();
			}
			notes.add(command.getNote());
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return NEW_NAME;
		}

	}

	/**
	 * Class for when a ticket enters the working state
	 * @author kaceyjohnson
	 *
	 */
	public class WorkingState implements TicketState {

		/**
		 * Update the Ticket based on the given Command.
		 * An UnsupportedOperationException is thrown if the CommandValue
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Ticket's
		 * state.
		 * @throws UnsupportedOperationException if the CommandValue is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {


			if (command.getCommand().equals(CommandValue.FEEDBACK)) {
				if (command.getFeedbackCode().equals(FeedbackCode.AWAITING_CALLER)) {
					setState(FEEDBACK_NAME);
					setFeedbackCode(Command.F_CALLER);
					setCancellationCode(null);
					setResolutionCode(null);
				} else if (command.getFeedbackCode().equals(FeedbackCode.AWAITING_CHANGE)) {
					setState(FEEDBACK_NAME);
					setFeedbackCode(Command.F_CHANGE);
					setCancellationCode(null);
					setResolutionCode(null);
				} else if (command.getFeedbackCode().equals(FeedbackCode.AWAITING_PROVIDER)) {
					setState(FEEDBACK_NAME);
					setFeedbackCode(Command.F_PROVIDER);
					setCancellationCode(null);
					setResolutionCode(null);
				} else {
					throw new UnsupportedOperationException();
				}
			} else if (command.getCommand().equals(CommandValue.RESOLVE)) {
				if (ticketType == TicketType.REQUEST) {
					if (command.getResolutionCode().equals(ResolutionCode.COMPLETED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_COMPLETED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.NOT_COMPLETED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_NOT_COMPLETED);
						setCancellationCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_CALLER_CLOSED);
						setCancellationCode(null);
					} else {
						throw new UnsupportedOperationException();
					}

				} else if (ticketType == TicketType.INCIDENT) {
					if (command.getResolutionCode().equals(ResolutionCode.SOLVED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_SOLVED);
						setCancellationCode(null);
						setFeedbackCode(null);
						

					} else if (command.getResolutionCode().equals(ResolutionCode.NOT_SOLVED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_NOT_SOLVED);
						setCancellationCode(null);
						setFeedbackCode(null);
					

					} else if (command.getResolutionCode().equals(ResolutionCode.WORKAROUND)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_WORKAROUND); 
						setCancellationCode(null);
						setFeedbackCode(null);
						

					} else if (command.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_CALLER_CLOSED);
						setCancellationCode(null);
						setFeedbackCode(null);

					} else {
						throw new UnsupportedOperationException();
					}
				}

			} else if (command.getCommand().equals(CommandValue.CANCEL)) {
				if (command.getCancellationCode().equals(CancellationCode.DUPLICATE)) {
					setState(CANCELED_NAME);
					setCancellationCode(Command.CC_DUPLICATE);
					setFeedbackCode(null);
					setResolutionCode(null);
				} else if (command.getCancellationCode().equals(CancellationCode.INAPPROPRIATE)) {
					setState(CANCELED_NAME);
					setCancellationCode(Command.CC_INAPPROPRIATE);
					setFeedbackCode(null);
					setResolutionCode(null);
				} else {
					throw new UnsupportedOperationException();
				}
			} else {
				throw new UnsupportedOperationException();
			}
			
			if (getState().equals(getStateName())) {
				throw new UnsupportedOperationException();
			}
			
			notes.add(command.getNote());
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return WORKING_NAME;
		}

	}

	/**
	 * Class for when a ticket enters the feedback state
	 * @author kaceyjohnson
	 *
	 */
	public class FeedbackState implements TicketState {



		/**
		 * Update the Ticket based on the given Command.
		 * An UnsupportedOperationException is thrown if the CommandValue
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Ticket's
		 * state.
		 * @throws UnsupportedOperationException if the CommandValue is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {

			if (command.getCommand().equals(CommandValue.REOPEN)) {
				setState(WORKING_NAME);
				setCancellationCode(null);
				setResolutionCode(null);
				setFeedbackCode(null);
			} else if (command.getCommand().equals(CommandValue.RESOLVE)) {
				if (ticketType == TicketType.REQUEST) {
					if (command.getResolutionCode().equals(ResolutionCode.COMPLETED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_COMPLETED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.NOT_COMPLETED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_NOT_COMPLETED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_CALLER_CLOSED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else {
						throw new UnsupportedOperationException();
					}
				} else if (ticketType == TicketType.INCIDENT) {
					if (command.getResolutionCode().equals(ResolutionCode.SOLVED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_SOLVED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.NOT_SOLVED)) {
						
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_NOT_SOLVED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.WORKAROUND)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_WORKAROUND); 
						setCancellationCode(null);
						setFeedbackCode(null);
					} else if (command.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED)) {
						setState(RESOLVED_NAME);
						setResolutionCode(Command.RC_CALLER_CLOSED);
						setCancellationCode(null);
						setFeedbackCode(null);
					} else {
						throw new UnsupportedOperationException();
					}
				}
			} else if (command.getCommand().equals(CommandValue.CANCEL)) {
				if (command.getCancellationCode().equals(CancellationCode.DUPLICATE)) {
					setState(CANCELED_NAME);
					setCancellationCode(Command.CC_DUPLICATE);
					setResolutionCode(null);
					setFeedbackCode(null);
				} else if (command.getCancellationCode().equals(CancellationCode.INAPPROPRIATE)) {
					setState(CANCELED_NAME);
					setCancellationCode(Command.CC_INAPPROPRIATE);
					setResolutionCode(null);
					setFeedbackCode(null);
				} else {
					throw new UnsupportedOperationException();
				}
			}	else {
				throw new UnsupportedOperationException();

			}
		
			if (getState().equals(getStateName())) {
				throw new UnsupportedOperationException();
			}
			
			notes.add(command.getNote());

		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return FEEDBACK_NAME;

		}

	}

	/**
	 * Class for when a ticket enters the resolved state
	 * @author kaceyjohnson
	 *
	 */
	public class ResolvedState implements TicketState {


		/**
		 * Update the Ticket based on the given Command.
		 * An UnsupportedOperationException is thrown if the CommandValue
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Ticket's
		 * state.
		 * @throws UnsupportedOperationException if the CommandValue is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			if (command.getCommand().equals(CommandValue.FEEDBACK)) {
				if (command.getFeedbackCode().equals(FeedbackCode.AWAITING_CALLER)) {
					setState(FEEDBACK_NAME);
					setFeedbackCode(Command.F_CALLER);
					setResolutionCode(null);
					setCancellationCode(null);
				} else if (command.getFeedbackCode().equals(FeedbackCode.AWAITING_CHANGE)) {
					setState(FEEDBACK_NAME);
					setFeedbackCode(Command.F_CHANGE);
					setResolutionCode(null);
					setCancellationCode(null);
				} else if (command.getFeedbackCode().equals(FeedbackCode.AWAITING_PROVIDER)) {
					setState(FEEDBACK_NAME);
					setFeedbackCode(Command.F_PROVIDER);
					setResolutionCode(null);
					setCancellationCode(null);

				} else {
					throw new UnsupportedOperationException();
				}
			} else if (command.getCommand().equals(CommandValue.REOPEN)) {
				setState(WORKING_NAME);
				setCancellationCode(null);
				setResolutionCode(null);
			} else if (command.getCommand().equals(CommandValue.CONFIRM)) {
				setState(CLOSED_NAME);
				setCancellationCode(null);
			} else {
				throw new UnsupportedOperationException();

			}

			notes.add(command.getNote());

			if (getState().equals(getStateName())) {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return RESOLVED_NAME;

		}

	}

	/**
	 * Class for when a ticket enters the closed state
	 * @author kaceyjohnson
	 *
	 */
	public class ClosedState implements TicketState {


		/**
		 * Update the Ticket based on the given Command.
		 * An UnsupportedOperationException is thrown if the CommandValue
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Ticket's
		 * state.
		 * @throws UnsupportedOperationException if the CommandValue is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			if (command.getCommand().equals(CommandValue.REOPEN)) {
				setState(WORKING_NAME);
				setResolutionCode(null);
				setCancellationCode(null);
				setFeedbackCode(null);
			} else {
				throw new UnsupportedOperationException();

			}
			
			if (getState().equals(getStateName())) {
				throw new UnsupportedOperationException();
			}
			notes.add(command.getNote());

		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return CLOSED_NAME;
		}

	}

	/**
	 * Class for when a ticket enters the canceled state
	 * @author kaceyjohnson
	 *
	 */
	public class CanceledState implements TicketState {

		/**
		 * Update the Ticket based on the given Command.
		 * An UnsupportedOperationException is thrown if the CommandValue
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Ticket's
		 * state.
		 * @throws UnsupportedOperationException if the CommandValue is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			if (getState().equals(getStateName())) {
				throw new UnsupportedOperationException();
			}
			
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return CANCELED_NAME;
		}
	}

	/**
	 * Enumerations for category commmands
	 * @author kaceyjohnson
	 *
	 */
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }

	/**
	 * Enumerations for the priority commands
	 * @author kaceyjohnson
	 *
	 */
	public enum Priority { URGENT, HIGH, MEDIUM, LOW }

	/**
	 * Enumerations for the ticket types
	 * @author kaceyjohnson
	 *
	 */
	public enum TicketType { REQUEST, INCIDENT }
}
