package org.opticalbox.subtitlestation.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Ensemble de tests aux limites du modèle.
 * @author Pierre
 *
 */
public class LimitsModelTests {
    
	/**
	 * Créé un nouveau fichier de sous-titres
	 */
	@Test(expected=NullPointerException.class)
	public void subtitleFileModelConstructor1() {
		new SubtitleLineModel(null, null); 
	}
	
	/**
	 * Addtionne deux time stamp
	 */
	@Test
	public void subtitleTimeStampModelAdd1() {
		SubtitleTimeStampModel t1 = new SubtitleTimeStampModel(0, 1, 5, 0);
		SubtitleTimeStampModel t2 = new SubtitleTimeStampModel(0, 1, 5, 0);
		Assert.assertEquals(new SubtitleTimeStampModel(0, 2, 10, 0), t1.add(t2));
		
		SubtitleTimeStampModel t3 = new SubtitleTimeStampModel(0, 1, 58, 0);
		Assert.assertEquals(new SubtitleTimeStampModel(0, 3, 3, 0), t2.add(t3));
		
		SubtitleTimeStampModel t4 = new SubtitleTimeStampModel(5, 57, 57, 958);
		SubtitleTimeStampModel t5 = new SubtitleTimeStampModel(18, 10, 5, 584);
		Assert.assertEquals(new SubtitleTimeStampModel(24, 8, 3, 542), t4.add(t5));
	}
	
}
