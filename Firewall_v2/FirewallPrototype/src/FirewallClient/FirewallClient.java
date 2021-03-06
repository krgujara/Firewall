/**
 * Client class is used as a Test class for the Firewall class.
 * This class is the entry point to the FirewallPrototype application
 * @author      Komal Gujarathi
 * @version     1
 */
package FirewallClient;
import Firewall.Firewall;

public class FirewallClient {
	public static void main(String[] args) {
		try {
			Firewall prototype = new Firewall("../FirewallPrototype/bin/Firewall/rules.csv");

			System.out.println(prototype.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
			System.out.println(prototype.accept_packet("inbound", "udp", 53, "192.168.2.1"));
			System.out.println(prototype.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
			System.out.println(prototype.accept_packet("inbound", "udp", 24, "52.12.48.92"));
			System.out.println(prototype.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
		} catch (Exception e) {
			System.out.println("Something went wrong!");
		}
	}
}