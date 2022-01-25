package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.model.enumeration.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String postImage;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private Integer likeAmount = 0;

    private Integer commentAmount = 0;

    private Double averageStars;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "likesPK.post",cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "saleItemPK.post",cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    public Post(String title,String description, Category category,List<SaleItem> items) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.items = items;
    }

    public static Post toModel(PostForm postForm) {
        return new Post(
                postForm.getTitle(),
                postForm.getDescription(),
                Category.convertFromString(postForm.getCategory().toLowerCase(Locale.ROOT)),
                postForm.getItems().stream().map(SaleItem::toModel).collect(Collectors.toList()));
    }

    public void plusOneComment() {
        this.commentAmount+=1;
    }

    public void plusOneLike() {
        this.likeAmount+=1;
    }
}
