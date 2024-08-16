package com.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ApiDTO;
import com.service.ApiService;


@Controller
@RequestMapping("/api/*")
public class ApiController {
	
	@Autowired
	private ApiService service;
	
	
	final static String serviceKey ="%2FGGZmEdzZnhjkYFJbEOPkg%2BO0Nd1ykFdhmEXzqezvpj9WGow81k2Jk7yjPYsVoJBR84LrkxanZ0MU5KYPAI18Q%3D%3D";
	
	@GetMapping("/page")
	public String page() {
		return "api/page";
	}
	@ResponseBody
	@PostMapping(value = "get", produces = "application/json;charset=utf-8")
	public String getData(String mobileOS) throws Exception  {
		String url = "https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1?"
				+ "numOfRows=50&MobileOS=lOS&MobileApp=aaa&_type=json&"
				+ "serviceKey=%2FGGZmEdzZnhjkYFJbEOPkg%2BO0Nd1ykFdhmEXzqezvpj9WGow81k2Jk7yjPYsVoJBR84LrkxanZ0MU5KYPAI18Q%3D%3D";
		
		
		//단순한 문자열로 정의한 url을 자바에서 활용할 수 있는 객체로 변환
		URL requestURL = new URL(url);
		//목적지로 향하는 다리 건설
		HttpURLConnection conn = (HttpURLConnection)requestURL.openConnection();
		
		conn.setRequestMethod("GET");
		
		//conn 다리가 건설되어 있는 목적지로부터 데이터를 읽어와야 함
		//1. conn 목적지로 InputStream 생성( conn.getInputStream() )
		InputStream is = conn.getInputStream();
		//2. 생성된 InputStream을 이용하기 위한 객체 생성( new InputStreamReader(is) ) 
		InputStreamReader isr = new InputStreamReader(is);
		//3. InputStreamReader 객체보다 편한 BufferedReader 사용을 위해 객체 생성
		BufferedReader br = new BufferedReader(isr);
		
		//생성된 BufferedReader를 이용해서 데이터를 읽고 활용하기
		String result = "";
		String line = null;
		while(true) {
			line = br.readLine();
			if(line == null) { break; }
			result += line;
		}
		System.out.println("결과");
		//System.out.println(result);
		System.out.println("물");
		br.close();
		conn.disconnect();
		return result;
	}
	
	@PostMapping("add")
	public ResponseEntity<String> apiAdd(@RequestBody List<ApiDTO> apiList) throws Exception {
	    //System.out.println(apiList);
	    boolean allSuccess = true;

	    for (ApiDTO api : apiList) {
	        if (!service.regists(api)) {
	            allSuccess = false;
	            break;
	        }
	    }

	    if (allSuccess) {
	        return ResponseEntity.ok("성공");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
	    }
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ApiDTO> updatePhoto(
	        @PathVariable Long id, // URL 경로에서 ID를 추출
	        @RequestBody ApiDTO updatedPhoto) {
	    boolean success = service.updatePhoto(id, updatedPhoto);
	    if (success) {
	    	
	    	return new ResponseEntity<ApiDTO>( updatedPhoto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<ApiDTO>(updatedPhoto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePhoto(@PathVariable Long id) {
        System.out.println(id);
        System.out.println("여기옴??");
        boolean success = service.deletePhoto(id);

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);

        if (success) {
            response.put("message", "데이터가 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "데이터 삭제 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

	


}
