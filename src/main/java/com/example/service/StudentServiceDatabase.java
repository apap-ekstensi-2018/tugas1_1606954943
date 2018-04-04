package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.StudentModel;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
    	log.info ("student " + npm + " deleted");
    	studentMapper.deleteStudent(npm);
    }
    
    @Override
    public void updateStudent(StudentModel student) {
    	log.info ("student " + student + " updated");
    	studentMapper.updateStudent(student);
    }
    
    public StudentModel selectMahasiswa (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectMahasiswa (npm);
    }
    
    public StudentModel selectAllMahasiswa ()
    {
        log.info ("select all student with");
        return studentMapper.selectAllMahasiswa ();
    }
    
    public void tambahMahasiswa (StudentModel student)
    {
        log.info ("insert student with npm {}", student.getNpm());
        studentMapper.tambahMahasiswa (student);
    }
    
    public StudentModel selectMahasiswaByProdi (String id_prodi)
    {
        log.info ("select student with id prodi {}", id_prodi);
        return studentMapper.selectMahasiswaByProdi (id_prodi);
    }
    
    public StudentModel selectMahasiswaByNpm (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectMahasiswaByNpm (npm);
    }
    
    @Override
    public void updateMahasiswa(StudentModel student) {
    	log.info ("student " + student + " updated");
    	studentMapper.updateMahasiswa(student);
    }
    
    public String selectAktifMahasiswa (String tahun_masuk, String id_prodi)
    {
        log.info ("select student with tahun_masuk {}", tahun_masuk);
        return studentMapper.selectAktifMahasiswa (tahun_masuk, id_prodi);
    }
    
    public String selectAktifAllMahasiswa (String tahun_masuk, String id_prodi)
    {
        log.info ("select student with tahun_masuk {}", tahun_masuk);
        return studentMapper.selectAktifAllMahasiswa (tahun_masuk, id_prodi);
    }
    
    @Override
    public List<UniversitasModel> selectAllUniversitas ()
    {
        log.info ("select all students");
        return studentMapper.selectAllUniversitas ();
    }
    
} 
