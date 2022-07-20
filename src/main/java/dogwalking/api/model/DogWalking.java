package dogwalking.api.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogWalking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DogWalkingStatus status;
    private LocalDate scheduledDate;
    private BigDecimal price;
    private Integer duration;
    private String latitude;
    private String longitude;
    @OneToMany(targetEntity=Pet.class, cascade=CascadeType.PERSIST)
    private List<Pet> pets;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
