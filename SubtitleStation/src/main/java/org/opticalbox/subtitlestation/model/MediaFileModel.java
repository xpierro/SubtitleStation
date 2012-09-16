package org.opticalbox.subtitlestation.model;

import java.io.File;

/**
 * Classe représentant un fichier média (lisible sur une durée et non textuel)
 * @author Pierre
 * TODO: préciser concept de média
 *
 */
public abstract class MediaFileModel {

	public abstract long getDuration();
	public abstract File open();
	public abstract void close();
}
