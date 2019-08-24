package com.badilong.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.badilong.search.model.Item;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

}
