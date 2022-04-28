package gui;

import connection.data;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class gui {

    panel drawpanel = new panel();

    //Begrenzungen
    private static final double latitudeOben = 47.846533;
    private static final double latitudeUnten = 47.786898;

    private static final double longitudeLinks  = 16.209652;
    private static final double longitudeRechts  = 16.281017;


    private static final double diffNorthing = Math.abs(latitudeOben - latitudeUnten) / 1000;
    private static final double diffEasting = Math.abs(longitudeLinks - longitudeRechts) / 800;

    private String sportart = "";


    public gui(){
        mainWindow();
    }


    public JFrame mainWindow() {

        JFrame window = new JFrame("Graph");

        //positioniert das Fenster in der mitte
        window.setLocationRelativeTo(null);

        //beendet das Programm endgültig
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //-------TOOLBAR|(Sportarten)----------//

        ImageIcon basketball = new ImageIcon(new ImageIcon("src/images/Material Design Icon Pack/icons8-basketball-50.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon fussball = new ImageIcon(new ImageIcon("src/images/Material Design Icon Pack/icons8-fußball-2-50.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon laufen = new ImageIcon(new ImageIcon("src/images/Material Design Icon Pack/icons8-laufen-50.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon radfahren = new ImageIcon(new ImageIcon("src/images/Material Design Icon Pack/icons8-radfahren-auf-straße-50.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon schwimmen = new ImageIcon(new ImageIcon("src/images/Material Design Icon Pack/icons8-schwimmen-50.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon volleyball = new ImageIcon(new ImageIcon("src/images/Material Design Icon Pack/icons8-volleyball-50.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        Action basketballAction = new AbstractAction("Basketball", basketball) {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Basketball");
                sportart = "Basketball";

            }
        };
        Action fussballAction = new AbstractAction("Fussball", fussball) {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Fussball");
                sportart = "Fussball";

            }
        };

        Action laufenAction = new AbstractAction("Laufen", laufen) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Laufen");
                sportart = "Laufen";
            }
        };

        Action radfahrenAction = new AbstractAction("Radfahren", radfahren) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Radfahren");
                sportart = "Radfahren";
            }
        };

        Action schwimmenAction = new AbstractAction("Schwimmen", schwimmen) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Schwimmen");
                sportart = "Schwimmen";
            }
        };

        Action volleyballAction = new AbstractAction("Volleyball", volleyball) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Volleyball");
                sportart = "Volleyball";
            }
        };

        JToolBar toolBar = new JToolBar();
        toolBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 0));
        toolBar.add(basketballAction);
        toolBar.add(fussballAction);
        toolBar.add(laufenAction);
        toolBar.add(radfahrenAction);
        toolBar.add(schwimmenAction);
        toolBar.add(volleyballAction);

        window.add(toolBar, BorderLayout.PAGE_START);

        //-------Graph|Painting|Dots-----------//

        drawpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x=e.getX();
                    int y=e.getY();

                    //Pixel in Koordianten Umwandeln und neue Sportart hinzufuegen

                    Point2D coord = pixelToCoord(x, y);

                    newSportart(coord);
                }
            }});



        JScrollPane scrollPane = new JScrollPane(drawpanel);

        window.add(scrollPane);

        //Größe
        window.setSize(800,800);

        //zeigt das Fenster
        window.setVisible(true);

        data data = new data();

        data.selectAll();

        return window;
    }

    //Wandelt Pixel in Koordinaten um
    public static Point2D pixelToCoord(int x, int y){

        double longitude =(longitudeLinks + (diffEasting * x));
        double latitude = (latitudeOben - (diffNorthing * y));

        return new Point2D.Double(longitude, latitude);
    }

    //Wandelt Koordinaten in Pixel um
    public static Point coordToPixel(double longitude, double latitude){

        int x = (int) Math.round((longitude - longitudeLinks)/diffEasting );
        int y = (int) Math.abs(Math.round((latitude - latitudeOben)/diffNorthing));

        return new Point(x, y);
    }

    //Neue Sportart Visualisieren im Jpanel
    public void newSportart(Point2D point){
        Color color = JColorChooser.showDialog(null, "Farbauswahl", null);
        drawpanel.setSportart(sportart, point.getX(), point.getY(), color);

    }

    public static void main(String[] args) {

        gui window = new gui();

    }
}
