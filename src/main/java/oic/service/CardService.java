package oic.service;

import oic.entity.Oic;
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
}
