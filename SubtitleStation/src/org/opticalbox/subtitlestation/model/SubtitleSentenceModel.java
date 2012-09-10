package org.opticalbox.subtitlestation.model;

/**
 * Classe repr�sentant une phrase de sous-titre dans une langue donn�e.
 * @author Pierre
 *
 */
public class SubtitleSentenceModel {
	private String value;
	private String language;
	
	public SubtitleSentenceModel(String value, String language) {
		this.value = value;
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
