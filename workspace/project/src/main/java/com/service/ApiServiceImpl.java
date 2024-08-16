package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.ApiDTO;
import com.mapper.ApiMapper;


@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	private ApiMapper apimapper;
	
	public boolean regists(ApiDTO api) {
		int row = apimapper.insertApi(api);
		if(row != 1) {
			return false;
		}
			return true;
	}
	@Override
    public boolean updatePhoto(Long id, ApiDTO updatedPhoto) {
        updatedPhoto.setId(id); // ID 설정 (업데이트할 객체의 ID)
        int row = apimapper.updateApi(updatedPhoto);
        return row == 1;
    }

    @Override
    public boolean deletePhoto(Long id) {
        int row = apimapper.deleteApi(id);
        return row == 1;
    }

}
