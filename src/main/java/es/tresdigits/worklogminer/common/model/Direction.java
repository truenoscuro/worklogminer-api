package es.tresdigits.worklogminer.common.model;


import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Sort;

public enum Direction {

  ASC(1, Sort.Direction.ASC),
  DESC(-1, Sort.Direction.DESC);

  private final Integer key;
  private final Sort.Direction direction;

  Direction(Integer key, Sort.Direction direction) {
    this.key = key;
    this.direction = direction;
  }

  public static Optional<Direction> find(Integer key) {
    return Arrays.stream(Direction.values())
        .filter(directionToFind -> Objects.equals(directionToFind.getKey(), key)).findFirst();
  }

  public Integer getKey() {
    return key;
  }

  public Sort.Direction getDirection() {
    return direction;
  }

}
