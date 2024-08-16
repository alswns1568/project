package com.service;

import com.dto.ApiDTO;

public interface ApiService {
	//insert
	boolean regists(ApiDTO api);
	
	// Update
    boolean updatePhoto(Long id, ApiDTO updatedPhoto);

    // Delete
    boolean deletePhoto(Long id);
}
