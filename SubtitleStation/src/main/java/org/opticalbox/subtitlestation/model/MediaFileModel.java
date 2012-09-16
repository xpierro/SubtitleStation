package org.opticalbox.subtitlestation.model;

import java.io.File;

/**
 * Classe repr�sentant un fichier m�dia (lisible sur une dur�e et non textuel)
 * @author Pierre
 * TODO: pr�ciser concept de m�dia
 *
 */
public abstract class MediaFileModel {

	public abstract long getDuration();
	public abstract File open();
	public abstract void close();
}
