package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;
import com.example.service.ProgramStudiService;


@Controller
public class StudentController {
	@Autowired
	StudentService studentDAO;
	ProgramStudiService programStudiDAO;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/student/add")
	public String add() {
		return "form-add";
	}

	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam(value = "npm", required = false) String npm) {
		StudentModel student = studentDAO.selectStudent(npm);

		if (student != null) {
			model.addAttribute("student", student);
			return "view";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}

	@RequestMapping("/student/view/{npm}")
	public String viewPath(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentDAO.selectStudent(npm);

		if (student != null) {
			model.addAttribute("student", student);
			return "view";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}

	@RequestMapping("/student/viewall")
	public String view(Model model) {
		List<StudentModel> students = studentDAO.selectAllStudents();
		model.addAttribute("students", students);

		return "viewall";
	}

	@RequestMapping("/student/delete/{npm}")
	public String delete(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentDAO.selectStudent(npm);
		if (student != null) {
			studentDAO.deleteStudent(npm);
		} else {
			return "not-found";
		}

		return "delete";
	}

	@RequestMapping("/student/update/{npm}")
	public String update(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentDAO.selectStudent(npm);
		if (student != null) {
			model.addAttribute("student", student);
			return "form-update";
		} else {
			return "not-found";
		}
	}

	@RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
	public String updateSubmit(@ModelAttribute StudentModel student)
	{
		studentDAO.updateMahasiswa(student);
		return "success-update";
	}

	@RequestMapping(value = "/mahasiswa", method = RequestMethod.GET)
	public String lihatMahasiswa(@RequestParam(value = "npm") String npm, Model model) {
		StudentModel student = studentDAO.selectMahasiswa(npm);
		System.out.println("student : " + student);
		if (student != null) {
			model.addAttribute("student", student);
			return "detail-mahasiswa";
		} else {
			return "not-found";
		}
	}

	@RequestMapping("/mahasiswa/ubah/{npm}")
	public String ubahMahasiswa(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentDAO.selectMahasiswa(npm);
		if (student != null) {
			model.addAttribute("student", student);
			return "form-update";
		} else {
			return "not-found";
		}
	}

	@RequestMapping(value = "/mahasiswa/ubah/submit", method = RequestMethod.POST)
	public String updateMahasiswaSubmit(@ModelAttribute StudentModel student, Model model)
	{
		studentDAO.updateMahasiswa(student);
		model.addAttribute("student", student);
		return "success-update";
	}

	@RequestMapping(value = "/mahasiswa/tambah")
	public String tambahMahasiswa() {
		return "form-tambah";
	}

	@RequestMapping("/mahasiswa/tambah/submit")
	public String addSubmit(@RequestParam(value = "nama", required = false) String nama,
			@RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
			@RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
			@RequestParam(value = "jenis_kelamin", required = false) String jenis_kelamin,
			@RequestParam(value = "agama", required = false) String agama,
			@RequestParam(value = "gol_darah", required = false) String gol_darah,
			@RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
			@RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
			@RequestParam(value = "id_prodi", required = false) String id_prodi, Model model) {
		String npm_fix, order;
		String thnMasuk = tahun_masuk.substring(2);
		StudentModel student = studentDAO.selectMahasiswaByProdi(id_prodi);
		String kodeUniv = student.getProdi().getFakultas().getUniv().getKode_univ() + "";
		String kodeProdi = student.getProdi().getKode_prodi();
		String kode_jalur = getJalurMahasiswa(jalur_masuk);
		String npm = '%' + thnMasuk + kodeUniv + kodeProdi + kode_jalur + '%';
		System.out.println("npm = " + npm);
		StudentModel student_n = studentDAO.selectMahasiswaByNpm(npm);
		if (student_n == null) {
			order = "001";
		} else {
			String id = student_n.getNpm();
			String no_input = id.substring(9);
			int temp_order = Integer.parseInt(no_input);
			temp_order = temp_order + 1;
			order = temp_order + "";

		}
		npm_fix = thnMasuk + kodeUniv + kodeProdi + kode_jalur + order;
		System.out.println("npm_fix = " + npm_fix);
		StudentModel new_student = new StudentModel(0, npm_fix, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama,
				gol_darah, "Aktif", tahun_masuk, jalur_masuk, id_prodi, null);
		studentDAO.tambahMahasiswa(new_student);
		model.addAttribute("student", new_student);
		return "success-add";
	}

	public String getJalurMahasiswa(String nama_jalur) {
		if (nama_jalur.equalsIgnoreCase("Undangan Reguler/SNMPTN")) {
			return "54";
		} else if (nama_jalur.equalsIgnoreCase("Ujian Tulis Mandiri")) {
			return "62";
		} else if (nama_jalur.equalsIgnoreCase("Undangan Paralel/PPKB")) {
			return "55";
		} else if (nama_jalur.equalsIgnoreCase("Ujian Tulis Bersama/SBMPTN")) {
			return "57";
		} else if (nama_jalur.equalsIgnoreCase("Undangan Olimpiade")) {
			return "53";
		}
		return "";
	}
	
	@RequestMapping(value = "/kelulusan", method = RequestMethod.GET)
	public String lihatKelulusan() {
		return "form-persentasi-kelulusan";
	}
	
	@RequestMapping(value = "/kelulusan/submit", method = RequestMethod.GET)
	public String submitKelulusan(Model model, @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk, @RequestParam(value = "id_prodi", required = false) String id_prodi) {	
		StudentModel student = studentDAO.selectMahasiswaByProdi(id_prodi);
		String prodi = student.getProdi().getNama_prodi() + "";
		String nama_fakultas = student.getProdi().getFakultas().getNama_fakultas() + "";
		String universitas = student.getProdi().getFakultas().getUniv().getNama_univ() + "";
		String jlhAll = studentDAO.selectAktifAllMahasiswa(tahun_masuk, id_prodi);
		String jlh = studentDAO.selectAktifMahasiswa(tahun_masuk, id_prodi);
		double a = Double.parseDouble(jlh);
		double n = Double.parseDouble(jlhAll);
		double persentasi = a/n*100;
		int persent = (int) persentasi;
		int jlhLulus = (int) a;
		int jlhAll_ = (int) n;
		System.out.println("jumlah = "+a);
		System.out.println("jumlah all = "+n);
		System.out.println("persentasi1 = "+persentasi);
		model.addAttribute("tahun_masuk", tahun_masuk);
		model.addAttribute("prodi", prodi);
		model.addAttribute("fakultas", nama_fakultas);
		model.addAttribute("universitas", universitas);
		model.addAttribute("jlhLulus", jlhLulus);
		model.addAttribute("jlhAll", jlhAll_);
		model.addAttribute("persentasi", persent);
		return "view-persentasi-kelulusan";
	} 
	
	@RequestMapping(value = "/mahasiswa/cari", method = RequestMethod.GET)
	public String pilihUniversitas(Model model) {
		List<UniversitasModel> universitas = studentDAO.selectAllUniversitas();
		model.addAttribute("universitas", universitas);
		return "pilih-universitas";
	}
	
	@RequestMapping(value = "/mahasiswa/cari/submit", method = RequestMethod.GET)
	public String submitUniversitas(Model model, @RequestParam(value = "kode_univ", required = false) String kode_univ) {
		System.out.println("universitas="+kode_univ);
		//System.out.println("nama="+nama_univ);
		return "pilih-fakultas";
	}

}
