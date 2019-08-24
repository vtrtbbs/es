package com.badilong.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

}
