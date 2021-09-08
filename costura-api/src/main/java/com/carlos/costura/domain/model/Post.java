package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.PostForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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
    private User user;

    private String postImage;

    private String description;

    @OneToMany(mappedBy = "saleItemPK.post",cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();

    public Post(String postImage, String description, List<SaleItem> items) {
        this.postImage = postImage;
        this.description = description;
        this.items = items;
    }

    public static Post toModel(PostForm postForm) {
        return new Post(postForm.getPostImage(),
                postForm.getDescription(),
                postForm.getItems().stream().map(SaleItem::toModel).collect(Collectors.toList()));
    }
}
