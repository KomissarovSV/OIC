package oic.repository;

import oic.entity.Oic;
import oic.repository.interfaces.IOicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OicRepository implements IOicRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static String sql = "SELECT ОИС.`ID ОИС`, ОИС.`Название ОИС`," +
            "ОИС.`Номер охранного документа`, `Типы ОИС`.`Тип (кратко)`, ОИС.`Дата приоритета`," +
            "ОИС.`Дата выдачи`, ОИС.Авторы " +
            "FROM `Типы ОИС` INNER JOIN ОИС ON `Типы ОИС`.`ID типа ОИС`=ОИС.`ID типа ОИС` " +
            "WHERE %s ORDER BY ОИС.`ID ОИС`";

    private static Oic mapOic(ResultSet rs,int num) {
        Oic oic = new Oic();
        try{
            oic.setNumber(rs.getLong("ID ОИС"));
            oic.setName(rs.getString("Название ОИС"));
            oic.setDocNum(rs.getString("Номер охранного документа"));
            oic.setType(rs.getString("Тип (кратко)"));
            oic.setPriorityDate(rs.getDate("Дата приоритета"));
            oic.setDeliveryDate(rs.getDate("Дата выдачи"));
            oic.setAuthors(rs.getString("Авторы"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return oic;
    }

    @Override
    public List<Oic> getAll() {
        String where = "ОИС.Действующий=True";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByInnovations() {
        String where = "ОИС.Действующий=True AND ((`Типы ОИС`.`Тип (кратко)`='ИП') OR (`Типы ОИС`.`Тип (кратко)`='ПМ') OR (`Типы ОИС`.`Тип (кратко)`='ПО'))";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByPrograms() {
        String where = "ОИС.Действующий=True AND ((`Типы ОИС`.`Тип (кратко)`='ПР') OR (`Типы ОИС`.`Тип (кратко)`='БД'))";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByKnowHow() {
        String where = "ОИС.Действующий=True AND (`Типы ОИС`.`Тип (кратко)`='НХ')";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByBalance() {
        String where = "ОИС.Действующий=True And ОИС.`На балансе`=True";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByFonds() {
        String where = "ОИС.`В уставном фонде`=True";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByContracts() {
        String where = "ОИС.`В договорах`=True";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByRnD() {
        String where = "(((ОИС.Действующий)=True) AND ((ОИС.`Связан с НИР`)=True))";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }

    @Override
    public List<Oic> getByDepartments() {
        String sql = "SELECT ОИС.`ID ОИС`, ОИС.`Название ОИС`, ОИС.`Номер охранного документа`, " +
                "`Типы ОИС`.`Тип (кратко)`, ОИС.`Дата выдачи`, " +
                "Подразделения.`Название длинное`+' ('+Подразделения.`Название краткое`+')' AS Подразделение, " +
                "ОИС.Авторы " +
                "FROM `Типы ОИС` " +
                "INNER JOIN (ОИС LEFT JOIN (Подразделения RIGHT JOIN ОИС_Подразделения ON Подразделения.`ID подразделения`=ОИС_Подразделения.`ID подразделения`) " +
                "ON ОИС.`ID ОИС`=ОИС_Подразделения.`ID ОИС`) ON `Типы ОИС`.`ID типа ОИС`=ОИС.`ID типа ОИС`" +
                "ORDER BY Подразделения.`Название длинное`, ОИС.`ID ОИС`";
        List<Oic> oicList = jdbcTemplate.query(sql, (rs,num)->{
            Oic oic = new Oic();
            oic.setNumber(rs.getLong("ID ОИС"));
            oic.setName(rs.getString("Название ОИС"));
            oic.setDocNum(rs.getString("Номер охранного документа"));
            oic.setType(rs.getString("Тип (кратко)"));
            oic.setDepartment(rs.getString("Подразделение"));
            oic.setDeliveryDate(rs.getDate("Дата выдачи"));
            oic.setAuthors(rs.getString("Авторы"));
            return oic;
        });
        return oicList;
    }

    @Override
    public List<Oic> getInActive() {
        String where = "ОИС.Действующий=FALSE";
        List<Oic> oicList = jdbcTemplate.query(String.format(sql,where), OicRepository::mapOic);
        return oicList;
    }
}
