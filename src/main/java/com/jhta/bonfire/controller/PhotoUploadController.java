package com.jhta.bonfire.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoUploadController {
    private final static Logger logger = LoggerFactory.getLogger(PhotoUploadController.class);
    
    @Autowired private ServletContext sc;
	
	@PostMapping(value="/upload/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public HashMap<String, String> uploadSummernoteImageFile(
        @RequestParam("file") MultipartFile multipartFile
        // , @PathVariable String boardName
        )
    {
		HashMap<String, String> map = new HashMap<String, String>();
		String path = sc.getRealPath("/resources/TemporalFileStorage");	// 서버에 파일 저장 경로 구하기.  /resources/upload/ "게시판table명" 으로 게시판별 저장되는 이미지파일 경로를 다르게 하면 어떨지?
		String fileName = UUID.randomUUID() +"."+ FilenameUtils.getExtension(multipartFile.getOriginalFilename());	//저장될 파일 명
        File file = Paths.get(path, fileName).toFile();
		
        try (InputStream fileStream = multipartFile.getInputStream();) {
            FileUtils.copyInputStreamToFile(fileStream, file); // 파일 저장 copyInputStreamToFile( 넘어온 data , 저장할 경로)
            map.put("fileName", fileName); // ajax로 보낼 url 경로 지정 흠 여기부분에서 /resources/upload/
            map.put("url", "/resources/TemporalFileStorage/" + fileName); // ajax로 보낼 url 경로 지정 흠 여기부분에서 /resources/upload/
                                                             // "게시판table명" 하면 좋을꺼같은데 어떤지?
            map.put("responseCode", "success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(file); // 저장된 파일 삭제
            map.put("responseCode", "error");
            logger.info("error:" + e);
        }
		return map;
	}
}