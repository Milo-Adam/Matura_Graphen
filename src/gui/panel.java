package gui;

import connection.Person;
import connection.Sportart;
import connection.data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class panel extends JPanel {

    private BufferedImage image;
    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Sportart> sportarts = new ArrayList<>();

    public panel() {
        try {
            image = ImageIO.read(new File("src/images/Stadtplan-Wiener-Neustadt.jpg"));

            persons = data.getPersons();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, this);
        g.setColor(Color.RED);

        for(int i = 0; i < persons.size(); i++){

            Point p = gui.coordToPixel(persons.get(i).Longitude, persons.get(i).Latitude);

            g.fillOval(p.x, p.y, 10, 10);
        }

        for(int i = 0; i < sportarts.size(); i++){

            g.setColor(sportarts.get(i).color);

            Point point = gui.coordToPixel(sportarts.get(i).Longitude, sportarts.get(i).Latitude);

            g.fillOval(point.x, point.y, 20, 20);
        }

        repaint();

    }

    //neue Sportart setzen
    public void setSportart(String newArt, double newLongitude, double newLatitude, Color newColor){

        Sportart art = new Sportart();

        art.Art = newArt;
        art.Latitude = newLatitude;
        art.Longitude = newLongitude;
        art.color = newColor;

        sportarts.add(art);

    }
}