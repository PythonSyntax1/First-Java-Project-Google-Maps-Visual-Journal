package ui;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import java.util.ArrayList;

public class Markers {

    private static ArrayList<Marker> goalMarkerList = new ArrayList<>();
    private static ArrayList<Marker> journalMarkerList = new ArrayList<>();

    //Creates a new marker at inputted location
    public Marker makeNewMarker(LatLong loc) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(loc);
        Marker marker = new Marker(markerOptions);
        return marker;
    }

    //Creates and adds new Marker to Journal Marker List at inputted location
    public void addJournalMarkerList(LatLong loc) {
        Marker marker = makeNewMarker(loc);
        journalMarkerList.add(marker);
    }

    //Creates and adds new Marker to Goal Marker List at inputted location
    public void addGoalMarkerList(LatLong loc) {
        Marker marker = makeNewMarker(loc);
        goalMarkerList.add(marker);
    }


    public ArrayList<Marker> getJournalMarkerList() {
        return journalMarkerList;
    }

    public ArrayList<Marker> getGoalMarkerList() {
        return goalMarkerList;
    }
}
