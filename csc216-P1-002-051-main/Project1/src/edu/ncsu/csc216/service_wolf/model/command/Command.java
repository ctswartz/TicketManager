package edu.ncsu.csc216.service_wolf.model.command;
/**
 * Class for maintaining the command that
 * the user will make in the ServiceWolf to 
 * initiate a state change. 
 * @author kaceyjohnson
 *
 */
public class Command {
	/** information regarding the command */
	private String commandInformation;
	/** message for the specific command */
	private String commandMessage;
	/** Command value that is being entered */
	private CommandValue value;
	
	/**
	 * Constructor for the command class
	 * @param value which represents one of the six possible commands that a user could make.
	 * @param commandInformation information regarding the command 
	 * @param commandMessage message from the command
	 */
	public Command(CommandValue value, String commandInformation, String commandMessage) {
		if (value == null) {
			throw new IllegalArgumentException("Command value cannot be null");
		}
		if (commandMessage == null || commandMessage.isEmpty()) {
			throw new IllegalArgumentException("Invalid command message");
		}
		
		if (value == CommandValue.ASSIGN || value == CommandValue.HOLD || 
				value == CommandValue.RESOLVE || value == CommandValue.CANCEL) {
			if (commandInformation == null || commandInformation.isEmpty()) {
				throw new IllegalArgumentException("Invalid command information");
			}
		} 
		if (value == CommandValue.REOPEN || value == CommandValue.INVESTIGATE) {
				if (commandInformation != null) {
			throw new IllegalArgumentException("Invalid command information");
				}
		}
		
		this.commandInformation = commandInformation; 
		this.commandMessage = commandMessage; 
		this.value = value;
	}

	/**
	 * Retrieves information regard the command 
	 * @return the commandInformation
	 */
	public String getCommandInformation() {
		return commandInformation;
	} 

	/**
	 * Retrieves the command message 
	 * @return the commandMessage
	 */
	public String getCommandMessage() {
		return commandMessage;
	}

	/** 
	 * Some of the possible commands. 
	 * @return the commandValue
	 */
	public CommandValue getCommand() {
		return value;
		
	}

	/**
	 * class for the command value 
	 * @author kaceyjohnson
	 *
	 */
	public enum CommandValue { ASSIGN, HOLD, INVESTIGATE, RESOLVE, REOPEN, CANCEL }
}
	