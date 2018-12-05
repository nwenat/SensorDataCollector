package bean;

import data.UserMetrics;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;

@Named
@RequestScoped
public class DataBean {

    @PersistenceContext
    EntityManager entityManager;

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
}