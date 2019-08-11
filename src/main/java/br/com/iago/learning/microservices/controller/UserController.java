package br.com.iago.learning.microservices.controller;

import br.com.iago.learning.microservices.model.Role;
import br.com.iago.learning.microservices.model.User;
import br.com.iago.learning.microservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/service/registration")
    public ResponseEntity<?> saveUser(@RequestParam User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            //Status code 409
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //Default role = user
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);

    }

    @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal) {
        //Principal principal = request.getUserPrincipal();
        if (principal == null || principal.getName() == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //username = principal.getName()
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));

    }

    @PostMapping("/service/names")
    public ResponseEntity<?> getNamesOfUsers(@RequestParam List<Long> idList) {
        return ResponseEntity.ok(userService.findUsers(idList));

    }

    @GetMapping("/service/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Está Funcionando");
    }

}
