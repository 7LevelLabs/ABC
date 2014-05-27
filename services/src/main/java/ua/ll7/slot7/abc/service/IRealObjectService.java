package ua.ll7.slot7.abc.service;

import org.springframework.stereotype.Service;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         26.05.14 : 14:30
 */
@Service
public interface IRealObjectService {
	//CRUD
	public void createRealObject(RealObject realObject);

	public void updateRealObject(RealObject realObject);

	public void deleteRealObject(RealObject realObject);

	//find
	public RealObject findRealObjectById (long id);

	public RealObject findRealObjectByName (String name);

	public boolean existByName (String name);

	public boolean existById(long anId);
}
