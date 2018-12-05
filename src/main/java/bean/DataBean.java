package bean;

import data.UserMetrics;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class DataBean {

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private LocalDateTime localDateTime = LocalDateTime.now();


    public void save() throws Exception {
        userTransaction.begin();
        entityManager.persist(new UserMetrics(2, 0, 1, 1, 9));
        userTransaction.commit();

    }

//    private List<UserMetrics> getUserMetrics() {
//        TypedQuery<UserMetrics> query = entityManager.createQuery("select u from UserMetrics u", UserMetrics.class);
//        return query.getResultList();
//    }


}
