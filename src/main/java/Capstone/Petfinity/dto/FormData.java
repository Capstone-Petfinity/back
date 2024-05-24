package Capstone.Petfinity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class FormData {

    String user_uuid;
    String image;

    public FormData(String user_uuid, String image) {
        this.user_uuid = user_uuid;
        this.image = image;
    }
}
