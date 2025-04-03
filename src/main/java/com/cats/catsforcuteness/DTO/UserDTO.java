package com.cats.catsforcuteness.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String fullName;
    private String email;

    public UserDTO(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

}
