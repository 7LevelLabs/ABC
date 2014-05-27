package ua.ll7.slot7.abc.model.realobject;

import org.springframework.stereotype.Repository;

/**
 * @author Alex Velichko
 *         26.05.14 : 14:20
 */
@Repository
public interface IRealObjectDAO {
	//CRUD
	public void createRealObject(RealObject realObject);

	public void updateRealObject(RealObject realObject);

	public void deleteRealObject(RealObject realObject);

	//find
	public RealObject findRealObjectById (long id);

	public RealObject findRealObjectByName (String name);
}
