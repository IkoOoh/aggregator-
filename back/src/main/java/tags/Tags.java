package tags;

import java.io.Serializable;
import java.util.ArrayList;

public class Tags implements Serializable{
	
	public String categoryName;
	
	public ArrayList<String> childCategories;
	
	public String parentName;
	
	public Tags(){}
}
