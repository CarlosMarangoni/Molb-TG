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

    private String user;

    private String name;

    private List<FollowerOutput> following;

    private List<FollowerOutput> followers;

    private List<PostSummary> posts;

    public static UserOutput toOutput(User user){
        return new UserOutput(
                user.getUser(),
                user.getName(),
                user.getFollowing().stream().map(FollowerOutput::toOutput).collect(Collectors.toList()),
                user.getFollowers().stream().map(FollowerOutput::toOutput).collect(Collectors.toList()),
                user.getPosts().stream().map(PostSummary::toSummary).collect(Collectors.toList()));
    }
}
