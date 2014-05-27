package ua.ll7.slot7.abc.helper;

import org.springframework.stereotype.Component;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         22.05.14 : 16:23
 */
@Component
public interface ILetterHelper {
	public Letter getNewLetter(char aChar, String aDescription);

	public void addRealObject(Letter letter, RealObject realObject);

}
