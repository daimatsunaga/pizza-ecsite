package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

@Repository
public class ItemRepository {
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER 
		= new BeanPropertyRowMapper<>(Item.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 全件検索
	 * @return　item全件
	 */
	public List<Item> findAll() {
		String sql = "SELECT * FROM items ORDER BY prise_m ASC;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	/**
	 * 曖昧検索
	 * @param name
	 * @return nameによる曖昧検索結果数件
	 */
	public List<Item> findByName(String name) {
		String sql = "SELECT * FEOM items WHERE name LIKE :name ORDER BY price_m ASC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
}