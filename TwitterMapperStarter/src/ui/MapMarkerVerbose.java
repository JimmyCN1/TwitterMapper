package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import query.Query;
import twitter4j.Status;

import java.awt.*;

import static util.Util.imageFromURL;

public class MapMarkerVerbose extends MapMarkerSimple {
    private Status status;

    public MapMarkerVerbose(Layer layer, Coordinate coord, Color color, Status status) {
        super(layer, coord);
        setColor(color);
        setBackColor(color);
        this.status = status;
//        paint(imageFromURL(status.getUser().getMiniProfileImageURL()).createGraphics(), new Point(50, 50), 100);
//        status.getText();
    }

    public Status getStatus() { return this.status; }

//    public void displayProfilePic() {
//
//    }

//    Point p = getMapPosition(this.getLat(), this.getLon(), this.getMarkerStyle()==MapMarkerVerbose.STYLE.FIXED)
}
