package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.FamilyDTO;
import org.springframework.http.ResponseEntity;

public interface FamilyDAO {

    ResponseEntity<FamilyDTO> getFamilyByID(Integer id);
}
