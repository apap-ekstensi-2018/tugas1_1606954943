package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultasModel
{
	private int id;
    private int kode_fakultas;
    private String nama_fakultas; 
    private String id_univ;
    private UniversitasModel univ;

}
