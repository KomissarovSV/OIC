package oic.service;

import oic.entity.Oic;
import oic.entity.OicModal;
import oic.entity.OicOsnov;
import oic.entity.OicType;
import oic.repository.interfaces.IOicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private IOicRepository oicRepository;

    public List<Oic> getAllOic(){
        return oicRepository.getAll();
    }
    public List<Oic> getOicByInnovations(){
        return oicRepository.getByInnovations();
    }
    public List<Oic> getOicByPrograms(){
        return oicRepository.getByPrograms();
    }
    public List<Oic> getOicByKnowHow(){
        return oicRepository.getByKnowHow();
    }
    public List<Oic> getOicByBalance(){
        return oicRepository.getByBalance();
    }
    public List<Oic> getOicByFonds(){
        return oicRepository.getByFonds();
    }
    public List<Oic> getOicByContracts(){
        return oicRepository.getByContracts();
    }
    public List<Oic> getOicByRnD(){
        return oicRepository.getByRnD();
    }
    public List<Oic> getOicByDepartments(){
        return oicRepository.getByDepartments();
    }
    public List<Oic> getOicInActive(){
        return oicRepository.getInActive();
    }
    public OicModal getOicModal(long id){
        return oicRepository.getOicModal(id);
    }

    public List<OicType> getTypes(){
        return oicRepository.getTypes();
    }

    public List<OicOsnov> getOsnov(){
        return oicRepository.getOsnov();
    }

    public void updateOic(OicModal oicModal){
        oicRepository.update(oicModal);
    }
}
