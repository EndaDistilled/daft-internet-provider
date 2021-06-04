package ie.dsch.daft.controllers;

import ie.dsch.daft.model.BroadbandPackage;
import ie.dsch.daft.service.BroadbandPackagesService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/broadband-packages")
@Slf4j
public class BroadbandPackagesController {

    private final BroadbandPackagesService broadbandPackagesService;

    public BroadbandPackagesController(BroadbandPackagesService broadbandPackagesService) {
        this.broadbandPackagesService = broadbandPackagesService;
    }

    @GetMapping("/{region}/{place}")
    public List<BroadbandPackage> getAllBroadbandPackages(@PathVariable String region, @PathVariable String place) {
        log.info("Request to get broadband packages for region: {} and place: {}.", region, place);
        return broadbandPackagesService.findAllPackages(region, place);
    }

}

