package Capstone.Petfinity.api;

import Capstone.Petfinity.DTO.LoginParentDTO;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.service.ParentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ParentApiController {

    @Autowired
    private final ParentService parentService;

    @PostMapping("/register/parent")
    public ResponseEntity<?> registerParent(@RequestBody LoginParentDTO request) {

        try {
            parentService.register(request);
            return ResponseEntity.ok("Login successful");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
//        Parent parent = new Parent();
//        parent.setName(request.getName());
//        UUID uuid = parentService.register(parent);
//        return new RegisterParentResponse(uuid);
    }

    @Data
    static class RegisterParentRequest {
        private String name;

    }
    @Data
    static class RegisterParentResponse {
        private UUID uuid;

        public RegisterParentResponse(UUID uuid) {
            this.uuid = uuid;
        }
    }
}
