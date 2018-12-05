package bean;

import data.UserMetrics;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Stateless
public class DataBean {

    @PersistenceContext
    EntityManager entityManager;
    private LocalDateTime localDateTime = LocalDateTime.now();

    private UserMetrics userMetrics = new UserMetrics(localDateTime, 3, 0, 2, 2 ,1);

    public void save() {

        entityManager.persist(userMetrics);

    }


}
