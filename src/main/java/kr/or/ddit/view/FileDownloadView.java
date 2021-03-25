package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;


public class FileDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// d:/upload/sally.png
		String realFilename = (String)model.get("realFilename");
		String filename = (String)model.get("filename");
		
		response.setHeader("Content-Disposition", "attachment; filename="+filename);
		
		ServletOutputStream sos = response.getOutputStream();
		
		FileInputStream fis = new FileInputStream(new File(realFilename));
		byte[] buf = new byte[512];
		
		while(fis.read(buf) != -1) {
			sos.write(buf);
		}
		 
		fis.close();
		sos.flush();
		sos.close();
		
		
	}
	
}
