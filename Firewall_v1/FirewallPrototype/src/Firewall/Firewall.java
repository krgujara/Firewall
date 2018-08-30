/**
 * Firewall class holds the set of allowedRules, 
 * and decides if a package should be accepted with respect to the allowedRules. 
 * @author      Komal Gujarathi
 * @version     1
 */
package Firewall;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Firewall.Rule.directions;
import Firewall.Rule.protocols;

public class Firewall {
	// list of all the allowedRules
	private ArrayList<Rule> allowedRules = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param rulesFile (string): path to the csv files, from where the rules are to be loaded.
	 */ 

	public Firewall(String rulesFile) {
		File file = new File(rulesFile);

		try {
			// read from the file
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNext()) {

				String eachInputLine = inputStream.next();
				String[] values = eachInputLine.split(","); 
				// values is the array of direction, protocol, port, ip address
				for (int i = 0; i < values.length; i++)
				{
					// extract port numbers (can be a value/ a range)
					String[] ports = values[2].split("-");

					int portBegin = Integer.valueOf(ports[0]);
					int portEnd = (ports.length > 1) ? Integer.valueOf(ports[1]) : 0;

					// extract ip address (can be a value/ a range)
					String[] ipaddresses = values[3].split("-");
					String ipBegin = ipaddresses[0];
					String ipEnd = ipaddresses.length > 1 ? ipaddresses[1] : "0";

					Rule each = new Rule(directions.valueOf(values[0]), protocols.valueOf(values[1]), portBegin,
							portEnd, ipBegin, ipEnd);
					allowedRules.add(each);
				}
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function that takes exactly four arguments and returns a boolean: true,
	 * if there exists a rule in the file that this object was initialized with that allows traffic with these particular properties, 
	 * and false otherwise.
	 * @param direction (string): “inbound” or “outbound”
 	 * @param protocol (string): exactly one of “tcp” or “udp”, all lowercase 
 	 * @param port (integer) – an integer in the range [1, 65535]
 	 * @param ip_address (string): a single well-formed IPv4 address.
	 * @return boolean value : if package is allowed true/ else false
	 */ 
	public boolean accept_packet(String direction, String protocol, Integer port, String ipAddress) {
		try
		{
			Rule packetToTest = new Rule(directions.valueOf(direction), protocols.valueOf(protocol), port, 0, ipAddress,
					"0");
			// for all the rules, check if any rule allows this package.. if so, return true.
			for (int i = 0; i < allowedRules.size(); i++) {
				Rule currRule = allowedRules.get(i);
				if (currRule.equals(packetToTest))
					return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
