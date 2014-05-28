package ua.ll7.slot7.abc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.ll7.slot7.abc.dao.ILetterDAO;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.service.ILetterService;

/**
 * @author Alex Velichko
 *         22.05.14 : 15:56
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class LetterService implements ILetterService {

	@Autowired
	private ILetterDAO letterDAO;

	@Override
	public void createLetter(Letter letter) {
		letterDAO.createLetter(letter);
	}

	@Override
	public void updateLetter(Letter letter) {
		letterDAO.updateLetter(letter);
	}

	@Override
	public void deleteLetter(Letter letter) {
		letterDAO.deleteLetter(letter);
	}

	@Override
	public void deleteLetterByChar(Character aChar) {
		letterDAO.deleteLetterByChar(aChar);
	}

	@Override
	public Letter findById(long letterId) {
		return letterDAO.findById(letterId);
	}

	@Override
	public Letter findByCharacter(Character character) {
		return letterDAO.findByCharacter(character);
	}

	@Override
	public boolean existByChar(Character aChar) {
		return findByCharacter(aChar)!=null;
	}
}
