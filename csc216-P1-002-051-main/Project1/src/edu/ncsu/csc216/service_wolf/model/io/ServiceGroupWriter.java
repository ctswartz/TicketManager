package edu.ncsu.csc216.service_wolf.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Writer class for the service class
 * @author kaceyjohnson
 *
 */
public class ServiceGroupWriter {

	/**
	 * Constructor for the class
	 */
	public ServiceGroupWriter() {
		//
	}
	 
	/**
	 * writes the service groups to file 
	 * @param fileName of the written file 
	 * @param serviceGroups to write to file 
	 */
	public static void writeServiceGroupsToFile(String fileName, List<ServiceGroup> serviceGroups) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(new File(fileName));
		
		for (int i = 0; i < serviceGroups.size(); i++) {
			ServiceGroup sg = serviceGroups.get(i);
		    fileWriter.println("# " + sg.getServiceGroupName());
		    for (int j = 0; j < sg.getIncidents().size(); j++) {
		    	Incident incident = sg.getIncidents().get(j);
		    	fileWriter.println("* " + incident.toString()); 
		    }
		} 
		fileWriter.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
