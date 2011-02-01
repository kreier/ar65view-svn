package bin;

import java.io.*;

/** all strings in this program here defined to load another language
 *
 * @author Маттиас
 */
public class Name {
    public static String FILE, NEU, OPEN, SAVE, SAVE_AS, EXIT;
    public static String EDIT, CUT, COPY, PASTE;
    public static String LOOK_AND_FEEL, DEF_PLATFORM, WINDOWS, LINUX, PLASTIQUE, METAL, MOTIF;
    public static String HELP, HELP_ABOUT, ABOUT;
    public static String ENERGY_IN_EV,MATERIAL;
    
    static
    {
       FILE = "File";
       NEU  = "New";
       OPEN = "Open";
       SAVE = "Save";
       SAVE_AS = "Save as ...";
       EXIT = "Exit";
       EDIT = "Edit";
       CUT  = "Cut";
       COPY = "Copy";
       PASTE = "Paste";
       LOOK_AND_FEEL = "Look'N'Feel";
       DEF_PLATFORM = "Standard Platform Style";
       WINDOWS = "Windows XP";
       LINUX = "Linux";
       PLASTIQUE = "Plastic";
       METAL = "Metal";
       MOTIF = "Motif";
       HELP = "Help";
       HELP_ABOUT = "How to use AR65view";
       ABOUT = "About AR65view";
       ENERGY_IN_EV = "Energy in eV";
       MATERIAL = "Material";
    }           

            
                
  public static void initDE() 
  {
    OPEN = "?ffnen";
    RandomAccessFile sprachdatei;
    String src = bin.AR65view.class.getResource("/data/deutsch.txt").toString();
    if( src.startsWith( "file:" ) ) {
            src = src.substring( 6, src.length() );
    }
    try	
    {
       sprachdatei = new RandomAccessFile ( src , "r" );
       FILE = leseString( sprachdatei) ;
       NEU  = leseString( sprachdatei) ;
       OPEN = leseString( sprachdatei) ;
       SAVE = leseString( sprachdatei) ;
       SAVE_AS = leseString( sprachdatei) ;
       EXIT = leseString( sprachdatei) ;
       EDIT = leseString( sprachdatei) ;
       CUT  = leseString( sprachdatei) ;
       COPY = leseString( sprachdatei) ;
       PASTE = leseString( sprachdatei) ;
       HELP = leseString( sprachdatei) ;
       ABOUT = leseString( sprachdatei) ;
       System.out.println( FILE );
       System.out.println( OPEN );
    }
    catch ( IOException e ) 
    { 
        e.printStackTrace(); 
    }

  }
    
  static String leseString(RandomAccessFile src)
  {
    String inhalt = "";
  	try
	{
		inhalt = src.readLine();
	}
	catch ( IOException e ) { e.printStackTrace(); }
	return inhalt;
  }                 

  /**
     * Creates a new instance of Name
     */
  public Name() {
      //initDE();
  } 
}
    
