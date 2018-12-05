package bean;

import data.UserMetrics;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.time.LocalDateTime;
import java.util.List;

@Named
@RequestScoped
public class DataBean {

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private LocalDateTime localDateTime = LocalDateTime.now();


    public void save(UserMetrics userMetrics) throws Exception {
        userTransaction.begin();
        entityManager.persist(userMetrics);
        userTransaction.commit();

    }

    public List<UserMetrics> getUserMetrics() {
        final Query query = entityManager.createQuery("select u from UserMetrics u");
        return query.getResultList();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}