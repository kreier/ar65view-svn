/*
 * Auswahlleiste.java
 *
 */

package bin;

import java.io.*;
import java.io.FilenameFilter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
 
public class Auswahlleiste extends JPanel {
  public static File Anker = new File( "." );
  public static DefaultListModel measureFiles = new DefaultListModel();
  public static JList fileList = new JList( measureFiles );
  
  public Auswahlleiste( ) {
    setLayout( new BorderLayout() );
    Border iborder = BorderFactory.createEmptyBorder(5,5,5,5);


    for ( String s : ("080808.1,** no data **").split(",") ) 
    measureFiles.addElement( s ); 

    //fileList.setListData( measureFiles );
    fileList.addListSelectionListener( new ListSelectionListener() { 
      public void valueChanged( ListSelectionEvent e ) { 
        if ( e.getValueIsAdjusting() ) 
          return; 
        if ( measureFiles.get( e.getLastIndex() ).equals( "** no data **" ) ) 
          System.exit( 0 );
        else {
            String newSpectra;
            bin.SpectraData.datei = "";
            bin.SpectraData.datei += measureFiles.get( e.getLastIndex() );
            newSpectra = bin.Auswahlleiste.Anker.getAbsolutePath();
            newSpectra += File.separatorChar;
            newSpectra += bin.SpectraData.datei;
            //System.out.println( newSpectra );
            bin.AR65view.spec.load( newSpectra );
            bin.AR65view.spektrum.updateUI();
            bin.AR65view.infobox.aktualisieren();
            bin.AR65view.infobox.updateUI();
        }
      } 
    } ); 
    add( new JScrollPane( fileList ) , BorderLayout.CENTER ); 
  } 
}