package ie.dsch.daft.service;

import ie.dsch.daft.model.BroadbandPackage;
import ie.dsch.daft.repositories.BroadbandPackagesRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BroadbandPackagesService {

  private final BroadbandPackagesRepository broadbandPackagesRepository;

  @Autowired
  public BroadbandPackagesService(BroadbandPackagesRepository broadbandPackagesRepository) {
    this.broadbandPackagesRepository = broadbandPackagesRepository;
  }

  public List<BroadbandPackage> findAllPackages(String region, String place) {
    List<BroadbandPackage> dbPackages = broadbandPackagesRepository.findAllByRegionAndPlace(region, place);
    if(dbPackages.isEmpty()) {
      List<BroadbandPackage> broadbandPackages;
      try {
        Document doc = Jsoup.connect(
            "https://switcher.ie/broadband/compare/broadband-packages/?region="+region+"&place="+place+"&product_type=broadband")
            .get();
        Elements scrapedPackages = doc.getElementsByClass("c-result-row");
        if(scrapedPackages != null){
          broadbandPackages = new ArrayList<>();
          for (Element p : scrapedPackages) {
            String ispName = p.select("div.c-result-row__plan-name img").attr("alt");
            String packageName = p.select(".c-result-row__plan-name").text();
            String packageSpeed = p.selectFirst(".c-result-row__plan-detail__item").text();
            BroadbandPackage broadbandPackage = BroadbandPackage.builder()
                .ispName(ispName)
                .packageName(packageName)
                .packageSpeed(packageSpeed)
                .place(place)
                .region(region)
                .build();
            broadbandPackages.add(broadbandPackage);
          }
          broadbandPackages.stream()
              .filter(Objects::nonNull)
              .forEach(broadbandPackagesRepository::save);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return broadbandPackagesRepository.findAllByRegionAndPlace(region, place);
  }

}
