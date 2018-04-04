package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import com.example.model.FakultasModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

@Mapper
public interface ProgramStudiMapper
{
    @Select("select * from program_studi where id = #{id_prodi}")
    ProgramStudiModel selectProgramStudi (@Param("id_prodi") int id_prodi);
    
    @Select("select p.* from program_studi p where p.id = #{id_prodi}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_prodi", column = "kode_prodi"),
            @Result(property = "nama_prodi", column = "nama_prodi"),
            @Result(property = "fakultas", column = "id_fakultas", one = @One(select = "selectFakultas"))
    })
    ProgramStudiModel selectProdi(@Param("id_prodi") String id_prodi);
    
    @Select("select * from fakultas where id = #{id_fakultas}")
    @Results( value = {
    		@Result(property = "id", column = "id"),
            @Result(property = "kode_fakultas", column = "kode_fakultas"),
            @Result(property = "nama_fakultas", column = "nama_fakultas"),
            @Result(property = "univ", column = "id_univ", one = @One(select = "selectUniversitas"))
    })
    FakultasModel selectFakultas(@Param("id_fakultas") int id_fakultas);
    
    @Select("select * from universitas where id = #{id_univ}")
    @Results( value = {
    		@Result(property = "id", column = "id"),
            @Result(property = "kode_univ", column = "kode_univ"),
            @Result(property = "nama_unv", column = "nama_unv"),
    })
    UniversitasModel selectUniversitas(@Param("id_univ") int id_univ);
}
