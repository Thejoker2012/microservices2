package br.com.iago.learning.microservices.service;

import br.com.iago.learning.microservices.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);
}
