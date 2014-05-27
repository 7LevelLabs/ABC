package ua.ll7.slot7.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.ll7.slot7.abc.model.realobject.IRealObjectDAO;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         26.05.14 : 14:31
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class RealObjectService implements IRealObjectService {

	@Autowired
	private IRealObjectDAO realObjectDAO;

	@Override
	public void createRealObject(RealObject realObject) {
		realObjectDAO.createRealObject(realObject);
	}

	@Override
	public void updateRealObject(RealObject realObject) {
		realObjectDAO.updateRealObject(realObject);
	}

	@Override
	public void deleteRealObject(RealObject realObject) {
		realObjectDAO.deleteRealObject(realObject);
	}

	@Override
	public RealObject findRealObjectById(long id) {
		return realObjectDAO.findRealObjectById(id);
	}

	@Override
	public RealObject findRealObjectByName(String name) {
		return realObjectDAO.findRealObjectByName(name);
	}

	@Override
	public boolean existByName(String name) {
		return findRealObjectByName(name) != null;
	}

	@Override
	public boolean existById(long anId) {
		return findRealObjectById(anId)!=null;
	}
}
