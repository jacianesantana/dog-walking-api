package dogwalking.api.service;

import dogwalking.api.adapter.DogWalkingAdapter;
import dogwalking.api.controller.request.DogWalkingRequest;
import dogwalking.api.model.DogWalking;
import dogwalking.api.model.DogWalkingStatus;
import dogwalking.api.repository.DogWalkingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static dogwalking.api.util.DogWalkingBuilder.dogWalkingBuild;
import static dogwalking.api.util.DogWalkingBuilder.dogWalkingResponseBuild;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class DogWalkingServiceTest {

    @InjectMocks
    private DogWalkingService dogWalkingService;

    @Mock
    private DogWalkingRepository dogWalkingRepository;

    @Mock
    private DogWalkingAdapter dogWalkingAdapter;

    @Mock
    private PriceService priceService;

    @Test
    void listWalkingsSuccess() {
        BDDMockito.when(dogWalkingRepository.findAll()).thenReturn(List.of(dogWalkingBuild()));
        BDDMockito.when(dogWalkingAdapter.toDogWalkingResponse(any(DogWalking.class)))
                .thenReturn(dogWalkingResponseBuild());

        var response = dogWalkingService.listWalkings(true);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.get(0).getStatus()).isEqualTo(DogWalkingStatus.SCHEDULED);
    }

    @Test
    void saveSuccess() {
        BDDMockito.when(dogWalkingAdapter.toDogWalking(any(DogWalkingRequest.class))).thenReturn(dogWalkingBuild());
        BDDMockito.when(priceService.getPrice(any(DogWalkingRequest.class))).thenReturn(BigDecimal.valueOf(25));
        BDDMockito.when(dogWalkingAdapter.toDogWalkingResponse(any(DogWalking.class)))
                .thenReturn(dogWalkingResponseBuild());
        BDDMockito.when(dogWalkingRepository.save(any(DogWalking.class))).thenReturn(dogWalkingBuild());

        var response = dogWalkingService.save(new DogWalkingRequest());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getDuration()).isEqualTo(30);
        Assertions.assertThat(response.getPets().get(0).getName()).isEqualTo("Nina");
    }

    @Test
    void updateStatusSuccess() {
        var dogWalkingResponse = dogWalkingResponseBuild();
        dogWalkingResponse.setStatus(DogWalkingStatus.STARTED);

        BDDMockito.when(dogWalkingRepository.findById(anyLong())).thenReturn(Optional.of(dogWalkingBuild()));
        BDDMockito.when(dogWalkingAdapter.toDogWalkingResponse(any(DogWalking.class)))
                .thenReturn(dogWalkingResponse);
        BDDMockito.when(dogWalkingRepository.save(any(DogWalking.class))).thenReturn(dogWalkingBuild());

        var response = dogWalkingService.updateStatus(1L, DogWalkingStatus.STARTED);

        Assertions.assertThat(response.getStatus()).isEqualTo(DogWalkingStatus.STARTED);
    }

    @Test
    void updateThrowsException() {
        BDDMockito.when(dogWalkingRepository.findById(anyLong())).thenThrow(new RuntimeException());

        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> dogWalkingService.updateStatus(1L, DogWalkingStatus.STARTED));
    }

}