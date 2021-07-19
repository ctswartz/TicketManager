package edu.ncsu.csc216.service_wolf.model.service_group;

import java.util.ArrayList;
import java.util.List;
import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * information regarding and maintaining a service group 
 * @author kaceyjohnson
 *
 */
public class ServiceGroup {
	/** service group name */
	private String serviceGroupName;
	/** Holds a list of incidents */
	private ArrayList<Incident> list;
	
	/**
	 * Constructor for the ServiceGroup class
	 * @param serviceGroupName is the service group name 
	 */
	public ServiceGroup(String serviceGroupName) {
		setServiceGroupName(serviceGroupName);
		list = new ArrayList<Incident>();
	}
	
	/**
	 * sets incident counter
	 */
	public void setIncidentCounter() {
		if (list.isEmpty()) {
			Incident.setCounter(0);
			return;
		}
		int maxId = list.get(0).getId(); 
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() > maxId) {
				maxId = list.get(i).getId();
			}
		}
		Incident.setCounter(maxId + 1);
	}
	
	/**
	 * sets the service group name 
	 * @param serviceGroupName of the service group 
	 */
	public void setServiceGroupName(String serviceGroupName) {
		if (serviceGroupName == null || serviceGroupName.isEmpty()) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		this.serviceGroupName = serviceGroupName;
		
	}
	 
	/**
	 * returns the service group name
	 * @return name of the service group
	 */
	public String getServiceGroupName() {
		return serviceGroupName;
	
	}
	
	/** 
	 * adds an incident to a service group 
	 * @param incident that will be added to a service group 
	 */
	public void addIncident(Incident incident) {
		
		if (incident ==  null || list.contains(incident)) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		if (list.isEmpty()) {
			list.add(incident);
			return;
		}
		int id = incident.getId();
		for (int i = 0; i < list.size(); i++)	 {
			if (id > list.get(i).getId()) {
				list.add(incident);
				return;
			} else if (id < list.get(i).getId()) {
				list.add(i, incident);
				return;
			}
		}
		Incident.setCounter(incident.getId());
		list.add(incident);
		
	}
	
	/**
	 * grabs incidents from a list 
	 * @return a list of incidents 
	 */
	public List<Incident> getIncidents () {
		return list;
		
	}
	
	/**
	 * retrieves the incident using id 
	 * @param id of the incident 
	 * @return the incident fo und
	 */
	public Incident getIncidentById(int id) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id)
				return list.get(i);
		}
		return null;
	}
	
	/** 
	 * changes command value 
	 * @param id of the command 
	 * @param command is the command state selected
	 */
	public void executeCommand(int id, Command command) {
		Incident i = getIncidentById(id);
		i.update(command);
	}
	 
	/**
	 * deletes incident by id 
	 * @param id of the incident 
	 */
	public void deleteIncidentById(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id)
				list.remove(list.get(i));
		}
	
	}
}
