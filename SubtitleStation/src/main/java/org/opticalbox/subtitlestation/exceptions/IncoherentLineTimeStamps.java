package org.opticalbox.subtitlestation.exceptions;

/**
 * Rejetée pour indiquer qu'une ligne est incohérente au niveau de son temps
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
