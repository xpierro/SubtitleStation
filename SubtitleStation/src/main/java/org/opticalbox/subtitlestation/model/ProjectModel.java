package org.opticalbox.subtitlestation.model;

/**
 * Un projet est la réunion d'un fichier de sous titre, d'un fichier video et d'un fichier sonore.
 * Fichier video et sonores peuvent être identiques
 * @author Pierre
 *
 */
public class ProjectModel {
	private MediaFileModel video;
	private MediaFileModel sound;
	private SubtitleFileModel subtitle;
	private String name;
	
	public ProjectModel(MediaFileModel video, MediaFileModel sound, SubtitleFileModel subtitle) {
		this.video = video;
		this.sound = sound;
		this.subtitle = subtitle;
	}
	
	public ProjectModel(MediaFileModel movie) {
		
	}

}
