package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.ConnectH2;
import com.epetkov.restjungle.dao.interfaces.*;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAOImpl implements AnimalDAO {

    private final FoodDAO foodDAO;
    private final FamilyDAO familyDAO;
    private final ConnectH2 connectH2;

    public AnimalDAOImpl(FoodDAO foodDAO, FamilyDAO familyDAO, ConnectH2 connectH2) {

        this.foodDAO = foodDAO;
        this.familyDAO = familyDAO;
        this.connectH2 = connectH2;
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {

        List<AnimalDTO> animalDTOList = new ArrayList<>();
        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.SELECT_ALL_ANIMALS);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                animalDTOList.add(getAnimalFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(animalDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnimalDTO> getAnimalByName(String name) {

        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.SELECT_ANIMAL_BY_NAME);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return new ResponseEntity<>(getAnimalFromRS(rs), HttpStatus.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food) {

        List<AnimalDTO> animalDTOList = new ArrayList<>();
        try {
            Connection connection = connectH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLs.SELECT_ANIMALS_BY_FOOD);
            ps.setString(1, food);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                animalDTOList.add(getAnimalFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(animalDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnimalDTO> createNewAnimal(AnimalDTO animalDTO) {

        // Todo: impl
        return null;
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> deleteAnimalByName(String name) {

        // Todo: impl
        return null;
    }

    private AnimalDTO getAnimalFromRS(ResultSet rs) throws SQLException {

        Integer animalID = rs.getInt("id");
        String animalName = rs.getString("name");
        Integer legs = rs.getInt("legs");
        Integer foodId = rs.getInt("id_food");
        Integer familyId = rs.getInt("id_family");

        AnimalDTO animal = new AnimalDTO();
        animal.setId(animalID);
        animal.setName(animalName);
        animal.setLegs(legs);
        animal.setFoodDTO(foodDAO.getFoodByID(foodId).getBody());
        animal.setFamilyDTO(familyDAO.getFamilyByID(familyId).getBody());

        return animal;
    }
}
