package domParser;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * @author Aastha
 *
 */


@RunWith(value = Parameterized.class)
public class DOMTests {
	String str1;
	String str2;
	boolean compareResult;
	
	public DOMTests(String str1, String str2, boolean compareResult){
		this.str1 = str1;
		this.str2 = str2;
		this.compareResult = compareResult;
	}
	
	 @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][]{
	                {"<div1>asdf<div2>cvasd</div2>ere</div1>", "<div1>asdf<div2>vasd</div2>ere</div1>",false},
	                {"<div1>asdf<div2>cvasd</div2>ere</div1>", "<div1>asdfc<div2>va<div3>s</div3>d</div2>ere</div1>", false},
	                {"<div1>asdf<div2>cvasd</div2>ere</div1>", "<div1>asdf<div2>cvasd</div2>ere</div1>",true},
	                {"<div1>asdf<div2>cvasd</div2><div3>yty</div3>ere</div1>", "<div1>asdf<div2>cvasd</div2><div3></div3>ere</div1>",false}
	        });
	    }
	    
	    @Test
	    public void parseAndCompare() {
	    	DOM dom1 = null, dom2 = null;
			try {
				dom1 = Parser.simpleHTMLParser(str1);
			
				dom2 = Parser.simpleHTMLParser(str2);
			} catch (Exception e) {
				System.out.println("Error with input.");
				
			}			
			assertThat(Parser.compareDom(dom1, dom2), is(compareResult));

	    }
	    
//	    @Test
//	    public void parseTest1(){
//	    	String str = "<div1>asdf<div2>cvasd</div2>ere</div1>";
//	    	DOM dom1 = new DOM();
//	    	Node root = new Node("div1");
//	    	root.getInnerText().addAll(Arrays.asList("asdf","ere"));
//	    	
//	    	Node n = new Node("div2");
//	    	n.getInnerText().add("cvasd");
//	    	root.getChildNodes().add(n);
//	    	dom1.setRoot(root);
//	    	
//	    	try {
//				assertTrue(Parser.compareDom(dom1, Parser.simpleHTMLParser(str)));
//			} catch (Exception e) {
//				System.out.println("Error with input.");
//			}	    	
//	    }
//	    
//	    @Test
//	    public void parseTest2(){
//	    	String str = "<div1>asdf<div2>cvasd</div2><div3>abcdef</div3>ere</div1>";
//	    	DOM dom1 = new DOM();
//	    	Node root = new Node("div1");
//	    	root.getInnerText().addAll(Arrays.asList("asdf","ere"));
//	    	Node child1 = new Node("div2");
//	    	child1.getInnerText().add("cvasd");
//	    	Node child2 = new Node("div3");
//	    	child2.getInnerText().add("abcdef");
//	    	root.getChildNodes().addAll(Arrays.asList(child1, child2));
//	    	dom1.setRoot(root);
//	    	
//	    	try {
//				assertTrue(Parser.compareDom(dom1, Parser.simpleHTMLParser(str)));
//			} catch (Exception e) {
//				System.out.println("Error with input.");
//			}		    	
//	    }
	
}
