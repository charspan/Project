package com.inxedu.os.controller.website;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.constants.enums.WebSiteProfileType;
import com.inxedu.os.entity.website.WebsiteProfile;
import com.inxedu.os.service.website.WebsiteProfileService;

/**
 * 网站配置管理
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteProfileController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteProfileController.class);
	@Autowired
	private WebsiteProfileService websiteProfileService;

	private static final String getWebSiteList = getViewPath("/admin/website/profile/website_profile_list");// 网站配置管理页面
	private static final String toupdateWebSite = getViewPath("/admin/website/profile/website_profile_update");// 更新网站配置管理页面
	private static final String getWebSiteOnline = getViewPath("/admin/website/profile/website_profile_online");// 在线咨询

	private static final String ICONAME="favicon.ico";//定义ico文件常量
	/**
	 * 查询网站配置 根据Type
	 */
	@RequestMapping("/websiteProfile/find/{type}")
	public String getWebSiteList(HttpServletRequest request, Model model, @PathVariable("type") String type) {
		String returnUrl = "";
		try {
			if (WebSiteProfileType.ico.toString().equals(type)) {// 类型是ico文件
				return getWebSiteList;
			}
			String flag = request.getParameter("flag");
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(type);
			if (StringUtils.isNotEmpty(flag)) {
				returnUrl = toupdateWebSite;// 跳转到更新页面
			} else {
				returnUrl = getWebSiteList;// 列表页
			}
			model.addAttribute("webSiteMap", map);
			model.addAttribute("type", type);
		} catch (Exception e) {
			logger.error("getWebSiteList", e);
		}
		return returnUrl;
	}
	/**
	 * 更新管理根据类型
	 */
	@RequestMapping("/websiteProfile/update")
	public String updateWebSiteByType(HttpServletRequest request, Model model, @RequestParam("type") String type) {
		try {
			if (ObjectUtils.isNotNull(type) && StringUtils.isNotEmpty(type)) {
				Gson gson = new Gson();
				JsonParser jsonParser = new JsonParser();
				Map<String, String> map = new HashMap<String, String>();
				if (type.equals(WebSiteProfileType.web.toString())) {
					map.put("email", request.getParameter("email"));// 公司邮箱
					map.put("phone", request.getParameter("phone"));// 24小时电话
					map.put("workTime", request.getParameter("workTime"));// 工作时间
					map.put("copyright", request.getParameter("copyright"));// 备案
					map.put("author", request.getParameter("author"));// 作者
					map.put("keywords", request.getParameter("keywords"));// 关键词
					map.put("description", request.getParameter("description"));// 描述
					map.put("title", request.getParameter("title"));//
					map.put("company", request.getParameter("company"));// 网校名称
				}
				// 类型为alipay
				if (type.equals(WebSiteProfileType.alipay.toString())) {
					map.put("alipaypartnerID", request.getParameter("alipaypartnerID"));// 合作者身份ID
					map.put("alipaykey", request.getParameter("alipaykey"));// 交易安全校验码
					map.put("sellerEmail", request.getParameter("sellerEmail"));// 商家邮箱
					map.put("status", request.getParameter("status"));// 支付宝选择（企业和个人）
				}

				// 类型为logo
				if (type.equals(WebSiteProfileType.logo.toString())) {// 类型为logo
					map.put("url", request.getParameter("url"));// clientID
				}
				// 统计代码
				if (type.equals(WebSiteProfileType.censusCode.toString())) {
					map.put("censusCodeString", request.getParameter("censusCodeString"));// thirdloginstatus第三方登录是否开启
				}
				// 将map转化json串
				JsonObject jsonObject = jsonParser.parse(gson.toJson(map)).getAsJsonObject();
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
					websiteProfile.setType(type);
					websiteProfile.setDesciption(jsonObject.toString());
					websiteProfileService.updateWebsiteProfile(websiteProfile);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteProfileController.updateWebSiteByType", e);
		}
		return "redirect:/admin/websiteProfile/find/" + type;
	}

	/**
	 * 上传ico文件
	 */
	@RequestMapping("/websiteProfile/uploadIco")
	public String uploadIcoFile(HttpServletRequest request, @RequestParam("icoFile") MultipartFile icoFile) {
		try {
			if (!icoFile.isEmpty()) {
				// 获得项目的真实路径
				String path = request.getSession().getServletContext().getRealPath("");
				File file = new File(path + "/" + ICONAME);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					icoFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error("uploadIcoFile", e);
		}
		return "redirect:/admin/websiteProfile/find/ico";
	}

	/**
	 * 查询在线咨询
	 */
	@RequestMapping("/websiteProfile/online")
	public String getWebsiteOnline(HttpServletRequest request, Model model) {
		try {
			// 查询在线咨询详情
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.online.toString());
			model.addAttribute("websiteonlinemap", map);
		} catch (Exception e) {
			logger.error("getWebsiteOnline", e);
			return setExceptionRequest(request, e);
		}
		return getWebSiteOnline;
	}

	/**
	 * 更新WebsiteOnline
	 */
	@RequestMapping("/websiteProfile/online/update")
	public String updateWebsiteOnline(HttpServletRequest request) {
		try {
			JsonParser jsonParser = new JsonParser();
			Map<String, String> map = new HashMap<String, String>();
			map.put("onlineUrl", request.getParameter("onlineUrl"));// 链接
			map.put("onlineImageUrl", request.getParameter("onlineImageUrl"));// 图片链接
			map.put("onlineKeyWord", request.getParameter("onlineKeyWord"));// 开关
			// 将map转化json串
			JsonObject jsonObject = jsonParser.parse(gson.toJson(map)).getAsJsonObject();
			if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
				WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
				websiteProfile.setType(WebSiteProfileType.online.toString());
				websiteProfile.setDesciption(jsonObject.toString());
				websiteProfileService.updateWebsiteProfile(websiteProfile);
			}
		} catch (Exception e) {
			logger.error("updateWebsiteOnline", e);
		}
		return "redirect:/admin/websiteProfile/online";
	}
	

}