package org.opticalbox.subtitlestation.model;

import org.junit.Assert;
import org.junit.Test;
import org.opticalbox.subtitlestation.exceptions.IncoherentLineTimeStamps;

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
	
	/**
	 * Teste la comparaison de time stamp
	 */
	@Test
	public void subtitleTimeStampModelCompareTo1() {
		SubtitleTimeStampModel t1 = new SubtitleTimeStampModel(0, 1, 5, 0);
		SubtitleTimeStampModel t2 = new SubtitleTimeStampModel(0, 1, 5, 0);
		SubtitleTimeStampModel t3 = new SubtitleTimeStampModel(0, 1, 58, 0);
		SubtitleTimeStampModel t4 = new SubtitleTimeStampModel(5, 57, 57, 958);
		SubtitleTimeStampModel t5 = new SubtitleTimeStampModel(18, 10, 5, 584);
		
		Assert.assertEquals(0, t1.compareTo(t2));
		Assert.assertEquals(-1, t1.compareTo(t3));
		Assert.assertEquals(1, t3.compareTo(t1));
		Assert.assertEquals(-1, t4.compareTo(t5));
		Assert.assertEquals(0, t4.compareTo(t4));
		Assert.assertEquals(1, t5.compareTo(t1));
	}
	
	/**
	 * Teste la comparaison de time stamp (null arg)
	 */
	@Test(expected=NullPointerException.class)
	public void subtitleTimeStampModelCompareTo2() {
		new SubtitleTimeStampModel().compareTo(null);
	}
	
	/**
	 * Teste la conversion en milisecondes d'un timestamp
	 */
	@Test
	public void subtitleTimeStampModelGetMili1() {
		Assert.assertEquals(0, new SubtitleTimeStampModel().getMiliSecondStamp());
		Assert.assertEquals(21085255L, new SubtitleTimeStampModel(5, 51, 25, 255).getMiliSecondStamp());
	}
	
	/**
	 * Teste le calcul de durée
	 */
	@Test
	public void subtitleLineModelGetDuration1() {
		Assert.assertEquals(0, new SubtitleLineModel().getDuration());
		Assert.assertEquals(1000, new SubtitleLineModel(new SubtitleTimeStampModel(0, 0, 0, 0), new SubtitleTimeStampModel(0, 0, 1, 0)).getDuration());
		Assert.assertEquals(1542, new SubtitleLineModel(new SubtitleTimeStampModel(0, 0, 0, 0), new SubtitleTimeStampModel(0, 0, 1, 542)).getDuration());
		Assert.assertEquals(61542, new SubtitleLineModel(new SubtitleTimeStampModel(0, 0, 0, 0), new SubtitleTimeStampModel(0, 1, 1, 542)).getDuration());
		Assert.assertEquals(60542, new SubtitleLineModel(new SubtitleTimeStampModel(0, 0, 1, 0), new SubtitleTimeStampModel(0, 1, 1, 542)).getDuration());
	}
	
	/**
	 * Teste la création de time stamp incohérent
	 */
	@Test(expected=IllegalArgumentException.class)
	public void subtitleTimeStampConstructor1() {
		new SubtitleTimeStampModel(58, 78, 85, 5454);
	}
	
	/**
	 * Teste la création de ligne incohérente
	 */
	 @Test(expected=IncoherentLineTimeStamps.class)
	 public void subtitleLineModelConstructor1() {
		 new SubtitleLineModel(new SubtitleTimeStampModel(1, 0, 0, 0), new SubtitleTimeStampModel(0, 0, 0, 255));
	 }
	
}
