package org.opticalbox.subtitlestation.model;

/**
 * Classe représentant un intervalle de temps.
 * @author Pierre
 *
 */
public class SubtitleTimeRangeModel {
	private SubtitleTimeStampModel begin;
	private SubtitleTimeStampModel end;
	
	public SubtitleTimeRangeModel(SubtitleTimeStampModel begin, SubtitleTimeStampModel end) {
		this.begin = (SubtitleTimeStampModel) begin.clone();
		this.end = (SubtitleTimeStampModel) end.clone();
	}

	public SubtitleTimeStampModel getBegin() {
		return begin;
	}

	public void setBegin(SubtitleTimeStampModel begin) {
		this.begin = begin;
	}

	public SubtitleTimeStampModel getEnd() {
		return end;
	}

	public void setEnd(SubtitleTimeStampModel end) {
		this.end = end;
	}
	
}
