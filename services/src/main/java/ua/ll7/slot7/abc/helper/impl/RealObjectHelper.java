package ua.ll7.slot7.abc.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.helper.IRealObjectHelper;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         26.05.14 : 14:13
 */
@Component
public class RealObjectHelper implements IRealObjectHelper {

	@Autowired
	private ILetterHelper letterHelper;

	@Override
	public RealObject getNewRealObject(Letter letter, String name, String description) {
		RealObject realObject = new RealObject(letter, name, description);

		letterHelper.addRealObject(letter, realObject);

		return realObject;
	}

	@Override
	public void deleteRealObject(Letter letter, RealObject realObject) {
		letter.getRealObjects().remove(realObject);
	}

	@Override
	public void updateRealObject(RealObject realObjectContainer, RealObject realObject) {
		if (realObjectContainer.getName() != null) {
			realObject.setName(realObjectContainer.getName());
		}
		if (realObjectContainer.getDescription() != null) {
			realObject.setDescription(realObjectContainer.getDescription());
		}
	}
}
