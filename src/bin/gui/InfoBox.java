/*
 * InfoBox.java
 */

package bin.gui;

import javax.swing.JLabel;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FontMetrics;

/** shows main measurement data as overview */
public class InfoBox extends JLabel {
    
    bin.Name N;
    bin.SpectraData S;
 
    /** Creates a new instance of InfoBox */
    public InfoBox() {
        S = bin.AR65view.spec;
        //Border iborder = BorderFactory.createEmptyBorder(5,5,5,5);
	//Border oborder = BorderFactory.createLoweredBevelBorder();
	//Border textBorder = BorderFactory.createCompoundBorder(oborder,iborder);
        setBorder(BorderFactory.createTitledBorder("Messdaten"));
	//setBorder(textBorder);
        setFont(new Font( Font.SANS_SERIF , Font.PLAIN , 10 )); 
        aktualisieren();
    }
    
    public void aktualisieren()
    {
        String s = "<HTML><table border='0' cellpadding='0' cellspacing='0' " +
                "width='100%'>";
        s += "<tr><td>"+N.MATERIAL+": </td><td> </td>" +
                "<td align='right'>"+S.Material+"</td></tr>";
        s += "<tr><td>Emin:</td><td> </td>" +
                "<td align='right'>"+Math.rint(S.minE[S.specAktuell]*100.0)/100+" eV</td></tr>";
        s +=  "<tr><td>Emax:</td><td> </td>" +
                "<td align='right'>"+Math.rint(S.maxE[S.specAktuell]*100.0)/100+" eV</td></tr>";
        s += "<tr><td>Step:</td><td> </td>" +
                "<td align='right'>"+Math.rint(S.Step*1000.0)/1000+" eV</td></tr>";
        s += "<tr><td>Dwell:</td><td> </td>" +
                "<td align='right'>"+(int)Math.rint(S.Dwell*1000)+" ms</td></tr>";
        s += "<tr><td>Scans:</td><td> </td>" +
                "<td align='right'>" + S.scans[S.specAktuell] + "</td></tr>";
        s += "<tr><td>Epass:</td><td> </td>" +
                "<td align='right'>"+S.Epass[S.specAktuell]+" eV</td></tr>";        
        s += "<tr><td>Phi:</td><td> </td>" +
                "<td align='right'>" +
                bin.AR65view.spec.phi[bin.AR65view.spec.specAktuell] +
                "°</td></tr>";
        s += "<tr><td>Theta:</td><td> </td>" +
                "<td align='right'>" +
                bin.AR65view.spec.theta[bin.AR65view.spec.specAktuell] +
                "°</td></tr>";
        s += "<tr><td>Channels:</td><td> </td>" +
                "<td align='right'>"+S.channels[S.specAktuell]+"</td></tr>";
        s += "</table><center>"+bin.SpectraData.datei+"</center>";
        this.setText(s);
    }
}
