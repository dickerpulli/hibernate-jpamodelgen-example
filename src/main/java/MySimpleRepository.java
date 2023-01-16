import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MySimpleRepository extends SimpleJpaRepository<MySimpleEntity, UUID> {

    private EntityManager entityManager;

    public MySimpleRepository(EntityManager entityManager) {
        super(MySimpleEntity.class, entityManager);
        this.entityManager = entityManager;
    }

    public List<MySimpleEntity> findByName(String name) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(MySimpleEntity.class);
        var entity = query.from(MySimpleEntity.class);
        query.select(entity)
            .where(builder.equal(entity.get(MySimpleEntity_.name), name));
        return entityManager.createQuery(query).getResultList();
    }
}
