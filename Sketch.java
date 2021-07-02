import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.Objects;
import javafx.scene.paint.Color;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * 
 * Anzeigefläche für ein Bild.
 *
 * @author S. Gebert 
 * @version 06.2021
 */
public class Sketch extends PApplet implements InvalidationListener
{       
    private PImage displayImg;
    private Picture picture;
    //public int width = 600;
    //public int height = 400;

    /**
     * settings() Methode 
     * Fenstergröße size(int width, int height) und smooth(int level) muss hier eingestellt werden.
     */
    @Override
    public void settings()
    {
        size(width,height);
    }        

    /**
     * Die setup() Methode wird einmal aufgerufen, wenn das Programm startet.
     * Hier werden Einstellungen wie die Hintergrundfarbe vorgenommen
     * und Medien wie Bilder und Schriftarten geladen.
     */
    @Override
    public void setup()
    {            
        //surface.setResizable(true);
    }

    /**
     * Aktualisiert das anzuzeigende Bild
     *
     * @param width Breite des Bildes
     * @param height Höhe des Bildes
     * @param pixels Die Pixeldaten des Bildes
     */

    public void setPicture(Picture pic) {
        this.picture = pic;
        pic.pixelsChangedProperty().addListener(this);
        //pic.sizeChangedProperty().addListener(this);
    }

    @Override
    public void invalidated( Observable observable )
    {
        displayImg = createImage(this.picture.getWidth(), this.picture.getHeight(), PApplet.RGB);
        surface.setSize(this.picture.getWidth(),this.picture.getHeight());
        this.displayImg.loadPixels();
        this.displayImg.pixels = this.picture.getPixels();
        this.displayImg.updatePixels();
    }

    /**
     * Getter für das hinterlegte PImage
     *
     * @return Das PImage
     */
    public PImage readImage()
    {
        return this.displayImg;
    }

    /**
     * Speichert das aktuell angezeigte Bild
     *
     * @param filename Der Dateiname des zu speichernden Bildes.
     */
    public void save(String filename)
    {
        displayImg.save(filename);
    }

    /**
     * Läd ein neues Bild in die Anzeige
     *
     * @param filename Der Dateinmame des anzuzeigenden Bildes.
     */
    public void load(String filename)
    {
        displayImg = loadImage( filename );
    }

    /**
     * Die draw() Methode wird nach der setup() Methode aufgerufen
     * und führt den Code innerhalb ihres Blocks fortlaufend aus,
     * bis das Programm gestoppt oder noLoop() aufgerufen wird.
     */
    @Override
    public void draw()
    {
        if( Objects.isNull(displayImg)) {
            return;
        }
        image(displayImg,0,0);

    }
}
