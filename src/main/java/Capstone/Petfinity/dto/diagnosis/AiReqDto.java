package Capstone.Petfinity.dto.diagnosis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@ToString
public class AiReqDto {

    private String user_uuid;
    private String user_type;
    private String disease_area;
    private String type;
    private String position;
    private String detail_area;
    private String disease;
    private MultipartFile img;
}
