package dogwalking.api.controller;

import dogwalking.api.controller.request.DogWalkingRequest;
import dogwalking.api.controller.response.DogWalkingResponse;
import dogwalking.api.model.DogWalkingStatus;
import dogwalking.api.service.DogWalkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dogwalking")
@RequiredArgsConstructor
public class DogWalkingController {

    private final DogWalkingService dogWalkingService;

    @GetMapping
    public ResponseEntity<List<DogWalkingResponse>> index(
            @RequestParam(required = false, defaultValue = "false") Boolean isOnlyAfter) {
        return new ResponseEntity<>(dogWalkingService.listWalkings(isOnlyAfter), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DogWalkingResponse> create(@RequestBody DogWalkingRequest dogWalkingRequest) {
        return new ResponseEntity<>(dogWalkingService.save(dogWalkingRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}/start")
    public ResponseEntity<DogWalkingResponse> startWalk(@PathVariable Long id) {
        return new ResponseEntity<>(dogWalkingService.updateStatus(id, DogWalkingStatus.STARTED), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/finish")
    public ResponseEntity<DogWalkingResponse> finishWalk(@PathVariable Long id) {
        return new ResponseEntity<>(dogWalkingService.updateStatus(id, DogWalkingStatus.FINISHED), HttpStatus.OK);
    }

}
