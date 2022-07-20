package dogwalking.api.service;

import dogwalking.api.model.Pet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static dogwalking.api.util.DogWalkingBuilder.dogWalkingRequestBuild;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Test
    void getPriceWithOnePetAndMinimumDurationSuccess() {
        var response = priceService.getPrice(dogWalkingRequestBuild());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(BigDecimal.valueOf(25));
    }

    @Test
    void getPriceWithPetsAndMinimumDurationSuccess() {
        var pet1 = Pet.builder().name("Nina").breed("Poodle").age(7).build();
        var pet2 = Pet.builder().name("Luck").breed("Shih-tzu").age(5).build();
        var request = dogWalkingRequestBuild();
        request.setPets(List.of(pet1, pet2));

        var response = priceService.getPrice(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(BigDecimal.valueOf(40));
    }

    @Test
    void getPriceWithOnePetAndMaximumDurationSuccess() {
        var request = dogWalkingRequestBuild();
        request.setDuration(60);

        var response = priceService.getPrice(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(BigDecimal.valueOf(35));
    }

    @Test
    void getPriceWithPetsAndMaximumDurationSuccess() {
        var pet1 = Pet.builder().name("Nina").breed("Poodle").age(7).build();
        var pet2 = Pet.builder().name("Luck").breed("Shih-tzu").age(5).build();
        var pet3 = Pet.builder().name("Safira").breed("Vira-lata").age(2).build();
        var request = dogWalkingRequestBuild();
        request.setPets(List.of(pet1, pet2, pet3));
        request.setDuration(60);

        var response = priceService.getPrice(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(BigDecimal.valueOf(75));
    }

}