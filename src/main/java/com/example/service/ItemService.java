package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 名前による曖昧検索
	 * 該当がない場合には全件を返す
	 * @param name
	 * @return　item数件
	 */
	public List<Item> findByName(String name) {
		if(name == null) {
			return itemRepository.findAll();
		} else {
			return itemRepository.findByName(name);
		}
	}
}
