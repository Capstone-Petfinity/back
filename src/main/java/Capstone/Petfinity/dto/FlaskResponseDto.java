package Capstone.Petfinity.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class FlaskResponseDto {

    private String user_type;
    private String disease_area;
    private String type;
    private String disease;
    private MultipartFile img;

    public FlaskResponseDto(String user_type, String disease_area, String type, String disease, MultipartFile img) {
        this.user_type = user_type;
        this.disease_area = disease_area;
        this.type = type;
        this.disease = disease;
        this.img = img;
    }
}
