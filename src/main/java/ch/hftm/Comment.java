package ch.hftm;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment extends PanacheEntity {

    public String text;

    @ManyToOne
    public Blog blog;
}
