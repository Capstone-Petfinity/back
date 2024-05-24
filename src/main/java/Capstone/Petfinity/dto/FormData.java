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

    String image;

    public FormData(String image) {
        this.image = image;
    }
}
