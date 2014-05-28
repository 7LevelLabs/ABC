package ua.ll7.slot7.abc.service;

import org.springframework.stereotype.Service;
import ua.ll7.slot7.abc.model.letter.Letter;

import java.util.List;

/**
 * @author Alex Velichko
 *         22.05.14 : 15:45
 */
@Service
public interface ILetterService {
	//CRUD
	public void createLetter(Letter letter);

	public void updateLetter(Letter letter);

	public void deleteLetter(Letter letter);

	public void deleteLetterByChar(Character aChar);

	//find
	public Letter findById(long letterId);

	public Letter findByCharacter(Character character);

	public boolean existById(long anId);

	public boolean existByChar(Character aChar);

	public List<Letter> getAll();
}
