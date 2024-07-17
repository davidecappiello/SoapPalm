package com.mirth.Poller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Poller {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String QUERY_TO_CHECK_CHANGES = "SELECT * FROM PRO_TDQ2HL7 pth WHERE FLAG_INOLTRATO = 0 AND REPARTO = 'PNGH'";
    private static final String QUERY_TO_EXECUTE_ON_CHANGE = "YOUR QUERY HERE";

    private String lastKnownValue = "";

    @Scheduled(fixedRate = 5000)
    public void pollDatabase() {
        String currentValue = jdbcTemplate.queryForObject(QUERY_TO_CHECK_CHANGES, String.class);

        if (!currentValue.equals(lastKnownValue)) {
            lastKnownValue = currentValue;
            //executeQueryOnChange();
        }
    }

//    private void executeQueryOnChange() {
//        jdbcTemplate.execute(QUERY_TO_EXECUTE_ON_CHANGE);
//    }
}
