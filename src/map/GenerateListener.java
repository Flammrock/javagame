package map;

import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import java.io.Serializable;


/**
 *
 * @author User
 */
public abstract class GenerateListener extends SimpleListener implements Serializable {
    
    public GenerateListener() {
        super("onGenerate");
    }
    
}

