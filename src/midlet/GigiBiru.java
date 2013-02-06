/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midlet;

import bluetooth.ClientConfiguration;
import bluetooth.ClientDevice;
import bluetooth.DeviceDiscoverer;
import bluetooth.InquiryListener;
import bluetooth.LC2APServer;
import bluetooth.MargeDefaults;
import bluetooth.ServerConfiguration;
import bluetooth.ServerDevice;

import bluetooth.ServiceDiscoverer;
import bluetooth.ServiceSearchListener;
import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.m2g.SVGImage;
import org.netbeans.microedition.svg.SVGMenu;
import org.netbeans.microedition.svg.SVGWaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;
import util.StringTokenizer;

/**
 * @author Administrator
 */
public class GigiBiru extends MIDlet implements CommandListener, InquiryListener, ServiceSearchListener {

    public static ServerConfiguration configuration;
    private boolean midletPaused = false;
    public static GigiBiru instance = null;
    private ServerDevice serverDevice = null;
    private String PROTOCOL_NAME = "btl2cap";
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private SVGMenu menuUtama;
    private SVGWaitScreen waitServerLC2AP;
    private Alert alertServerLC2AP;
    private Form formChat;
    private TextField txtPesan;
    private TextBox txtInputUserId;
    private SVGWaitScreen waitClientLC2AP;
    private Alert alert;
    private List formFoundDevice;
    private Command backCommand;
    private Command okCommand;
    private Command okSend;
    private Command backCommand1;
    private Command cancelCommand;
    private Command okCommand1;
    private Command backCommand2;
    private SVGImage svgImage;
    private SimpleCancellableTask taskServerLC2AP;
    private SVGImage svgImage1;
    private SimpleCancellableTask taskClientLC2AP;
    //</editor-fold>//GEN-END:|fields|0|
    private String userId;
    private ServiceDiscoverer serviceDiscoverer;
    private DeviceDiscoverer deviceDiscoverer;
    private Vector devices;
    private static final UUID SERVICE_UUID = new UUID(MargeDefaults.DEFAULT_UUID, false);
    private ClientDevice clientDevice;
    //</editor-fold>

    /**
     * The GigiBiru constructor.
     */
    public GigiBiru() {
        try {
            instance = this;
            configuration = new ServerConfiguration();
            configuration.setProtocol(PROTOCOL_NAME);
            this.serviceDiscoverer = ServiceDiscoverer.getInstance();
            this.deviceDiscoverer = DeviceDiscoverer.getInstance();
            this.devices = new Vector(5);
        } catch (BluetoothStateException ex) {
            ex.printStackTrace();
        }
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getTxtInputUserId());//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == formChat) {//GEN-BEGIN:|7-commandAction|1|39-preAction
            if (command == backCommand) {//GEN-END:|7-commandAction|1|39-preAction
                // write pre-action user code here
                switchDisplayable(null, getMenuUtama().getSvgCanvas());//GEN-LINE:|7-commandAction|2|39-postAction
            // write post-action user code here
            } else if (command == okSend) {//GEN-LINE:|7-commandAction|3|52-preAction
                // write pre-action user code here
                sendMessage();//GEN-LINE:|7-commandAction|4|52-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|73-preAction
        } else if (displayable == formFoundDevice) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|73-preAction
                // write pre-action user code here
                formFoundDeviceAction();//GEN-LINE:|7-commandAction|6|73-postAction
            // write post-action user code here
            } else if (command == backCommand2) {//GEN-LINE:|7-commandAction|7|76-preAction
                // write pre-action user code here
                switchDisplayable(null, getMenuUtama().getSvgCanvas());//GEN-LINE:|7-commandAction|8|76-postAction
            // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|9|79-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|10|79-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|16-preAction
        } else if (displayable == menuUtama) {
            if (command == SVGMenu.SELECT_COMMAND) {//GEN-END:|7-commandAction|11|16-preAction
                // write pre-action user code here
                menuUtamaAction();//GEN-LINE:|7-commandAction|12|16-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|44-preAction
        } else if (displayable == txtInputUserId) {
            if (command == okCommand) {//GEN-END:|7-commandAction|13|44-preAction
                // write pre-action user code here
                simpanUserId();//GEN-LINE:|7-commandAction|14|44-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|59-preAction
        } else if (displayable == waitClientLC2AP) {
            if (command == SVGWaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|15|59-preAction
                // write pre-action user code here
                switchDisplayable(getAlert(), getWaitClientLC2AP().getSvgCanvas());//GEN-LINE:|7-commandAction|16|59-postAction
            // write post-action user code here
            } else if (command == SVGWaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|17|58-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|18|58-postAction
                // write post-action user code here
            } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|19|66-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|20|66-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|31-preAction
        } else if (displayable == waitServerLC2AP) {
            if (command == SVGWaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|21|31-preAction
                // write pre-action user code here
                switchDisplayable(getAlertServerLC2AP(), getWaitServerLC2AP().getSvgCanvas());//GEN-LINE:|7-commandAction|22|31-postAction
            // write post-action user code here
            } else if (command == SVGWaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|23|30-preAction
                // write pre-action user code here
                switchDisplayable(null, getFormChat());//GEN-LINE:|7-commandAction|24|30-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|25|7-postCommandAction
        }//GEN-END:|7-commandAction|25|7-postCommandAction
    // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|26|
    //</editor-fold>//GEN-END:|7-commandAction|26|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: menuUtama ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of menuUtama component.
     * @return the initialized component instance
     */
    public SVGMenu getMenuUtama() {
        if (menuUtama == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            menuUtama = new SVGMenu(getSvgImage(), getDisplay());//GEN-BEGIN:|14-getter|1|14-postInit
            menuUtama.setTitle("svgMenu");
            menuUtama.addCommand(SVGMenu.SELECT_COMMAND);
            menuUtama.setCommandListener(this);
            menuUtama.addMenuElement("serverLC2AP");
            menuUtama.addMenuElement("clientLC2AP");
            menuUtama.addMenuElement("keluar");//GEN-END:|14-getter|1|14-postInit
        // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return menuUtama;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: menuUtamaAction ">//GEN-BEGIN:|14-action|0|14-preAction
    /**
     * Performs an action assigned to the selected SVG menu element in the menuUtama component.
     */
    public void menuUtamaAction() {//GEN-END:|14-action|0|14-preAction
        // enter pre-action user code here
        String __selectedElement = getMenuUtama().getMenuElementID(getMenuUtama().getSelectedIndex());//GEN-BEGIN:|14-action|1|21-preAction
        if (__selectedElement != null) {
            if (__selectedElement.equals("serverLC2AP")) {//GEN-END:|14-action|1|21-preAction
                // write pre-action user code here
                switchDisplayable(null, getWaitServerLC2AP().getSvgCanvas());//GEN-LINE:|14-action|2|21-postAction
            // write post-action user code here
            } else if (__selectedElement.equals("clientLC2AP")) {//GEN-LINE:|14-action|3|22-preAction
                // write pre-action user code here
                switchDisplayable(null, getWaitClientLC2AP().getSvgCanvas());//GEN-LINE:|14-action|4|22-postAction
            // write post-action user code here
            } else if (__selectedElement.equals("keluar")) {//GEN-LINE:|14-action|5|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|14-action|6|19-postAction
            // write post-action user code here
            }//GEN-BEGIN:|14-action|7|14-postAction
        }//GEN-END:|14-action|7|14-postAction
    // enter post-action user code here
    }//GEN-BEGIN:|14-action|8|
    //</editor-fold>//GEN-END:|14-action|8|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: svgImage ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of svgImage component.
     * @return the initialized component instance
     */
    public SVGImage getSvgImage() {
        if (svgImage == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|26-getter|1|26-@java.io.IOException
                svgImage = (SVGImage) SVGImage.createImage(getClass().getResourceAsStream("/svg/utama.svg"), null);
            } catch (java.io.IOException e) {//GEN-END:|26-getter|1|26-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|26-getter|2|26-postInit
        // write post-init user code here
        }//GEN-BEGIN:|26-getter|3|
        return svgImage;
    }
    //</editor-fold>//GEN-END:|26-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitServerLC2AP ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of waitServerLC2AP component.
     * @return the initialized component instance
     */
    public SVGWaitScreen getWaitServerLC2AP() {
        if (waitServerLC2AP == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            waitServerLC2AP = new SVGWaitScreen(getSvgImage1(), getDisplay());//GEN-BEGIN:|27-getter|1|27-postInit
            waitServerLC2AP.setTitle("Harap Sabar");
            waitServerLC2AP.setCommandListener(this);
            waitServerLC2AP.setFullScreenMode(true);
            waitServerLC2AP.setResetAnimationWhenStopped(false);
            waitServerLC2AP.setTask(getTaskServerLC2AP());//GEN-END:|27-getter|1|27-postInit
        // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return waitServerLC2AP;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alertServerLC2AP ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of alertServerLC2AP component.
     * @return the initialized component instance
     */
    public Alert getAlertServerLC2AP() {
        if (alertServerLC2AP == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            alertServerLC2AP = new Alert("alert");//GEN-BEGIN:|34-getter|1|34-postInit
            alertServerLC2AP.setTimeout(Alert.FOREVER);//GEN-END:|34-getter|1|34-postInit
        // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return alertServerLC2AP;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: taskServerLC2AP ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of taskServerLC2AP component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTaskServerLC2AP() {
        if (taskServerLC2AP == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            taskServerLC2AP = new SimpleCancellableTask();//GEN-BEGIN:|32-getter|1|32-execute
            taskServerLC2AP.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|32-getter|1|32-execute
                    configuration.setMaxNumberOfConnections(8);
                    configuration.setAuthenticate(true);
                    configuration.setAuthorize(true);
                    configuration.setEncrypt(true);
                    LC2APServer.waitClient();
                }//GEN-BEGIN:|32-getter|2|32-postInit
            });//GEN-END:|32-getter|2|32-postInit
        // write post-init user code here
        }//GEN-BEGIN:|32-getter|3|
        return taskServerLC2AP;
    }
    //</editor-fold>//GEN-END:|32-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: formChat ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of formChat component.
     * @return the initialized component instance
     */
    public Form getFormChat() {
        if (formChat == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            formChat = new Form("Chat Room", new Item[] { getTxtPesan() });//GEN-BEGIN:|36-getter|1|36-postInit
            formChat.addCommand(getBackCommand());
            formChat.addCommand(getOkSend());
            formChat.setCommandListener(this);//GEN-END:|36-getter|1|36-postInit
        // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return formChat;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|38-getter|1|38-postInit
        // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: svgImage1 ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of svgImage1 component.
     * @return the initialized component instance
     */
    public SVGImage getSvgImage1() {
        if (svgImage1 == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|37-getter|1|37-@java.io.IOException
                svgImage1 = (SVGImage) SVGImage.createImage(getClass().getResourceAsStream("/svg/loading.svg"), null);
            } catch (java.io.IOException e) {//GEN-END:|37-getter|1|37-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|37-getter|2|37-postInit
        // write post-init user code here
        }//GEN-BEGIN:|37-getter|3|
        return svgImage1;
    }
    //</editor-fold>//GEN-END:|37-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: simpanUserId ">//GEN-BEGIN:|46-entry|0|47-preAction
    /**
     * Performs an action assigned to the simpanUserId entry-point.
     */
    public void simpanUserId() {//GEN-END:|46-entry|0|47-preAction
        this.userId = getTxtInputUserId().getString();
        getFormChat().setTitle("Selamat datang " + userId);
        switchDisplayable(null, getMenuUtama().getSvgCanvas());//GEN-LINE:|46-entry|1|47-postAction
    // write post-action user code here
    }//GEN-BEGIN:|46-entry|2|
    //</editor-fold>//GEN-END:|46-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtInputUserId ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initiliazed instance of txtInputUserId component.
     * @return the initialized component instance
     */
    public TextBox getTxtInputUserId() {
        if (txtInputUserId == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            txtInputUserId = new TextBox("User ID", "", 100, TextField.ANY);//GEN-BEGIN:|42-getter|1|42-postInit
            txtInputUserId.addCommand(getOkCommand());
            txtInputUserId.setCommandListener(this);//GEN-END:|42-getter|1|42-postInit
        // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return txtInputUserId;
    }
    //</editor-fold>//GEN-END:|42-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|43-getter|1|43-postInit
        // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|43-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtPesan ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of txtPesan component.
     * @return the initialized component instance
     */
    public TextField getTxtPesan() {
        if (txtPesan == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            txtPesan = new TextField("Ketikkan Pesan Anda", null, 32, TextField.ANY);//GEN-LINE:|50-getter|1|50-postInit
        // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return txtPesan;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okSend ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of okSend component.
     * @return the initialized component instance
     */
    public Command getOkSend() {
        if (okSend == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            okSend = new Command("Kirim", Command.OK, 0);//GEN-LINE:|51-getter|1|51-postInit
        // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return okSend;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

    public void sendMessage(String message) {
        String toSend = userId + ":" + message;
        if (serverDevice != null) {
            serverDevice.send(toSend.getBytes());
        } else {
            clientDevice.send(toSend.getBytes());
        }

        getFormChat().insert(1, new StringItem(userId, message));
    }
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: sendMessage ">//GEN-BEGIN:|53-entry|0|54-preAction
    /**
     * Performs an action assigned to the sendMessage entry-point.
     */
    public void sendMessage() {//GEN-END:|53-entry|0|54-preAction
        sendMessage(this.getTxtPesan().getString());
        getTxtPesan().setString("");
//GEN-LINE:|53-entry|1|54-postAction
    // write post-action user code here
    }//GEN-BEGIN:|53-entry|2|
    //</editor-fold>//GEN-END:|53-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitClientLC2AP ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of waitClientLC2AP component.
     * @return the initialized component instance
     */
    public SVGWaitScreen getWaitClientLC2AP() {
        if (waitClientLC2AP == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            waitClientLC2AP = new SVGWaitScreen(getSvgImage1(), getDisplay());//GEN-BEGIN:|57-getter|1|57-postInit
            waitClientLC2AP.setTitle("Harap Sabar");
            waitClientLC2AP.addCommand(getCancelCommand());
            waitClientLC2AP.setCommandListener(this);
            waitClientLC2AP.setFullScreenMode(true);
            waitClientLC2AP.setTask(getTaskClientLC2AP());//GEN-END:|57-getter|1|57-postInit
        // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return waitClientLC2AP;
    }
    //</editor-fold>//GEN-END:|57-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            alert = new Alert("alert");//GEN-BEGIN:|62-getter|1|62-postInit
            alert.setTimeout(Alert.FOREVER);//GEN-END:|62-getter|1|62-postInit
        // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return alert;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: taskClientLC2AP ">//GEN-BEGIN:|60-getter|0|60-preInit
    /**
     * Returns an initiliazed instance of taskClientLC2AP component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTaskClientLC2AP() {
        if (taskClientLC2AP == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
            taskClientLC2AP = new SimpleCancellableTask();//GEN-BEGIN:|60-getter|1|60-execute
            taskClientLC2AP.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|60-getter|1|60-execute
                    //DeviceDiscoverer.startInquiry(DiscoveryAgent.GIAC, InquiryScreen.this)
                    deviceDiscoverer.startInquiry(DiscoveryAgent.GIAC, GigiBiru.instance);
                }//GEN-BEGIN:|60-getter|2|60-postInit
            });//GEN-END:|60-getter|2|60-postInit
        // write post-init user code here
        }//GEN-BEGIN:|60-getter|3|
        return taskClientLC2AP;
    }
    //</editor-fold>//GEN-END:|60-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|65-getter|0|65-preInit
    /**
     * Returns an initiliazed instance of cancelCommand component.
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|65-getter|0|65-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|65-getter|1|65-postInit
        // write post-init user code here
        }//GEN-BEGIN:|65-getter|2|
        return cancelCommand;
    }
    //</editor-fold>//GEN-END:|65-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|68-getter|0|68-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|68-getter|1|68-postInit
        // write post-init user code here
        }//GEN-BEGIN:|68-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|68-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: formFoundDevice ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of formFoundDevice component.
     * @return the initialized component instance
     */
    public List getFormFoundDevice() {
        if (formFoundDevice == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            formFoundDevice = new List("Found Devices", Choice.IMPLICIT);//GEN-BEGIN:|71-getter|1|71-postInit
            formFoundDevice.addCommand(getBackCommand2());
            formFoundDevice.addCommand(getOkCommand1());
            formFoundDevice.setCommandListener(this);//GEN-END:|71-getter|1|71-postInit
        // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return formFoundDevice;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: formFoundDeviceAction ">//GEN-BEGIN:|71-action|0|71-preAction
    /**
     * Performs an action assigned to the selected list element in the formFoundDevice component.
     */
    public void formFoundDeviceAction() {//GEN-END:|71-action|0|71-preAction
        // enter pre-action user code here
        String __selectedString = getFormFoundDevice().getString(getFormFoundDevice().getSelectedIndex());//GEN-LINE:|71-action|1|71-postAction
        this.deviceDiscoverer.cancelInquiry();
        try {
            serviceDiscoverer.startSearch(new UUID[]{SERVICE_UUID},
                    (RemoteDevice) this.devices.elementAt(getFormFoundDevice().getSelectedIndex()), GigiBiru.instance);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }
    }//GEN-BEGIN:|71-action|2|
    //</editor-fold>//GEN-END:|71-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|75-getter|1|75-postInit
        // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|78-getter|1|78-postInit
        // write post-init user code here
        }//GEN-BEGIN:|78-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|78-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    public void receiveMessage(byte[] message) {
        StringTokenizer tokenizer = new StringTokenizer(new String(message));
        tokenizer.setDelimiters(":");
        String from = tokenizer.nextToken();
        String msg = tokenizer.nextToken();
        getFormChat().insert(1, new StringItem(from, msg));
    }

    public void errorOnReceiving(IOException e) {
        System.out.println("errorOnReceiving");
    }

    public void errorOnSending(IOException e) {
        System.out.println("errorOnSending");
    }

    public void connectionEstablished(ServerDevice device, RemoteDevice remote) {
        System.out.println("connectionEstablished");
        System.out.println(remote.getBluetoothAddress());
        //if (!this.cancelled) {
        device.startListening();
        device.setEnableBroadcast(true);
        this.serverDevice = device;
        this.clientDevice = null;
        switchDisplayable(null, getFormChat());
    //}
    }

    public void errorOnConnection(IOException e) {
        System.out.println("errorOnConnection");
    }

    public void deviceDiscovered(RemoteDevice device, DeviceClass deviceClass) {
        this.getFormFoundDevice().deleteAll();
        this.devices.removeAllElements();
        this.devices.addElement(device);
        getFormFoundDevice().setTitle("Inquirying... " + this.devices.size());
        try {
            this.getFormFoundDevice().append(device.getFriendlyName(false), null);
        } catch (IOException e) {
            this.getFormFoundDevice().append(device.getBluetoothAddress(), null);
            e.printStackTrace();
        }
        switchDisplayable(null, getFormFoundDevice());
    }

    public void inquiryCompleted(RemoteDevice[] devices) {
        getFormFoundDevice().setTitle("Ditemukan " + Integer.toString(this.devices.size()) + " koneksi bluetooth");

    }

    public void inquiryError() {
        System.out.println("Error");
    }

    public void connectionEstablished(ClientDevice device) {
        // if (!this.cancelled) {
        device.startListening();
        this.clientDevice = device;
        this.serverDevice = null;
        switchDisplayable(null, getFormChat());
    //}
    }

    public void serviceSearchCompleted(RemoteDevice remoteDevice, ServiceRecord[] services) {
        ClientConfiguration config = new ClientConfiguration(services[0]);
        try {
            connectionEstablished(LC2APServer.connectToServer(config));
        //switchDisplayable(null, getFormChat());
        } catch (IOException e) {
            errorOnConnection(e);
        }


    }

    public void deviceNotReachable() {
    }

    public void serviceSearchError() {
    }
}
