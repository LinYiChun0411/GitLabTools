package gitlab;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class GitController {
	private WebClient webClient;
	private String projectName;
	
	public GitController() {
		this.webClient = new WebClient(BrowserVersion.CHROME);
		this.webClient.getOptions().setThrowExceptionOnScriptError(false);
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void close(){
		this.webClient.close();
		
	}
	public void loginGitLab(String inputName,String inputPassword){
		try {
			HtmlPage htmlPage=(HtmlPage) webClient.getPage("http://swcodeserver.infortrend.com/users/sign_in");
			
			DomElement userName = htmlPage.getElementById("user_login");
			userName.setAttribute("value", inputName);
			DomElement passWord = htmlPage.getElementById("user_password");
			passWord.setAttribute("value", inputPassword);
			DomElement login = htmlPage.getElementByName("commit");
			HtmlPage resultPage=login.click();
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addTag(String repo,String project, String branch , String newTag) {///SW_Team/EonOne_WorkFlow
		String url="http://swcodeserver.infortrend.com/"+repo+"/"+project+"/tags/new";
		
		try {
			HtmlPage htmlPage = (HtmlPage) webClient.getPage(url);
			DomElement tagName = htmlPage.getElementByName("tag_name");
			tagName.setAttribute("value", newTag);
			DomElement createFrom = htmlPage.getElementByName("ref");
			createFrom.setAttribute("value", branch);
			
			DomElement button = null;
			List<DomElement> buttons = htmlPage.getElementsByName("button");
			for(DomElement theButton :buttons){
				if( "submit".equals( theButton.getAttribute("type") ) ){
					button = theButton;
				}
			}
			button.removeAttribute("disabled");
			button.removeAttribute("disabled");//class
			button.removeAttribute("class");//class
//			System.out.println(tagName.asXml());
//			System.out.println(createFrom.asXml());
//			System.out.println(button.asXml());
			button.click();
//			int statusCode=createVersionBtn.click().getWebResponse().getStatusCode();
			System.out.println("add tagName :"+newTag+"; from branch:"+branch+"; at project:"+repo+"/"+project);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
