package ch.hftm.blog.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Ein Kommentar zu einem Blog-Eintrag")
public class Comment extends PanacheEntity {

    private String text;

    @ManyToOne
    @JsonbTransient 
    private Blog blog;

}
