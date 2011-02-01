/*
 * Spectra.java
 *
 */

package bin;

import java.awt.*;
import java.awt.FontMetrics;
import java.util.Random; 
import javax.swing.*; 

/** the graphical class where the spectra is drawn to the panel
 *
 */
public class Spectra extends JPanel {
    
  private static final Random r = new Random(); 

  private SpectraData S;
  
  @Override 
  protected void paintComponent( Graphics g ) 
  { 
    super.paintComponent( g ); 

    S = AR65view.spec;
	  
    g.setColor( Color.WHITE ); // Zeichenflaeche mit Rahmen
    g.fillRect( 1, 1, getWidth() - 2, getHeight() - 2 ); 
    g.setColor( Color.BLACK ); 
    g.drawRect( 0, 0, getWidth()    , getHeight()     );
    
    int yOffset = getHeight() - 20; // Koordinatensystem
    int xOffset = getWidth() - 10;
    g.drawLine( 36, yOffset+1 , xOffset , yOffset+1 ); // x
    g.drawLine( 36, 10 , 40 , 10 );
    g.drawLine( 40, yOffset+10 , 40 , 10 ); // y
    g.drawLine( xOffset, yOffset+10 , xOffset , yOffset+1 );

    g.setFont(new Font( Font.SANS_SERIF , Font.PLAIN , 10 )); // Beschriftung
    FontMetrics fm = g.getFontMetrics();
    String MaxCounts = String.valueOf(S.maxCounts[S.specAktuell]);
    String MinCounts = String.valueOf(S.minCounts[S.specAktuell]);
    String MaxE = String.valueOf( Math.rint(S.maxE[S.specAktuell]*100.0)/100 );
    g.drawString( MaxCounts , 35-fm.stringWidth( MaxCounts ) , 15 );
    g.drawString( MinCounts , 35-fm.stringWidth( MinCounts ) , yOffset+5 );
    g.drawString( MaxE , xOffset-fm.stringWidth( MaxE ) , yOffset+12 );
    g.drawString( String.valueOf(Math.rint(S.minE[S.specAktuell]*100.0)/100) , 45 , yOffset+12 );
    g.drawString( bin.Name.ENERGY_IN_EV  , xOffset/2 , yOffset+12);
    g.drawString( S.Material , xOffset-fm.stringWidth( S.Material ) , 20 );
    
    g.setColor( Color.BLUE );
    float dynamic = (float)S.maxCounts[S.specAktuell] - S.minCounts[S.specAktuell];
    float yDyn = ((getHeight() - 30)/dynamic);
    float xDyn = (float)(getWidth()-50)/S.channels[S.specAktuell];

    int x1, x2, y1, y2;
    int nr = S.specAktuell;
    int min = S.minCounts[nr];
    for ( int x = 0; x < S.channels[nr]-1 ; x++ )
    {
	x1 = 40 + (int)(x*xDyn);
	x2 = 40 + (int)((x+1)*xDyn);
	y1 = yOffset - (int)((S.messwert[nr][x]  -  min)*yDyn);
	y2 = yOffset - (int)((S.messwert[nr][x+1] - min)*yDyn);
        g.drawLine(x1,y1,x2,y2);
    }
  }  
}
