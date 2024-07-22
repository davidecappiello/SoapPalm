package com.mirth.Poller;

import com.mirth.prometeo.Entity.PRO_TDQ2HL7;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class Poller {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Poller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String QUERY_TO_CHECK_CHANGES = "SELECT * FROM PRO_TDQ2HL7 pth WHERE FLAG_INOLTRATO = 0 AND REPARTO = 'PNGH'";
    private final String QUERY_TO_EXECUTE_ON_CHANGE = "YOUR QUERY HERE";



// parametro in secondi ma che va messo nell'.app
    /*@Scheduled(fixedRate = 60000)
    public void pollDatabase() {

        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format.format("\n%tl:%tM", gfg_calender, gfg_calender);
        List<PRO_TDQ2HL7> entities = jdbcTemplate.query(QUERY_TO_CHECK_CHANGES, new RowMapper<PRO_TDQ2HL7>() {


            @Override
            public PRO_TDQ2HL7 mapRow(ResultSet rs, int rowNum) throws SQLException {




                System.out.println(format);

                PRO_TDQ2HL7 result = new PRO_TDQ2HL7(
                       rs.getString("ACCESSNUMBER"),
                       rs.getString("TUBENUMBER"),
                       rs.getInt("STATO"),
                       rs.getInt("FLAG_INOLTRATO"),
                       rs.getString("LOGUSERID"),
                       rs.getString("HOSTORDERNUMBER"),
                       rs.getString("REPARTO"),
                       rs.getDate("DATE_CHK")
                );
                System.out.println("\n");
                System.out.println(result.getACCESSNUMBER());
                System.out.println(result.getTUBENUMBER());
                System.out.println(result.getSTATO());
                System.out.println(result.getFLAG_INOLTRATO());
                System.out.println(result.getLOGUSERID());
                System.out.println(result.getHOSTORDERNUMBER());
                System.out.println(result.getREPARTO());
                System.out.println(result.getDATE_CHK());
                return result;
            }
        });
    }*/
}
