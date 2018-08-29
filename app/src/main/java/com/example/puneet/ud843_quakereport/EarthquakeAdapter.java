package com.example.puneet.ud843_quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {

        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            //Find the earthquake at the given position in the list of earthquakes
            Earthquake currentEarthquake = getItem(position);

            TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
            //passing the extracted magnitude to the Decimal Formatter method
            String formatMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());
            magnitudeView.setText(formatMagnitude);

            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeView.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);




            //Getting the original location
            String originalLocation = currentEarthquake.getmLocation();
            String locationOffset;
            String primaryLocation;


            if (originalLocation.contains(LOCATION_SEPARATOR)){
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                locationOffset = parts[0] + " of";
                primaryLocation = parts[1];
            } else {
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = originalLocation;
            }
            TextView locationView = (TextView) listItemView.findViewById(R.id.primary_location);
            locationView.setText(primaryLocation);

            TextView s_locationView = (TextView) listItemView.findViewById(R.id.location_offset);
            s_locationView.setText(locationOffset);

            Date date = new Date(currentEarthquake.getmTimeInMillisecond());

            TextView dateView = (TextView) listItemView.findViewById(R.id.date);
            //Format the String i.e "Mar 3, 2010"
            String dateFormat = formatDate(date);
            dateView.setText(dateFormat);

            TextView timeView = (TextView) listItemView.findViewById(R.id.time);
            //Format the String i.e "4:00pm"
            String timeFormat = formatTime(date);
            timeView.setText(timeFormat);


        }
        //Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormat.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm a");
        return timeFormat.format(date);
    }

    //This will format the value of magnitude to "0.0"
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        //Convert the color resource ID into an actual integer color value
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
    }


