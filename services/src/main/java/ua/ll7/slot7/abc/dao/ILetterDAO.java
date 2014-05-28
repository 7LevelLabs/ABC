package ua.ll7.slot7.abc.dao;

import ua.ll7.slot7.abc.model.letter.Letter;

/**
 * @author Alex Velichko
 *         22.05.14 : 15:34
 */
public interface ILetterDAO {
	//CRUD
	public void createLetter(Letter letter);

	public void updateLetter(Letter letter);

	public void deleteLetter(Letter letter);

	//find
	public Letter findById(long letterId);

	public Letter findByCharacter(Character character);

	public void deleteLetterByChar(Character aChar);
}
