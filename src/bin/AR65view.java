/*
 * AR65view.java
 */

package bin;

import java.awt.*;
import javax.swing.*; 
import java.io.*;
import javax.imageio.ImageIO;

/** Sets GUI-Manager and mainwindow, adds GUI components
 *
 *  @author Matthias Kreier
 */
public class AR65view {
  /** all methods of one SpectraData object are accessible 
   *  by the public AR65view.spec method */
  public static SpectraData spec = new SpectraData();
  public static Spectra spektrum = new Spectra();
  public static File Quelldatei = new File( "." );
  public static bin.gui.InfoBox infobox = new bin.gui.InfoBox();
  public static JFrame Hauptfenster = null;
  
  /** simplify adding Components to the Container
   *  of the GridBagLayout
   */
  static void addComponent( Container cont, 
                            GridBagLayout gbl, 
                            Component c, 
                            int x, int y, 
                            int width, int height, 
                            double weightx, double weighty ) 
  { 
    GridBagConstraints gbc = new GridBagConstraints(); 
    gbc.fill = GridBagConstraints.BOTH; 
    gbc.gridx = x; gbc.gridy = y; 
    gbc.gridwidth = width; gbc.gridheight = height; 
    gbc.weightx = weightx; gbc.weighty = weighty;
    gbc.insets = new Insets( 2, 2, 2, 2 );
    gbl.setConstraints( c, gbc ); 
    cont.add( c ); 
  } 
  
  public AR65view() 
  {
    // First we create the main window with the specified characteristics 
    Hauptfenster = new JFrame( "Spectrum Viewer Omicron AR 65" ); 
    Hauptfenster.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    Hauptfenster.setSize( 700, 540 );
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
    Hauptfenster.setLocation( (d.width - Hauptfenster.getSize().width ) / 2,
                              (d.height - Hauptfenster.getSize().height) / 3 );
    Hauptfenster.setLocationByPlatform(true);

    
    // replacing standard JAVA logo by own icon
    try { 
        Image progIcon = 
                  ImageIO.read( AR65view.class.getResource( "/pic/logo2.png" ));
        Hauptfenster.setIconImage( progIcon ); 
	} 
    catch ( IOException e ) { e.printStackTrace(); }  

    // Look and Feel des Betriebssystems setzen - im allgemeinen wohl Windows
    try
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        bin.AR65view.updateThisSwingSet();
    } catch (Exception ex) {
        System.out.println("Failed loading L&F");
	System.out.println(ex);
    }
    
    Hauptfenster.setJMenuBar(new bin.gui.MenuBar());

    bin.gui.ToolBar toolbar = new bin.gui.ToolBar();
    Auswahlleiste datlist = new Auswahlleiste();
    JButton seldir = new JButton( bin.perform.Aktion.choosePath );
    JButton chkdir = new JButton( bin.perform.Aktion.checkPath );
    JLabel leer1 = new JLabel("");
    JLabel leer2 = new JLabel("");

    Infoleiste infolist = new Infoleiste();
    infolist.setLayout(new BoxLayout( infolist , BoxLayout.Y_AXIS));    
    
    // Inhalt ins Hauptfenster setzen
    Container c = Hauptfenster.getContentPane();
    Hauptfenster.setBackground(Color.LIGHT_GRAY);
    GridBagLayout gbl = new GridBagLayout();
    c.setLayout( gbl );
    //                               x  y  w  h  wx   wy 
    addComponent( c, gbl, toolbar  , 0, 0, 3, 1, 1.0,   0 );
    
    addComponent( c, gbl, seldir   , 0, 1, 1, 1,   0,   0 );
    //addComponent( c, gbl, chkdir   , 0, 2, 1, 1,   0,   0 );
    addComponent( c, gbl, datlist  , 0, 2, 1, 4,   0, 0.1 ); 
    addComponent( c, gbl, leer1    , 0, 6, 1, 1,   0, 1.0 );
  
    addComponent( c, gbl, infobox  , 1, 1, 1, 2,   0,   0 );
    addComponent( c, gbl, infolist , 1, 3, 1, 3,   0,   0 );
    addComponent( c, gbl, leer2    , 1, 6, 1, 1,   0, 1.0 );
        
    addComponent( c, gbl, spektrum , 2, 1, 1, 6, 1.0, 1.0 );    
       
    Hauptfenster.setVisible( true );
  }
  /**
   * Returns the frame instance
   */
  public static JFrame getFrame() {
    return Hauptfenster;
  }

  public static void updateThisSwingSet() {
    JFrame frame = getFrame();
    SwingUtilities.updateComponentTreeUI(frame);
  }
}