package oic.repository.interfaces;

import oic.entity.Oic;
import java.util.List;

public interface IOicRepository {
    List<Oic> getAll();
    List<Oic> getByInnovations();
    List<Oic> getByPrograms();
    List<Oic> getByKnowHow();
    List<Oic> getByBalance();
    List<Oic> getByFonds();
    List<Oic> getByContracts();
    List<Oic> getByRnD();
    List<Oic> getByDepartments();
    List<Oic> getInActive();
}
