package com.badilong.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.badilong.search.model.Item;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
	
	/**
	 * 根据区间价格查询
	 * @param price1
	 * @param price2
	 * @return
	 */
	List<Item> findByPriceBetween(double price1,double price2);

}
