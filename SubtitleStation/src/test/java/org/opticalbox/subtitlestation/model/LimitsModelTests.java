package org.opticalbox.subtitlestation.model;

import org.junit.Test;

/**
 * Ensemble de tests aux limites du mod�le.
 * @author Pierre
 *
 */
public class LimitsModelTests {
    
	@Test(expected=NullPointerException.class)
	public void subtitleFileModelConstructor1() {
		new SubtitleLineModel(null); 
	}
	
}
