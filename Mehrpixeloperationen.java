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
        int breite = originalbild.getWidth();
        int hoehe  = originalbild.getHeight();

        Color[][] pixel = originalbild.getPixelsColorTable();
        Color[][] pixelNeu = pixel.clone();

        int laenge = filter.length;
        int halb = laenge/2;

        for (int x = halb; x < breite - halb; x++){
            for (int y = halb; y < hoehe - halb; y++){
                int xx = x - halb;
                int yy = y - halb;

                double rot = 0.0;
                double gruen = 0.0;
                double blau = 0.0;

                for (int i = 0; i < laenge; i++){
                    for (int j = 0; j < laenge; j++){

                        rot += filter[i][j] * pixel[xx+i][yy+j].getRed();
                        gruen += filter[i][j] * pixel[xx+i][yy+j].getGreen();
                        blau += filter[i][j] * pixel[xx+i][yy+j].getBlue();

                    }
                }

                if (rot < 0.0) rot = 0.0;
                if (rot > 1.0) rot = 0.0;
                if (gruen < 0.0) gruen = 0.0;
                if (gruen > 1.0) gruen = 0.0;
                if (blau < 0.0) blau = 0.0;
                if (blau > 1.0) blau = 0.0;

                pixelNeu [x][y] = new Color (rot, gruen, blau, 1.0);

            }
        }

        for (int x=0; x<breite; x++){
            for (int y=0; y<hoehe; y++){
                if(x<halb){
                    pixelNeu[x][y]= Color.BLACK;

                }
                if(x>breite-halb){
                    pixelNeu[x][y]= Color.BLACK;
                }
                if(y<halb){
                    pixelNeu[x][y]= Color.BLACK;

                }
                if(y>hoehe-halb){
                    pixelNeu[x][y]= Color.BLACK;
                }
            }

        }
        Picture neuesBild = originalbild.copy();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }

    public Picture schaerfen(Picture originalbild){
        double [][] filter = {{0,-1,0},{-1,5,-1},{0,-1,0}};
        return faltung(originalbild,filter);
    }

    public Picture relief(Picture originalbild){
        double [][] filter = {{-2,-1,0},{-1,1,1},{0,1,2}};
        return faltung(originalbild,filter);
    }

    public Picture kantenfinden(Picture originalbild){
        double [][] filter = {{0,-1,0},{-1,4,-1},{0,-1,0}};
        return faltung(originalbild,filter);
    }

    public Picture weichzeichnen(Picture originalbild, int groesse){
        double [][] filter = new double[groesse][groesse];
        int pixel = groesse*groesse;

        for (int x=0; x < groesse; x++){
            for (int y=0; y<groesse; y++){
                filter[x][y] = 1.0/pixel;
            }
        }
        return faltung(originalbild,filter);
    }
}
