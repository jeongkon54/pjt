package com.kidari.lecture.service;

import com.kidari.lecture.model.Hall;
import com.kidari.lecture.repository.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class HallService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HallRepository hallRepository;

    public List<Hall> getHallList() { return hallRepository.findAll(); }

    @Transactional
    public Hall create(Hall Hall) {
        log.info(" 강연장 생성 !!! ");
        return hallRepository.save(Hall);
    }
}
