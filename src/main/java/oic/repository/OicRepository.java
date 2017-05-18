package oic.repository;

import oic.entity.Oic;
import oic.entity.OicModal;
import oic.entity.OicOsnov;
import oic.entity.OicType;
import oic.entity.tree.GRNTI;
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

    @Override
    public OicModal getOicModal(long id) {
        List<OicModal> list = jdbcTemplate.query("SELECT * FROM оис  " +
                "WHERE оис.`ID ОИС` = ?",new Object[] {id}, (rs, n) -> {
            OicModal oicModal = new OicModal();
            oicModal.setNumber(rs.getLong("ID ОИС"));
            oicModal.setName(rs.getString("Название ОИС"));
            oicModal.setActive(rs.getBoolean("Действующий"));
            oicModal.setTypeOic(rs.getLong("ID типа ОИС"));
            oicModal.setTypeOsnov(rs.getLong("Тип основания"));

            oicModal.setPriorityDate(rs.getDate("Дата приоритета"));
            oicModal.setDeliveryDate(rs.getDate("Дата выдачи"));
            oicModal.setTakeDate(rs.getDate("Дата получения"));
            oicModal.setDuration(rs.getDate("Срок действия"));

            oicModal.setSecDocNum(rs.getString("Номер охранного документа"));
            oicModal.setBelNum(rs.getString("Номер бюллетеня"));
            oicModal.setInnerNumZaiv(rs.getLong("ID заявки"));
            oicModal.setInnerDeloNum(rs.getString("Номер внутреннего дела"));
            oicModal.setShifr(rs.getString("Шифр МПК"));

            oicModal.setHasContracts(rs.getBoolean("В договорах"));
            oicModal.setHasRND(rs.getBoolean("Связан с НИР"));
            oicModal.setHasFond(rs.getBoolean("В уставном фонде"));

            oicModal.setHasBalance(rs.getBoolean("На балансе"));
            oicModal.setPostan(rs.getDate("Дата постановки на баланс"));
            oicModal.setCost(rs.getDouble("Балансовая стоимость"));

            oicModal.setBalanceNote(rs.getString("Примечания по балансу"));
            oicModal.setRightNote(rs.getString("Примечания по МП"));
            oicModal.setContactNote(rs.getString("Контакты"));
            oicModal.setGeneralNote(rs.getString("Примечания"));
            oicModal.setReferat(rs.getString("Реферат"));

            oicModal.setAuthors(rs.getString("Авторы"));
            return oicModal;
        });
        return list.get(0);
    }

    @Override
    public List<OicType> getTypes() {
        List<OicType> types = jdbcTemplate.query("SELECT * FROM `типы оис`", (rs, n) -> {
            OicType oicType = new OicType();
            oicType.setTypeId(rs.getLong("ID типа ОИС"));
            oicType.setCategoryId(rs.getLong("ID категории"));
            oicType.setName(rs.getString("Название типа ОИС"));
            oicType.setShortName(rs.getString("Тип (кратко)"));
            return oicType;
        });
        return types;
    }

    @Override
    public List<OicOsnov> getOsnov() {
        List<OicOsnov> list = jdbcTemplate.query("SELECT * FROM основания", (rs, n) -> {
            OicOsnov oicOsnov = new OicOsnov();
            oicOsnov.setId(rs.getLong("ID основания"));
            oicOsnov.setName(rs.getString("Наименование"));
            return oicOsnov;
        });
        return list;
    }

    @Override
    public void update(OicModal oicModal) {
        Object[] params = {
                oicModal.getName(),
                oicModal.getActive(),
                oicModal.getTypeOic(),
                oicModal.getTypeOsnov(),
                oicModal.getPriorityDate(),
                oicModal.getDeliveryDate(),
                oicModal.getTakeDate(),
                oicModal.getDuration(),
                oicModal.getSecDocNum(),
                oicModal.getBelNum(),
                oicModal.getInnerNumZaiv(),
                oicModal.getInnerDeloNum(),
                oicModal.getShifr(),
                oicModal.getHasContracts(),
                oicModal.getHasRND(),
                oicModal.getHasFond(),
                oicModal.getHasBalance(),
                oicModal.getPostan(),
                oicModal.getCost(),
                oicModal.getBalanceNote(),
                oicModal.getRightNote(),
                oicModal.getContactNote(),
                oicModal.getGeneralNote(),
                oicModal.getReferat(),
                oicModal.getAuthors(),
                oicModal.getNumber(),
        };
        String sql = "UPDATE оис " +
                "SET `Название ОИС`= ?," +
                "`Действующий`= ?," +
                "`ID типа ОИС`= ?," +
                "`Тип основания`= ?," +
                "`Дата приоритета`= ?," +
                "`Дата выдачи`= ?," +
                "`Дата получения`= ?," +
                "`Срок действия`= ?," +
                "`Номер охранного документа`= ?," +
                "`Номер бюллетеня`= ?," +
                "`ID заявки`= ?," +
                "`Номер внутреннего дела`= ?," +
                "`Шифр МПК`= ?," +
                "`В договорах`= ?," +
                "`Связан с НИР`= ?," +
                "`В уставном фонде`= ?," +
                "`На балансе`= ?," +
                "`Дата постановки на баланс`= ?," +
                "`Балансовая стоимость`= ?," +
                "`Примечания по балансу`= ?," +
                "`Примечания по МП`= ?," +
                "`Контакты`= ?," +
                "`Примечания`= ?," +
                "`Реферат`= ?," +
                "`Авторы`= ? " +
                "WHERE `ID ОИС`= ?";
        jdbcTemplate.update(sql,params);
    }

    @Override
    public void create(OicModal oicModal) {
        Object[] params = {
                oicModal.getName(),
                oicModal.getActive(),
                oicModal.getTypeOic(),
                oicModal.getTypeOsnov(),
                oicModal.getPriorityDate(),
                oicModal.getDeliveryDate(),
                oicModal.getTakeDate(),
                oicModal.getDuration(),
                oicModal.getSecDocNum(),
                oicModal.getBelNum(),
                oicModal.getInnerNumZaiv(),
                oicModal.getInnerDeloNum(),
                oicModal.getShifr(),
                oicModal.getHasContracts(),
                oicModal.getHasRND(),
                oicModal.getHasFond(),
                oicModal.getHasBalance(),
                oicModal.getPostan(),
                oicModal.getCost(),
                oicModal.getBalanceNote(),
                oicModal.getRightNote(),
                oicModal.getContactNote(),
                oicModal.getGeneralNote(),
                oicModal.getReferat(),
                oicModal.getAuthors(),
        };
        String maxSQL = "SELECT max(`ID ОИС`) + 1 AS max FROM ОИС";
        long max = jdbcTemplate.query(maxSQL, (rs, n) -> rs.getLong("max")).get(0);
        String sql = "INSERT INTO оис " +
                "(`Название ОИС`," +
                "`Действующий`," +
                "`ID типа ОИС`," +
                "`Тип основания`," +
                "`Дата приоритета`," +
                "`Дата выдачи`," +
                "`Дата получения`," +
                "`Срок действия`," +
                "`Номер охранного документа`," +
                "`Номер бюллетеня`," +
                "`ID заявки`," +
                "`Номер внутреннего дела`," +
                "`Шифр МПК`," +
                "`В договорах`," +
                "`Связан с НИР`," +
                "`В уставном фонде`," +
                "`На балансе`," +
                "`Дата постановки на баланс`," +
                "`Балансовая стоимость`," +
                "`Примечания по балансу`," +
                "`Примечания по МП`," +
                "`Контакты`," +
                "`Примечания`," +
                "`Реферат`," +
                "`Авторы`," +
                "`ID ОИС`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," + max + ")";
        jdbcTemplate.update(sql,params);
    }

    @Override
    public List<GRNTI> getGRNTI() {
        List<GRNTI> query = jdbcTemplate.query("SELECT * FROM грнти", (rs, n) -> {
            GRNTI grnti = new GRNTI();
            grnti.setId(rs.getLong("ID шифра ГРНТИ"));
            grnti.setShifr(rs.getString("Шифр ГРНТИ"));
            grnti.setParent(rs.getLong("Предок"));
            grnti.setName(rs.getString("Наименование"));
            return grnti;
        });
        return query;
    }

    @Override
    public List<GRNTI> getGRNTI(long parentId) {
        List<GRNTI> query = jdbcTemplate.query("SELECT * FROM грнти WHERE `Предок`=?", (rs, n) -> {
            GRNTI grnti = new GRNTI();
            grnti.setId(rs.getLong("ID шифра ГРНТИ"));
            grnti.setShifr(rs.getString("Шифр ГРНТИ"));
            grnti.setParent(rs.getLong("Предок"));
            grnti.setName(rs.getString("Наименование"));
            return grnti;
        },new Object[]{parentId});
        return query;
    }
}
