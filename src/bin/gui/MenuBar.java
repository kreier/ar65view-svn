/*
 * MenuBar.java
 */

package bin.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractAction;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import java.awt.event.*;


/**
 * Beschreibungstext JavaDoc
 */
public class MenuBar extends JMenuBar
{
    public static JMenu LnFMenu = null;
    // M?gliche Look & Feels
    static final String mac      = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    static final String metal    = "javax.swing.plaf.metal.MetalLookAndFeel";
    static final String motif    = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    static final String windows  = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    static final String gtk      = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

    // Aktuelles Look & Feel
    static String currentLookAndFeel = windows;

    /** Creates a new instance of MenuBar */
    public MenuBar() {
        JMenuItem mi;

        JMenu fileMenu = new JMenu( bin.Name.FILE ) ;
        fileMenu.add( bin.perform.Aktion.newAction);
        fileMenu.add( bin.perform.Aktion.openAction);
        fileMenu.add( bin.perform.Aktion.saveAction);
        fileMenu.add( bin.perform.Aktion.saveasAction);
        fileMenu.addSeparator();
        fileMenu.add( bin.perform.Aktion.exitAction);        
        add( fileMenu );
        
        JMenu editMenu = new JMenu( bin.Name.EDIT );
        //editMenu.add( bin.perform.Aktion.cutAction);
        //editMenu.add( bin.perform.Aktion.copyAction);
        //editMenu.add( bin.perform.Aktion.pasteAction);
        //editMenu.addSeparator();
        editMenu.add( bin.perform.Aktion.removeSpikesAction);
        editMenu.add( bin.perform.Aktion.smoothAction);
        editMenu.add( bin.perform.Aktion.removeShirleyAction);
        editMenu.add( bin.perform.Aktion.normalizeSpectra );
        editMenu.addSeparator();
        editMenu.add( bin.perform.Aktion.exportSpectra );
        add( editMenu );
        
        LnFMenu = new JMenu( bin.Name.LOOK_AND_FEEL );
        LnFMenu.setMnemonic(bin.perform.Aktion.getMnemonic("LafMenu.laf_mnemonic"));
        LnFMenu.getAccessibleContext().setAccessibleDescription(
	    bin.perform.Aktion.getString("LafMenu.laf_accessible_description"));
        createLafMenuItem(LnFMenu, "LafMenu.windows_label", "LafMenu.windows_mnemonic",
                        "LafMenu.windows_accessible_description", windows);
        createLafMenuItem(LnFMenu, "LafMenu.mac_label", "LafMenu.mac_mnemonic",
                        "LafMenu.mac_accessible_description", mac);
	createLafMenuItem(LnFMenu, "LafMenu.java_label", "LafMenu.java_mnemonic",
		       "LafMenu.java_accessible_description", metal);
        createLafMenuItem(LnFMenu, "LafMenu.gtk_label", "LafMenu.gtk_mnemonic",
                        "LafMenu.gtk_accessible_description", gtk);
        createLafMenuItem(LnFMenu, "LafMenu.motif_label", "LafMenu.motif_mnemonic",
                        "LafMenu.motif_accessible_description", motif);
        add( LnFMenu );
        
        JMenu helpMenu = new JMenu( bin.Name.HELP );
        helpMenu.add( bin.perform.Aktion.helpAction );
        helpMenu.add( bin.perform.Aktion.aboutAction );
        add( helpMenu );
        
        //bin.perform.Aktion.pasteAction.setEnabled(false);
    }

    /**
     *  Erstellt einen JRadioButtonMenuItem f?r das L&F Men?
     */
    public JMenuItem createLafMenuItem(JMenu menu, String label, String mnemonic,
			       String accessibleDescription, String laf) {
        JMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(bin.perform.Aktion.getString(label)));
	mi.setMnemonic(bin.perform.Aktion.getMnemonic(mnemonic));
	mi.getAccessibleContext().setAccessibleDescription(bin.perform.Aktion.getString(accessibleDescription));
	mi.addActionListener(new ChangeLookAndFeelAction(laf));
	mi.setEnabled(isAvailableLookAndFeel(laf));
        //if (laf.equals(mac)) mi.setEnabled(false); // den Fehler verstehe wer will...

	return mi;
    }    
    /**
     * alte ?bernommene Beschreibung gel?scht ...
     */
     protected boolean isAvailableLookAndFeel(String laf) {
         try {
             Class lnfClass = Class.forName(laf);
             LookAndFeel newLAF = (LookAndFeel)(lnfClass.newInstance());
             return newLAF.isSupportedLookAndFeel();
         } catch(Exception e) { // If ANYTHING weird happens, return false
             return false;
         }
     }
}

class ChangeLookAndFeelAction extends AbstractAction {
	//bin.AR65view ar65set;
	String laf;
        protected ChangeLookAndFeelAction( String laf) {
            super("ChangeTheme");
	    //this.ar65set = ar65set;
	    this.laf = laf;
        }

        public void actionPerformed(ActionEvent e) {
	    bin.perform.Aktion.setLookAndFeel(laf);
        }
}