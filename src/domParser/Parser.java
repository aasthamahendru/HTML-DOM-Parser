package domParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Aastha
 *
 */


public class Parser {
	
	
	
	public static DOM simpleHTMLParser (String html) throws Exception {
        //implement method to build the DOM Structure, return the root DOM
		
		DOM dom = new DOM();
		
		StringBuilder inputString = new StringBuilder(html);
		
		Node root = parser(inputString);
		
		if (root == null) {
			throw new Exception();
		}
		
		dom.setRoot(root);
		
		return dom;
		
	}

	public static Node parser(StringBuilder inputString) {
		
		Stack<Node> tags = new Stack<>();
		
		String tagPattern = "<(.*?)>";
		
		Pattern pattern = Pattern.compile(tagPattern);
		Matcher matcher = pattern.matcher(inputString);
		
		int startIndex = 0;

		while (matcher.find(startIndex)) {

			String tagName = matcher.group(1);
			
			startIndex += matcher.group(0).length();

			if (tagName.charAt(0) == '/' && !tags.isEmpty()) {

				if (tagName.substring(1).equals(tags.peek().getTag())) {

					Node tmp = tags.pop();

					if (!tags.isEmpty()) {

						tags.peek().getChildNodes().add(tmp);

						if (inputString.indexOf("<", startIndex) > startIndex) {

							tags.peek().getInnerText().add(inputString.substring(startIndex, inputString.indexOf("<", startIndex)));

							startIndex = inputString.indexOf("<", startIndex);

						}
					}
					else {
						
						if (startIndex != inputString.length()) {
							return null;							//invalid
						}
						
						return tmp;									//root node
					
					}
				}
				else {

					return null;									//invalid

				}
			}
			else {

				Node n = new Node(tagName);

				if (inputString.indexOf("<", startIndex) > startIndex) {

					n.getInnerText().add(inputString.substring(startIndex, inputString.indexOf("<", startIndex)));

					startIndex = inputString.indexOf("<", startIndex);

				}

				tags.push(n);

			}
		}

		return null;
		
	}

	
	
	public static String printDom (DOM dom) {
        //implement method to print the DOM Structure
		
		String outputString;
		
		Node root = dom.getRoot();
		
		int count = 0;
		
		
		if (root == null) {
			return null;
		}
		
		outputString = printNode(root, count);
		
		
		return outputString;
	}     
	
	
	
	public static String printNode(Node node, int count) {
		
		StringBuilder outputString = new StringBuilder();
		
		List<String> innerHTML = new ArrayList<String>();
		List<Node> childNodes = new ArrayList<Node>();
		
		
		outputString.append( "\n" + repeater("\t", count) + "Tag: " + node.getTag());
		
		count +=1;
		
		innerHTML = node.getInnerText();
		childNodes = node.getChildNodes();
		
		for (String str : innerHTML) {
			outputString.append("\n" + repeater("\t", count) + "InnerHTML: " + str);
		}
		
		outputString.append("\n" + repeater("\t", count) + "MoreInfo: ");
		
		if (childNodes.isEmpty()) {
			outputString.append("<empty>");
		}
		
		else {
			count += 1;
			for (Node n : childNodes) {
				outputString.append(printNode(n, count));
			}
		}
	
		return outputString.toString();
		
	}
	
	
	 
	public static String repeater (String str, int count) {
		
		String result = "";
		
		for (int i = 0; i < count; i++) {
			result = result + str;
		}
		
		return result;
	}



	public static boolean compareDom (DOM dom1, DOM dom2){
        //implement comparison method
		
		Node root1 = dom1.getRoot();
		Node root2 = dom2.getRoot();
		
		return compareNodes(root1, root2);
	}



	public static boolean compareNodes (Node node1, Node node2) {
		
		boolean result = true;
		
		if	(node1 == null && node2 == null) {
			return true;
		}
		
		if (!node1.getTag().equals(node2.getTag())) {
			return false;
		}
		
		if (node1.getInnerText().size() != node2.getInnerText().size()) {
			return false;
		}
		
		if (node1.getInnerText().size() > 0) {
			
			for (int i = 0; i < node1.getInnerText().size(); i++) {
				if (!node1.getInnerText().get(i).equals(node2.getInnerText().get(i))) {
					return false;
				}
			}
		}
		
		if (node1.getChildNodes().size() != node2.getChildNodes().size()) {
			return false;
		}
		
		if (node1.getChildNodes().size() > 0) {
			
			for (int i = 0; i < node1.getChildNodes().size(); i++) {
				result = result && compareNodes(node1.getChildNodes().get(i), node2.getChildNodes().get(i));
			}
		}
		else {
			return true;
		}
		
		return result;
	}



	public static void main(String[] args) {
		
		DOM dom, dom2;
		
		String input = "<div1>asdf<div2>cvasd</div2>ere</div1>";
		String input2 = "<html><div1>a<sdf<div2>cvasd</div2>ere</div1></html>";
		
		try {
			dom  = simpleHTMLParser(input);
	
			System.out.println("DOM Structure 1: ");
			
			System.out.println(printDom(dom));
		
		}
		catch (Exception e) {
			
			System.out.println("Error with input 1.");
			return;	
		}
		
		
		try {
			dom2  = simpleHTMLParser(input2);
			 
			System.out.println("\n\nDOM Structure 2: ");
				
			System.out.println(printDom(dom2));
		}
		catch (Exception e) {
			
			System.out.println("Error with input 2.");
			return;	
		}

				
		System.out.println("\n\n" + "Comparing both DOM structures: " + compareDom(dom, dom2));
		

	}

}
