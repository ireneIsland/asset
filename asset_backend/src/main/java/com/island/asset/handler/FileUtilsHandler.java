package com.island.asset.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.island.asset.domain.AttachDoc;

@Component("FileUtils")
public class FileUtilsHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileUtilsHandler fuh;
	
	/**
	 * 上傳多筆檔案
	 * 將增值過的流水編號回傳
	 * @param files
	 * @param seqs
	 * @param assetNo
	 * @return
	 * @throws Exception 
	 */
	public List<Object> uploadFileList(MultipartFile[] files, String[] seqs, String assetNo) throws Exception {
		logger.info("upload AttachDocs Start!");
		logger.info("total of ["+ seqs.length +"] files are ready to upload");
		List<Object> list = new ArrayList<>();
		for(int i=0; i<seqs.length; i++) {
    		Map<String, Object> map = new HashMap<>();
    		String filePath = fuh.uploadFile(files[i], assetNo);
    		
    		map.put("seq", seqs[i]);
    		map.put("path", filePath);
    		list.add(map);
    	} 
		
		return list;
	}
	
	/**
	 * 刪除多筆檔案
	 * @param attachDocList
	 */
	public void deleteAttachDocList(List<AttachDoc> attachDocList) {
		logger.info("delete AttachDocs Start!");

		for (AttachDoc ad : attachDocList) {

			if (!ad.getPath().isEmpty() && ad.getPath() != null) {
				fuh.deleteFile(ad.getPath());
			}
		}
	}

	/**
	 * 上傳一筆檔案
	 * @param files
	 * @param assetNo
	 * @return
	 * @throws Exception 
	 */
	public String uploadFile(MultipartFile files, String assetNo) throws Exception {

		String cusPath = fuh.settingPath();
		String fileName = assetNo + "_" + files.getOriginalFilename();

		File dest = new File(cusPath, fileName);
		logger.info("upload Path: " + dest);
		/**
		 * 判斷目錄是否存在
		 */
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		/**
		 * 儲存檔案
		 */
		try {
			files.transferTo(dest);

		} catch (IllegalStateException | IOException e) {
			logger.info(dest + "Upload operation failed.");
			logger.error(e.getMessage());
			throw e;
		}

		logger.info("upload success.");
		return fileName.toString();
	}

	/**
	 * 刪除一筆檔案
	 * @param fileName
	 */
	public void deleteFile(String fileName) {

		String cusPath = fuh.settingPath();

		try {

			File file = new File(cusPath, fileName);

			if (file.delete()) {
				logger.info(file.getName() + " is deleted!");
			} else {
				logger.error("Delete operation is failed.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 取得專案路徑&設定存放路徑
	 * 
	 * @return
	 */
	public String settingPath() {
		try {
			String projectContextPath = ResourceUtils.getURL("asset_UploadFile").getPath();
			return projectContextPath;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
