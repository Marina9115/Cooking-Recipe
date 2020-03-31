package cookingrecipe.cookingrecipe.dao;


//
//import org.hibernate.SessionFactory;
//import org.hibernate.query.NativeQuery;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import cookingrecipe.cookingrecipe.model.User;
//import cookingrecipe.cookingrecipe.dao.UserRepository;
//
//
//@Repository
//@Transactional
//public abstract class UserRepositoryImpl implements UserRepository {
//    @Autowired
//    private SessionFactory sessionFactory;
//    @Override
//    public User getUserByUsername(String username) {
//        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User u where u.username=:username", User.class);
//        query.setParameter("username", username);
//        return query.uniqueResult();
//    }
//}
