package edu.ncsu.csc216.service_wolf.model.io;


//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.Scanner;


import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Class for processing service group information from the file 
 * @author kaceyjohnson
 *
 */
public class ServiceGroupsReader {
	/**
	 * Constructor for class
	 */
	public ServiceGroupsReader() {
//
	}

	/**
	 * reads in ServiceGroups from the file 
	 * @param fileName of the file 
	 * @return an array list of the service groups
	 */
	public static ArrayList<ServiceGroup> readServiceGroupsFile(String fileName)  {
		
		ArrayList<ServiceGroup> serviceGroups = new ArrayList<ServiceGroup>(); //Create an empty array of ServiceGroup objects
		try { //Attempt to do the following
				//Read the line, process it in processServiceGroups, and get the object
				//If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
		fileReader.useDelimiter("\\r?\\n?[#]");
		while (fileReader.hasNext()) { //While we have more lines in the file
			
 
				ServiceGroup group = processServiceGroup(fileReader.next()); 

				//Create a flag to see if the newly created Course is a duplicate of something already in the list  
				boolean duplicate = false;
				//Look at all the courses in our list
				for (int i = 0; i < serviceGroups.size(); i++) {
					//Get the course at index i
					ServiceGroup current = serviceGroups.get(i); 
					//Check if the name and section are the same
					if (group.getServiceGroupName().equals(current.getServiceGroupName())) {
						//It's a duplicate!
						duplicate = true;
						break; //We can break out of the loop, no need to continue searching
					}
				}
				//If the course is NOT a duplicate
				if (!duplicate) {
					serviceGroups.add(group); //Add to the ArrayList!
				} //Otherwise ignore
			
			}
		} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Unable to load file.");
		}
		//Close the Scanner b/c we're responsible with our file handles
		//Return the ArrayList with all the courses we read!
		return serviceGroups;
	}

	/**
	 * Processes the service groups 
	 * @param serviceGroup is the group that must be processed
	 * @return the processed service group
	 */
	private static ServiceGroup processServiceGroup(String serviceGroup) throws FileNotFoundException {
		Scanner input = new Scanner(serviceGroup);
		String serviceGroupName = input.nextLine().trim();
		input.useDelimiter("\\r?\\n?[*]");
		
		ServiceGroup serviceGroupFound = new ServiceGroup(serviceGroupName);
		while (input.hasNext()) {
			String in = input.next();
			Incident n = processIncident(in);
			serviceGroupFound.addIncident(n);
		}
		input.close();
		return serviceGroupFound;
	}

	/**
	 * Processed the incident 
	 * @param incident is the name of the incident
	 * @return the processed incident
	 */
	private static Incident processIncident(String incident) {
		Scanner input = new Scanner(incident);
		String incidentInfo = input.nextLine();
		input.useDelimiter("\\r?\\n?[-]");
		
		ArrayList<String> log = new ArrayList<String>();
		while(input.hasNext()) {
			String logMessage = input.next().substring(2);
			log.add(logMessage);
		}
		input.close();
		
		Scanner incidentInput = new Scanner(incidentInfo);
		incidentInput.useDelimiter(",");
		Incident incidents = null;
		int id = 0;
		String title = "";
		String caller = "";
		String owner = "";
		int reopenCount = 0;	
		String statusDetails = "";
		String state = "";
		try { 
			id = Integer.parseInt(incidentInput.next().substring(1));
			state = incidentInput.next();
			title = incidentInput.next();
			caller = incidentInput.next();
			reopenCount = Integer.parseInt(incidentInput.next());
			owner = incidentInput.next();
			statusDetails = incidentInput.next();
			incidentInput.close();
		} catch(InputMismatchException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		incidents = new Incident(id, title, caller, owner, reopenCount, statusDetails, state, log);

		return incidents;
	}
}