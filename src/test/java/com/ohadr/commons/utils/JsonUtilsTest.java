package com.ohadr.commons.utils;

import java.util.*;

import org.junit.Test;
import com.ohadr.common.utils.JsonUtils;
import junit.framework.Assert;

public class JsonUtilsTest
{
	@Test
	public void testJsonUtils()
	{
		Map<String,Object> map = new HashMap<>();
		map.put("ohads", "value");
		
		String json = JsonUtils.convertToJson(map);
		
		System.out.println(json);	//		{"ohads":"value"}

		Assert.assertEquals("{\"ohads\":\"value\"}", json);

	}
}
