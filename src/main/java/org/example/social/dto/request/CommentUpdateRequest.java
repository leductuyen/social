package org.example.social.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentUpdateRequest {
    String id;
    String comment;
    String postId;
    String userId;
}
