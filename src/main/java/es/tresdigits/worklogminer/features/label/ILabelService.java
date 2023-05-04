package es.tresdigits.worklogminer.features.label;

import java.util.List;

public interface ILabelService {

  List<LabelDto> list();

  LabelDto get(Integer id);

  LabelDto update(LabelDto labelDto);

  LabelDto create(LabelDto labelDto);

  void delete(Integer id);
}
