package ua.ll7.slot7.abc.helper.impl;

import org.springframework.stereotype.Component;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

import java.util.LinkedList;

/**
 * @author Alex Velichko
 *         22.05.14 : 16:25
 */
@Component
public class LetterHelper implements ILetterHelper {

	@Override
	public Letter getNewLetter(char aChar, String aDescription) {
		Letter letter = new Letter(aChar,aDescription);

		letter.setRealObjects(new LinkedList<RealObject>());

		return letter;
	}

	@Override
	public void addRealObject(Letter letter, RealObject realObject) {
		letter.getRealObjects().add(realObject);
	}
}
