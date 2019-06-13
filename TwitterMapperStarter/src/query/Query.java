package query;

import filters.Filter;
import filters.Parser;
import filters.SyntaxError;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import twitter.TwitterSource;
import twitter4j.Status;
import ui.MapMarkerSimple;
import ui.MapMarkerVerbose;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static util.Util.imageFromURL;
import static util.Util.statusCoordinate;

/**
 * A query over the twitter stream.
 * TODO: Task 4: you are to complete this class.
 */
public class Query implements Observer {
    // The map on which to display markers when the query matches
    private final JMapViewer map;
    // Each query has its own "layer" so they can be turned on and off all at once
    private Layer layer;
    // The color of the outside area of the marker
    private final Color color;
    // The string representing the filter for this query
    private final String queryString;
    // The filter parsed from the queryString
    private final Filter filter;
    // The checkBox in the UI corresponding to this query (so we can turn it on and off and delete it)
    private JCheckBox checkBox;
    private List<MapMarkerSimple> markers = new ArrayList<>();

    public Color getColor() {
        return color;
    }
    public String getQueryString() {
        return queryString;
    }
    public Filter getFilter() {
        return filter;
    }
    public Layer getLayer() {
        return layer;
    }
    public JCheckBox getCheckBox() {
        return checkBox;
    }
    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
    public void setVisible(boolean visible) {
        layer.setVisible(visible);
    }
    public boolean getVisible() { return layer.isVisible(); }

    public Query(String queryString, Color color, JMapViewer map) {
        this.queryString = queryString;
        this.filter = Filter.parse(queryString);
        this.color = color;
        this.layer = new Layer(queryString);
        this.map = map;
    }

    @Override
    public String toString() {
        return "Query: " + queryString;
    }

    /**
     * This query is no longer interesting, so terminate it and remove all traces of its existence.
     *
     */
    public void terminate() {
        for (MapMarkerSimple marker : markers) {
            map.removeMapMarker(marker);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Status status = (Status) arg;
        if (filter.matches(status)) {
            MapMarkerVerbose marker = new MapMarkerVerbose(layer, statusCoordinate((Status) arg), color, status);
            Point point = map.getMapPosition(marker.getLat(), marker.getLon());
            marker.paint(imageFromURL(((Status) arg).getUser().getMiniProfileImageURL()).createGraphics(), point, 10);
            map.addMapMarker(marker);
            this.markers.add(marker);
//            map.addMapPolygon(imageFromURL(((Status) arg).getUser().getMiniProfileImageURL()).getGraphics());

        }
    }
}

