package com.badilong.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.badilong.search.model.Item;
import com.badilong.search.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadilongSearchApplicationTests {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void index() {
		List<Item> list = new ArrayList<>();
		 list.add(new Item(11L, "坚果3手机R0", " 手机1", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
	    list.add(new Item(12L, "坚果手5机R1", " 手机1", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
	    list.add(new Item(13L, "华为5META10", " 手机1", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
	    list.add(new Item(14L, "华为5META10", " 手机1", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
	    // 接收对象集合，实现批量新增
	    itemRepository.saveAll(list);
	}
	@Test
	public void modifyItem() {
		Item item = new Item(1L, "苹果iphone8", " 手机","苹果", 3499.00, "http://image.baidu.com/13123.jpg");
	    itemRepository.save(item);
	}
	
	@Test
	public void queryAll() {
		// 查找所有
        //Iterable<Item> list = itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = itemRepository.findAll(Sort.by("price").ascending());
        list.forEach(item->{
        	System.out.println("---title---"+item.getTitle()+"------price-----"+item.getPrice());
        });
	}
	
	@Test
	public void queryByPriceBetween() {
		 Iterable<Item> list = itemRepository.findByPriceBetween(2000.00, 3500.00);
		 list.forEach(item->{System.out.println("--title--"+item.getTitle()+"--price--"+item.getPrice());});
	}
	
	//@Test
	public void search() {		
		//构建基本查询条件
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();		
		//添加基本分词查询
		queryBuilder.withQuery(QueryBuilders.matchQuery("title","手机"));
		// 搜索结果
		Page<Item> items = itemRepository.search(queryBuilder.build());
		// 总条数
		long total = items.getTotalElements();
		System.out.println("total = " + total);
		for (Item item : items) {
	        System.out.println("-查到了-title--"+item.getTitle());
	    }		
	}
	
	@Test
	public void searchByPage() {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		queryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));
		// 分页：
	    int page = 0;
	    int size = 2;
	    queryBuilder.withPageable(PageRequest.of(page,size));
	   // 搜索，获取结果
	    Page<Item> items = itemRepository.search(queryBuilder.build());
	    // 总条数
	    long total = items.getTotalElements();
	    System.out.println("总条数 = " + total);
	    // 总页数
	    System.out.println("总页数 = " + items.getTotalPages());
	    // 当前页
	    System.out.println("当前页：" + items.getNumber());
	    // 每页大小
	    System.out.println("每页大小：" + items.getSize());
	    for (Item item : items) {
	        System.out.println("======"+item.getTitle());
	    }
	    
	}
}
