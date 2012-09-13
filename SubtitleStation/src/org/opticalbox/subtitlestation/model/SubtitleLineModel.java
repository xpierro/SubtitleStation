package org.opticalbox.subtitlestation.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repr�sente une ligne de sous-titres
 * @author Pierre
 *
 */
public class SubtitleLineModel {
	private SubtitleTimeRangeModel timeRange;
	private Map<String, String> sentencesMap;
	
	/**
	 * Cr�e une nouvelle ligne
	 * @param timeRange Le time range � appliquer
	 * @param sentences Les phrases de la ligne
	 */
	public SubtitleLineModel(SubtitleTimeRangeModel timeRange, Map<String, String> sentences) {
		if (timeRange == null) {
			throw new NullPointerException("Le time range est nul");
		}
		if (sentences == null) {
			throw new NullPointerException("La map de phrases est nulles");
		}
		
		this.sentencesMap = sentences;
		this.timeRange = timeRange;
	}
	
	/**
	 * Cr�e une ligne vierge (time range sp�cifi�, aucune phrase)
	 * @param timeRange Le time range � appliquer
	 */
	public SubtitleLineModel(SubtitleTimeRangeModel timeRange) {
		this(timeRange, new HashMap<String, String>());
	}

	/**
	 * Cr�e une nouvelle ligne vierge (time stamp � 0, aucune phrase)
	 */
	public SubtitleLineModel() {
		this(new SubtitleTimeRangeModel(new SubtitleTimeStampModel(), new SubtitleTimeStampModel()));
	}
	
	/**
	 * Renvoie le time range de la ligne
	 * @return Le time range appliqu� � la ligne
	 */
	public SubtitleTimeRangeModel getTimeRange() {
		return timeRange;
	}

	/**
	 * Sp�cifie le time range de la ligne
	 * @param timeRange Le time range appliqu� � la ligne
	 */
	public void setTimeRange(SubtitleTimeRangeModel timeRange) {
		this.timeRange = timeRange;
	}

	/**
	 * Donne acc�s � toutes les phrases de la ligne
	 * @return La liste des phrases de la ligne
	 */
	public Map<String, String> getSentences() {
		return sentencesMap;
	}

	/**
	 * Ins�re les phrases 
	 * @param sentences L'ensemble des phrases � mettre dans la ligne (remplace tout le pr�c�dent)
	 */
	public void setSentences(Map<String, String> sentences) {
		this.sentencesMap = sentences;
	}
	
	/**
	 * Ajoute un language
	 * @param sentence La nouvelle phrase � ajouter
	 * @param language Le language de la phrase
	 */
	public void addSentences(String language, String sentence) {
		sentencesMap.put(language, sentence);
	}
	
	/**
	 * Edite la phrase du language fourni.
	 * @param language Le language que l'on veut �diter
	 * @param text Le nouveau texte
	 */
	public void editSentence(String language, String text) {
		sentencesMap.put(language, text);
	}
	
	/**
	 * Retourne le texte de la phrase en langue fournie
	 * @param language La langue dont on veut la phrase dans la ligne
	 * @return Le texte associ� � la langue fournie dans la ligne.
	 */
	public String getSentenceValue(String language) {
		return sentencesMap.get(language);
	}
}
