package org.opticalbox.subtitlestation.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de test décrivant les use cases possibles sur le modèle
 * @author Pierre
 *
 */
public class UseCasesModelTests {
	/**
	 * Crée un nouveau projet vierge
	 */
	@Test
	public void createNewBlankProject() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		Assert.assertEquals(0, subtitleFileModel.getLineCount());
	}
	
	/**
	 * Ajoute une ligne de sous-titre vide
	 */
	@Test
	public void addNewSubtitleLine() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine();
		Assert.assertEquals(1, subtitleFileModel.getLineCount());
		Assert.assertEquals(subtitleFileModel.getLastLine().getBegin(), subtitleFileModel.getLastLine().getEnd());
	}
	
	/**
	 * Ajoute une ligne de sous-titre vide après la dernière
	 */
	@Test
	public void appendNewSubtitleLine() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeStampModel(0, 0, 0, 0 ),  new SubtitleTimeStampModel(0, 1, 25, 521));
		subtitleFileModel.addLine(); // Automatiquement placé au timerange de la fin du précédent
		
		Assert.assertEquals(2, subtitleFileModel.getLineCount());
		Assert.assertEquals(subtitleFileModel.getLineAt(0).getEnd() ,
				 			subtitleFileModel.getLineAt(1).getBegin());
		Assert.assertTrue(subtitleFileModel.validate());
	}
	
	/**
	 * Edite une ligne de sous-titre
	 */
	@Test
	public void editSubtitleSentence() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeStampModel(0, 0, 0, 0 ),  new SubtitleTimeStampModel(0, 1, 25, 521));
		subtitleFileModel.addLine(); // Automatiquement placé au timerange de la fin du précédent
		subtitleFileModel.getLineAt(1).editSentence("fr", "hello");
		Assert.assertEquals("hello", subtitleFileModel.getLineAt(1).getSentenceValue("fr"));
	}
	
	/**
	 * Modifie le time range d'une ligne de sous-titre
	 */
	@Test
	public void changeBeginEndTimeRange() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeStampModel(0, 0, 0, 0 ),  new SubtitleTimeStampModel(0, 1, 25, 521));
		subtitleFileModel.addLine(); // Automatiquement placé au timerange de la fin du précédent
		subtitleFileModel.getLineAt(1).editSentence("fr", "hello");
		subtitleFileModel.getLineAt(1).setEnd(new SubtitleTimeStampModel(0, 1, 26, 254));
		Assert.assertTrue(subtitleFileModel.validate());
		Assert.assertEquals(new SubtitleTimeStampModel(0, 1, 26, 254), subtitleFileModel.getLineAt(1).getEnd());
	}
	
	/**
	 * Répare un fichier désordonné
	 */
	@Test
	public void repareFile() {
		SubtitleFileModel subtitleFileModel = new SubtitleFileModel();
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeStampModel(0, 1, 0, 0 ),  new SubtitleTimeStampModel(0, 1, 25, 521));
		subtitleFileModel.addLine().setTimeRange(new SubtitleTimeStampModel(0, 0, 0, 0 ),  new SubtitleTimeStampModel(0, 0, 25, 521));
		Assert.assertFalse(subtitleFileModel.validate());
		subtitleFileModel.repare();
		Assert.assertTrue(subtitleFileModel.validate());
	}

}
