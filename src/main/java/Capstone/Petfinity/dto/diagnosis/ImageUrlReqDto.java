package Capstone.Petfinity.dto.diagnosis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ImageUrlReqDto {

    private String code;
    private boolean status;
    private List<String> result;
}
