package ua.ll7.slot7.abc.service;

import org.springframework.stereotype.Component;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         27.05.14 : 16:08
 */
@Component
public interface IBLService {

	//CRUD-like

	//Letter

	public Letter createAndPersistLetter(Character aChar, String description);

	public Letter getLetterByRealObjectName(String realObjectName);

	//RealObject

	public RealObject createAndPersistRealObject(Character letterChar, String name, String Description);

	public void deleteRealObjectById(long anId);

	public void deleteRealObjectByName(String realObjectName);

}
