package vankhai.bookshop.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import vankhai.bookshop.entity.Sach;

@Repository(value = "SachDao")
@Transactional(rollbackFor = Exception.class)
public class SachDao {

	@Autowired
	SessionFactory sessionFactory;

	public String processFile(MultipartFile file) {
		String UPLOADED_FOLDER = "D:/JavaWeb/CuoiKy/BookShopMVC/src/main/webapp/img/";
		try {
			byte[] bytes = file.getBytes();
			Path path = (Path) Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getOriginalFilename();

	}
	
	public void themSach( Sach sach) {
//		Sach save=new Sach();
//		save.setTenSach(sach.getTenSach());
//		save.setIdTacGia(sach.getTacGia());
//		save.setIdTheLoai(sach.getIdTheLoai());
//		save.setHinhAnh(sach.getHinhAnh());
//		save.setGhiChu(sach.getGhiChu());
//		save.setDonGia(sach.getDonGia());
		Session session=this.sessionFactory.getCurrentSession();
		session.save(sach);
		
		
	}
	public List<Sach> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "from " + Sach.class.getName();
		Query<Sach> query = session.createQuery(sql);
		return query.getResultList();
	}

}
