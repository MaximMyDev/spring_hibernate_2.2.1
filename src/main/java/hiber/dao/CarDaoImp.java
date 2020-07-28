package hiber.dao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    private Long getCarByNameSeries(String name, int series) {
        Car car = null;
        car = (Car) sessionFactory.getCurrentSession().createQuery("FROM Car where name = :getName AND series = :getSeries").setParameter("getName", name).setParameter("getSeries", series).getSingleResult();
        return car.getId();
    }

    public User getUserByNameSeries(String name, int series) {
        User user = null;
        Long carID = getCarByNameSeries(name, series);
        return user = (User) sessionFactory.getCurrentSession().createQuery("FROM User where id = :getID").setParameter("getID", carID).getSingleResult();
    }

}

/*
public class JpaResultHelper {
    public static Object getSingleResultOrNull(Query query){
        List results = query.getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();
    }
}
public static <T> T getSingleResult(TypedQuery<T> query) {
    query.setMaxResults(1);
    List<T> list = query.getResultList();
    if (list == null || list.isEmpty()) {
        return null;
    }

    return list.get(0);
}

TypedQuery<Profile> query = em.createNamedQuery(namedQuery, Profile.class);
...
return org.springframework.dao.support.DataAccessUtils.singleResult(query.getResultList());
 */