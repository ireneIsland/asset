package com.island.asset.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.island.asset.domain.AttachDoc;

@Component("FileUtils")
public class FileUtilsHandler {

	public String uploadFile(MultipartFile files, String assetNo) {

		FileUtilsHandler fuh = new FileUtilsHandler();

		String cusPath = fuh.settingPath();
		String fileName = assetNo + "_" + files.getOriginalFilename();

		File dest = new File(cusPath, fileName);
		System.out.println("上傳路徑: " + dest);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("檔案上傳失敗");
		}

		System.out.println("upload success.");
		System.out.println("檔案名稱: " + fileName);
		return fileName.toString();
	}

	public void deleteFile(String fileName) {
		
		FileUtilsHandler fuh = new FileUtilsHandler();
		String cusPath = fuh.settingPath();

		try {

			File file = new File(cusPath, fileName);
			System.out.println(file);

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		}
		return null;
	}

	public void deleteAttachDocList(List<AttachDoc> attachDocList) {
		
		System.out.println("執行刪除動作");
		FileUtilsHandler fileUtilsHandler = new FileUtilsHandler();
		
		for(AttachDoc ad : attachDocList) {
			
			if(!ad.getPath().isEmpty() && ad.getPath() != null) {
				fileUtilsHandler.deleteFile(ad.getPath());
			}
		}
		
	}
}
