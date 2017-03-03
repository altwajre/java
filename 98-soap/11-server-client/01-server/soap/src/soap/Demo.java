package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Demo {
	
	@WebMethod
	public String getText(String name){
		return "Hello " + name + " from soap";
	}
	
	@WebMethod
	public int sum(int x, int y){
		return x + y;
	}
}
