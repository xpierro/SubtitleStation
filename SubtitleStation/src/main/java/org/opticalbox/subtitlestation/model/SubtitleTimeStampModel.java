package org.opticalbox.subtitlestation.model;

/**
 * Classe représentant un point dans le temps - Non mutable
 * @author Pierre
 *
 */
public final class SubtitleTimeStampModel implements Comparable<SubtitleTimeStampModel>, Cloneable {
    private final int hours;
    private final int minutes;
    private final int seconds;
    private final int miliSeconds;
    
    /**
     * Créé un timestamp
     * @param hours Le nombre d'heures
     * @param minutes Le nombre de minutes
     * @param seconds Le nombre de secondes
     * @param miliSeconds Le nombre de milisecondes
     */
    public SubtitleTimeStampModel(int hours, int minutes, int seconds, int miliSeconds) {
    	if (minutes > 59 || seconds > 59 || miliSeconds > 1000) {
    		throw new IllegalArgumentException(hours + ":" + minutes + ":" + seconds + "." + miliSeconds + " is not a valid time stamp");
    	}
    	this.hours = hours;
    	this.minutes = minutes;
    	this.seconds = seconds;
    	this.miliSeconds = miliSeconds;
    }
    
    /**
     * Crée un timestamp à 0
     */
    public SubtitleTimeStampModel() {
    	this(0, 0, 0, 0);
    }
    
    public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public int getMiliSeconds() {
		return miliSeconds;
	}
	
	/**
	 * Additionne le timestamp courant avec un autre
	 * @param subtitleTimeStampModel Le timestamp avec lequel additionner le timestamp courant
	 * @return Un nouveau timestamp addition
	 */
	public SubtitleTimeStampModel add(SubtitleTimeStampModel subtitleTimeStampModel) {
		int mili = miliSeconds + subtitleTimeStampModel.miliSeconds;
		int miliInt = mili / 1000;
		int miliDec = mili - 1000 * miliInt;
		
		int sec = seconds + subtitleTimeStampModel.seconds + miliInt;
		int secInt = sec / 60;
		int secDec = sec - 60 * secInt; 
		
		int min = minutes + subtitleTimeStampModel.minutes + secInt; 
		int minInt = min / 60;
		int minDec = min - 60 * minInt;
		
		int h = hours + subtitleTimeStampModel.hours + minInt;
		
		return new SubtitleTimeStampModel(h, minDec, secDec, miliDec);
		
	}
	
	public long getMiliSecondStamp() {
		return miliSeconds 
			   + seconds * 1000 
	           + minutes * 60 * 1000 
	           + hours * 3600 * 1000; 
	}
	

	@Override
	public int compareTo(SubtitleTimeStampModel another) {
		if (another == null) {
			throw new NullPointerException("the Time Stamp is null");
		}
		
		int hoursComparison = Integer.valueOf(hours).compareTo(another.hours);
		int minutesComparison = Integer.valueOf(minutes).compareTo(another.minutes);
		int secondsComparison = Integer.valueOf(seconds).compareTo(another.seconds);
		int miliSecondsComparison = Integer.valueOf(miliSeconds).compareTo(another.miliSeconds);
		
		return hoursComparison != 0 ? hoursComparison : 
			   minutesComparison != 0 ? minutesComparison : 
			   secondsComparison != 0 ? secondsComparison : 
			   miliSecondsComparison;
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
    
	@Override
	public String toString() {
		return hours + ":" + minutes + ":" + seconds + "." + miliSeconds;
	}
    
}
