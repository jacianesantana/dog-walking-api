package dogwalking.api.controller;

import dogwalking.api.controller.request.DogWalkingRequest;
import dogwalking.api.model.DogWalkingStatus;
import dogwalking.api.service.DogWalkingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static dogwalking.api.util.DogWalkingBuilder.dogWalkingResponseBuild;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class DogWalkingControllerTest {

    @InjectMocks
    private DogWalkingController dogWalkingController;

    @Mock
    private DogWalkingService dogWalkingService;

    @Test
    void indexSuccess() {
        BDDMockito.when(dogWalkingService.listWalkings(anyBoolean())).thenReturn(List.of(dogWalkingResponseBuild()));

        var response = dogWalkingController.index(true);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createSuccess() {
        BDDMockito.when(dogWalkingService.save(any(DogWalkingRequest.class))).thenReturn(dogWalkingResponseBuild());

        var response = dogWalkingController.create(new DogWalkingRequest());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getStatus()).isEqualTo(DogWalkingStatus.SCHEDULED);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void startWalkSuccess() {
        var dogWalkingResponse = dogWalkingResponseBuild();
        dogWalkingResponse.setStatus(DogWalkingStatus.STARTED);

        BDDMockito.when(dogWalkingService.updateStatus(anyLong(), any(DogWalkingStatus.STARTED.getDeclaringClass())))
                .thenReturn(dogWalkingResponse);

        var response = dogWalkingController.startWalk(1L);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getStatus()).isEqualTo(DogWalkingStatus.STARTED);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void finishWalkSuccess() {
        var dogWalkingResponse = dogWalkingResponseBuild();
        dogWalkingResponse.setStatus(DogWalkingStatus.FINISHED);

        BDDMockito.when(dogWalkingService.updateStatus(anyLong(), any(DogWalkingStatus.FINISHED.getDeclaringClass())))
                .thenReturn(dogWalkingResponse);

        var response = dogWalkingController.finishWalk(1L);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getStatus()).isEqualTo(DogWalkingStatus.FINISHED);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}