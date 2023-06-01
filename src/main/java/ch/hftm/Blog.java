package ch.hftm;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Blog extends PanacheEntity{
    
    private String title;
    private String content;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    public List<Comment> comments;
    
    public Blog() {
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

