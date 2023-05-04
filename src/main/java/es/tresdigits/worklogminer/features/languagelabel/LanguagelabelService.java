package es.tresdigits.worklogminer.features.languagelabel;

import es.tresdigits.worklogminer.common.MapperBuilder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LanguagelabelService implements ILanguagelabelService {

  private final LanguagelabelRepository languagelabelRepository;
  private final LanguagelabelMapper languagelabelMapper;


  @Autowired
  public LanguagelabelService(LanguagelabelRepository languagelabelRepository) {
    this.languagelabelRepository = languagelabelRepository;
    this.languagelabelMapper = MapperBuilder.LANGUAGELABEL_MAPPER;
  }

  @Override
  @Cacheable("translations")
  public List<LanguagelabelDto> list() {
    List<LanguagelabelEntity> languagelabelList = languagelabelRepository.findAll();
    return languagelabelMapper.entityToDtoList(languagelabelList);
  }

  @Override
  public List<LanguagelabelDto> listWithRelationships() {
    List<LanguagelabelEntity> languagelabelList = languagelabelRepository.findAll();
    return languagelabelMapper.entityToDtoListWithRelationships(languagelabelList);
  }
}
