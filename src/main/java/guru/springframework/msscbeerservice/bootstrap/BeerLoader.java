package guru.springframework.msscbeerservice.bootstrap;

import guru.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

  public static final String BEER_1_UPC= "0631234200036";
  public static final String BEER_2_UPC= "0631234300019";
  public static final String BEER_3_UPC= "0083783375213";

  private final BeerRepository beerRepository;

  @Override
  public void run(String... args) throws Exception {
    if (beerRepository.count() == 0) {
      loadBeerObjects();
      log.info("Beer loaded in database");
    }
    log.info("Beer in the system: {}", beerRepository.count());
  }

  private void loadBeerObjects() {


    beerRepository.save(Beer.builder()
        .beerName("Mango bobs")
        .beerStyle(BeerStyleEnum.IPA.name())
        .quantityToBrew(200)
        .minOnHand(12)
        .upc(BEER_1_UPC)
        .price(new BigDecimal("12.95"))
        .build());

    beerRepository.save(Beer.builder()
        .beerName("Galaxy Cat")
        .beerStyle(BeerStyleEnum.PALE_ALE.name())
        .quantityToBrew(200)
        .minOnHand(12)
        .upc(BEER_2_UPC)
        .price(new BigDecimal("11.95"))
        .build());

    beerRepository.save(Beer.builder()
        .beerName("No hammers on the bar")
        .beerStyle(BeerStyleEnum.PALE_ALE.name())
        .quantityToBrew(200)
        .minOnHand(12)
        .upc(BEER_3_UPC)
        .price(new BigDecimal("11.95"))
        .build());

    log.debug("Loaded beers: {}", beerRepository.count());
  }
}
