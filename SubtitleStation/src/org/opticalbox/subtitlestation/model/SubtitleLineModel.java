package org.opticalbox.subtitlestation.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente une ligne de sous-titres
 * @author Pierre
 *
 */
public class SubtitleLineModel {
	private SubtitleTimeStampModel begin;
	private SubtitleTimeStampModel end;
	private Map<String, String> sentencesMap;
	
	/**
	 * Crée une nouvelle ligne
	 * @param timeRange Le time range à appliquer
	 * @param sentences Les phrases de la ligne
	 */
	public SubtitleLineModel(SubtitleTimeStampModel begin, SubtitleTimeStampModel end, Map<String, String> sentences) {
		if (begin == null || end == null) {
			throw new NullPointerException("Le time range est nul");
		}
		if (sentences == null) {
			throw new NullPointerException("La map de phrases est nulles");
		}
		
		this.sentencesMap = sentences;
		this.end = end;
		this.begin = begin;
	}
	
	/**
	 * Crée une ligne vierge (time range spécifié, aucune phrase)
	 * @param timeRange Le time range à appliquer
	 */
	public SubtitleLineModel(SubtitleTimeStampModel begin, SubtitleTimeStampModel end) {
		this(begin, end, new HashMap<String, String>());
	}

	/**
	 * Crée une nouvelle ligne vierge (time stamp à 0, aucune phrase)
	 */
	public SubtitleLineModel() {
		this(new SubtitleTimeStampModel(), new SubtitleTimeStampModel());
	}
	
	/**
	 * Renvoie le time range inférieur de la ligne
	 * @return Le time range inférieur appliqué à la ligne
	 */
	public SubtitleTimeStampModel getBegin() {
		return begin;
	}
	

	/**
	 * Renvoie le time range supérieur de la ligne
	 * @return Le time range supérieur appliqué à la ligne
	 */
	public SubtitleTimeStampModel getEnd() {
		return end;
	}

	/**
	 * Spécifie le time range de la ligne
	 * @param timeRange Le time range appliqué à la ligne
	 */
	public void setTimeRange(SubtitleTimeStampModel begin, SubtitleTimeStampModel end) {
		this.begin = begin;
		this.end = end;
	}

	/**
	 * Donne accès à toutes les phrases de la ligne
	 * @return La liste des phrases de la ligne
	 */
	public Map<String, String> getSentences() {
		return sentencesMap;
	}

	/**
	 * Insère les phrases 
	 * @param sentences L'ensemble des phrases à mettre dans la ligne (remplace tout le précédent)
	 */
	public void setSentences(Map<String, String> sentences) {
		this.sentencesMap = sentences;
	}
	
	/**
	 * Ajoute un language
	 * @param sentence La nouvelle phrase à ajouter
	 * @param language Le language de la phrase
	 */
	public void addSentences(String language, String sentence) {
		sentencesMap.put(language, sentence);
	}
	
	/**
	 * Edite la phrase du language fourni.
	 * @param language Le language que l'on veut éditer
	 * @param text Le nouveau texte
	 */
	public void editSentence(String language, String text) {
		sentencesMap.put(language, text);
	}
	
	/**
	 * Retourne le texte de la phrase en langue fournie
	 * @param language La langue dont on veut la phrase dans la ligne
	 * @return Le texte associé à la langue fournie dans la ligne.
	 */
	public String getSentenceValue(String language) {
		return sentencesMap.get(language);
	}
}
