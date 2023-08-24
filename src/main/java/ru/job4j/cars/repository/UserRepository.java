package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.cars.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    //    private final SessionFactory sf;
    private final CrudRepository crudRepository;

    /**
     * Sohranit' v baze.
     *
     * @param user pol'zovatel'.
     * @return pol'zovatel' s id.
     */
    public User create(User user) {
//        Session session = sf.openSession();
//        try {
//            session.beginTransaction();
//            session.save(user);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return user;
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    /**
     * Obnovit' v baze pol'zovatelja.
     *
     * @param user pol'zovatel'.
     */
    public void update(User user) {
//        Session session = sf.openSession();
//        try {
//            session.beginTransaction();
//            session.createQuery(
//                            "UPDATE User SET password = :fPassword WHERE id = :fId")
//                    .setParameter("fPassword", user.getPassword())
//                    .setParameter("fId", user.getId())
//                    .executeUpdate();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * Udalit' pol'zovatelja po id.
     *
     * @param userId ID.
     */
    public void delete(int userId) {
//    public void delete(int id) {
//        Session session = sf.openSession();
//        try {
//            session.beginTransaction();
//            session.createQuery(
//                            "DELETE User WHERE id = :fId")
//                    .setParameter("fId", id)
//                    .executeUpdate();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
    }

    /**
     * Spisok pol'zovatelej otsortirovannyh po vozrastaniju ID.
     *
     * @return spisok pol'zovatelej.
     */
    public List<User> findAllOrderById() {
//        Session session = sf.openSession();
//        List<User> result = Collections.emptyList();
//        try {
//            session.beginTransaction();
//            result = session.createQuery("FROM User ORDER BY id ASC", User.class).list();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return result;
        return crudRepository.query("from User order by id asc", User.class);
    }

    /**
     * Najti pol'zovatelja po ID.
     *
     * @return pol'zovatel'.
     */
    public Optional<User> findById(int userId) {
//    public Optional<User> findById(int id) {
//        Session session = sf.openSession();
//        Optional<User> result = Optional.empty();
//        try {
//            session.beginTransaction();
//            result = session.createQuery(
//                            "FROM User WHERE id = :fId", User.class)
//                    .setParameter("fId", id)
//                    .uniqueResultOptional();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return result;
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Spisok pol'zovatelej po login LIKE %key%
     *
     * @param key kljuch poiska.
     * @return spisok pol'zovatelej.
     */
    public List<User> findByLikeLogin(String key) {
//        Session session = sf.openSession();
//        List<User> result = Collections.emptyList();
//        try {
//            session.beginTransaction();
//            result = session.createQuery(
//                            "FROM User WHERE login LIKE :fKey", User.class)
//                    .setParameter("fKey", "%" + key + "%")
//                    .list();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return result;
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Najti pol'zovatelja po login.
     *
     * @param login login
     * @return Optional of user.
     */
    public Optional<User> findByLogin(String login) {
//        Session session = sf.openSession();
//        Optional<User> result = Optional.empty();
//        try {
//            session.beginTransaction();
//            var query = session.createQuery(
//                    "FROM User WHERE login = :fLogin", User.class);
//            query.setParameter("fLogin", login);
//            result = query.uniqueResultOptional();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return result;
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }
}