package org.opticalbox.subtitlestation.model;

import org.junit.Test;

/**
 * Ensemble de tests aux limites du modèle.
 * @author Pierre
 *
 */
public class LimitsModelTests {
    
	@Test(expected=NullPointerException.class)
	public void subtitleFileModelConstructor1() {
		new SubtitleLineModel(null); 
	}
	
}
