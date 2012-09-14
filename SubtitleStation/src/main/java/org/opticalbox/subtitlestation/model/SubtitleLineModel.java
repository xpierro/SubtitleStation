package org.opticalbox.subtitlestation.model;

import java.util.HashMap;
import java.util.Map;

import org.opticalbox.subtitlestation.exceptions.IncoherentLineTimeStamps;

/**
 * Représente une ligne de sous-titres
 * @author Pierre
 *
 */
public class SubtitleLineModel implements Comparable<SubtitleLineModel>{
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
		if (begin.compareTo(end) > 0) {
			throw new IncoherentLineTimeStamps(begin + ">" + end);
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
	 * Spécifie le début de la ligne
	 * @param begin Le timestamp du début
	 */
	public void setBegin(SubtitleTimeStampModel begin) {
		this.begin = begin;
	}
	
	/**
	 * Spécifie la fin de la ligne
	 * @param end Le timestamp de fin
	 */
	public void setEnd(SubtitleTimeStampModel end) {
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

	/**
	 * Concatène tous les cas possibles de croisement et classiques
	 *  1 [------>]  | [----->]
	 *  2   [--->]   |    [----->]
	 *    1 -> 2     |      1 -> 2
	 */
	@Override
	public int compareTo(SubtitleLineModel another) {
		if (another == null) {
			throw new NullPointerException("Can't compare Line with a null value");
		}
		
		if (begin.compareTo(end) > 0) {
			throw new IncoherentLineTimeStamps(begin + "<" + end);
		} else if (another.begin.compareTo(another.end) > 0) {
			throw new IncoherentLineTimeStamps(another.begin + "<" + another.end);
		}
		
		if (begin.compareTo(another.begin) < 0 ) {
			return -1;
		} else if (another.begin.compareTo(begin) < 0) {
			return 1;
		} else {
			return 0;
		}		
	}
	
	
	
	/**
	 * Donne la durée de la ligne en millisecondes
	 * @return La durée en millisecondes de la ligne
	 * TODO: faire 
	 */
	public long getDuration() {
		return end.getMiliSecondStamp() - begin.getMiliSecondStamp();
	}
}
