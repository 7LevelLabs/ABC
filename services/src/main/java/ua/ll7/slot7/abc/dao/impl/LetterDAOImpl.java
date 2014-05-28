package ua.ll7.slot7.abc.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.ll7.slot7.abc.dao.ILetterDAO;
import ua.ll7.slot7.abc.model.letter.Letter;

/**
 * @author Alex Velichko
 *         22.05.14 : 15:36
 */
@Repository
public class LetterDAOImpl implements ILetterDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createLetter(Letter letter) {
		Session session = sessionFactory.getCurrentSession();
		session.save(letter);
	}

	@Override
	public void updateLetter(Letter letter) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(letter);
	}

	@Override
	public void deleteLetter(Letter letter) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(letter);
	}

	@Override
	public Letter findById(long letterId) {
		Letter letter = null;

		Session session = sessionFactory.getCurrentSession();

		String selectString =
			"select letter " +
				"from Letter as letter " +
				"where letter.id like :id";

		Query query = session.createQuery(selectString);
		query.setParameter("id", letterId);

		letter = (Letter) query.uniqueResult();

		return letter;
	}

	@Override
	public Letter findByCharacter(Character character) {
		Letter letter = null;

		Session session = sessionFactory.getCurrentSession();

		String selectString =
			"select letter " +
				"from Letter as letter " +
				"where letter.aChar like :ch";

		Query query = session.createQuery(selectString);
		query.setParameter("ch", character);

		letter = (Letter) query.uniqueResult();

		return letter;
	}

	@Override
	public void deleteLetterByChar(Character aChar) {
		Letter letter = null;

		Session session = sessionFactory.getCurrentSession();

		String deleteString =
			"delete Letter " +
				"where aChar like :ch";

		Query query = session.createQuery(deleteString);
		query.setParameter("ch", aChar);

		query.executeUpdate();
	}
}
