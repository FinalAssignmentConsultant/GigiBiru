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

import java.io.IOException;
import java.util.Vector;
import midlet.GigiBiru;


/**
 * Abstract representation of a generic Bluetooth device.
 */
public abstract class Device implements Runnable {

	protected boolean enableBroadcast;

	protected Vector channels;

	private boolean listening;

	

	private long readInterval;

	/**
	 * Constructor. The incomming read messages of the given Channel will be
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
	public Device(
			CommunicationChannel channel, int readInterval) {
		this.enableBroadcast = false;
		this.channels = new Vector(7);
		this.channels.addElement(channel);
		
		this.listening = false;
		this.readInterval = readInterval;
	}

	/**
	 * Gets the CommunicationChannel at position i.
	 * 
	 * @param i
	 *            Position of the desired Channel.
	 * @return <code>CommunicationChannel</code> at referenced position.
	 */
	protected CommunicationChannel getChannel(int i) {
		return (CommunicationChannel) this.channels.elementAt(i);
	}

	/**
	 * Sends the given bytes through the available Channels.
	 * 
	 * @param message
	 *            The bytes to be sent.
	 */
	public void send(byte[] message) {
		for (int i = 0; i < channels.size(); i++) {
			try {
				this.getChannel(i).send(message);
			} catch (IOException e) {
				GigiBiru.instance.errorOnSending(e);
				this.channels.removeElement(this.getChannel(i));
			}
		}
	}

	/**
	 * Sends the given bytes through the available Channels, skiping the
	 * specified one.
	 * 
	 * @param message
	 *            The bytes to be sent.
	 * @param skip
	 *            The Channel that will not receive the message.
	 */
	protected void send(byte[] message, CommunicationChannel skip) {
		for (int i = 0; i < channels.size(); i++) {
			if (!skip.equals(this.getChannel(i))) {
				try {
					this.getChannel(i).send(message);
				} catch (IOException e) {
					GigiBiru.instance.errorOnSending(e);
					this.channels.removeElement(this.getChannel(i));
				}
			}
		}
	}

	/**
	 * Starts listening for incoming messages.
	 */
	// XXX Two different Threads can't be openned.
	public void startListening() {
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * The run method that will start the listening process.
	 */
	// TODO Move to a inner class
	public final void run() {
		if (!listening) {
			this.listening = true;
			this.listen();
		}
	}

	/**
	 * Stops listening for incoming messages.
	 */
	public final void stopListenning() {
		this.listening = false;
	}

	/**
	 * Listening for incoming messages.
	 */
	private final void listen() {
		while (listening) {
			for (int i = 0; i < this.channels.size(); i++) {
				try {
					byte[] b = this.getChannel(i).receive();
					if (b != null) {
						if (this.enableBroadcast) {
							this.send(b, this.getChannel(i));
						}
						GigiBiru.instance.receiveMessage(b);
					}
				} catch (IOException e) {
					GigiBiru.instance.errorOnReceiving(e);
					this.channels.removeElement(this.getChannel(i));
				}
			}
			try {
				Thread.sleep(this.readInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Closes the device's communication channels.
	 */
	public void close() {
		for (int i = 0; i < this.channels.size(); i++) {
			this.getChannel(i).close();
		}
	}

	/**
	 * Returns the current read interval between every incoming message check.
	 * 
	 * @return Read interval.
	 */
	public long getReadInterval() {
		return readInterval;
	}

	/**
	 * Sets the current read interval between every incoming message check.
	 */
	public void setReadInterval(long readInterval) {
		this.readInterval = readInterval;
	}

}