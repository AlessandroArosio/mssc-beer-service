package guru.brewery.model.events;

import guru.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = -295562363245520113L;

  private BeerDto beerDto;
}
