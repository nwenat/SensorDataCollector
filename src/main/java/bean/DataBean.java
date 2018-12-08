package bean;

import data.UserMetrics;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@SessionScoped
public class DataBean implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public void save(UserMetrics userMetrics) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        userTransaction.begin();
        entityManager.merge(userMetrics);
        userTransaction.commit();
    }

    public List<UserMetrics> getUserMetrics() {
        final Query query = entityManager.createQuery("select u from UserMetrics u");
        return query.getResultList();
    }

    public UserMetrics getLastMetric() {
        final Query query = entityManager.createQuery("select u from UserMetrics u order by u.id desc");
        query.setMaxResults(1);
        if (! query.getResultList().isEmpty()) {
            return (UserMetrics)query.getResultList().get(0);
        }
        return null;
    }

    public List<UserMetrics> getMetricsBetween(LocalDateTime after, LocalDateTime before) {
        final Query query = entityManager.createQuery("select u from UserMetrics u where u.dateTime between :after and :before");
        query.setParameter("after", after);
        query.setParameter("before", before);
        if (! query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }

    public List<UserMetrics> getMetricsAfter(LocalDateTime after) {
        final Query query = entityManager.createQuery("select u from UserMetrics u where u.dateTime >= :after");
        query.setParameter("after", after);
        if (! query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }

    public List<UserMetrics> getMetricsBefore(LocalDateTime before) {
        final Query query = entityManager.createQuery("select u from UserMetrics u where u.dateTime <= :before");
        query.setParameter("before", before);
        if (! query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
}
