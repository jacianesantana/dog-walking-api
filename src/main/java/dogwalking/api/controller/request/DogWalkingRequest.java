package dogwalking.api.controller.request;

import dogwalking.api.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogWalkingRequest {

    private LocalDate scheduledDate;
    private Integer duration;
    private String latitude;
    private String longitude;
    private List<Pet> pets;
    private LocalDateTime startTime;

}
