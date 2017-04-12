package cn.tz.www.admin.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.tz.www.admin.config.Config.Values;
import cn.tz.www.customer.entity.table.Slide;
import cn.tz.www.customer.entity.tools.CommonUtil;
import cn.tz.www.customer.entity.tools.Constants;
import cn.tz.www.customer.entity.tools.JsonObj;

@Controller
public class SlideController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private Values values;
	@PostMapping(value = "/slide/addSlide")
	@ResponseBody
	public JsonObj addSlide(Slide slide, Principal principal, HttpServletRequest request) {
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户信息过期请重新登录");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		//String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String savePath = values.getUploadedImagesDir();
		if (CommonUtil.isNull(savePath)) {// 没有配置特定目录 就保存到temp目录
			savePath = servletContext.getRealPath("/") + "temp";
		}
		savePath += "/slide/";
		String extName = "";// 扩展名

		String newFileName = "";
		Iterator<String> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String nowTime = CommonUtil.getNow(6, null);// 当前时间 毫秒数
			String fileName = it.next();
			String orginFileName = fileName;
			MultipartFile uploadify = multipartRequest.getFile(fileName);

			String filename = uploadify.getOriginalFilename();
			File dir = new File(savePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (filename.lastIndexOf(".") >= 0) {
				extName = filename.substring(filename.lastIndexOf("."));
			}
			newFileName = nowTime + extName;

			File file = new File(savePath + newFileName);

			try {
				uploadify.transferTo(file);
			} catch (IllegalStateException | IOException e) {
			
				e.printStackTrace();
			}
			CommonUtil.thumbnailImage(file, Constants.THUMBNAIL_WIDTH, Constants.THUMBNAIL_HEIGHT, "small_", false);// 生成缩略图
			slide.setImgPath("slide/small_" + newFileName);

		}
		
		return JsonObj.newSuccessJsonObj("创建用户成功");
	}
}
