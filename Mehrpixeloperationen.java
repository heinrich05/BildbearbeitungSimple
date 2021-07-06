import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Algorithmen zur Anwendung von Filtern auf ein Picture
 * z.B. drehen, spiegeln usw.
 *
 * @author S. Gebert
 * @version 06.2021
 */
public class Mehrpixeloperationen  implements Operation
{
    /**
     * Erstellt eine mit der aktuell aktiven Operation veränderte Kopie eines Bildes.
     *
     * @param originalBild Das zu verändernde Bild
     * @return Das geänderte Bild
     */
    @Override
    public Picture apply(Picture originalBild)
    {
         return originalBild.copy();   
    }

 

    public Picture beispieloperation(Picture originalBild )
    {
        return originalBild.copy(); 
    }
    
    public Picture faltung (Picture originalbild, double[][] filter){
        Color[] pixel = originalBild.getPixelsTableColor();
        Color[] pixelNeu = newColor[breite][hoehe];
        
        int lange = filter.lenght;
        int halb = laenge/2;
        
        
    }
}
