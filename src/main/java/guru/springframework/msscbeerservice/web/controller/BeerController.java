package guru.springframework.msscbeerservice.web.controller;

import guru.brewery.model.BeerDto;
import guru.brewery.model.BeerPagedList;
import guru.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class BeerController {

  private static final Integer DEFAULT_PAGE_NUMBER = 0;
  private static final Integer DEFAULT_PAGE_SIZE = 25;

  private final BeerService beerService;

  @GetMapping(produces = {"application/json"}, path = "beer")
  public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                 @RequestParam(value = "beerName", required = false) String beerName,
                                                 @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                 @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }

    if (pageNumber == null || pageNumber < 0) {
      pageNumber = DEFAULT_PAGE_NUMBER;
    }

    if (pageSize == null || pageSize < 1) {
      pageSize = DEFAULT_PAGE_SIZE;
    }

    BeerPagedList beerList = beerService.listBeers(beerName,
        beerStyle,
        PageRequest.of(pageNumber, pageSize),
        showInventoryOnHand);

    return new ResponseEntity<>(beerList, HttpStatus.OK);
  }

  @GetMapping(value = {"beer/{beerId}"})
  public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                             @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }

    return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
  }

  @GetMapping(value = {"beerUpc/{upc}"})
  public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc) {

    return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
  }

  @PostMapping(path = "beer")
  public ResponseEntity saveBeer(@RequestBody @Validated BeerDto beerDto) {
    return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
  }

  @PutMapping(value = "beer/{beerId}")
  public ResponseEntity updateBeer(@Validated @RequestBody BeerDto beerDto, @PathVariable("beerId") UUID beerId) {
    return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
  }

}
