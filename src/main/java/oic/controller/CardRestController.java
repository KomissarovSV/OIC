package oic.controller;

import oic.entity.Oic;
import oic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("card")
public class CardRestController {

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "oic/all",method = GET)
    public List<Oic> getAllOic(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getAllOic();
    }
    @RequestMapping(value = "oic/innovations",method = GET)
    public List<Oic> getOicByInnovations(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByInnovations();
    }
    @RequestMapping(value = "oic/programs",method = GET)
    public List<Oic> getOicByPrograms(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByPrograms();
    }
    @RequestMapping(value = "oic/knowhow",method = GET)
    public List<Oic> getOicByKnowHow(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByKnowHow();
    }
    @RequestMapping(value = "oic/balance",method = GET)
    public List<Oic> getOicByBalance(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByBalance();
    }
    @RequestMapping(value = "oic/fonds",method = GET)
    public List<Oic> getOicByFonds(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByFonds();
    }
    @RequestMapping(value = "oic/contracts",method = GET)
    public List<Oic> getOicByContracts(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByContracts();
    }
    @RequestMapping(value = "oic/rnd",method = GET)
    public List<Oic> getOicByRnD(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByRnD();
    }
    @RequestMapping(value = "oic/departments",method = GET)
    public List<Oic> getOicByDepartments(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicByDepartments();
    }
    @RequestMapping(value = "oic/inactive",method = GET)
    public List<Oic> getOicInActive(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getOicInActive();
    }
}
