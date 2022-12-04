package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.ConnectH2;
import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class FoodDAOImpl implements FoodDAO {

    private final ConnectH2 connectH2;

    public FoodDAOImpl(ConnectH2 connectH2) {

        this.connectH2 = connectH2;
    }

    @Override
    public ResponseEntity<FoodDTO> getFoodByID(Integer id) {

        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.SELECT_FOOD_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return new ResponseEntity<>(getFoodFromRS(rs), HttpStatus.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<FoodDTO> getFoodByName(String name) {

        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.SELECT_FOOD_BY_NAME);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return new ResponseEntity<>(getFoodFromRS(rs), HttpStatus.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<FoodDTO> createNewFood(FoodDTO foodDTO) {

        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.INSERT_NEW_FOOD);

            FoodDTO confirmFood = this.getFoodByName(foodDTO.getName()).getBody();
            if (confirmFood != null) {

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            ps.setString(1, foodDTO.getName());
            ps.execute();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLs.SELECT_LAST_INSERT_ID);
            if (rs.next()) {

                Integer savedID = rs.getInt(1);
                FoodDTO savedFood = this.getFoodByID(savedID).getBody();

                return new ResponseEntity<>(savedFood, HttpStatus.OK);
            }
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private FoodDTO getFoodFromRS(ResultSet rs) throws SQLException {

        Integer foodID = rs.getInt("id");
        String foodName = rs.getString("name");

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(foodID);
        foodDTO.setName(foodName);

        return foodDTO;
    }
}
