package org.opticalbox.subtitlestation.model;

/**
 * Classe représentant un point dans le temps
 * @author Pierre
 *
 */
public class SubtitleTimeStampModel {
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
    
    
}
