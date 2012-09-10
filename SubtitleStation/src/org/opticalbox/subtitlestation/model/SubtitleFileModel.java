package org.opticalbox.subtitlestation.model;

import java.util.List;

/**
 * Classe principale du modèle: représente un fichier de sous-titres
 * @author Pierre
 *
 */
public class SubtitleFileModel {
	private List<SubtitleLineModel> lines;
	
	public SubtitleFileModel(List<SubtitleLineModel> lines) {
		this.lines.addAll(lines);
	}

	public List<SubtitleLineModel> getLines() {
		return lines;
	}

	public void setLines(List<SubtitleLineModel> lines) {
		this.lines = lines;
	}
	
	
}
