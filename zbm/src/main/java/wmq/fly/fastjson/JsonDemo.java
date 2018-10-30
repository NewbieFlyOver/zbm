package wmq.fly.fastjson;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 使用fastjson实现json格式数据与对象之间相互转化
 *
 */
public class JsonDemo {
	
	public static void main(String[] args) {
		//jsonStrToJsonObject();
		//jsonStrToObject();
		//toJsonObjectStr();
		ObjectToJsonObjectStr();
	}
	
	/**
	 * 将json字符串转化为json对象
	 */
	public static void jsonStrToJsonObject() {
		String jsonStr = "{\"sites\":[{\"name\":\"三多\",\"url\":\"不抛弃\"},{\"name\":\"史今\",\"url\":\"不放弃\"}]}";
		JSONObject jsonObject = new JSONObject();
		// 将json字符串转为jsonbject
		JSONObject jsonStrObject = jsonObject.parseObject(jsonStr);
		JSONArray jsonArray = jsonStrObject.getJSONArray("sites");
		for (Object object : jsonArray) {
			JSONObject stObject = (JSONObject) object;
			String name = stObject.getString("name");
			String url = stObject.getString("url");
			System.out.println(name + "---" + url);
		}
	}
	
	/**
	 * 将json字符串转化为对象
	 */
	public static void jsonStrToObject() {
		String jsonStr = "{\"name\":\"不抛弃，不放弃\",\"id\":\"0\",\"items\":[{\"itemName\":\"三多\",\"itemId\":\"1\"},{\"itemName\":\"史今\",\"itemId\":\"2\"}]}";
		//使用java反射机制 对应生成对象
		User user = new JSONObject().parseObject(jsonStr, User.class);
		System.out.println("user:" + user.toString());
	}
	
	/**
	 * Json api封装json
	 */
	public static void toJsonObjectStr() {
		JSONObject root = new JSONObject();
		root.put("id", "1");
		root.put("name", "成才");
		JSONArray arrayList = new JSONArray();
		JSONObject object1 = new JSONObject();
		object1.put("itemId", "2");
		object1.put("itemName", "醒悟");
		JSONObject object2 = new JSONObject();
		object2.put("itemId", "3");
		object2.put("itemName", "成长");
		arrayList.add(object1); 
		arrayList.add(object2);
		root.put("items", arrayList);
		System.out.println(root.toJSONString());
	}
	
	/**
	 * 将对象转换成json字符串
	 */
	public static void ObjectToJsonObjectStr() {
		User user = new User();
		user.setId("1");
		user.setName("六一");
		List<Item> items =new ArrayList<Item>();
		Item item1 = new Item();
		item1.setItemId("2");
		item1.setItemName("坚韧");
		Item item2 = new Item();
		item2.setItemId("3");
		item2.setItemName("优秀");
		items.add(item1);
		items.add(item2);
		user.setItems(items);
		System.out.println(new JSONObject().toJSONString(user));
	}
	
}


class Item {
	private String itemId;
	private String itemName;

	public String getItemId() {

		return itemId;
	}

	public void setItemId(String itemId) {

		this.itemId = itemId;
	}

	public String getItemName() {

		return itemName;
	}

	public void setItemName(String itemName) {

		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + "]";
	}
}

class User {
	private String id;
	private String name;
	private List<Item> items;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public List<Item> getItems() {

		return items;
	}

	public void setItems(List<Item> items) {

		this.items = items;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", items=" + items + "]";
	}

}
