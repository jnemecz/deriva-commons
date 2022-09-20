package cz.deriva.commons.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.deriva.commons.utils.AssertUtils;
import cz.deriva.commons.utils.ValidationUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentStructure {

  @JsonProperty("fraction")
  private final BigDecimal fraction;

  @JsonProperty("percents")
  private final BigDecimal percents;

  /**
   * @param fraction hodnota 0.1 -> 10%, 2 => 200%
   */
  public PercentStructure(final BigDecimal fraction) {

    AssertUtils.notNull(fraction,"fraction");

    this.fraction = fraction;
    this.percents = this.fraction.multiply(BigDecimal.valueOf(100));

  }

  public static PercentStructure diff(final BigDecimal part, final BigDecimal total) {

    AssertUtils.notNull(part,"part");
    AssertUtils.notNull(total,"total");

    BigDecimal diff = BigDecimal.ZERO;

    if (ValidationUtils.isGtZero(total)) {
      diff = part.divide(total, 3, RoundingMode.HALF_DOWN);
    }

    return new PercentStructure(diff);

  }

  public static PercentStructure empty() {
    return new PercentStructure(BigDecimal.ZERO);
  }

  public BigDecimal getFraction() {
    return  fraction;
  }

  public BigDecimal getPercents() {
    return percents;
  }

}
