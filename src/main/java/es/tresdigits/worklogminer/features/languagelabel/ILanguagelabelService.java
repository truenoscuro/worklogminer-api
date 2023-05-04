package es.tresdigits.worklogminer.features.languagelabel;

import java.util.List;

public interface ILanguagelabelService {

  List<LanguagelabelDto> list();

  List<LanguagelabelDto> listWithRelationships();

}
