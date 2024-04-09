package Capstone.Petfinity.api;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.service.ParentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ParentApiController {

    private final ParentService parentService;

    @PostMapping("/register/parent")
    // 미완성
    public RegisterParentResponse registerParent(@RequestBody RegisterParentRequest request) {

        Parent parent = new Parent();
        parent.setName(request.getName());
        UUID uuid = parentService.register(parent);
        return new RegisterParentResponse(uuid);
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
