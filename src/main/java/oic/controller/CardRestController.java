package oic.controller;

import oic.entity.Oic;
import oic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("card")
public class CardRestController {

    @Autowired
    private CardService cardService;

    @RequestMapping("oic/all")
    public List<Oic> getAllOic(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return cardService.getAllOic();
    }
}
