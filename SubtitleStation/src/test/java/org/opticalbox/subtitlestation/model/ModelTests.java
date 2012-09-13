package org.opticalbox.subtitlestation.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de test décrivant les use cases possibles sur le modèle
 * @author Pierre
 *
 */
public class ModelTests {
	/**
	 * Crée un nouveau projet vierge
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
	 * Ajoute une ligne de sous-titre vide après la dernière
	 */
	@Test
	public void appendNewSubtitleLine() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeRangeModel(new SubtitleTimeStampModel(0, 0, 0, 0 ),  new SubtitleTimeStampModel(0, 1, 25, 521)));
		subtitleFileModel.addLine(); // Automatiquement placé au timerange de la fin du précédent
		
		Assert.assertEquals(subtitleFileModel.getLineCount(), 2);
		Assert.assertEquals(subtitleFileModel.getLineAt(0).getTimeRange().getEnd() ,
				 			subtitleFileModel.getLineAt(1).getTimeRange().getBegin());
		Assert.assertTrue(subtitleFileModel.validate());
	}

}
