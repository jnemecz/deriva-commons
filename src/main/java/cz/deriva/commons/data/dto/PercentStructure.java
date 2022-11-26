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

  @JsonProperty("part")
  private BigDecimal part;

  @JsonProperty("total")
  private BigDecimal total;

  /**
   * @param fraction hodnota 0.1 -> 10%, 2 => 200%
   */
  public PercentStructure(final BigDecimal fraction) {

    AssertUtils.notNull(fraction, "fraction");

    this.fraction = fraction;
    this.percents = this.fraction.multiply(BigDecimal.valueOf(100));

    this.part = BigDecimal.ZERO;
    this.total = BigDecimal.ZERO;

  }

  public PercentStructure(BigDecimal total, BigDecimal part) {

    this(PercentStructure.computerDiff(part, total));

    this.total = total;
    this.part = part;

  }

  public static PercentStructure diff(final BigDecimal part, final BigDecimal total) {

    AssertUtils.notNull(part, "part");
    AssertUtils.notNull(total, "total");

    return new PercentStructure(total, part);

  }

  private static BigDecimal computerDiff(BigDecimal part, BigDecimal total) {

    BigDecimal diff = BigDecimal.ZERO;

    if (ValidationUtils.isGtZero(total)) {
      diff = part.divide(total, 3, RoundingMode.HALF_DOWN);
    }

    return diff;

  }

  public static PercentStructure empty() {
    return new PercentStructure(BigDecimal.ZERO);
  }

  public BigDecimal getFraction() {
    return fraction;
  }

  public BigDecimal getPercents() {
    return percents;
  }

}
