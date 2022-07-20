package dogwalking.api.adapter;

import dogwalking.api.controller.request.DogWalkingRequest;
import dogwalking.api.controller.response.DogWalkingResponse;
import dogwalking.api.model.DogWalking;
import org.springframework.stereotype.Component;

@Component
public class DogWalkingAdapter {

    public DogWalking toDogWalking(DogWalkingRequest dogWalkingRequest) {
        return DogWalking.builder()
                .scheduledDate(dogWalkingRequest.getScheduledDate())
                .duration(dogWalkingRequest.getDuration())
                .pets(dogWalkingRequest.getPets())
                .startTime(dogWalkingRequest.getStartTime())
                .build();
    }

    public DogWalkingResponse toDogWalkingResponse(DogWalking dogWalking) {
        return DogWalkingResponse.builder()
                .id(dogWalking.getId())
                .status(dogWalking.getStatus())
                .scheduledDate(dogWalking.getScheduledDate())
                .price(dogWalking.getPrice())
                .duration(dogWalking.getDuration())
                .pets(dogWalking.getPets())
                .startTime(dogWalking.getStartTime())
                .endTime(dogWalking.getEndTime())
                .build();
    }

}
