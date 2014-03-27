package com.example.sportmix;
import java.util.List;
import java.util.logging.Logger;

import com.example.sportmix.R;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
public class OthersFragment extends SupportMapFragment {
	 GoogleMap mapView;
	 GPSTracker gps;
	    private Context context;

	    @Override
	    public void onCreate(Bundle arg0) {
	        super.onCreate(arg0);
	    }

	    @Override
	    public View onCreateView(LayoutInflater mInflater, ViewGroup arg1,
	        Bundle arg2) {
	        View view = super.onCreateView(mInflater, arg1, arg2);

	        return view;
	    }

	    @Override
	    public void onInflate(Activity arg0, AttributeSet arg1, Bundle arg2) {
	        super.onInflate(arg0, arg1, arg2);
	    }

	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        context = getActivity();
	        mapView = getMap();
	     // latitude and longitude
	        double latitude = 100;
	        double longitude = 100;
	        Location mCurrentLocation;
	        SQLHelper db = new SQLHelper(getActivity());
	        List<Score> contacts = db.getAllScores();
	        double lat,longi;
	      Integer size=contacts.size();
	        Log.d("Data:",size.toString());
	      
	        gps = new GPSTracker(getActivity());
	        if(gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
               

                // \n is for new line
                Toast.makeText(getActivity().getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }
	        // create marker
	        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
	         
	        // adding marker
	        mapView.addMarker(marker);
	      for(Score c:contacts)
	        {
	        	lat=c.getLatitude();
	        	longi=c.getLongitude();
	        	 MarkerOptions marker1 = new MarkerOptions().position(new LatLng(lat, longi)).title("Hello Maps ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
	        	 mapView.addMarker(marker1);
	        }
	        
	       /* LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
	        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
	        double lat= location.getLatitude();
	        double lng = location.getLongitude();
	        LatLng ll = new LatLng(lat, lng);
	        mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 20));
	        */
	    }
	 
	
	}
       
