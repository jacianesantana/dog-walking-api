package dogwalking.api.util;

import dogwalking.api.controller.request.DogWalkingRequest;
import dogwalking.api.controller.response.DogWalkingResponse;
import dogwalking.api.model.DogWalking;
import dogwalking.api.model.DogWalkingStatus;
import dogwalking.api.model.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DogWalkingBuilder {

    public static DogWalking dogWalkingBuild() {
        return DogWalking.builder()
                .id(1L)
                .status(DogWalkingStatus.SCHEDULED)
                .scheduledDate(LocalDate.now().plusDays(1L))
                .price(BigDecimal.valueOf(25))
                .duration(30)
                .pets(List.of(Pet.builder().name("Nina").breed("Poodle").age(7).build()))
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(30))
                .build();
    }

    public static DogWalkingResponse dogWalkingResponseBuild() {
        return DogWalkingResponse.builder()
                .id(1L)
                .status(DogWalkingStatus.SCHEDULED)
                .scheduledDate(LocalDate.now().plusDays(1L))
                .price(BigDecimal.valueOf(25))
                .duration(30)
                .pets(List.of(Pet.builder().name("Nina").breed("Poodle").age(7).build()))
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(30))
                .build();
    }

    public static DogWalkingRequest dogWalkingRequestBuild() {
        return DogWalkingRequest.builder()
                .scheduledDate(LocalDate.now().plusDays(1L))
                .duration(30)
                .pets(List.of(Pet.builder().name("Nina").breed("Poodle").age(7).build()))
                .startTime(LocalDateTime.now())
                .build();
    }

}
