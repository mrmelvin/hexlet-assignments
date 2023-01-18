package exercise.domain;

import io.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import io.ebean.annotation.NotNull;
import io.ebeaninternal.server.util.Str;

@Entity
public class Article extends Model {

    @Id
    private long id;

    private String title;

    @Lob
    private String body;

    @ManyToOne
    @NotNull
    private Category category;

    // BEGIN
    public Article(String innerTitle, String innerBody, Category innerCategory) {
        this.title = innerTitle;
        this.body = innerBody;
        this.category = innerCategory;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public Category getCategory() {
        return this.category;
    }
    // END
}
