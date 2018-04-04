package com.example.service;

import java.util.List;

import com.example.model.StudentModel;
import com.example.model.UniversitasModel;
import com.example.model.ProgramStudiModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);


    List<StudentModel> selectAllStudents ();


    void addStudent (StudentModel student);


    void deleteStudent (String npm);
    
    void updateStudent (StudentModel student);
    
    StudentModel selectMahasiswa (String npm);
    
    void tambahMahasiswa (StudentModel student);
    
    StudentModel selectMahasiswaByProdi (String id_prodi);
    
    StudentModel selectMahasiswaByNpm (String npm);
    
    StudentModel selectAllMahasiswa ();
    
    void updateMahasiswa (StudentModel student);
    
    String selectAktifMahasiswa(String tahun_masuk, String id_prodi);
    
    String selectAktifAllMahasiswa(String tahun_masuk, String id_prodi);
    
    List<UniversitasModel> selectAllUniversitas();
} 
