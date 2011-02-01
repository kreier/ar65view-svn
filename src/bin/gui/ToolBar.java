/*
 * ToolBar.java
 */

package bin.gui;

import javax.swing.JToolBar;

/** Beschreibungstext JavaDoc
 *  
 *  @author Matthias Kreier
 */
public class ToolBar extends JToolBar 
{
    
    /** Creates a new instance of ToolBar */
    public ToolBar() {
        add( bin.perform.Aktion.newAction );
        add( bin.perform.Aktion.openAction );
        add( bin.perform.Aktion.saveAction );
        add( bin.perform.Aktion.saveasAction );
        addSeparator();
        add( bin.perform.Aktion.cutAction );
        add( bin.perform.Aktion.copyAction );
        add( bin.perform.Aktion.pasteAction );
        addSeparator();
        add( bin.perform.Aktion.removeSpikesAction );
        add( bin.perform.Aktion.smoothAction );
        add( bin.perform.Aktion.removeShirleyAction );
        add( bin.perform.Aktion.normalizeSpectra );
        add( bin.perform.Aktion.exportSpectra );
        addSeparator();
        add( bin.perform.Aktion.exitAction );
        
        this.setFloatable(false);
    }
    
}
