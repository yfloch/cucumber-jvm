package cucumber.examples.squeaker.models;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaMessageRepository implements MessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Message message) {
        entityManager.persist(message);
    }

    public List<Message> findByContent(final String partialText) {
        final TypedQuery<Message> q = entityManager.createQuery(
                "SELECT o FROM Message o WHERE o.content LIKE :likeExpr",
                Message.class);
        q.setParameter("likeExpr", "%" + partialText + "%");
        return q.getResultList();
    }
}
