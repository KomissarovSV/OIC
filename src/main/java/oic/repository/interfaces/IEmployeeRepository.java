package oic.repository.interfaces;

import oic.entity.СотрудникиEntity;
import java.util.List;

public interface IEmployeeRepository {
    List<СотрудникиEntity> findAll();
}
