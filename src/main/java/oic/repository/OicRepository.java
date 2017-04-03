package oic.repository;

import oic.entity.Oic;
import oic.repository.interfaces.IOicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OicRepository implements IOicRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Oic> getAll() {
        List<Oic> all = jdbcTemplate.query("SELECT ОИС.`ID ОИС`, ОИС.`Название ОИС`, " +
                "ОИС.`Номер охранного документа`, `Типы ОИС`.`Тип (кратко)`, ОИС.`Дата приоритета`, " +
                "ОИС.`Дата выдачи`, ОИС.Авторы " +
                "FROM `Типы ОИС` INNER JOIN ОИС ON `Типы ОИС`.`ID типа ОИС`=ОИС.`ID типа ОИС` " +
                "WHERE ОИС.Действующий=True " +
                "ORDER BY ОИС.`ID ОИС`;", (rs, num) -> {
            Oic oic = new Oic();
            oic.setNumber(rs.getLong("ID ОИС"));
            oic.setName(rs.getString("Название ОИС"));
            oic.setDocNum(rs.getString("Номер охранного документа"));
            oic.setType(rs.getString("Тип (кратко)"));
            oic.setPriorityDate(rs.getDate("Дата приоритета"));
            oic.setDeliveryDate(rs.getDate("Дата выдачи"));
            oic.setAuthors(rs.getString("Авторы"));
            return oic;
        });
        return all;
    }
}
