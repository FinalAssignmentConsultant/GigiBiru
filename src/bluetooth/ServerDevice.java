/*
 * Marge, Java Bluetooth Framework
 * Copyright (C) 2006  Project Marge
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * owner@marge.dev.java.net
 * http://marge.dev.java.net
 */

package bluetooth;





/**
 * Representation of a Bluetooth server device.
 */
public class ServerDevice extends Device {

	/**
	 * Contructor used to create a server. The incomming read messages of the given Channel will be
	 * forwarded to the Listener at every Read Interval.
	 * 
	 * @param communicationListener
	 *            <code>CommunicationListener</code> which will the notified
	 *            for incoming messages.
	 * @param channel
	 *            <code>CommunicationChannel</code> used for communication.
	 * @param readInterval
	 *            Time between each message reading.
	 */
	public ServerDevice(CommunicationChannel channel, int readInterval) {
		super(channel, readInterval);
	}

	/**
	 * Enables message broadcast between connected devices.
	 * 
	 * @param enableBroadcast
	 *            Parameter for enabling broadcast or not.
	 */
	public void setEnableBroadcast(boolean enableBroadcast) {
		this.enableBroadcast = enableBroadcast;
	}

	/**
	 * Adds a new communication channel into the server. It is used if you want
	 * to connect a new device.
	 * 
	 * @param channel
	 *            <code>CommunicationChannel</code> that will be added.
	 */
	public void addChannel(CommunicationChannel channel) {
		// TODO check if not bigger than possible connections
		this.channels.addElement(channel);
	}
}