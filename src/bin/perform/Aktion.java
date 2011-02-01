/*
 * Aktion.java
 *
 * Alle Aktionen, die durch einen Button oder Menueintrag ausgewaehlt werden 
 * koennen, sind methoden der Klasse Aktion - hier beschrieben
 */

package bin.perform;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JMenuItem;

import java.awt.datatransfer.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Matthias Kreier
 */
public class Aktion {

    final static bin.SpectraData sp = bin.AR65view.spec;
    
    final static Icon newIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/new16.png") ); 
    final static Icon newIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/new22.png") ); 
    final static Icon openIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/open16.png") );
    final static Icon openIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/open22.png") );
    final static Icon saveIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/save16.png") ); 
    final static Icon saveIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/save22.png") ); 
    final static Icon saveasIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/saveas16.png") ); 
    final static Icon saveasIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/saveas22.png") ); 
    final static Icon exitIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/exit16.png") );
    final static Icon exitIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/exit22.png") );
    
    final static Icon cutIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/cut16.png") );
    final static Icon cutIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/cut22.png") );
    final static Icon copyIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/copy16.png") );
    final static Icon copyIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/copy22.png") );
    final static Icon pasteIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/paste16.png") );
    final static Icon pasteIcon22 = new ImageIcon(bin.AR65view.class.getResource("/pic/paste22.png") );

    final static Icon aboutIcon = new ImageIcon(bin.AR65view.class.getResource("/pic/info16.png") );
    final static Icon helpIcon16 = new ImageIcon(bin.AR65view.class.getResource("/pic/help16.png") );
    
    final static Icon logoIcon = new ImageIcon(bin.AR65view.class.getResource("/pic/logo2.png") );
    final static Icon infoIcon = new ImageIcon(bin.AR65view.class.getResource("/pic/Info128.png") );
    final static Icon helpIcon = new ImageIcon(bin.AR65view.class.getResource("/pic/help50.png") );
    final static Icon smooth16 = new ImageIcon(bin.AR65view.class.getResource("/pic/smooth16.png") );
    final static Icon smooth22 = new ImageIcon(bin.AR65view.class.getResource("/pic/smooth22.png") );
    final static Icon spikes16 = new ImageIcon(bin.AR65view.class.getResource("/pic/spikes16.png") );
    final static Icon spikes22 = new ImageIcon(bin.AR65view.class.getResource("/pic/spikes22.png") );
    final static Icon shirley16 = new ImageIcon(bin.AR65view.class.getResource("/pic/shirley16.png") );
    final static Icon shirley22 = new ImageIcon(bin.AR65view.class.getResource("/pic/shirley22.png") );
    final static Icon normalize16 = new ImageIcon(bin.AR65view.class.getResource("/pic/normalize16.png") );
    final static Icon normalize22 = new ImageIcon(bin.AR65view.class.getResource("/pic/normalize22.png") );
    final static Icon export16 = new ImageIcon(bin.AR65view.class.getResource("/pic/export16.png") );
    final static Icon export22 = new ImageIcon(bin.AR65view.class.getResource("/pic/export22.png") );

    // M?gliche Look & Feels
    static final String mac      = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    static final String metal    = "javax.swing.plaf.metal.MetalLookAndFeel";
    static final String motif    = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    static final String windows  = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    static final String gtk      = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

    // Aktuelles Look & Feel
    static String currentLookAndFeel = metal;

    // Resource bundle for internationalized and accessible text (mal deutsch ...)
    static ResourceBundle bundle = null;

    public static Action newAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.NEU ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
		putValue( Action.SMALL_ICON,     newIcon16 ); 
		putValue( Action.LARGE_ICON_KEY, newIcon22 );
                putValue( Action.SHORT_DESCRIPTION, bin.Name.NEU );
          } 
	  public void actionPerformed( ActionEvent e ) { 
		bin.Name.initDE(); 
	  } 
    };
    public static Action openAction = new AbstractAction() { 
      { putValue( Action.NAME, bin.Name.OPEN ); 
        putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
        putValue( Action.SMALL_ICON,     openIcon16 );
        putValue( Action.LARGE_ICON_KEY, openIcon22 );
        putValue( Action.SHORT_DESCRIPTION, bin.Name.OPEN );
      } 
      public void actionPerformed( ActionEvent e ) { 
        JFileChooser fc = new JFileChooser( bin.AR65view.Quelldatei ); 
        fc.setFileFilter( new FileFilter() { 
          @Override public boolean accept( File f ) 
          { 
            return f.isDirectory() ||
                   f.getName().endsWith( ".1" ) ||
                   f.getName().endsWith( ".2" ) ||
                   f.getName().endsWith( ".3" ) ||
                   f.getName().endsWith( ".4" ) ||
                   f.getName().endsWith( ".5" ) ||
                   f.getName().endsWith( ".6" ) ||
                   f.getName().endsWith( ".7" ) ||
                   f.getName().endsWith( ".8" ) ||
                   f.getName().endsWith( ".9" ) ||
                   f.getName().endsWith( ".10" ) ||
                   f.getName().endsWith( ".11" ) ||
                   f.getName().endsWith( ".12" ) ||
                   f.getName().endsWith( ".13" ) ||
                   f.getName().endsWith( ".14" ) ||
                   f.getName().endsWith( ".15" ) ||
                   f.getName().endsWith( ".vir" ) ||
                   f.getName().endsWith( ".VIR" );
          } 
          @Override public String getDescription() 
          { 
            return "Data of AR65 and WESPHOA"; 
          } 
        } ); 
        int state = fc.showOpenDialog( null ); 
        if ( state == JFileChooser.APPROVE_OPTION ) 
        { 
          bin.AR65view.Quelldatei = fc.getSelectedFile(); 
          //System.out.println( bin.AR65view.Quelldatei.getPath() );
          bin.AR65view.spec.load( bin.AR65view.Quelldatei.getPath() );
          bin.AR65view.spektrum.revalidate();
        } 
        else 
          System.out.println( "Selection aborted" ); 
   
      } 
    };	
    public static Action saveAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.SAVE ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
		putValue( Action.SMALL_ICON,     saveIcon16 ); 
                putValue( Action.LARGE_ICON_KEY, saveIcon22 );
                putValue( Action.SHORT_DESCRIPTION, bin.Name.SAVE );
          } 
	  public void actionPerformed( ActionEvent e ) { 
		System.out.println( "Save" ); 
	  } 
    };
    public static Action saveasAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.SAVE_AS ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
                putValue( Action.SMALL_ICON,     saveasIcon16 ); 
                putValue( Action.LARGE_ICON_KEY, saveasIcon22 );
                putValue( Action.SHORT_DESCRIPTION, bin.Name.SAVE_AS );
          } 
	  public void actionPerformed( ActionEvent e ) { 
		System.out.println( "Save as" );
	  } 
    };
    public static Action exitAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.EXIT ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 0 );
		putValue( Action.SMALL_ICON,     exitIcon16 );
                putValue( Action.LARGE_ICON_KEY, exitIcon22 );
                putValue( Action.SHORT_DESCRIPTION, bin.Name.EXIT );
          } 
	  public void actionPerformed( ActionEvent e ) { 
		System.exit( 0 ); 
	  } 
    };
    public static Action cutAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.CUT ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
		putValue( Action.SMALL_ICON,     cutIcon16 ); 
		putValue( Action.LARGE_ICON_KEY, cutIcon22 ); 
          } 
	  public void actionPerformed( ActionEvent e ) {
            // Was soll das "cut?"
          }
    };
    public static Action copyAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.COPY ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
		putValue( Action.SMALL_ICON,     copyIcon16 ); 
		putValue( Action.LARGE_ICON_KEY, copyIcon22 ); 
          } 
	  public void actionPerformed( ActionEvent e ) { 
		System.out.println( "Kopieren..." ); 
	  } 
    };	
    public static Action pasteAction = new AbstractAction() { 
	  { putValue( Action.NAME, bin.Name.PASTE ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
		putValue( Action.SMALL_ICON,     pasteIcon16 ); 
		putValue( Action.LARGE_ICON_KEY, pasteIcon22 ); 
          } 
	  public void actionPerformed( ActionEvent e ) { 
		bin.Name.initDE(); 
	  } 
    };
    public static Action aboutAction = new AbstractAction() { 
      { putValue( Action.NAME, bin.Name.ABOUT ); 
	putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 0 );
	putValue( Action.SMALL_ICON,     aboutIcon );	} 
      public void actionPerformed( ActionEvent e ) { 
        JOptionPane.showMessageDialog( null, 
          "<HTML><H2>AR65view</H2>" +
          "<H4>Version 0.2.11.01</H4>" +
          "This progam can be used to analyse, view and modify<BR>" +
          "investigated data collected by the Omicron AR65 electron<BR>" +
          "spectrometer as well as the WESPHOA ARPES chamber,<BR>" +
          "owned by the workgroup EES of Prof. Manzke at <BR>" +
          "the Humboldt University in Berlin.<BR>" +
          "<BR>This program is free software; you can redistribute it and/or<BR>" +
          "modify it under the terms of the GNU General Public License as <BR>" +
          "published by the Free Software Foundation; either version 2 of <BR>" +
          "the License, or (at your option) any later version.<BR>" +
          "<BR>This program is distributed in the hope that it will be " +
          "useful, but <BR>WITHOUT ANY WARRANTY; without even the implied " +
          "warranty of <BR>MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.<BR> " +
          "See the GNU General Public License for more details.<BR>" +
          "<BR>&copy; 2006-2011 Matthias Kreier</HTML>",      // Fragetext
          "Info zu AR65view",  // Titel 
           JOptionPane.OK_OPTION, 
           infoIcon );
      }
    };
    public static Action helpAction = new AbstractAction() { 
      { putValue( Action.NAME, bin.Name.HELP_ABOUT );
	putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 ); 
	putValue( Action.SMALL_ICON,     helpIcon16 ); 
      } 
      public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( null,
      "<HTML><H2>How to use AR65view</H2>" +
      "In general you press the button 'select directory' and navigate to your folder<BR>" +
      "with your measured data files (*.1 odr *.VIR). After pressing the OK-Button all<BR>" +
      "files of this directory are shown in the left list. Once you click on one of them,<BR>" +
      "the respected file is opened and the data therein is shown as graph. If you have<BR>" +
      "a stepper file you can switch the regions. Your original data will not be altered.<BR><BR>" +
      "The data now loaded can be edited as long as you're not loading another file.<BR>" +
      "You can easily remove spikes, smooth the spectra (several times), remove the<BR>" +
      "'Shirley Background' from inelastic scatterd electrons, normalize your data to<BR>" +
      "a maximum of 10000 and then export all data to the clipboard. By Copy&Paste<BR>" +
      "you can now import this data to Origin, OtiPlot, LabPlot or SciDAVis or any<BR>" +
      "other Information Graphics Software (see Wikipedia) you like.</HTML>", // Fragetext
      "How to use AR65view",  // Titel
      JOptionPane.OK_OPTION, helpIcon );
      } 
    };
    public static Action smoothAction = new AbstractAction() {
	  {     putValue( Action.NAME, "smooth spectra" ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 );
                putValue( Action.SMALL_ICON,     smooth16 );
                putValue( Action.LARGE_ICON_KEY, smooth22 );
                putValue( Action.SHORT_DESCRIPTION, "smooth spectra" );
          } 
	  public void actionPerformed( ActionEvent e ) { 
		bin.AR65view.spec.smooth();
                bin.AR65view.spektrum.updateUI();
	  }         
    };
    public static Action removeSpikesAction = new AbstractAction() {
	  {     putValue( Action.NAME, "remove spikes" ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 );
                putValue( Action.SMALL_ICON,     spikes16 );
		putValue( Action.LARGE_ICON_KEY, spikes22 );
                putValue( Action.SHORT_DESCRIPTION, "remove spikes from spectra");
          } 
	  public void actionPerformed( ActionEvent e ) { 
		bin.AR65view.spec.removeSpikes();
                bin.AR65view.spektrum.updateUI();
	  }         
    };    
    public static Action removeShirleyAction = new AbstractAction() {
	  {     putValue( Action.NAME, "remove shirley" ); 
		putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 1 );
                putValue( Action.SMALL_ICON,     shirley16 );
		putValue( Action.LARGE_ICON_KEY, shirley22 );
                putValue( Action.SHORT_DESCRIPTION, "removes shirley background");
          } 
	  public void actionPerformed( ActionEvent e ) { 
                //TODO Shirley remove funktion in SpectraData reinschreiben
                bin.AR65view.spec.removeShirley();
                bin.AR65view.spektrum.updateUI();
	  }         
    };    
    public static ActionListener spikes = new ActionListener() { 
      public void actionPerformed( ActionEvent e ) { 
        bin.AR65view.spec.init();
	bin.AR65view.spektrum.updateUI();
      } 
    };
    public static Action choosePath = new AbstractAction() { 
      { putValue( Action.NAME, "select directory" );   } 
      public void actionPerformed( ActionEvent e ) { 
        JFileChooser fc = new JFileChooser( bin.Auswahlleiste.Anker );
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false); // disable "All files" option
        int state = fc.showOpenDialog( null ); 
        if ( state == JFileChooser.APPROVE_OPTION ) 
        { 
          bin.Auswahlleiste.Anker = fc.getSelectedFile();
        } 
        else
          System.out.println( "Auswahl abgebrochen" ); 
        bin.Auswahlleiste.fileList.clearSelection();
        bin.Auswahlleiste.measureFiles.clear();
        // Verzeichnisinhalt einfuegen
        String[] entries = bin.Auswahlleiste.Anker.list();
        for(int i = 0; i < entries.length ; i++) {
            File test = new File( bin.Auswahlleiste.Anker , entries[i] );
            if( test.isFile() ) { 
                bin.Auswahlleiste.measureFiles.addElement( entries[i] );
                //TODO check if it is really a AR65 measurement file
                //TODO check ending of file (1, 2, ...)
            }
        }
        bin.Auswahlleiste.measureFiles.addElement( "Ende" );        
      } 
    };
    public static Action checkPath = new AbstractAction() { 
        { putValue( Action.NAME, "check directory"); }
      public void actionPerformed( ActionEvent e ) {
        //bin.Auswahlleiste.measureFiles.removeListDataListener()
        bin.Auswahlleiste.fileList.clearSelection();
        bin.Auswahlleiste.measureFiles.clear();
        // Verzeichnisinhalt einfuegen
        String[] entries = bin.Auswahlleiste.Anker.list();
        //System.out.println( Arrays.toString( entries ) );
        for(int i = 0; i < entries.length ; i++) {
            File test = new File( bin.Auswahlleiste.Anker , entries[i] );
            if( test.isFile() ) { 
                bin.Auswahlleiste.measureFiles.addElement( entries[i] );
                //TODO check if it is really a AR65 measurement file
                //TODO check ending of file (1, 2, ...)
            }
        }
        bin.Auswahlleiste.measureFiles.addElement( "Ende" );        
      } 
    };
    public static Action exportSpectra = new AbstractAction() { 
        { putValue( Action.NAME, "export spectra");
          putValue( Action.SMALL_ICON,     export16 ); 
	  putValue( Action.LARGE_ICON_KEY, export22 );
          putValue( Action.SHORT_DESCRIPTION, "export spectra data to clipboard" );        }
      public void actionPerformed( ActionEvent e ) {
          Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
          String s = "";
          for(int i=0;i<bin.AR65view.spec.channels[bin.AR65view.spec.specAktuell];i++)
          {
              s += String.valueOf( bin.AR65view.spec.messwert[bin.AR65view.spec.specAktuell][i] );
              s += "\n";
          }    
          StringSelection cont = new StringSelection( s );
          clip.setContents(cont, null);
      }
    };
    public static Action normalizeSpectra = new AbstractAction() { 
        { putValue( Action.NAME, "normalize"); 
          putValue( Action.SMALL_ICON,     normalize16 ); 
          putValue( Action.LARGE_ICON_KEY, normalize22 );
          putValue( Action.SHORT_DESCRIPTION, "normalize spectra" );}
      public void actionPerformed( ActionEvent e ) {
          Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
          int maximum = bin.AR65view.spec.maxCounts[bin.AR65view.spec.specAktuell];
          if(maximum == 0) maximum = 1;
          double faktor = (double)10000/maximum;
          double value = 0;
          int chan = bin.AR65view.spec.channels[bin.AR65view.spec.specAktuell];
          for(int i = 0 ; i < chan ; i++ )
          {
              value = (double)faktor*
                      bin.AR65view.spec.messwert[bin.AR65view.spec.specAktuell][i];
              bin.AR65view.spec.messwert[bin.AR65view.spec.specAktuell][i] = (int)value;
          }    
          bin.AR65view.spec.maxCounts[bin.AR65view.spec.specAktuell] = 10000;
          bin.AR65view.spec.minCounts[bin.AR65view.spec.specAktuell] = 
                  (int)Math.rint(bin.AR65view.spec.minCounts[bin.AR65view.spec.specAktuell]*faktor);
          bin.AR65view.spektrum.updateUI();
      }
    };
    public static Action spec0 = new AbstractAction() { 
        { putValue( Action.NAME, "R 1"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 0;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };
    public static Action spec1 = new AbstractAction() { 
        { putValue( Action.NAME, "R 2"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 1;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };
    public static Action spec2 = new AbstractAction() { 
        { putValue( Action.NAME, "R 3"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 2;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };  
    public static Action spec3 = new AbstractAction() { 
        { putValue( Action.NAME, "R 4"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 3;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    }; 
    public static Action spec4 = new AbstractAction() { 
        { putValue( Action.NAME, "R 5"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 4;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    }; 
    public static Action spec5 = new AbstractAction() { 
        { putValue( Action.NAME, "R 6"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 5;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };     
    public static Action spec6 = new AbstractAction() { 
        { putValue( Action.NAME, "R 7"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 6;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };
    public static Action spec7 = new AbstractAction() { 
        { putValue( Action.NAME, "R 8"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 7;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };
    public static Action spec8 = new AbstractAction() { 
        { putValue( Action.NAME, "R 9"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 8;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };
    public static Action spec9 = new AbstractAction() { 
        { putValue( Action.NAME, "R10"); }
      public void actionPerformed( ActionEvent e ) {
          bin.AR65view.spec.specAktuell = 9;
          bin.AR65view.spektrum.repaint();
          bin.AR65view.infobox.aktualisieren();
          bin.AR65view.infobox.revalidate();
      }
    };

    /**
     * Returns a mnemonic from the resource bundle. Typically used as
     * keyboard shortcuts in menu items.
     */
    public static char getMnemonic(String key) {
	return (getString(key)).charAt(0);
    }
    /**
     * This method returns a string from the programs resource bundle.
     */
    public static String getString(String key) {
	String value = null;
	try {
	    value = getResourceBundle().getString(key);
	} catch (MissingResourceException e) {
	    System.out.println("java.util.MissingResourceException: Couldn't find value for: " + key);
	}
	if(value == null) {
	    value = "Could not find resource: " + key + "  ";
	}
	return value;
    }   
    /**
     * Returns the resource bundle associated with this program. Used
     * to get accessable and internationalized strings. (sp?ter mal deutsch)
     */
    public static ResourceBundle getResourceBundle() {
	if(bundle == null) {
	    bundle = ResourceBundle.getBundle("data.ar65view");
	}
	return bundle;
    }   
    /**
     * Stores the current L&F, and calls updateLookAndFeel, below
     */
    public static void setLookAndFeel(String laf) {
	if ( !currentLookAndFeel.equals(laf)) {
	    currentLookAndFeel = laf;

            String lafName = null;
            if(laf.equals(mac)) lafName = getString("LafMenu.mac_label");
            if(laf.equals(metal)) lafName = getString("LafMenu.java_label");
            if(laf.equals(motif)) lafName = getString("LafMenu.motif_label");
            if(laf.equals(windows)) lafName = getString("LafMenu.windows_label");
            if(laf.equals(gtk)) lafName = getString("LafMenu.gtk_label");

            for(int i=0;i<bin.gui.MenuBar.LnFMenu.getItemCount();i++) {
                JMenuItem item = bin.gui.MenuBar.LnFMenu.getItem(i);
                if(item.getText().equals(lafName)) {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }
            }
	    updateLookAndFeel();
	}
    }

    /**
     * Sets the current L&F on each module
     */
    public static void updateLookAndFeel() {
	try {
	    UIManager.setLookAndFeel(currentLookAndFeel);
            bin.AR65view.updateThisSwingSet();
	} catch (Exception ex) {
	    System.out.println("Failed loading L&F: " + currentLookAndFeel);
	    System.out.println(ex);
	}
    }


    /**
     * Creates a new instance of Aktion
     */
    public Aktion() {
    }
}

