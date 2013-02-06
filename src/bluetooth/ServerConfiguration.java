package bluetooth;
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



import javax.bluetooth.UUID;




// TODO put attributes in a hashtable. The attributes would be setted by a put
// method like available in the hashtable and by setEncript(), setAuthorize, ...
/**
 * Configuration for creating a <code>ServerDevice</code>. Hold all the
 * information used for creating a Server using the
 * <code>CommunicationFactory</code>.
 *
 */
public class ServerConfiguration extends Configuration {

	private UUID uuid;

	private int service;

	private int maxNumberOfConnections;

	private String serverName;

	private String protocol;

	private boolean encrypt;

	private boolean authorize;

	private boolean authenticate;

	private String[] additionalParamsKeyValueToConnection;

	/**
	 * Constructor.
	 *
	 * @param communicationListener
	 *            <code>CommunicationListener</code> which will the notified for incoming messages.
	 */
	public ServerConfiguration() {
		
		// TODO create class that can generate service value, server name and
		// UUID. Maybe using the MIDlet name and package as parameters using
		// some hash alghoritm
		this.uuid = new UUID(MargeDefaults.DEFAULT_UUID, false);
		this.service = MargeDefaults.DEFAULT_SERVICE;
		this.serverName = MargeDefaults.DEFAULT_SERVER_NAME;
		this.encrypt = false;
		this.authorize = false;
		this.authenticate = false;
		this.maxNumberOfConnections = 1;
		this.protocol = "btssp";
	}

	/**
	 * Returns a URL used to create a Server Service.
	 *
	 * @return Service creation URL.
	 */
	public String getConnectionURL() {
		String url = this.getProtocol() + "://localhost:"
				+ this.getUuid().toString() + ";name=" + this.getServerName()
				+ ";encrypt=" + this.getEncrypt() + ";authorize="
				+ this.getAuthorize() + ";authenticate="
				+ this.getAuthenticate();

		String[] additionalParamsKeyValueToConnection = this
				.getAdditionalParamsKeyValueToConnection();
		if (additionalParamsKeyValueToConnection != null) {
			for (int i = 0; i < additionalParamsKeyValueToConnection.length; i++) {
				url += ";" + additionalParamsKeyValueToConnection[i];
			}
		}
		return url;
	}

	/**
	 * Returns the Server name.
	 *
	 * @return Server name.
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Sets the name that will be used to create the server.
	 *
	 * @param serverName
	 *            New server name.
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * Returns the number that will identify the Server service.
	 *
	 * @return Service identifier.
	 */
	public int getService() {
		return service;
	}

	/**
	 * Sets the number that will identify the Server service.
	 *
	 * @param service
	 *            Service identifier.
	 */
	public void setService(int service) {
		this.service = service;
	}

	/**
	 * Returns the current Universally Unique Identifier of the server.
	 *
	 * @return Universally Unique Identifier.
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * Sets the server Universally Unique Identifier of the server.
	 *
	 * @param uuid
	 *            New Universally Unique Identifier.
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * Returns the Bluetooth Stack protocol name that will be used to create
	 * this Server.
	 *
	 * @return Bluetooth Stack protocol name.
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Sets the Bluetooth Stack protocol name that will be used to create this
	 * Server
	 *
	 * @param protocol
	 *            Bluetooth Stack protocol name.
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Returns if the server will use authentication or not.
	 *
	 * @return flag.
	 */
	public boolean getAuthenticate() {
		return authenticate;
	}

	/**
	 * Sets if the server will use authentication.
	 *
	 * @param authenticate
	 */
	public void setAuthenticate(boolean authenticate) {
		this.authenticate = authenticate;
	}

	/**
	 * Returns the flag indicating if the server will need Authorization.
	 *
	 * @return Authorization flag.
	 */
	public boolean getAuthorize() {
		return authorize;
	}

	/**
	 * Sets the flag indicating if the server will need Authorization.
	 *
	 * @param authorize
	 *            Authorization flag.
	 */
	public void setAuthorize(boolean authorize) {
		this.authorize = authorize;
	}

	/**
	 * Returns the flag indicating if the server will use data Encryption.
	 *
	 * @return Encryption flag.
	 */
	public boolean getEncrypt() {
		return encrypt;
	}

	/**
	 * Sets the flag indicating if the server will use data Encryption.
	 *
	 * @param encrypt
	 *            Encryption flag.
	 */
	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * Return any additional parameters that will be used in the server. It does
	 * not inclued the Encrypt, Authorize and Authenticate parameters.
	 *
	 * @return Additional parameters.
	 */
	public String[] getAdditionalParamsKeyValueToConnection() {
		return additionalParamsKeyValueToConnection;
	}

	// XXX Like, new String[]{"receiveMTU=512", transmitMTU=512",
	// "master=true"};
	/**
	 * Sets the additional parameters that will be used in the server. For
	 * Encrypt, Authorize and Authenticate parameters use the default get's and
	 * set's.
	 *
	 * @param additionalParamsKeyValueToConnection
	 *            Additional parameters.
	 */
	public void setAdditionalParamsKeyValueToConnection(
			String[] additionalParamsKeyValueToConnection) {
		this.additionalParamsKeyValueToConnection = additionalParamsKeyValueToConnection;
	}

	/**
	 * Returns the maximum number of connections that the Server will support.
	 *
	 * @return Maximum number of connections.
	 */
	public int getMaxNumberOfConnections() {
		return maxNumberOfConnections;
	}

	/**
	 * Sets the maximum number of connections that the Server will support. This
	 * number is limited by 7 by the Bluetooth technology.
	 *
	 * @param maxNumberOfConnections
	 *            The new maximum number of connections.
	 */
	public void setMaxNumberOfConnections(int maxNumberOfConnections) {
		this.maxNumberOfConnections = maxNumberOfConnections;
	}

}