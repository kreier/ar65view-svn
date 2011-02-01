/*
 * SpectraData.java
 *
 */

package bin;

import java.io.*;
import java.util.Random;
import javax.swing.*;
import java.net.URL;
import java.lang.String;

/** This class contains the data of all 10 regions including
 *  energy, scan, dwell time, maxCounts and other statistic data.<BR>
 *  <BR>
 *  All functions to manipulate the data (smooth, stretch, ...) are
 *  included in this class. They can be called directly and apply 
 *  on the loaded data.
 */
public class SpectraData {
    
  private static final Random r = new Random(); 
  
  public int specAnzahl;
  public int specAktuell;
  public int [ ] channels = new int[ 10 ];
  public int [ ] [ ] messwert = new int[ 10 ] [ 4000 ];
  public int [ ] minCounts = new int[ 10 ];
  public int [ ] maxCounts = new int[ 10 ];
  public int [ ] scans = new int[ 10 ];
  public int [ ] shirley = new int [ 4000 ];
  
  public double Step;
  public double Dwell;
  public double [ ] minE = new double[ 10 ];
  public double [ ] maxE = new double[ 10 ];
  public double [ ] theta = new double[ 10 ];
  public double [ ] phi   = new double[ 10 ];
  /** Pass-Energie des Energieanalysators */
  public double [ ] Epass = new double[ 10 ];
  
  public String Material;
  public static String datei;
  
  private String InfoData;

  /** contructor of SpectraData, calls the init procedure */
  public SpectraData( )  
  {
    this.init();
  }

  /** die externen Variablen mit sinnvollen Werten setzen */
  public void init() 
  {
    specAnzahl = 1;
    specAktuell = 0;
    scans[specAktuell] = 2;
    minCounts[specAktuell] = 0;
    maxCounts[specAktuell] = 2542;
    minE[specAktuell] = 55;
    maxE[specAktuell] = 102;
    Dwell = 0.1;
    Step = 0.5;
    Epass[specAktuell] = 10;
    datei = "CdHgTe.1";
    Material = "CdHgTe";
    channels[0] = 942;
    InputStream cdhgte = bin.AR65view.class.getResourceAsStream("/data/cdhgte.1");
    LineNumberReader lnr = new LineNumberReader( new InputStreamReader( cdhgte ) );
    String line = "";
    for(int i=0;i<channels[0];i++)
    { 
        try
	{
		line = lnr.readLine();
	}
	catch ( IOException e ) { e.printStackTrace(); }
        messwert[0][i] = (int)Double.parseDouble( line );
    }
    removeAktivation();
  }
  
  /** simply smooth data (like Shirley did) - or Savitzky-Golay */
  public void smooth() 
  {
    int old = messwert[specAktuell][0];
    minCounts[specAktuell] = old;
    maxCounts[specAktuell] = old;
    messwert[specAktuell][0] = 
            (messwert[specAktuell][0]+messwert[specAktuell][1])/2;
    float mean = 0;
    for(int i=1; i<(channels[specAktuell]-1); i++)
	{
	  mean = (float)(old + messwert[specAktuell][i+1] + 
                  (2*messwert[specAktuell][i]))/4;
	  old = messwert[specAktuell][i];
	  messwert[specAktuell][i] = (int)mean;
          if(old>maxCounts[specAktuell]){maxCounts[specAktuell]=old;}
          if(old<minCounts[specAktuell]){minCounts[specAktuell]=old;}
	}
    messwert[specAktuell][channels[specAktuell]-1] = 
            (messwert[specAktuell][channels[specAktuell]-1] + 
            messwert[specAktuell][channels[specAktuell]-2])/2;
    if(minCounts[specAktuell] == maxCounts[specAktuell]) 
        maxCounts[specAktuell]++;
  }

  /** load spectradata from file DateiName 
   * 
   * <p>There are two types of files: one with energy and angle data ( new, 
   * written by Thorsten Zandt, different order in InfoData line, 132 char 
   * length) and the old original Omicron data file system (program written 
   * in Pascal) with a shorter InfoData line (84 chars)  
   * 
   * @param String DateiName
   *        <p>Der Dateiname ist der volle Pfad des aktuellen Betriebssystems
   *        zur Quelldatei 
   */
  public void load(String DateiName)  
  {  
    String quelle = "";
    RandomAccessFile vir ;		// Quelldatei
    try							// open
    {
	vir = new RandomAccessFile ( DateiName , "r" );
    }
    catch ( IOException e ) 
    { 
      JOptionPane.showMessageDialog( null , "The file " + DateiName 
	 + "\ncan not be opened." , "File not found" , JOptionPane.ERROR_MESSAGE ); 
      return;
    }
    specAktuell = 0;
    theta[specAktuell] = 0;
    phi[specAktuell] = 0;
    String muellData;
    removeAktivation();
    String DateiEndung = "";
    DateiEndung = DateiName.substring(DateiName.length()-3, DateiName.length());
    if( DateiEndung.equalsIgnoreCase("VIR") ) {// VIR data from WESPHOA
        specAnzahl = 1;
        channels[0] = (int)lese( vir );
	for(int i=0; i<channels[0]; i++) // Einlesen der Messwerte
	{
	  messwert[0][i] = (int) lese( vir );
	}
        // Am Ende der VIR-Dateien folgen die Details wie folgt:
        scans[0] = (int) lese( vir );
        minCounts[0] = (int) lese( vir );
	maxCounts[0] = (int) lese( vir );
        theta[0] = lese( vir );
        minE[0]  = lese( vir );
        maxE[0]  = lese( vir );        
        double Elampe1 = lese( vir );// Photonenenergie Anregung - 21.22 eV HeI
        double Elampe2 = lese( vir );// was f?r eine zweite Lampe ??
        double deltaE = lese( vir ); // Energieaufl?sung in meV
        Dwell = lese( vir );
        String DatumZeit = leseZ( vir );//Datum, Anfangs- und Endzeit
        String MessTyp = leseZ( vir );  // EDC oder CFS oder ...
        Material = leseZ( vir );
        phi[0] = lese( vir );        
        Epass[specAktuell] = lese( vir ); 
        /* Es folgen noch zwei Zeilen mit Beschreibung A und B des Experimentes,
         * meist sind jene Zeilen jedoch nicht ausgef?llt - siehe Laborbuch!
         *
         * Eventuell waren die Daten am Ende der Datei nicht lesbar. Um eine
         * Division durch Null zu verhindern, setzen wir Standardwerte: */
        if(maxE[0]==minE[0]) maxE[0]=maxE[0]+10;
        if(maxCounts[0]==minCounts[0])
        {
            maxCounts[0]=maxCounts[0]+500;
            for(int i=0; i<channels[0]; i++) // nochmals Maximum suchen
	    {
                if(messwert[0][i] > maxCounts[0]) maxCounts[0] = messwert[0][i];
	    }
        }
        if(Material.length()==0) Material = "unbekannt";
        /* Die Schrittweite Step ist nicht in der Messdatei enthalten, kann
         * allerdings sehr einfach berechnet werden:
         */
        Step = (maxE[0]-minE[0])/scans[0];
    }
    else { // common file from AR65 
    Material =  leseZ( vir );
    InfoData = leseZ( vir );
    /* There are two types of files: one with energy and angle data ( new, 
     * written by Thorsten Zandt, different order in InfoData line, 132 char 
     * length) and the old original Omicron data file system (program written 
     * in Pascal) with a shorter InfoData line (84 chars)  */
    if( InfoData.length() < 100 ) { // old file by programm EDC
        specAnzahl = 1;
        specAktuell = 0;
        convertInfoData();
        muellData = leseZ( vir ); // line contains no usefull data
        messwert[0][0] = (int)lese( vir );
        minCounts[0] = messwert[0][0];
	maxCounts[0] = messwert[0][0];
	for(int i=1; i<channels[0]; i++) // Einlesen der Messwerte
	{
	  messwert[0][i] = (int) lese( vir );
	  if (messwert[0][i]<minCounts[0]) minCounts[0] = messwert[0][i];
	  if (messwert[0][i]>maxCounts[0]) maxCounts[0] = messwert[0][i];
	}
        if(minCounts[0] == maxCounts[0]) maxCounts[0]++;
    }
    else { // file type with angle information, by programm EDC_STEP
      specAnzahl = 0;
      do {  
        muellData = leseZ( vir ); // line contains no usefull data
        muellData = leseZ( vir ); // line contains 'Ekin Phi Theta Counts'        
        specAnzahl++;
        specAktuell = specAnzahl - 1;
        convertInfoDataAngle();
        setAktivation(specAktuell);
        messwert[specAktuell][0] = (int)lese2( vir );
        minCounts[specAktuell] = messwert[specAktuell][0];
	maxCounts[specAktuell] = messwert[specAktuell][0];
	for(int i=1; i<channels[specAktuell]; i++)  // Einlesen der Messwerte
	{
	  messwert[specAktuell][i] = (int) lese2( vir );
	  if (messwert[specAktuell][i]<minCounts[specAktuell]) 
              minCounts[specAktuell] = messwert[specAktuell][i];
	  if (messwert[specAktuell][i]>maxCounts[specAktuell]) 
              maxCounts[specAktuell] = messwert[specAktuell][i];
	}
        if(minCounts[specAktuell] == maxCounts[specAktuell]) maxCounts[specAktuell]++;
      }
      while ((InfoData = leseZ( vir )) != null);
      specAktuell = 0;
    } // end EDC_STEP
    } // end AR65 at all
  }
  
  /** remove spikes from the spectra data */
  public void removeSpikes() 
  {
    double threshold = 1.2; // 20% ueber Durchschnitt ist Spike
    if(messwert[specAktuell][0]<messwert[specAktuell][1]) {
        messwert[specAktuell][1]=messwert[specAktuell][0]; 
    }
    else { messwert[specAktuell][0]=messwert[specAktuell][1]; }
    double mittel = 0;
    for(int i=2; i<(channels[specAktuell]-2); i++)
    {
        mittel = threshold * (messwert[specAktuell][i-1] +
                messwert[specAktuell][i-2]) / 2;
        if(messwert[specAktuell][i]>mittel)// larger than average of left 2 pix
        {
            mittel = threshold * (messwert[specAktuell][i-1] + 
                    messwert[0][i+1]) / 2; // average of left and right
            if(messwert[specAktuell][i]>mittel)
            {
               messwert[specAktuell][i]=(int)mittel;
            }    
        }
    }
  }

  /** remove Shirley background (inelastic scattering) from the spectra data */
  public void removeShirley()
  {
    // einmal Spektrum durchlaufen und aufsummieren (von rechts nach links):
    shirley[channels[specAktuell]] = messwert[specAktuell][channels[specAktuell]];
    for(int i=channels[specAktuell]-1; i>-1; i--)
    {
        shirley[i] = messwert[specAktuell][i] + shirley[i+1];
    }
    double faktor = 1; // Reduzierung von Shirley vor dem Abzug der Werte
    // letztes Drittel auf maximale Differenz untersuchen
    for(int i=0; i<(int)((channels[specAktuell])*0.3); i++)
    {
        if(shirley[i]*faktor > messwert[specAktuell][i])
        {
            faktor = (double)messwert[specAktuell][i]/(double)shirley[i];
        }
    }
    for(int i=1; i<(channels[specAktuell]); i++)
    {
        messwert[specAktuell][i] = messwert[specAktuell][i] - (int)(shirley[i]*faktor);
    }
  }

  /** read a line of src, convert to double */
  static double lese(RandomAccessFile src) 
  {
    String inhalt = "";
  	try
	{
	  inhalt = src.readLine();
	}
	catch ( IOException e ) { e.printStackTrace(); }
        if( inhalt != null ) {
            return Double.parseDouble( inhalt );
        }
	return 0;
  }

  /** read a line of src */
  static String leseZ(RandomAccessFile src) 
  {
    String inhalt = "";
  	try
	{
		inhalt = src.readLine();
	}
	catch ( IOException e ) { e.printStackTrace(); }
        if( inhalt != null ) {
            return inhalt;
        }
	return "";    
  }
  
  /** read a line of src from a file with angle information */
  static double lese2(RandomAccessFile src)
  {
    String inhalt = "";
  	try
	{
		inhalt = src.readLine();
	}
	catch ( IOException e ) { e.printStackTrace(); }
        if( inhalt != null ) {
            return Double.parseDouble( inhalt.substring(23) );
        }
	return 0;    
  }
  
  /** string InfoData sets values, for program EDC */
  void convertInfoData() 
  {
    minE[specAktuell] = Double.parseDouble(InfoData.substring(3,12));
    maxE[specAktuell] = Double.parseDouble(InfoData.substring(15,24));
    Step = Double.parseDouble(InfoData.substring(28,36));
    scans[0] = (int)Double.parseDouble(InfoData.substring(39,48));
    Dwell = Double.parseDouble(InfoData.substring(52,60));
    channels[specAktuell] = (int)Double.parseDouble(InfoData.substring(62,66));
    theta[0] = 0;
    phi[0] = 0;
    Epass[specAktuell] = Double.parseDouble(InfoData.substring(69,74));    
  }

  /** string InfoData sets values, program EDC_STEP */
  void convertInfoDataAngle()  
  {
    minE[specAktuell] = Double.parseDouble(InfoData.substring(3,12));
    maxE[specAktuell] = Double.parseDouble(InfoData.substring(15,24));
    Step = Double.parseDouble(InfoData.substring(28,36));
    scans[specAktuell] = (int)Double.parseDouble(InfoData.substring(39,48));
    Dwell = Double.parseDouble(InfoData.substring(52,60));
    phi[specAktuell] = Double.parseDouble(InfoData.substring(63,72));
    theta[specAktuell] = Double.parseDouble(InfoData.substring(76,84));
    channels[specAktuell] = (int)Double.parseDouble(InfoData.substring(110,114));
    Epass[specAktuell] = Double.parseDouble(InfoData.substring(118,122));
  }  
  
  /** remove activation from R2 to R10 (all regions expect first one) */
  public void removeAktivation() 
  {
      bin.perform.Aktion.spec0.setEnabled(true);
      bin.perform.Aktion.spec1.setEnabled(false);
      bin.perform.Aktion.spec2.setEnabled(false);
      bin.perform.Aktion.spec3.setEnabled(false);
      bin.perform.Aktion.spec4.setEnabled(false);
      bin.perform.Aktion.spec5.setEnabled(false);
      bin.perform.Aktion.spec6.setEnabled(false);
      bin.perform.Aktion.spec7.setEnabled(false);
      bin.perform.Aktion.spec8.setEnabled(false);
      bin.perform.Aktion.spec9.setEnabled(false);
  }
  
  /** aktiviert im Anzeigefeld 'Region' das entsprechende Spektrum (1 bis 10) */
  public void setAktivation(int numberSpectra )
  {
      switch ( numberSpectra ) 
      {
          case 0:
              bin.perform.Aktion.spec0.setEnabled(true);
              break;
          case 1:
              bin.perform.Aktion.spec1.setEnabled(true);
              break;
          case 2:
              bin.perform.Aktion.spec2.setEnabled(true);
              break;
          case 3:
              bin.perform.Aktion.spec3.setEnabled(true);
              break;
          case 4:
              bin.perform.Aktion.spec4.setEnabled(true);
              break;
          case 5:
              bin.perform.Aktion.spec5.setEnabled(true);
              break;
          case 6:
              bin.perform.Aktion.spec6.setEnabled(true);
              break;
          case 7:
              bin.perform.Aktion.spec7.setEnabled(true);
              break;
          case 8:
              bin.perform.Aktion.spec8.setEnabled(true);
              break;
          case 9:
              bin.perform.Aktion.spec9.setEnabled(true);
              break;
      }
  }
}
