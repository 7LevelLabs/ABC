package ua.ll7.slot7.abc.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.model.letter.Letter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springConfigIT.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class LetterServiceIT extends Assert {

	@Autowired
	private ILetterService letterService;

	@Autowired
	private ILetterHelper letterHelper;

	@Test
	public void testCreateFindCharLetter() throws Exception {
		char aChar = 'A';
		String aLetterDescription = "Letter A";
		Letter letter = letterHelper.getNewLetter(aChar, aLetterDescription);

		letterService.createLetter(letter);

		System.out.println(letter);

		Letter letterRead = letterService.findByCharacter(aChar);

		assertEquals(letter,letterRead);
	}

	@Test
	public void testUpdateLetter() throws Exception {
		char aChar = 'A';
		String aLetterDescription = "Letter A";
		Letter letter = letterHelper.getNewLetter(aChar, aLetterDescription);

		letterService.createLetter(letter);

		letter.setDescription("Letter A - new description");

		letterService.updateLetter(letter);

		Letter letterRead = letterService.findByCharacter(aChar);

		assertEquals(letterRead.getDescription(),"Letter A - new description");
	}

	@Test
	public void testDeleteLetter() throws Exception {
		char aChar = 'A';
		String aLetterDescription = "Letter A";
		Letter letter = letterHelper.getNewLetter(aChar, aLetterDescription);

		letterService.createLetter(letter);

		letterService.deleteLetter(letter);

		Letter letterRead = letterService.findByCharacter(aChar);

		assertTrue(letterRead==null);
	}

}