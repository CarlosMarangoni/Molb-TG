package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.PostForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String postImage;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private Integer commentAmount = 0;

    private Double averageStars;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "postItemPK.post",cascade = CascadeType.ALL)
    private List<PostItem> items = new ArrayList<>();

    @ManyToOne
    private Category category;

    public Post(String title,String description,List<PostItem> items) {
        this.title = title;
        this.description = description;
        this.items = items;
    }

    public Post(Long id) {
        this.id = id;
    }

    public static Post toModel(PostForm postForm) {
        return new Post(
                postForm.getTitle(),
                postForm.getDescription(),
                postForm.getItems().stream().map(PostItem::toModel).collect(Collectors.toList()));
    }

    public static Post of(Long postId) {
        return new Post(postId);
    }

    public void plusOneComment() {
        this.commentAmount+=1;
    }
}
