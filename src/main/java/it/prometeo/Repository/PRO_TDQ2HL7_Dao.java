package it.prometeo.Repository;

import it.prometeo.Entity.PRO_TDQ2HL7;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class PRO_TDQ2HL7_Dao {

    @Autowired
    private DataSource dataSource;

    public void updateTDQ2HL7(PRO_TDQ2HL7 proTdq2HL7) {

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("UPDATE PRO_TDQ2HL7 " +
                    "SET FLAG_INOLTRATO = 210 " +
                    "WHERE ACCESSNUMBER = ? ");
            statement.setString(1, proTdq2HL7.getACCESSNUMBER());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
