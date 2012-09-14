package org.opticalbox.subtitlestation.model;

import java.util.HashMap;
import java.util.Map;

import org.opticalbox.subtitlestation.exceptions.IncoherentLineTimeStamps;

/**
 * Repr�sente une ligne de sous-titres
 * @author Pierre
 *
 */
public class SubtitleLineModel implements Comparable<SubtitleLineModel>{
	private SubtitleTimeStampModel begin;
	private SubtitleTimeStampModel end;
	private Map<String, String> sentencesMap;
	
	/**
	 * Cr�e une nouvelle ligne
	 * @param timeRange Le time range � appliquer
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
	 * Cr�e une ligne vierge (time range sp�cifi�, aucune phrase)
	 * @param timeRange Le time range � appliquer
	 */
	public SubtitleLineModel(SubtitleTimeStampModel begin, SubtitleTimeStampModel end) {
		this(begin, end, new HashMap<String, String>());
	}

	/**
	 * Cr�e une nouvelle ligne vierge (time stamp � 0, aucune phrase)
	 */
	public SubtitleLineModel() {
		this(new SubtitleTimeStampModel(), new SubtitleTimeStampModel());
	}
	
	/**
	 * Renvoie le time range inf�rieur de la ligne
	 * @return Le time range inf�rieur appliqu� � la ligne
	 */
	public SubtitleTimeStampModel getBegin() {
		return begin;
	}
	

	/**
	 * Renvoie le time range sup�rieur de la ligne
	 * @return Le time range sup�rieur appliqu� � la ligne
	 */
	public SubtitleTimeStampModel getEnd() {
		return end;
	}

	/**
	 * Sp�cifie le time range de la ligne
	 * @param timeRange Le time range appliqu� � la ligne
	 */
	public void setTimeRange(SubtitleTimeStampModel begin, SubtitleTimeStampModel end) {
		this.begin = begin;
		this.end = end;
	}
	
	/**
	 * Sp�cifie le d�but de la ligne
	 * @param begin Le timestamp du d�but
	 */
	public void setBegin(SubtitleTimeStampModel begin) {
		this.begin = begin;
	}
	
	/**
	 * Sp�cifie la fin de la ligne
	 * @param end Le timestamp de fin
	 */
	public void setEnd(SubtitleTimeStampModel end) {
		this.end = end;
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

	/**
	 * Concat�ne tous les cas possibles de croisement et classiques
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
	 * Donne la dur�e de la ligne en millisecondes
	 * @return La dur�e en millisecondes de la ligne
	 * TODO: faire 
	 */
	public long getDuration() {
		return end.getMiliSecondStamp() - begin.getMiliSecondStamp();
	}
}
