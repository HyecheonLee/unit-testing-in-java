package com.hyecheon.iloveyouboss.controller;

import com.hyecheon.iloveyouboss.domain.BooleanQuestion;
import com.hyecheon.iloveyouboss.domain.Persistable;
import com.hyecheon.iloveyouboss.domain.Question;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Clock;
import java.util.List;
import java.util.function.Consumer;

@Data
public class QuestionController {
    private Clock clock = Clock.systemUTC();


    private static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("persistence-unit");
    }

    public Question find(Integer id) {
        return em().find(Question.class, id);
    }

    public List<Question> getAll() {
        return em()
                .createQuery("select q from Question q", Question.class)
                .getResultList();
    }

    public List<Question> findWithMatchingText(String text) {
        String query =
                "select q from Question q where q.text like '%" + text + "%'";
        return em().createQuery(query, Question.class).getResultList();
    }

    public int addBooleanQuestion(String text) {
        return persist(new BooleanQuestion(text));
    }

    private void executeInTransaction(Consumer<EntityManager> func) {
        EntityManager em = em();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            func.accept(em);
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    private int persist(Persistable object) {
        object.setCreateTimestamp(clock.instant());
        executeInTransaction((em) -> em.persist(object));
        return object.getId();
    }

    private EntityManager em() {
        return getEntityManagerFactory().createEntityManager();
    }

}
