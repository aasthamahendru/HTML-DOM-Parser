package domParser;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Aastha
 *
 */


public class Node {
	
	private String tagName;
	private List<String> innerText = new ArrayList<String>();
	private List<Node> childNodes = new ArrayList<Node>();
	
	
	public Node(String tag) {
		tagName = tag;
	}
	
	
	public String getTag() {
		return tagName;
	}
	
	public void setTag(String tag) {
		this.tagName = tag;
	}
	
	
	public List<String> getInnerText() {
		return innerText;
	}
	
	public void setInnerText(List<String> innerHTML) {
		this.innerText = innerHTML;
	}
	
	
	public List<Node> getChildNodes() {
		return childNodes;
	}
	
	public void setChildNodes(List<Node> childNodes) {
		this.childNodes = childNodes;
	}
	
	public void appendInnerText (String str) {
		this.getInnerText().add(str);
	}

}
