package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput {

    private Long id;

    private String user;

    private String description;

    private String profileImage;

    private String name;

    private List<FollowerOutput> following;

    private List<FollowerOutput> followers;

    private List<PostSummary> posts;

    public static UserOutput toOutput(User user){
        return new UserOutput(
                user.getId(),
                user.getUser(),
                user.getDescription(),
                user.getProfileImage(),
                user.getName(),
                user.getFollowing().stream().map(f->FollowerOutput.toOutput(f.getFollowersPK().getTo())).collect(Collectors.toList()),
                user.getFollowers().stream().map(f->FollowerOutput.toOutput(f.getFollowersPK().getFrom())).collect(Collectors.toList()),
                user.getPosts().stream().map(PostSummary::toSummary).collect(Collectors.toList()));
    }
}
