package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.ConnectH2;
import com.epetkov.restjungle.dao.interfaces.FamilyDAO;
import com.epetkov.restjungle.data.dto.FamilyDTO;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class FamilyDAOImpl implements FamilyDAO {

    private final ConnectH2 connectH2;

    public FamilyDAOImpl(ConnectH2 connectH2) {

        this.connectH2 = connectH2;
    }

    @Override
    public ResponseEntity<FamilyDTO> getFamilyByID(Integer id) {

        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.SELECT_FAMILY_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return new ResponseEntity<>(getFamilyFromRS(rs), HttpStatus.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private FamilyDTO getFamilyFromRS(ResultSet rs) throws SQLException {

        Integer familyID = rs.getInt("id");
        String familyName = rs.getString("name");

        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setId(familyID);
        familyDTO.setName(familyName);

        return familyDTO;
    }
}
