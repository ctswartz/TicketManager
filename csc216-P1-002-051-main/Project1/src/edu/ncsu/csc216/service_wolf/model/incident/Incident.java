 package edu.ncsu.csc216.service_wolf.model.incident;

import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.command.Command.CommandValue;

/**
 * Incident class for maintaining information
 * in regards to the specified incident.
 * @author kaceyjohnson
 */
public class Incident {
	/** Incident id */
	private int incidentId;
	/** Current state of the incident */
	private IncidentState currentState;
	/** title of the incident */
	private String title; 
	/** caller name */
	private String caller;
	/** Number of times that an incident has be reopened */
	private int reopenCount;
	/** Incident personnel owner name */
	private String owner; 
	/** Incident status details which will take on values of the 
	 * on hold reasons, resolutions reasons, or cancellation reasons
	 * depending on the current state */
	private String statusDetails;
	/** State of the incident */ 
	private String state;
	/** incident log */		
	private ArrayList<String> incidentLog;
	
	/** New state of the incident */
	public static final String NEW_NAME = "New";
	/** In progress state of the incident */
	public static final String IN_PROGRESS_NAME = "In Progress";
	/** On Hold state of the incident */
	public static final String ON_HOLD_NAME = "On Hold";
	/** Resolved state of the incident */
	public static final String RESOLVED_NAME = "Resolved";
	/** Canceled state of the incident */
	public static final String CANCELED_NAME = "Canceled";

	/** Awaiting caller */
	public static final String HOLD_AWAITING_CALLER = "Awaiting Caller";
	/** Awaiting change */
	public static final String HOLD_AWAITING_CHANGE = "Awaiting Change";
	/** Awaiting vendor  */
	public static final String HOLD_AWAITING_VENDOR = "Awaiting Vendor";

	/** Permanently solved incident  */
	public static final String RESOLUTION_PERMANENTLY_SOLVED = "Permanently Solved";
	/** Incident was resolved by a workaround  */
	public static final String RESOLUTION_WORKAROUND = "Workaround";
	/** Incident was closed by the caller */
	public static final String RESOLUTION_CALLER_CLOSED = "Caller Closed";
	
	/** The incident is a duplicate of another incident */
	public static final String CANCELLATION_DUPLICATE = "Duplicate";
	/** The incident is unnecessary */
	public static final String CANCELLATION_UNNECESSARY = "Unnecessary";
	/** This incident is not an incident */
	public static final String CANCELLATION_NOT_AN_INCIDENT = "Not an Incident";
	/** The incident was canceled */
	public static final String CANCELLATION_CALLER_CANCELLED = "Caller Canceled";

	/** This incident is not unowned */
	public static final String UNOWNED = "Unowned";
	/** No status */
	public static final String NO_STATUS = "No Status";
	/** counter */
	public static int counter = 0;
	/** New State Object */
	private static NewState newState;
	/** In Progress State Object */
	private static InProgressState inProgressState;
	/** On Hold State Object */
	private static OnHoldState onHoldState;
	/** Resolved State Object */
	private static ResolvedState resolvedState;
	/** Canceled State Object */
	private static CanceledState canceledState;
	
	

	/** first constructor of the class
	 * @param title of the incident
	 * @param caller of the incident 
	 * @param message of the incident 
	 */ 
	public Incident(String title, String caller, String message) {
		incidentLog = new ArrayList<String>();
		newState = new NewState();
		inProgressState = new InProgressState();
		onHoldState = new OnHoldState(); 
		resolvedState = new ResolvedState();
		canceledState = new CanceledState();
		setId(Incident.counter); 
		setTitle(title);
		setCaller(caller);
		setOwner(UNOWNED);
		setState(NEW_NAME);
		setStatusDetails(NO_STATUS);
		addMessagesToIncidentLog(message);
		incrementCounter();
		
	}

	/**
	 * second constructor the checks the constraints of the incident 
	 * @param incidentId of the incident 
	 * @param title of the incident 
	 * @param caller of the incident
	 * @param owner of the incident 
	 * @param reopenCount of a certain incident 
	 * @param statusDetails of the incident 
	 * @param state of the incident 
	 * @param incidentLog messages of the incident 
	 */
	public Incident(int incidentId, String title, String caller, String owner, 
			int reopenCount, String statusDetails, String state, ArrayList<String> incidentLog) {
		this.incidentLog = incidentLog;
		newState = new NewState();
		inProgressState = new InProgressState();
		onHoldState = new OnHoldState();
		resolvedState = new ResolvedState();
		canceledState = new CanceledState();
		setId(incidentId);
		setTitle(title);
		setCaller(caller);	
		setOwner(owner); 
		setReopenCount(reopenCount);
		setState(state);
		setCurrentState(currentState);
		setStatusDetails(statusDetails);

	}
	/** retrieves id of the incident
	 * @return the incidentId
	 */
	public int getId() {
		return incidentId;
	}
	/**
	 * sets the id of the incident 
	 * @param incidentId the incidentId to set
	 */
	private void setId(int incidentId) {
		if (incidentId > Incident.counter) {
			setCounter(incidentId + 1);
		} else if (incidentId <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.incidentId = incidentId;
	}


	/**
	 * Retrieves the current state of the incident 
	 * @return the currentState of the incident 
	 */
	public IncidentState getCurrentState() {
		return currentState;
	}

	/**
	 * Sets the current state of the incident 
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(IncidentState currentState) {
		this.currentState = currentState;
	}

	/**
	 * retrieves the title of the incident  
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * sets the title of the incident 
	 * @param title the title to set
	 */
	private void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.title = title;
	}
	/**
	 * retrieves the caller of the incident 
	 * @return the caller
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * sets the name of the caller 
	 * @param caller the caller to set
	 */
	private void setCaller(String caller) {
		if (caller == null || caller.isEmpty()) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.caller = caller;
	}
	/**
	 * returns the number of reopened incidents
	 * @return the reopenCount
	 */
	public int getReopenCount() {
		return reopenCount;
	}
	/**
	 * Number of incidents that are reopened 
	 * @param reopenCount the reopenCount to set
	 */
	public void setReopenCount(int reopenCount) {
		this.reopenCount = reopenCount;
	}
	/**
	 * returns the owner of the incident
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * sets the owner of the tickets name
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		if (owner == null || owner.isEmpty()) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.owner = owner;
	}
	/**
	 * retrieves status details of the status details
	 * @return the statusDetails
	 */
	public String getStatusDetails() {
		return this.statusDetails;
	}
	
	/**
	 * sets the status details of the incident 
	 * @param statusDetails the current statues of the incident details 
	 */
	private void setStatusDetails(String statusDetails) {
	
		this.statusDetails = statusDetails;
	}
	
	/**
	 * The current state of the incident 
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * Sets the state of the incident 
	 * @param state the state to set
	 */
	public void setState(String state) {
		if (state == null || state.isEmpty()) {
			throw new IllegalArgumentException("Invalid state");
		}
		if (state.equals(NEW_NAME)) {
			setCurrentState(newState);
			this.state = NEW_NAME;
			
		}
		else if (state.equals(IN_PROGRESS_NAME)) {
			setCurrentState(inProgressState);
			this.state = IN_PROGRESS_NAME;
		}
		else if (state.equals(ON_HOLD_NAME)) {
			setCurrentState(onHoldState);
			this.state = ON_HOLD_NAME;
		}
		else if (state.equals(RESOLVED_NAME)) {
			setCurrentState(resolvedState);
			this.state = RESOLVED_NAME;
		}
		else if (state.equals(CANCELED_NAME)) {
			setCurrentState(canceledState);
			this.state = CANCELED_NAME;
		}
	}

	/**
	 * Contains the original incident description and any messages recorded 
	 * during transitions.
	 * @param message added to the incident log 
	 */
	private void addMessagesToIncidentLog(String message) {
		if (message == null || message.isEmpty()) {
			throw new IllegalArgumentException("Invalid incident");
		} 
		this.incidentLog.add(message);

	}
	/**
	 * increments the counter 
	 */
	public static void incrementCounter() {
		Incident.counter = counter + 1;
	}


	/**
	 * Sets the counter amounter
	 * @param counter for the number of incidents 
	 */
	public static void setCounter(int counter) {
		Incident.counter = counter;
	}

	/**
	 * Retrieves messages from the incident log 
	 * @return the desired incident log message
	 */
	public String getIncidentLogMessages() {
		if (incidentLog == null) {
			throw new IllegalArgumentException("Empty log");
		}
		String message = "";
		for (int i = 0; i < incidentLog.size(); i++) {
			message = message.concat("\r- " + incidentLog.get(i).stripLeading());
		}
		return message;
	}
	/**
	 * Update the command 
	 * @param value of the command 
	 */
	public void update(Command value) {
		if (currentState == newState) {
			if (value.getCommand() == CommandValue.ASSIGN || value.getCommand() == CommandValue.CANCEL) {
				currentState.updateState(value);
			}
		} else if (currentState == inProgressState) {
			if (value.getCommand() == CommandValue.HOLD || value.getCommand() == CommandValue.RESOLVE
					|| value.getCommand() == CommandValue.CANCEL) {
				currentState.updateState(value);
			}
		} else if (currentState == onHoldState) {
			if (value.getCommand() == CommandValue.INVESTIGATE) {
				currentState.updateState(value);
			}
		} else if (currentState == resolvedState) {
			if (value.getCommand() == CommandValue.REOPEN || value.getCommand() == CommandValue.CANCEL) {
				currentState.updateState(value);
			}
		} else if (currentState == canceledState) {
			currentState.updateState(value);
		} 	
	}
	/**
	 * String representation of the incident details 	
	 */
	@Override
	public String toString() {
		return incidentId + "," + state + "," + title + ","
				+ caller + "," + reopenCount + "," + owner + "," + statusDetails
				 + getIncidentLogMessages();
	}


	/**
	 * Interface for states in the Incident State Pattern.  All 
	 * concrete incident states must implement the IncidentState interface.
	 * The IncidentState interface should be a private interface of the 
	 * Incident class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface IncidentState {

		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);

		/** 
		 * get the name of the in progress state 
		 * @return the name of the state
		 */
		String getStateName();

	}


	/** 
	 * State class for the New state
	 * @author kaceyjohnson
	 *
	 */
	public class NewState implements IncidentState {
		/** Constructor for inner class NewState */
		private NewState()	{

		}

		/** 
		 * get the name of the in progress state 
		 * @param command is the command the user will make to change states
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.ASSIGN) {
				setState(IN_PROGRESS_NAME);
				setStatusDetails(command.getCommandInformation());
				addMessagesToIncidentLog(command.getCommandMessage());
			} else if (command.getCommand() == CommandValue.CANCEL) {
				setState(CANCELED_NAME);
				setOwner(UNOWNED);
				setStatusDetails(command.getCommandInformation());
				addMessagesToIncidentLog(command.getCommandMessage());
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * name of the state 
		 * @return the name of the current state
		 */
		public String getStateName() {
			return NEW_NAME;
		}
	}

	/** 
	 * State class for the In Progress state
	 * @author kaceyjohnson
	 *
	 */
	public class InProgressState implements IncidentState {
		/** Constructor for inner class InProgress */
		private InProgressState()	{

		}

		/** 
		 * update the state to the in progress state with command 
		 * @param command to update the state 
		 */
		public void updateState(Command command) { 
				if (command.getCommand() == CommandValue.ASSIGN) {
					setState(IN_PROGRESS_NAME);
					setStatusDetails(command.getCommandInformation());
					addMessagesToIncidentLog(command.getCommandMessage());
				} else if (command.getCommand() == CommandValue.CANCEL) {
					setState(CANCELED_NAME);
					setStatusDetails(command.getCommandInformation());
					addMessagesToIncidentLog(command.getCommandMessage());
				} else if (command.getCommand() == CommandValue.HOLD) {
					setState(ON_HOLD_NAME);
					setStatusDetails(command.getCommandInformation());
					addMessagesToIncidentLog(command.getCommandMessage());
				} else if (command.getCommand() == CommandValue.RESOLVE) {
					setState(RESOLVED_NAME);
					setStatusDetails(command.getCommandInformation());
					addMessagesToIncidentLog(command.getCommandMessage());
				} else {
					throw new UnsupportedOperationException();
				}
		}

		/** 
		 * get the name of the in progress state 
		 * @return the name of the state
		 */
		public String getStateName() {
			return IN_PROGRESS_NAME;

		}
	}

	/** 
	 * State class for the OnHold state
	 * @author kaceyjohnson
	 *
	 */
	public class OnHoldState implements IncidentState {
		/** Constructor for inner class OnHold */
		private OnHoldState() {

		}

		/** update the state to the in progress state with command 
		 * @param command to update the state 
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.INVESTIGATE) {
				
				setState(IN_PROGRESS_NAME);
				setStatusDetails(null);
				addMessagesToIncidentLog(command.getCommandMessage());
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/** 
		 * get the name of the in progress state 
		 * @return the name of the state
		 */
		public String getStateName() {
			return ON_HOLD_NAME;

		}
	}

	/** 
	 * State class for the Resolved state
	 * @author kaceyjohnson
	 *
	 */
	public class ResolvedState implements IncidentState {
		/** Constructor for inner class Resolved */
		private ResolvedState()	{

		}

		/**
		 * Updates the current state of an incident 
		 * @param command is the command that is passed in from Command class. 
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.REOPEN) {
				setState(IN_PROGRESS_NAME);
				setStatusDetails(null);
				addMessagesToIncidentLog(command.getCommandMessage());
			} else if (command.getCommand() == CommandValue.CANCEL) {
				setState(CANCELED_NAME);
				setStatusDetails(command.getCommandInformation());
				addMessagesToIncidentLog(command.getCommandMessage());
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/** 
		 * get the name of the in progress state 
		 * @return the name of the state
		 */
		public String getStateName() {
			return RESOLVED_NAME;

		}
	}

	/** 
	 * State class for the Canceled state
	 * @author kaceyjohnson
	 *
	 */
	public class CanceledState implements IncidentState {
		/** Constructor for inner class CanceledState */
		private CanceledState()	{

		}

		/** 
		 * get the name of the in progress state 
		 * @param command value passed into the method. 
		 */
		public void updateState(Command command) {
			if(command.getCommand() == CommandValue.ASSIGN || command.getCommand() == CommandValue.CANCEL
					|| command.getCommand() == CommandValue.HOLD || command.getCommand() == CommandValue.INVESTIGATE
					|| command.getCommand() == CommandValue.RESOLVE || command.getCommand() == CommandValue.REOPEN) {
			throw new UnsupportedOperationException();
			}
		}

		/** 
		 * get the name of the in progress state 
		 * @return the name of the state
		 */
		public String getStateName() {
			return CANCELED_NAME;

		}
	}
}
