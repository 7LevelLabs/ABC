package ua.ll7.slot7.abc.helper;

import org.springframework.stereotype.Component;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         26.05.14 : 14:12
 */
@Component
public interface IRealObjectHelper {
	public RealObject getNewRealObject(Letter letter, String name, String description);

	public void deleteRealObject(Letter letter, RealObject realObject);
}
