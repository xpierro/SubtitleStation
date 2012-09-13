package org.opticalbox.subtitlestation.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de test d�crivant les use cases possibles sur le mod�le
 * @author Pierre
 *
 */
public class ModelTests {
	/**
	 * Cr�e un nouveau projet vierge
	 */
	@Test
	public void createNewBlankProject() {
		@SuppressWarnings("unused")
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
	}
	
	/**
	 * Ajoute une ligne de sous-titre vide
	 */
	@Test
	public void addNewSubtitleLine() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine();
	}
	
	/**
	 * Ajoute une ligne de sous-titre vide apr�s la derni�re
	 */
	@Test
	public void appendNewSubtitleLine() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeRangeModel(new SubtitleTimeStampModel(0, 0, 0, 0 ),  new SubtitleTimeStampModel(0, 1, 25, 521)));
		subtitleFileModel.addLine(); // Automatiquement plac� au timerange de la fin du pr�c�dent
		
		Assert.assertEquals(subtitleFileModel.getLineCount(), 2);
		Assert.assertEquals(subtitleFileModel.getLineAt(0).getTimeRange().getEnd() ,
				 			subtitleFileModel.getLineAt(1).getTimeRange().getBegin());
		Assert.assertTrue(subtitleFileModel.validate());
	}

}
