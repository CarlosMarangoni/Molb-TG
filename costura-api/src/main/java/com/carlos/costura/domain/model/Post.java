package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.PostForm;
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
    private String description;

    @NotNull
    private BigDecimal price;

    private Integer likeAmount = 0;

    private Integer commentAmount = 0;

    private Double averageStars;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "likesPK.post")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "saleItemPK.post",cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();

    public Post(String postImage, String description, BigDecimal price, List<SaleItem> items) {
        this.postImage = postImage;
        this.description = description;
        this.price = price;
        this.items = items;
    }

    public static Post toModel(PostForm postForm) {
        return new Post(postForm.getPostImage(),
                postForm.getDescription(),
                postForm.getPrice(),
                postForm.getItems().stream().map(SaleItem::toModel).collect(Collectors.toList()));
    }

    public void plusOneComment() {
        this.commentAmount+=1;
    }

    public void plusOneLike() {
        this.likeAmount+=1;
    }
}
