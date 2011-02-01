package bin;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*; 
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
 
public class Infoleiste extends JPanel 
{

 public static SpinnerNumberModel model = new SpinnerNumberModel( 17.08, 0.0, 120.0, 0.02 ); 
 
 private JRadioButton option;
 private ButtonGroup winkelgruppe = new ButtonGroup();
 private JPanel WinkelBereich = new JPanel();
 
 public Infoleiste( ) {
  setLayout(new FlowLayout( FlowLayout.CENTER ) );

    WinkelBereich.setLayout(new GridLayout(5,2));
    WinkelBereich.setBorder(BorderFactory.createTitledBorder("Region"));
    
    AngleButton( bin.perform.Aktion.spec0 );
    AngleButton( bin.perform.Aktion.spec1 );
    AngleButton( bin.perform.Aktion.spec2 );
    AngleButton( bin.perform.Aktion.spec3 );
    AngleButton( bin.perform.Aktion.spec4 );
    AngleButton( bin.perform.Aktion.spec5 );
    AngleButton( bin.perform.Aktion.spec6 );
    AngleButton( bin.perform.Aktion.spec7 );
    AngleButton( bin.perform.Aktion.spec8 );
    AngleButton( bin.perform.Aktion.spec9 );
    
    add( WinkelBereich );	

    JPanel FermiEnergie = new JPanel();
    FermiEnergie.setLayout(new GridLayout(2,1,5,5));
    //FermiEnergie.setBackground(Color.lightGray);
    FermiEnergie.setBorder(BorderFactory.createTitledBorder("Fermi energy"));
    JSpinner spin1 = new JSpinner( model ); 
    FermiEnergie.add( spin1 );
    FermiEnergie.add(new JButton("set energy"));
    add( FermiEnergie );    
    
  } 
 
  public void AngleButton(Action perform)
  {
       option = new JRadioButton( perform );
       winkelgruppe.add(option);
       WinkelBereich.add(option);
  }
}
