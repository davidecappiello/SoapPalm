package it.prometeo.Repository;

import it.prometeo.Entity.DocumentEvent;
import it.prometeo.Entity.PRO_TDQ2HL7;
import it.prometeo.Entity.WXSDOCUMENT;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DocumentDao {

    private final DataSource dataSource;

    public DocumentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DocumentEvent findEvent(String accessNumber, String fileName) {

        DocumentEvent result = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT * " +
                    "FROM PRO_DOCUMENT_EVENT " +
                    "WHERE ACCESSNUMBER = ? " +
                    "AND FILE_NAME = ? ");
            statement.setString(1, accessNumber);
            statement.setString(2, fileName);
            ResultSet results = statement.executeQuery();


            if (!results.next())
                return result;
            else {
                result = new DocumentEvent();
                result.setACCESSNUMBER(results.getString("ACCESSNUMBER"));
                result.setFILE_NAME(results.getString("FILE_NAME"));
                result.setFLAG_INOLTRATO(results.getInt("FLAG_INOLTRATO"));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertEvent(String accessNumber, String fileName) {

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO " +
                    "PRO_DOCUMENT_EVENT (ACCESSNUMBER, FILE_NAME, FLAG_INOLTRATO) VALUES (?,?,?)");
            statement.setString(1, accessNumber);
            statement.setString(2, fileName);
            statement.setInt(3,0);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WXSDOCUMENT findDocument(String accessNumber, String fileName){

        WXSDOCUMENT result = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT * " +
                    "FROM WXSDOCUMENT " +
                    "WHERE REFID = ? " +
                    "AND FILE_NAME = ? ");
            statement.setString(1, accessNumber);
            statement.setString(2, fileName);
            ResultSet results = statement.executeQuery();


            if (!results.next())
                return result;
            else {
                result = new WXSDOCUMENT(
                        results.getInt("ID"),
                        results.getString("REFID"),
                        results.getDate("DATA_ACC"),
                        results.getDate("DATA_REF"),
                        results.getString("MED"),
                        results.getString("REP"),
                        results.getString("NAME"),
                        results.getString("FIRSTNAME"),
                        results.getString("SITE"),
                        results.getString("LABO"),
                        results.getString("DIPA"),
                        results.getString("VAL"),
                        results.getInt("LOCKID"),
                        results.getDate("LOCKDATE"),
                        results.getString("METAINFO"),
                        results.getString("METASIGN"),
                        results.getString("STATUS"),
                        results.getBlob("DOCUMENT"),
                        results.getDate("LOADDATE"),
                        results.getDate("SIGNDATE"),
                        results.getString("CATEGORY"),
                        results.getString("FILE_NAME")
                );
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDocumentEvent(DocumentEvent documentEvent) {

        System.out.println(documentEvent.getFILE_NAME());

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("UPDATE PRO_DOCUMENT_EVENT " +
                    "SET FLAG_INOLTRATO = 1 " +
                    "WHERE ACCESSNUMBER = ? " +
                    "AND FILE_NAME = ? ");
            statement.setString(1, documentEvent.getACCESSNUMBER());
            statement.setString(2, documentEvent.getFILE_NAME());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
