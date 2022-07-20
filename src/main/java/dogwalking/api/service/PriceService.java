package dogwalking.api.service;

import dogwalking.api.controller.request.DogWalkingRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceService {

    private static final Integer MIN_DURATION = 30;
    private static final BigDecimal MIN_PRICE = BigDecimal.valueOf(25);
    private static final BigDecimal ADD_MIN_DURATION = BigDecimal.valueOf(15);
    private static final Integer MAX_DURATION = 60;
    private static final BigDecimal MAX_PRICE = BigDecimal.valueOf(35);
    private static final BigDecimal ADD_MAX_DURATION = BigDecimal.valueOf(20);

    public BigDecimal getPrice(DogWalkingRequest dogWalkingRequest) {
        if (dogWalkingRequest.getDuration().equals(MIN_DURATION)) {
            var additionalValue = (dogWalkingRequest.getPets().size() - 1) * ADD_MIN_DURATION.intValue();
            return MIN_PRICE.add(new BigDecimal(additionalValue));
        }
        var additionalValue = (dogWalkingRequest.getPets().size() - 1) * ADD_MAX_DURATION.intValue();
        return MAX_PRICE.add(new BigDecimal(additionalValue));
    }

}
