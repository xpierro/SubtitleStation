package org.opticalbox.subtitlestation.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.opticalbox.subtitlestation.exceptions.IncoherentLineTimeStamps;

/**
 * Classe principale du mod�le: repr�sente un fichier de sous-titres
 * @author Pierre
 *
 */
public class SubtitleFileModel {
	private List<SubtitleLineModel> lines;
	
	/**
	 * Cr�e un nouveau mod�le de fichier � partir d'une s�rie de lignes
	 * @param lines S�rie de lignes � ajouter
	 */
	public SubtitleFileModel(List<SubtitleLineModel> lines) {
		this.lines = new LinkedList<SubtitleLineModel>();
		this.lines.addAll(lines);
	}

	/**
	 * Cr�e un nouveau mod�le de fichier � partir d'une s�rie de lignes vide
	 */
	public SubtitleFileModel() {
		this(new LinkedList<SubtitleLineModel>());
	}
	
	/**
	 * Retourne l'ensemble des lignes du fichier.
	 * @return La liste des lignes du fichier
	 */
	public List<SubtitleLineModel> getLines() {
		return lines;
	}

	/**
	 * Remplace la liste de line
	 * @param lines La nouvelle s�rie de lignes du fichier
	 */
	public void setLines(List<SubtitleLineModel> lines) {
		this.lines = new LinkedList<SubtitleLineModel>();
		this.lines.addAll(lines);
	}
	
	/**
	 * Ajoute une nouvelle ligne � la fin du fichier
	 * @param line La ligne � ajouter � la fin du fichier
	 * @return La ligne ajout�e
	 */
	public SubtitleLineModel addLine(SubtitleLineModel line) {
		lines.add(line);
		return getLastLine();
	}
	
	/**
	 * Ajoute une ligne vierge � la fin du fichier
	 * Si une ligne est pr�sente avant, le time range de la nouvelle ligne sera automatiquement plac� � la fin de l'ancienne.
	 * @return La ligne ajout�e
	 */
	public SubtitleLineModel addLine() {
		SubtitleLineModel subtitleLineModel = new SubtitleLineModel();
		if (lines.size() > 0) {
			SubtitleLineModel lastLineModel = getLastLine();
			subtitleLineModel.setTimeRange(lastLineModel.getEnd(), lastLineModel.getEnd());
		}
		return addLine(subtitleLineModel);
	}
	
	/**
	 * Renvoie la derni�re ligne du fichier
	 * @return La derni�re ligne du fichier
	 */
	public SubtitleLineModel getLastLine() {
		return lines.get(lines.size() - 1);
	}
	
	/**
	 * Renvoie la ligne du fichier plac�e � l'index sp�cifi�e
	 * @param index La position de la ligne dans le fichier
	 * @return La ligne plac�e � l'index fourni en argument
	 */
	public SubtitleLineModel getLineAt(int index) {
		return lines.get(index);
	}
	
	/**
	 * Renvoie le nombre de lignes du fichier
	 * @return Le nombre de lignes du fichier
	 */
	public int getLineCount() {
		return lines.size();
	}
	
	/**
	 * Valide que le fichier de sous-titre soit bien s�quentiellement constitu�
	 * @return true si chaque ligne est � un range inf�rieur � la ligne suivant, false sinon
	 * TODO: �valuer la n�cessit� d'une telle validation: on pourrait r�ordonner les lignes
	 */
	public boolean validate() {
		SubtitleLineModel previous = new SubtitleLineModel();
		for (SubtitleLineModel line : lines) {
			try {
				if (previous.compareTo(line) > 0) {
					return false;	
				}
			} catch (IncoherentLineTimeStamps e) {
				return false;
			}
			previous = line;
		}
		return true;
	}
	
	/**
	 * R�ordonne toutes les lignes du fichier pour le rentre � nouveau
	 * valide
	 * @return true si la validation fonction, false sinon
	 */
	public boolean repare() {
		Set<SubtitleLineModel> sortedLines = new TreeSet<SubtitleLineModel>(lines);
		lines.clear();
		lines.addAll(sortedLines);
		return validate();
	}
}
