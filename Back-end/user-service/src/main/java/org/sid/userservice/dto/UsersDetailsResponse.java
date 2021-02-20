package org.sid.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.userservice.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDetailsResponse {

    private List<User> users;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}
