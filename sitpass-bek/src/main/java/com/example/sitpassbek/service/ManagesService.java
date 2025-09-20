package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.manages.CheckManageDTO;
import com.example.sitpassbek.dto.manages.CreateManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDetailDTO;

import java.util.List;

public interface ManagesService {

    ManagesDTO assignManager(CreateManagesDTO createManagesDTO);

    void deleteManages(CreateManagesDTO createManagesDTO);

    List<ManagesDetailDTO> getActiveManagersByFacility(Long facilityId);

    boolean doesUserManageFacility(CheckManageDTO dto);

}
