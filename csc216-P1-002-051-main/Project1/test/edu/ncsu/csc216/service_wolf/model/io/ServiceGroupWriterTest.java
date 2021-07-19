/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Writes in Service groups from the file 
 * @author kaceyjohnson
 *
 */
public class ServiceGroupWriterTest {

	/**
	 * Test method for writeServiceGroupToFile
	 */
	@Test
	public void testWriteServiceGroupToFile() {
		List<ServiceGroup> serviceGroup = new ArrayList<ServiceGroup>();
		ServiceGroup service1 = new ServiceGroup("CSC IT");
		ServiceGroup service2 = new ServiceGroup("ITECS");
		ServiceGroup service3 = new ServiceGroup("OIT");
//		// Incident 1 for service group CSC IT 
		ArrayList<String> incident1 = new ArrayList<String>();
		incident1.add("Set up piazza for Spring 2021");
		incident1.add("Canceled; not an NC State IT service");
		Incident i1 = new Incident(2, "Piazza", "sesmith5", "Unowned", 0, "Not an Incident", "Canceled", incident1);
		
//		// Incident 2 for service group CSC IT 
		ArrayList<String> incident2 = new ArrayList<String>();
		incident2.add("When I go to wolfware.ncsu.edu, I get a 500 error");
		Incident i2 = new Incident(3, "Moodle down", "sesmith5", "Unowned", 0, "No Status", "New", incident2);
//		
//		
//		// Incident 3 for service group CSC IT
		ArrayList<String> incident3 = new ArrayList<String>();
		incident3.add("Please set up Jenkins VMs for Spring 2021 semester.");
		incident3.add("Assigned to C. Gurley");
		incident3.add("Set up test VM. Awaiting verification from caller.");
		incident3.add("VM works great, please deploy the rest.");
		incident3.add("VMs deployed. Marked resolved.");
		incident3.add("One of the VMs has the wrong version of Checkstyle installed.");
		incident3.add("Updated version of Checkstyle.");
		Incident i3 = new Incident(4, "Set up Jenkins VMs", "sesmith5", "cgurley", 1, "Permanently Solved", "Resolved", incident3);

//		// Incident 4 for service group CSC IT
		ArrayList<String> incident4 = new ArrayList<String>();
		incident4.add("Jenkins requires VPN to access.  Please open to general access.");
		incident4.add("Assigned to C. Gurley");
		Incident i4 = new Incident(9, "Jenkins behind firewall", "sesmith5", "cgurley", 0, "No Status", "In Progress", incident4);
		
		// Adding incidents so service group CSC IT 
		service1.addIncident(i1);
		service1.addIncident(i2);
		service1.addIncident(i3);
		service1.addIncident(i4);
		
		// Incident 1 for service group ITECS
		ArrayList<String> incident5 = new ArrayList<String>();
		incident5.add("I can't install Java on my computer.");
		incident5.add("Assigned to itecs1");
		incident5.add("Awaiting caller's feedback on attempting to install Java from Oracle");
		Incident i5 = new Incident(7, "Java not installed correctly", "zmgrosec", "itecs1", 0, "Awaiting Caller", "On Hold", incident5);
		service2.addIncident(i5);
	
//		// Incident 1 for service group OIT
		ArrayList<String> incident6 = new ArrayList<String>();
		incident6.add("I forgot my password and can't log into NC State accounts");
		incident6.add("OIT staff member on call with support");
		Incident i6 = new Incident(1, "Forgot password", "jctetter", "oit_staff", 0, "No Status", "In Progress", incident6);
		service3.addIncident(i6);
		
		serviceGroup.add(service1);
		serviceGroup.add(service2);
		serviceGroup.add(service3);
		
		try { 
			ServiceGroupWriter.writeServiceGroupsToFile("test-files/valid_records.txt", serviceGroup);
			
		} catch (IllegalArgumentException e) {
			fail();
		}
		checkFiles("test-files/incidents1.txt", "test-files/valid_records.txt");
	}
	 
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
