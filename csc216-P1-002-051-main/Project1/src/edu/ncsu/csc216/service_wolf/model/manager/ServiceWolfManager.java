package edu.ncsu.csc216.service_wolf.model.manager;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupWriter;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupsReader;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * manager of the service wolf 
 */
public class ServiceWolfManager {
	
	/** Instance of the ServiceWolfManager */
	private static ServiceWolfManager instance;
	/** List of ServiceGroups */
	ArrayList<ServiceGroup> serviceGroup;
	/** Current serviceGroup */
	ServiceGroup currentServiceGroup;
	/** 
	 * Constructor for the class
	 */
	public ServiceWolfManager() {
		serviceGroup = new ArrayList<ServiceGroup>();
	}
	
	/**
	 * returns instance of the ServiceWolfManager
	 * @return an instance of the service wolf manager 
	 */
	public static ServiceWolfManager getInstance() {
		if (instance == null) {
			instance = new ServiceWolfManager();
		}
		return instance;
		
	}
	/**
	 * saves the file
	 * @param fileName of the saved file 
	 */
	public void saveToFile(String fileName) {
		if (currentServiceGroup == null || currentServiceGroup.getIncidents() == null)	{
			throw new IllegalArgumentException("Unable to save file.");
		}
		ServiceGroupWriter.writeServiceGroupsToFile(fileName, serviceGroup);
		
	}
	
	/**
	 * loads from a the file 
	 * @param fileName of the loaded file 
	 */
	public void loadFromFile(String fileName) {
		List<ServiceGroup> sg;
		sg = ServiceGroupsReader.readServiceGroupsFile(fileName);
		currentServiceGroup = sg.get(0);
		for (ServiceGroup s : sg) {
			addServiceGroupToListByName(s);
		}
	}
	 
	/** 
	 * 2D array of the incident information
	 * @return 2D array of incident info 
	 */
	public String [][] getIncidentsAsArray() {
		if (currentServiceGroup == null) {
			return null;
		}
		
		String [][] incidents = new String[currentServiceGroup.getIncidents().size()][4];
		for (int i = 0; i < currentServiceGroup.getIncidents().size(); i++) {
			Incident s = currentServiceGroup.getIncidents().get(i);
			incidents[i][0] = s.getId() + "";
			incidents[i][1] = s.getState();
			incidents[i][2] = s.getTitle();
			incidents[i][3]	= s.getStatusDetails();
		}
		return incidents;
		
	}
	
	/**
	 * returns found incident 
	 * @param id of the desired incident 
	 * @return the found incident 
	 */
	public Incident getIncidentById(int id) {
		Incident found = null;
		for (int i = 0; i < serviceGroup.size(); i++) {
			for (int j = 0; j < serviceGroup.get(i).getIncidents().size(); j++) {
				int incidentId = serviceGroup.get(i).getIncidents().get(j).getId();
				if (id == incidentId) {
					found = serviceGroup.get(i).getIncidents().get(j);
					return found;
				}
			}
		}
		return null;
	}
	
	/**
	 * Executes the command 
	 * @param id of the incident command initiate 
	 * @param command to execute 
	 */
	public void executeCommand(int id, Command command) {
		currentServiceGroup.executeCommand(id, command);
	}
	
	/**
	 * deletes the specified incident 
	 * @param id of the specific incident
	 */
	public void deleteIncidentById(int id) {
		if (currentServiceGroup == null) {
			throw new IllegalArgumentException("Cannot delete incident with no service group");
			
		}
		currentServiceGroup.deleteIncidentById(id);
	}
	
	/*** 
	 * adds the incident to the specified service group 
	 * @param title of the incident group
	 * @param caller for the incident
	 * @param message attached to incident
	 */
	public void addIncidentToServiceGroup(String title, String caller, String message) {
		if (title == null || caller == null || message == null 
				|| title.isEmpty() || caller.isEmpty() || message.isEmpty()) {
			throw new IllegalArgumentException("Invalid incident info.");
		}
		Incident i = new Incident(title, caller, message);
		currentServiceGroup.addIncident(i);
	}
	
	
	/**
	 * loads the service group
	 * @param serviceGroupName added the list of service groups
	 */
	public void loadServiceGroup(String serviceGroupName) {
		ServiceGroup s = new ServiceGroup(serviceGroupName);
		if (serviceGroup.isEmpty()) {
			currentServiceGroup = s;
			addServiceGroupToListByName(currentServiceGroup);
			return;
		}
		for (int i = 0; i < serviceGroup.size(); i++) {
			if (serviceGroup.get(i).equals(s)) {
				throw new IllegalArgumentException("Invalid service group name.");
			}
		}
		
		
	}
	
	
	/**
	 * name of the service group 
	 * @return the name of the service group
	 */
	public String getServiceGroupName() {
		if (currentServiceGroup == null) {
			return null;
		}
		return currentServiceGroup.getServiceGroupName();
	}
	
	/**
	 * list of the service groups 
	 * @return array of the service groups 
	 */
	public String [] getServiceGroupList() {
		String[] serviceGroupNames = new String[serviceGroup.size()];
		for (int i = 0; i < serviceGroup.size(); i++) {
			String name = serviceGroup.get(i).getServiceGroupName();
			serviceGroupNames[i] = name;
		}
		return serviceGroupNames;
	}
	
	/**
	 * clears the service groups 
	 */
	public void clearServiceGroups() {
		serviceGroup = new ArrayList<ServiceGroup>();
		currentServiceGroup = null;
	}
	
	/**
	 * allows the service group to be edited 
	 * @param serviceGroupName of the service group to be edited 
	 */
	public void editServiceGroup(String serviceGroupName) {
		if (serviceGroupName == null || serviceGroupName.isEmpty()) {
			throw new IllegalArgumentException("Invalid service group name");
		}
		checkDuplicateServiceName(serviceGroupName);
		currentServiceGroup.setServiceGroupName(serviceGroupName);
		addServiceGroup(serviceGroupName);
		loadServiceGroup(serviceGroupName);
	}
	
	/**
	 * adds a service group
	 * @param serviceGroupName name of the service group to add 
	 */
	public void addServiceGroup(String serviceGroupName) {
		if (serviceGroupName == null || serviceGroupName.isEmpty()) {
			throw new IllegalArgumentException("Invalid service group name");
		}
		
		for (int i = 0; i < serviceGroup.size(); i++) {
			if (serviceGroupName.equals(serviceGroup.get(i).getServiceGroupName())) {
				throw new IllegalArgumentException("Invalid service group name.");
			}
		}
		ServiceGroup s = new ServiceGroup(serviceGroupName);
		addServiceGroupToListByName(s);
	}
	
	/**
	 * adds the service group to the list by name 
	 * @param serviceGroupName of the specific service group
	 */
	public void addServiceGroupToListByName(ServiceGroup serviceGroupName)	{
		checkDuplicateServiceName(serviceGroupName.getServiceGroupName());
		if (serviceGroup.isEmpty()) {
			serviceGroup.add(serviceGroupName);
			return;
		}
		for (int i = 0; i < serviceGroup.size(); i++) {
			if (serviceGroup.get(i).getServiceGroupName().compareTo(serviceGroupName.getServiceGroupName()) > 0) {
				serviceGroup.add(i, serviceGroupName);
				return;
			} else if (serviceGroup.get(i).getServiceGroupName().compareTo(serviceGroupName.getServiceGroupName()) < 0) {
				serviceGroup.add(i + 1, serviceGroupName);
				return;
			} 
		}
	}
	
	/**
	 * determines if duplicate service groups exists 
	 * @param serviceGroupName of the suspected service group 
	 */
	public void checkDuplicateServiceName(String serviceGroupName) {
		for (int i = 0; i < serviceGroup.size(); i++) {
			String currentName = serviceGroup.get(i).getServiceGroupName();
			if(serviceGroupName.equals(currentName)) {
				throw new IllegalArgumentException("Invalid service group name.");
			}
		}
	}
	
	/**
	 * removes a service group 
	 */
	public void deleteServiceGroup() {
		 if (currentServiceGroup == null) {
			 throw new IllegalArgumentException("No service group selected.");
		 }
		 serviceGroup.remove(currentServiceGroup);
		 if (serviceGroup.isEmpty()) {
			 currentServiceGroup = null;
		 }
		 else {
			 currentServiceGroup = serviceGroup.get(0);
		 }
	}
	
	/**
	 * resets the entire ServiceWolfManager
	 */
	protected void resetManager() {
		instance = null;
	}
	
}
