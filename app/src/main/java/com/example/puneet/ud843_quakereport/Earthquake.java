package com.example.puneet.ud843_quakereport;

public class Earthquake {

    private double mMagnitude;

    private String mLocation;

    private long mTimeInMillisecond;

    private String url;

    public Earthquake(double mMagnitude, String mLocation, long mTimeInMillisecond , String url) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTimeInMillisecond = mTimeInMillisecond;
        this.url = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }


    public long getmTimeInMillisecond() {
        return mTimeInMillisecond;
    }

    public String getUrl() {
        return url;
    }
}
