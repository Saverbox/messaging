package ch.hftm.blog.entity;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Builder
@AllArgsConstructor
public class Blog{
    @Id
    @GeneratedValue
    @Schema(required = true, description = "Blog-ID")
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    public List<Comment> comments;

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

