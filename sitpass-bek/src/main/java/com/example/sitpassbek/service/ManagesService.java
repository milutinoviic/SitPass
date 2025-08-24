package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.manages.CreateManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDTO;

public interface ManagesService {

    ManagesDTO assignManager(CreateManagesDTO createManagesDTO);

    void deleteManages(CreateManagesDTO createManagesDTO);

}
