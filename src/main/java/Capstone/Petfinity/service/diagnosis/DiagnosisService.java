package Capstone.Petfinity.service.diagnosis;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.diagnosis.*;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.LoginStatusException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.repository.DiagnosisRepository;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.VetRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.StringUtils.containsWhitespace;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DiagnosisService {

    private final ParentRepository parentRepository;
    private final VetRepository vetRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void saveDiagnosis(DiagnosisDto diagnosis) {

        String userUuid = diagnosis.getUser_uuid();

        userExistCheck(userUuid);
        checkLoginStatus(userUuid);
        diagnosisRepository.save(diagnosis);
        log.info("진단결과 저장 성공");
    }

    public List<DiagnosisListDto> diagnosisList(DiagnosisListReqDto request) throws Exception {

        String uuid = request.getUuid();

        userExistCheck(uuid);
        checkLoginStatus(uuid);

        List<DiagnosisListDto> result = diagnosisRepository.findDiagnoses(request.getUuid());

        for (DiagnosisListDto diagnosis : result) {
            String img_url = imageUrl(diagnosis.getInsert_id());
            diagnosis.setImg_url(img_url);
        }

        log.info("진단결과 리스트 조회 성공");
        return result;
    }

    public Diagnosis infoDiagnosis(InfoDiagnosisReqDto request) {

        String diagnosisUuid = request.getUuid();

        checkDiagnosis(diagnosisUuid);

        log.info("진단결과 정보 조회 성공");
        return diagnosisRepository.findDiagnosis(diagnosisUuid);
    }

    public String imageUrl(String postSeq) throws Exception {

        log.info("=====이미지 url 변환 시작=====");
        RestTemplate restTemplate = new RestTemplate();

        String imageUrl = "https://blog-back.donghyuns.com/post/url";

        // Header 설정
        HttpHeaders headers = new HttpHeaders();

        // 파라미터로 들어온 dto를 JSON 객체로 변환
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Body 설정
        String body = objectMapper.writeValueAsString(Collections.singletonMap("postSeq", postSeq));

        // Request Message 설정
        HttpEntity<?> requestMessage = new HttpEntity<>(body, headers);

        // 이미지 변환 요청
        log.info("이미지 변환 요청");
        ResponseEntity<String> response = restTemplate.postForEntity(imageUrl, requestMessage, String.class);
        System.out.println("response = " + response);

        ImageUrlReqDto imageUrlReqDto = objectMapper.readValue(response.getBody(), ImageUrlReqDto.class);
        System.out.println("imageUrlReqDto = " + imageUrlReqDto);

        // 결과값 추출
        String img_url = imageUrlReqDto.getResult().get(0);
        System.out.println("response.getBody() = " + img_url);

        log.info("=====이미지 url 변환 종료=====");
        return img_url;
    }

    private void userExistCheck(String uuid) {

        Parent findParent = parentRepository.findOneByUuid(uuid);
        Vet findVet = vetRepository.findOneByUuid(uuid);

        if (findParent == null && findVet == null){

            log.warn("회원이 존재하지 않습니다");
            throw new NotExistException();
        }
    }

    private void checkLoginStatus(String uuid){

        Parent findParent = parentRepository.findOneByUuid(uuid);
        Vet findVet = vetRepository.findOneByUuid(uuid);

        boolean petLoginStatus;
        boolean vetLoginStatus;

        if (findParent != null) {
            petLoginStatus = parentRepository.checkLoginStatus(findParent);

            if (!petLoginStatus) {
                log.warn("로그아웃 상태입니다");
                throw new LoginStatusException();
            }
        }
        if (findVet != null) {
            vetLoginStatus = vetRepository.checkLoginStatus(findVet);

            if (!vetLoginStatus) {
                log.warn("로그아웃 상태입니다");
                throw new LoginStatusException();
            }
        }
    }

    private void checkDiagnosis(String uuid) {

        if (uuid.isEmpty()) {
            log.warn("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(uuid) || uuid.length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (diagnosisRepository.findDiagnosis(uuid) == null) {
            log.warn("해당 진단결과가 존재하지 않습니다");
            throw new NotExistException();
        }
    }
}
