package oic.repository;

import oic.entity.СотрудникиEntity;
import oic.repository.interfaces.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<СотрудникиEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM Сотрудники", (rs, rowNum) -> {
            СотрудникиEntity employee = new СотрудникиEntity();
            employee.setIdСотрудника(rs.getInt(1));
            employee.setФамилия(rs.getString(2));
            employee.setИмя(rs.getString(3));
            employee.setОтчество(rs.getString(4));
            employee.setIdЗвания(rs.getByte(5));
            employee.setIdСтепени(rs.getByte(6));
            employee.setДатаРождения(rs.getDate(7));
            employee.setТелефонМобильный(rs.getString(8));
            employee.setТелефонДомашний(rs.getString(9));
            employee.seteMailЛичный(rs.getString(10));
            employee.setПримечания(rs.getString(11));
            employee.setШифрЛэти(rs.getInt(12));
            employee.setФио(rs.getString(13));
            return employee;
        });
    }
}
