package org.opticalbox.subtitlestation.model;

import java.util.LinkedList;
import java.util.List;

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
			subtitleLineModel.setTimeRange(new SubtitleTimeRangeModel(lastLineModel.getTimeRange().getEnd(), lastLineModel.getTimeRange().getEnd()));
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
}
