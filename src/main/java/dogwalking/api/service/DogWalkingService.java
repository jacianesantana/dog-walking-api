package dogwalking.api.service;

import dogwalking.api.adapter.DogWalkingAdapter;
import dogwalking.api.controller.request.DogWalkingRequest;
import dogwalking.api.controller.response.DogWalkingResponse;
import dogwalking.api.model.DogWalkingStatus;
import dogwalking.api.repository.DogWalkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogWalkingService {

    private final DogWalkingRepository dogWalkingRepository;
    private final DogWalkingAdapter dogWalkingAdapter;
    private final PriceService priceService;

    public List<DogWalkingResponse> listWalkings(Boolean isOnlyAfter) {
        var dogWalkingResponseList = dogWalkingRepository.findAll().stream()
                .map(dogWalkingAdapter::toDogWalkingResponse)
                .collect(Collectors.toList());
        if (isOnlyAfter) {
             return dogWalkingResponseList.stream()
                     .filter(dogWalkingResponse -> dogWalkingResponse.getScheduledDate().isEqual(LocalDate.now()) ||
                             dogWalkingResponse.getScheduledDate().isAfter(LocalDate.now()))
                     .collect(Collectors.toList());
        }
        return dogWalkingResponseList;
    }

    public DogWalkingResponse save(DogWalkingRequest dogWalkingRequest) {
        var dogWalking = dogWalkingAdapter.toDogWalking(dogWalkingRequest);
        dogWalking.setStatus(DogWalkingStatus.SCHEDULED);
        dogWalking.setPrice(priceService.getPrice(dogWalkingRequest));
        dogWalking.setEndTime(dogWalking.getStartTime().plusMinutes(dogWalking.getDuration()));
        return dogWalkingAdapter.toDogWalkingResponse(dogWalkingRepository.save(dogWalking));
    }

    public DogWalkingResponse updateStatus(Long id, DogWalkingStatus status) {
        var dogWalking = dogWalkingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Walk not found"));
        dogWalking.setStatus(status);
        return dogWalkingAdapter.toDogWalkingResponse(dogWalkingRepository.save(dogWalking));
    }

}
