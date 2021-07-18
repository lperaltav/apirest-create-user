package cl.app.dao.impl;

import java.util.List;

import javax.persistence.Query;

import cl.app.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cl.app.model.User;
import cl.app.util.HibernateUtil;

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public User add(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean search(String correo) {
		boolean find = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("From User u where u.email = :correo");
			query.setParameter("correo", correo);

			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
//				encontrado
				find = true;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return find;

	}

}
