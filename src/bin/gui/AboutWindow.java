// Infofenster zum Programm ar65view

package bin.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
 
public class AboutWindow extends JFrame {
  public AboutWindow( ) {
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setSize( 300, 200 );
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
    setLocation( ((d.width-300)/2) , ((d.height-200)/2) );
	setLocationByPlatform(false);	

    JPanel pane = new JPanel(new BorderLayout()); 
    JLabel thema = new JLabel( "<html> <p/><center><img src='/pic/logo3.png'><p/>Das ist nur ein kleiner Versuch, <p/>in HTML einen Text unter JAVA unterzubringen.</center> <p/>Bildschirmbreite: " + d.width +"</p>Bildschirmhoehe: " +d.height+ "</html>");
	JButton okay = new JButton( "OK" );
	pane.add(thema , BorderLayout.NORTH);
	pane.add(okay, BorderLayout.SOUTH);
	
    ActionListener InfoEnde = new ActionListener() { 
      public void actionPerformed( ActionEvent e ) { 
        System.exit( 0 ); // ich will aber nur dieses Infofenster schliessen !!
      } 
    }; 
    okay.addActionListener( InfoEnde ); 	
	
	setUndecorated(true);
//	Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
//    f.setLocation( (d.width - f.getSize().width ) / 2, 
//               (d.height - f.getSize().height) / 2 );	
    add( pane );
	setVisible( true ); 
  } 
}
