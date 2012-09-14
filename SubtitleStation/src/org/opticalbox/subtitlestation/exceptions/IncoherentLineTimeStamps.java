package org.opticalbox.subtitlestation.exceptions;

/**
 * Rejet�e pour indiquer qu'une ligne est incoh�rente au niveau de son temps
 * (typiquement end < begin)
 * @author Pierre
 *
 */
public class IncoherentLineTimeStamps extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public IncoherentLineTimeStamps() {
		this("Incoherent unknow time stamp");
	}
	
	public IncoherentLineTimeStamps(String message) {
		super(message);
	}

}
