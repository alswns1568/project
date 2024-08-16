package com.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.dto.ApiDTO;

@Mapper
public interface ApiMapper {

	// insert
	int insertApi(ApiDTO api);

	// Update
	int updateApi(ApiDTO api);

	// Delete
	int deleteApi(Long id);
}
