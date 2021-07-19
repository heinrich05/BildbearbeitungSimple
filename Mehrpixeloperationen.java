import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Algorithmen zur Anwendung von Filtern auf ein Picture
 * z.B. drehen, spiegeln usw.
 *
 * @author S. Gebert
 * @version 06.2021
 */
public class Mehrpixeloperationen  implements Bildoperation
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
        Color[][] pixel = originalbild.getPixelsTableColor();
        Color[][] pixelNeu = newColor[breite][hoehe];
        
        int lange = filter.lenght;
        int halb = laenge/2;
        
        for (int x = halb; x < originalbild.getWidth() - halb; x++){
            for (int y = halb; y < originalbild.getHeight() - halb; y++){
                for (int i = 0; i < laenge; i++){
                    for (int j = 0; j < laenge; j++){
                        int xx = x - halb;
                        int yy = y - halb;
                        
                        double rot = 0.0;
                        double gruen = 0.0;
                        double blau = 0.0;
                        
                        rot += filter[i][j] * pixel[xx+i][yy+j].getRed();
                        gruen += filter[i][j] * pixel[xx+i][yy+j].getGreen();
                        blau += filter[i][j] * pixel[xx+i][yy+j].getBlue();
                        
                        if (rot < 0.0) rot = 0.0;
                        if (rot > 255.0) rot = 0.0;
                        if (gruen < 0.0) gruen = 0.0;
                        if (gruen > 255.0) gruen = 0.0;
                        if (blau < 0.0) blau = 0.0;
                        if (blau > 255.0) blau = 0.0;
                        
                        pixelNeu [x][y] = new Color ((int) rot, (int) gruen, (int) blau);
                    }
                }
            }
        }
        Picture neuesBild = new Picture();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }
}
