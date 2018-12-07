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

    private LocalDateTime dateToStatistics;

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

    public Long getNumberOfElements() {
        final Query query = entityManager.createQuery("select count(u) from UserMetrics u");
        if (! query.getResultList().isEmpty()) {
            return null;
        }
        return (Long) query.getResultList().get(0);
    }

    public LocalDateTime getDateToStatistics() {
        return dateToStatistics;
    }

    public void setDateToStatistics(LocalDateTime dateToStatistics) {
        this.dateToStatistics = dateToStatistics;
    }
}