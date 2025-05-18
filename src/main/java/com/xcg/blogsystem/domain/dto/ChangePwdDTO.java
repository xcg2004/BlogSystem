package com.xcg.blogsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePwdDTO implements Serializable {
    private String username;
    private String oldPassword;
    private String newPassword;

}
