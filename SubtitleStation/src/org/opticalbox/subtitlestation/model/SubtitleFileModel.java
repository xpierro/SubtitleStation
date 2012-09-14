package org.opticalbox.subtitlestation.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.opticalbox.subtitlestation.exceptions.IncoherentLineTimeStamps;

/**
 * Classe principale du modèle: représente un fichier de sous-titres
 * @author Pierre
 *
 */
public class SubtitleFileModel {
	private List<SubtitleLineModel> lines;
	
	/**
	 * Crée un nouveau modèle de fichier à partir d'une série de lignes
	 * @param lines Série de lignes à ajouter
	 */
	public SubtitleFileModel(List<SubtitleLineModel> lines) {
		this.lines = new LinkedList<SubtitleLineModel>();
		this.lines.addAll(lines);
	}

	/**
	 * Crée un nouveau modèle de fichier à partir d'une série de lignes vide
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
	 * @param lines La nouvelle série de lignes du fichier
	 */
	public void setLines(List<SubtitleLineModel> lines) {
		this.lines = new LinkedList<SubtitleLineModel>();
		this.lines.addAll(lines);
	}
	
	/**
	 * Ajoute une nouvelle ligne à la fin du fichier
	 * @param line La ligne à ajouter à la fin du fichier
	 * @return La ligne ajoutée
	 */
	public SubtitleLineModel addLine(SubtitleLineModel line) {
		lines.add(line);
		return getLastLine();
	}
	
	/**
	 * Ajoute une ligne vierge à la fin du fichier
	 * Si une ligne est présente avant, le time range de la nouvelle ligne sera automatiquement placé à la fin de l'ancienne.
	 * @return La ligne ajoutée
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
	 * Renvoie la dernière ligne du fichier
	 * @return La dernière ligne du fichier
	 */
	public SubtitleLineModel getLastLine() {
		return lines.get(lines.size() - 1);
	}
	
	/**
	 * Renvoie la ligne du fichier placée à l'index spécifiée
	 * @param index La position de la ligne dans le fichier
	 * @return La ligne placée à l'index fourni en argument
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
	 * Valide que le fichier de sous-titre soit bien séquentiellement constitué
	 * @return true si chaque ligne est à un range inférieur à la ligne suivant, false sinon
	 * TODO: évaluer la nécessité d'une telle validation: on pourrait réordonner les lignes
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
	 * Réordonne toutes les lignes du fichier pour le rentre à nouveau
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
