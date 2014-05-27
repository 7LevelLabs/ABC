package ua.ll7.slot7.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.helper.IRealObjectHelper;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         27.05.14 : 16:09
 */
@Component
public class BLServiceImpl implements IBLService {

	@Autowired
	private ILetterService letterService;

	@Autowired
	private IRealObjectService realObjectService;

	@Autowired
	private ILetterHelper letterHelper;

	@Autowired
	private IRealObjectHelper realObjectHelper;

	@Override
	public Letter createAndPersistLetter(Character aChar, String description) throws IllegalArgumentException {

		if (letterService.existByChar(aChar)) {
			throw new IllegalArgumentException(aChar + " : already exists");
		}

		Letter letter = letterHelper.getNewLetter(aChar, description);
		letterService.createLetter(letter);

		return letter;
	}

	@Override
	public Letter getLetterByRealObjectName(String realObjectName) {
		RealObject realObject = realObjectService.findRealObjectByName(realObjectName);
		Letter letter = letterService.findById(realObject.getId());

		return letter;
	}

	@Override
	public RealObject createAndPersistRealObject(Character letterChar, String name, String description) throws IllegalArgumentException {

		if (!letterService.existByChar(letterChar)) {
			throw new IllegalArgumentException("No such letter : "+letterChar);
		}

		if (realObjectService.existByName(name)) {
			throw new IllegalArgumentException(name + " : already exists");
		}

		Letter letter = letterService.findByCharacter(letterChar);
		RealObject realObject = realObjectHelper.getNewRealObject(letter,name,description);
		realObjectService.createRealObject(realObject);

		return realObject;
	}

	@Override
	public void deleteRealObjectById(long anId) {
		RealObject realObject = realObjectService.findRealObjectById(anId);
		deleteRealObjectByName(realObject.getName());
	}

	@Override
	public void deleteRealObjectByName(String realObjectName) {
		if (realObjectService.existByName(realObjectName)) {
			Letter letter = getLetterByRealObjectName(realObjectName);
			RealObject realObject = realObjectService.findRealObjectByName(realObjectName);

			realObjectHelper.deleteRealObject(letter,realObject);
			realObjectService.deleteRealObject(realObject);

			letterService.updateLetter(letter);
		}
	}
}
