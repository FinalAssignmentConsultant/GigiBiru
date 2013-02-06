/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bluetooth;



/**
 *
 * @author Administrator
 */
public abstract class Configuration {
	protected int readInterval;
	public Configuration() {
		super();
		this.readInterval = 100;
	}

	/**
	 * Returns a Connection URL for use in the Generic Connection Framework,
	 * following the current configurations.
	 *
	 * @return GCF connection URL.
	 */
	public abstract String getConnectionURL();



	/**
	 * Returns the current read interval between every incoming message check.
	 *
	 * @return Read interval.
	 */
	public int getReadInterval() {
		return readInterval;
	}

	/**
	 * Sets the current read interval between every incoming message check.
	 */
	public void setReadInterval(int readInterval) {
		this.readInterval = readInterval;
	}

}
