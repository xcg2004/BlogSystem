package com.xcg.blogsystem.domain.vo;


import com.xcg.blogsystem.domain.po.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO extends BaseEntity implements Serializable {
    private Long id;

    private String username;

    private String email;


}
