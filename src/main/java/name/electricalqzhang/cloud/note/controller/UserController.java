 package name.electricalqzhang.cloud.note.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.UserService;
import name.electricalqzhang.cloud.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Resource
	private UserService userService;

	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name, String password, HttpSession session) {
		User user = userService.login(name, password);
        /* 将信息保存在session中 */
        session.setAttribute("loginUser", user);
		return 	new JsonResult(user);
	}

	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name, String nick, String password, String confirm) {
		User user = userService.regist(name, nick, password, confirm);
		return new JsonResult(user);
	}

	
	@RequestMapping(value="/image.do", produces="image/png")
	@ResponseBody
	public byte[] image() throws Exception {
		return createPng();
	}
	
	@RequestMapping(value="/downloadimg.do", produces="application/octet-stream")
	@ResponseBody
	public byte[] downloadImg(HttpServletResponse res) throws IOException {
		res.setHeader("Content-Disposition", "attachment;filename=\"demo.png\"");
		return createPng();
	}
	
	@RequestMapping(value="/downloadexcel.do", produces="application/octet-stream")
	@ResponseBody
	public byte[] downloadExcel(HttpServletResponse res) throws IOException {
		res.setHeader("Content-Disposition", "attachment;filename=\"demo.xls\"");
		return createExcel();
	}
	
	@RequestMapping(value="/upload.do")
	@ResponseBody
	public JsonResult upload(MultipartFile userfile1, MultipartFile userfile2) throws IllegalStateException, IOException {
		String file1 = userfile1.getOriginalFilename();
		String file2 = userfile2.getOriginalFilename();

		System.out.println(file1);
		System.out.println(file2);
		
		File dir = new File("D:/demo");
		dir.mkdir();
		File f1 = new File(dir, file1);
		File f2 = new File(dir, file2);
		
//		userfile1.transferTo(f1);
//		userfile1.transferTo(f2);
		InputStream in1 = userfile1.getInputStream();
		FileOutputStream out1 = new FileOutputStream(f1);
		int b;
		while ((b=in1.read()) != -1) {
			out1.write(b);
		}
		in1.close();
		out1.close();
		
		InputStream in2 = userfile2.getInputStream();
		FileOutputStream out2 = new FileOutputStream(f2);
		byte[] buf = new byte[8*1024];
		int n;
		while ((n=in2.read(buf)) != -1) {
			out2.write(buf, 0, n);
		}
		in2.close();
		out2.close();
		
		return new JsonResult(true);
	}
	
	private byte[] createPng() throws IOException {
		BufferedImage img = new BufferedImage(200, 80, BufferedImage.TYPE_3BYTE_BGR);
		img.setRGB(100, 40, 0xffffff);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		out.close();
		byte[] png = out.toByteArray();
		return png;
	}
	
	private byte[] createExcel() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Demo");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("java learning");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		out.close();
		return out.toByteArray();
	}
}
