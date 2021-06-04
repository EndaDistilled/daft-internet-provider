package ie.dsch.daft.repositories;

import ie.dsch.daft.model.BroadbandPackage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadbandPackagesRepository extends JpaRepository<BroadbandPackage, Long> {

  List<BroadbandPackage> findAllByRegionAndPlace(String region, String place);

}
