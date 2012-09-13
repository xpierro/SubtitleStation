package org.opticalbox.subtitlestation.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Représente une ligne de sous-titres
 * @author Pierre
 *
 */
public class SubtitleLineModel {
	private SubtitleTimeRangeModel timeRange;
	private List<SubtitleSentenceModel> sentences;
	
	public SubtitleLineModel(SubtitleTimeRangeModel timeRange, List<SubtitleSentenceModel> sentences) {
		this.sentences = new LinkedList<SubtitleSentenceModel>();
		this.timeRange = timeRange;
		this.sentences.addAll(sentences);
	}
	
	public SubtitleLineModel(SubtitleTimeRangeModel timeRange) {
		this(timeRange, new LinkedList<SubtitleSentenceModel>());
	}

	public SubtitleLineModel() {
		this(new SubtitleTimeRangeModel(new SubtitleTimeStampModel(), new SubtitleTimeStampModel()));
	}
	
	public SubtitleTimeRangeModel getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(SubtitleTimeRangeModel timeRange) {
		this.timeRange = timeRange;
	}

	public List<SubtitleSentenceModel> getSentences() {
		return sentences;
	}

	public void setSentences(List<SubtitleSentenceModel> sentences) {
		this.sentences = sentences;
	}
	
	public void addSentences(SubtitleSentenceModel sentence) {
		sentences.add(sentence);
	}
}
