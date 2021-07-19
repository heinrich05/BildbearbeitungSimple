import java.util.Random;

/**
 * Algorithmen zur Änderung der Pixelpositionen eines Pictures
 * z.B. drehen, spiegeln usw.
 *
 * @author S. Gebert
 * @version 06.2021
 */
public class Pixeloperationen  implements Bildoperation
{
    private int opCount=5; //number of operations available
    // IDs der geometrischen Operationen
    // Jede geometrische Operation erhält eine eindeutige Zahl,
    // um sich diese besser merken zu können, wird die Zahl einer Konstanten 'static final'
    // mit leserlichem Namen 'OP_<NameDerOperation>' zugeordnet.
    public static final int OP_Nil = 0;
    public static final int OP_graustufenDurchschnitt = 1;
    // ID der aktuell aktiven geometrischen Operation.
    private int op = OP_Nil;

    /**
     * Erstellt eine mit der aktuell aktiven geometrische Operation veränderte Kopie eines Bildes.
     *
     * @param originalBild Das zu verändernde Bild
     * @return Das geänderte Bild
     */
    @Override
    public Picture apply(Picture originalBild)
    {
        // Pro geometrische Operation wird hier eine Zeile benötigt, die die entprechende Operation ausführt.
        switch( this.op ){
            case OP_graustufenDurchschnitt: return graustufenDurchschnitt(originalBild);
            case OP_Nil:
            default: return originalBild.copy();
        }    
    }

    /**
     * Wählt eine Operation zum Ausführen via apply aus.
     *
     * @param op Nummer der auszuführenden Operation.
     */
    public void setOperation(int op)
    {
        if(op > this.opCount ) return;
        this.op = op;
    }

    // Anleitung zur Erstellung einer weiteren geometrischen Operation.
    // 1. Erstelle eine Methode mit der Signatur "public Picture meineGeometrischeOperation( Picture originalBild )",
    //    (siehe Beispiele unten)
    // 2. Passe wenn nötig die Methode apply an (siehe oben)
    //    und erstelle falls nötig eine neue Konstante "int OP_meineGeometrischeOperation".
    //

    
    public Picture graustufenDurchschnitt(Picture originalBild) {
        int breite = originalBild.getWidth();
        int hoehe  = originalBild.getHeight();

        Color[][] pixel = originalBild.getPixelsTable();
        Color[][] pixelNeu = new Color[breite][hoehe];

        for(int x=0; x < breite; x++) {
            for(int y=0;y < hoehe; y++) {
                int rot = pixel.getRed[x][y];
                int grün = pixel.getGreen[x][y];
                int blau = pixel.getBlau[x][y];
                
                int durchschnitt = (rot+grün+blau)/3;
                
                grau = new Color(durchschnitt,durchschnitt,durchschnitt);
                
                pixelNeu[x][y] = grau;
            }
        }
        Picture neuesBild = originalBild.copy();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }

    
    }
