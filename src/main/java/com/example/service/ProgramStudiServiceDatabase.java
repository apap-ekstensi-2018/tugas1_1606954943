package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProgramStudiMapper;
import com.example.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService
{
    @Autowired
    private ProgramStudiMapper programStudiMapper;


    @Override
    public ProgramStudiModel selectProdi (String id_prodi)
    {
        log.info ("select program studi with id_prodi {}", id_prodi);
        return programStudiMapper.selectProdi (id_prodi);
    }


}
