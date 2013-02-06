/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetooth;

import java.io.IOException;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.L2CAPConnectionNotifier;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import midlet.GigiBiru;

/**
 *
 * @author Administrator
 */
public class LC2APServer {

    public static ClientDevice connectToServer(ClientConfiguration configuration)
            throws IOException {

        ClientDeviceFactory clientFactory = new ClientDeviceFactory(configuration);
        clientFactory.start();

        synchronized (LC2APServer.class) {
            try {
                LC2APServer.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (clientFactory.getException() != null) {
            throw clientFactory.getException();
        }

        return clientFactory.getDevice();
    }

    public static void waitClient() {
        try {
            LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);
            L2CAPConnectionNotifier conn = (L2CAPConnectionNotifier) Connector.open(GigiBiru.configuration.getConnectionURL());
            L2CAPConnection connection = conn.acceptAndOpen();
            L2CAPCommunicationChannel channel = new L2CAPCommunicationChannel(connection);
            RemoteDevice remoteDevice = RemoteDevice.getRemoteDevice(channel.getConnection());
            ServerDevice serverDevice = new ServerDevice(channel, GigiBiru.configuration.getReadInterval());
            GigiBiru.instance.connectionEstablished(serverDevice, remoteDevice);
            int connections = 0;
            while (++connections < GigiBiru.configuration.getMaxNumberOfConnections()) {
                connection = conn.acceptAndOpen();
                channel = new L2CAPCommunicationChannel(connection);
                remoteDevice = RemoteDevice.getRemoteDevice(channel.getConnection());
                serverDevice.addChannel(channel);
                GigiBiru.instance.connectionEstablished(serverDevice, remoteDevice);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    final static public class ClientDeviceFactory extends Thread {

        private IOException exception;
        private ClientConfiguration configuration;
        private ClientDevice device;

        public ClientDeviceFactory(ClientConfiguration configuration) {
            this.configuration = configuration;
            this.exception = null;
        }

        public void run() {
            try {
                CommunicationChannel channel = new L2CAPCommunicationChannel(
                        (L2CAPConnection) Connector.open(configuration.getConnectionURL()));

                device = new ClientDevice(channel, configuration.getReadInterval());
            } catch (IOException e) {
                exception = e;
            }
            synchronized (LC2APServer.class) {
                LC2APServer.class.notify();
            }
        }

        public ClientDevice getDevice() {
            return device;
        }

        public IOException getException() {
            return exception;
        }
    }
}
