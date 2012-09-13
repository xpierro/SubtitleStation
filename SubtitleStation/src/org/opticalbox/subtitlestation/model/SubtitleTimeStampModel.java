package org.opticalbox.subtitlestation.model;

/**
 * Classe représentant un point dans le temps
 * @author Pierre
 *
 */
public class SubtitleTimeStampModel implements Comparable<SubtitleTimeStampModel>, Cloneable {
    private int hours;
    private int minutes;
    private int seconds;
    private int miliSeconds;
    
    public SubtitleTimeStampModel(int hours, int minutes, int seconds, int miliSeconds) {
    	this.hours = hours;
    	this.minutes = minutes;
    	this.seconds = seconds;
    	this.miliSeconds = miliSeconds;
    }
    
    public SubtitleTimeStampModel() {
    	this(0, 0, 0, 0);
    }
    
    public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public int getMiliSeconds() {
		return miliSeconds;
	}
	public void setMiliSeconds(int miliSeconds) {
		this.miliSeconds = miliSeconds;
	}

	@Override
	public int compareTo(SubtitleTimeStampModel another) {
		if (another == null) {
			throw new NullPointerException("the Time Stamp is null");
		}
		
		int hoursComparison = Integer.valueOf(hours).compareTo(another.hours);
		if(hours > 0) {
			return hoursComparison;
		} else {
			int minutesComparison = Integer.valueOf(minutes).compareTo(another.minutes);
			if (minutesComparison > 0){
				return minutesComparison;
			} else {
				int secondsComparison = Integer.valueOf(seconds).compareTo(another.seconds);
				if (secondsComparison > 0) {
					return secondsComparison;
				} else {
					return Integer.valueOf(miliSeconds).compareTo(another.miliSeconds);
				}
			}
		}		
	}
	
	@Override
	public boolean equals(Object o) {
		return (o != null && o instanceof SubtitleTimeStampModel 
				&& compareTo((SubtitleTimeStampModel) o) == 0);
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    
}
