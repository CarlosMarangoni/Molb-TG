package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.pk.LikePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Table(name = "post_likes")
public class Like {

    @EmbeddedId
    LikePK likesPK = new LikePK();
}
