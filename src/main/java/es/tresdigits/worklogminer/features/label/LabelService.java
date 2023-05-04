package es.tresdigits.worklogminer.features.label;

import es.tresdigits.worklogminer.common.MapperBuilder;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LabelService implements ILabelService {

  private final LabelRepository labelRepository;
  private final LabelMapper labelMapper;

  @Autowired
  public LabelService(LabelRepository labelRepository) {
    this.labelRepository = labelRepository;
    this.labelMapper = MapperBuilder.LABEL_MAPPER;
  }

  @Override
  public List<LabelDto> list() {
    return labelMapper.entityToDtoList(labelRepository.findAll());
  }

  @Override
  public LabelDto get(Integer id) {
    Optional<LabelEntity> label = labelRepository.findById(id);
    return label.map(labelMapper::entityToDto)
        .orElseThrow(() -> new DataIntegrityViolationException("The id is not valid"));
  }

  @Override
  public LabelDto update(LabelDto labelDto) {
    if (labelRepository.existsById(labelDto.getCode())) {
      labelRepository.save(labelMapper.dtoToEntity(labelDto));
      return labelDto;
    } else {
      throw new DataIntegrityViolationException("The id is not valid");
    }
  }

  @Override
  public LabelDto create(LabelDto labelDto) {
    labelRepository.save(labelMapper.dtoToEntity(labelDto));
    return labelDto;
  }

  @Override
  public void delete(Integer id) {
    labelRepository.deleteById(id);
  }
}
