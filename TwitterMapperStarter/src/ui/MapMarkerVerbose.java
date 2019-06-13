package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import query.Query;

import java.awt.*;

public class MapMarkerVerbose extends MapMarkerSimple {
//    private Query query;

    public MapMarkerVerbose(Layer layer, Coordinate coord, Color color) {
        super(layer, coord);
        setBackColor(color);

    }


}
