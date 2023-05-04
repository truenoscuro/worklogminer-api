package es.tresdigits.worklogminer.common.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class EpochToLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException {
    long value = jsonParser.getValueAsLong();
    Instant instant = Instant.ofEpochMilli(value);
    return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
  }


}
