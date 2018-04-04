package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import com.example.model.StudentModel;
import com.example.model.ProgramStudiModel;
import com.example.model.FakultasModel;
import com.example.model.UniversitasModel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper
{
    @Select("select npm, name, gpa from student where npm = #{npm}")
    StudentModel selectStudent (@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent (StudentModel student);
    
    @Delete("DELETE FROM student WHERE npm = #{npm}")
    void deleteStudent (@Param("npm") String npm); 
    
    @Update("UPDATE student SET name = #{name}, gpa = #{gpa} where npm = #{npm}")
    void updateStudent(StudentModel student); 
    
    @Update("UPDATE mahasiswa SET nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah}, tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi} where npm = #{npm}")
    void updateMahasiswa(StudentModel student); 
    
    @Update("UPDATE mahasiswa SET npm = #{new_npm} where npm = #{npm}")
    void updateNPMMahasiswa(@Param("npm") String npm, @Param("new_npm") String new_npm); 
    
    @Select("SELECT COUNT(*) as count from mahasiswa where tahun_masuk = #{tahun_masuk} and id_prodi = #{id_prodi} and status = 'Lulus'")
    String selectAktifMahasiswa(@Param("tahun_masuk") String tahun_masuk, @Param("id_prodi") String id_prodi);
    
    @Select("SELECT COUNT(*) as count from mahasiswa where tahun_masuk = #{tahun_masuk} and id_prodi = #{id_prodi}")
    String selectAktifAllMahasiswa(@Param("tahun_masuk") String tahun_masuk, @Param("id_prodi") String id_prodi);
    
    @Select("select * from mahasiswa")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk")
    })
    StudentModel selectAllMahasiswa ();
    
    @Select("select * from mahasiswa where npm = #{npm}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk"),
            @Result(property = "id_prodi", column = "id_prodi"),
            @Result(property = "prodi", column = "id_prodi", one = @One(select = "selectProdi"))
    })
    StudentModel selectMahasiswa (@Param("npm") String npm);
    
    @Select("select * from mahasiswa where id_prodi = #{id_prodi} order by id desc limit 1")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk"),
            @Result(property = "prodi", column = "id_prodi", one = @One(select = "selectProdi"))
    })
    StudentModel selectMahasiswaByProdi (@Param("id_prodi") String id_prodi);
    
    @Select("select * from mahasiswa where npm LIKE #{npm} order by npm desc limit 1")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm")
    })
    StudentModel selectMahasiswaByNpm (@Param("npm") String npm);
    
    @Select("select p.* from program_studi p where p.id = #{id_prodi}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_prodi", column = "kode_prodi"),
            @Result(property = "nama_prodi", column = "nama_prodi"),
            @Result(property = "fakultas", column = "id_fakultas", one = @One(select = "selectFakultas"))
    })
    ProgramStudiModel selectProdi(@Param("id_prodi") int id_prodi);
    
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
    
    @Select("select * from universitas")
    @Results( value = {
    		@Result(property = "id", column = "id"),
            @Result(property = "kode_univ", column = "kode_univ"),
            @Result(property = "nama_unv", column = "nama_unv"),
    })
    List<UniversitasModel> selectAllUniversitas();
    
    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    void tambahMahasiswa (StudentModel student);
}
