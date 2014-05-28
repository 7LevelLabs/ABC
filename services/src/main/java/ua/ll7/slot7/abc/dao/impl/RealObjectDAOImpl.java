package ua.ll7.slot7.abc.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.ll7.slot7.abc.dao.IRealObjectDAO;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         26.05.14 : 14:24
 */
@Repository
public class RealObjectDAOImpl implements IRealObjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createRealObject(RealObject realObject) {
		Session session = sessionFactory.getCurrentSession();
		session.save(realObject);
	}

	@Override
	public void updateRealObject(RealObject realObject) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(realObject);
	}

	@Override
	public void deleteRealObject(RealObject realObject) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(realObject);
	}

	@Override
	public RealObject findRealObjectById(long id) {
		RealObject realObject = null;

		Session session = sessionFactory.getCurrentSession();

		String selectString =
			"select realObject " +
				"from RealObject as realObject " +
				"where realObject.id like :id";

		Query query = session.createQuery(selectString);
		query.setParameter("id", id);

		realObject = (RealObject) query.uniqueResult();

		return realObject;
	}

	@Override
	public RealObject findRealObjectByName(String name) {
		RealObject realObject = null;

		Session session = sessionFactory.getCurrentSession();

		String selectString =
			"select realObject " +
				"from RealObject as realObject " +
				"where realObject.name like :name";

		Query query = session.createQuery(selectString);
		query.setParameter("name", name);

		realObject = (RealObject) query.uniqueResult();

		return realObject;
	}
}
