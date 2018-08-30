/**
 * Rule class holds all the attributes (direction, protocol, port, ipAddress)for a allowed Rule. 
 * @author      Komal Gujarathi
 * @version     1
 */
package Firewall;

import IpAddress.IpAddress;

public class Rule {

	public enum directions {
		inbound, outbound
	};

	public enum protocols {
		tcp, udp
	};

	private directions direction;
	private protocols protocol;
	private int portBegin;
	private int portEnd;
	private IpAddress ipAddress;

	/**
	 * Constructor
	 * @param direction (directions enum): values can be inbound/ outbound
	 * @param protocol (protocols enum): values can be tcp/ udp
	 * @param portBegin (int): port value/ first value of the range if port is given as a range
	 * @param portEnd (int): second value of the range if port is given as a range, 0 otherwise
	 * @param ipAddressBegin (String): ip value/ first value of the range if ipAddress is given as a range 
	 * @param ipAddressEnd (String): ip value/ second value of the range if ipAddress is given as a range 
	 */ 
	public Rule(directions direction, protocols protocol, int portBegin, int portEnd, String ipAddressBegin,
			String ipAddressEnd) {
		super();
		this.direction = direction;
		this.protocol = protocol;
		this.portBegin = portBegin;
		this.portEnd = portEnd;
		this.ipAddress = new IpAddress(ipAddressBegin, ipAddressEnd);
	}

	@Override
	public String toString() {
		return "Rule [direction=" + direction + ", protocol=" + protocol + ", portBegin=" + portBegin + ", portEnd="
				+ portEnd + ", ipAddress=" + ipAddress + "]";
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (direction != other.direction)
			return false;
		// if port number not provided as a range, check if 2 port numbers are equal
		if (portEnd == 0) {
			if (portBegin != other.portBegin)
				return false;
		} else if ((other.portBegin < portBegin || portEnd < other.portEnd)) // check if port number falls in the range [portBegin, portEnd]
			return false;
		if (!(ipAddress.equals(other.ipAddress))) 
			return false;
		if (protocol != other.protocol)
			return false;

		return true;
	}
}