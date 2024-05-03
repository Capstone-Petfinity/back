package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Pet;
import Capstone.Petfinity.domain.Reservation;
import Capstone.Petfinity.dto.reservation.ReservationDto;
import Capstone.Petfinity.dto.reservation.ReservationReqDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    private final ParentRepository parentRepository;

    public void save(ReservationReqDto reservationDto) {

        Reservation reservation = new Reservation();

        reservation.setUuid(UUID.randomUUID().toString());
        Parent parent = findParent(reservationDto.getParentUuid());
        reservation.setParent(parent);
        Pet pet = findPet(reservationDto.getPetUuid());
        reservation.setPet(pet);
        Hospital hospital = findHospital(reservationDto.getHospitalUuid());
        reservation.setHospital(hospital);
        LocalDate date = LocalDate.parse(reservationDto.getReservationDate()); //string -> localdate
        reservation.setReservation_date(date);

        em.persist(reservation);
    }

    private Parent findParent(String parentUuid) {

        return parentRepository.findOneByUuid(parentUuid);
    }

    public Pet findPet(String petUuid) {

        return em.find(Pet.class, petUuid);
    }

    public Hospital findHospital(String hospitalUuid) {

        return em.find(Hospital.class, hospitalUuid);
    }

    public List<ReservationDto> findReservation(String uuid) {

        return em.createQuery("select new Capstone.Petfinity.dto.reservation.ReservationDto" +
                        "(r.uuid, r.parent.uuid, r.pet, r.hospital, r.reservation_date) " +
                        "from Reservation r where r.parent.uuid = :uuid", ReservationDto.class)
                .setParameter("uuid", uuid)
                .getResultList();
    }
}
