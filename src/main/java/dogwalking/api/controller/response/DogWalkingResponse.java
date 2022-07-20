package dogwalking.api.controller.response;

import dogwalking.api.model.DogWalkingStatus;
import dogwalking.api.model.Pet;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DogWalkingResponse {

    private Long id;
    private DogWalkingStatus status;
    private LocalDate scheduledDate;
    private BigDecimal price;
    private Integer duration;
    private List<Pet> pets;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
