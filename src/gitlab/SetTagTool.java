package gitlab;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SetTagTool {

	public static void main(String[] args) {
		//json tool
		Gson gson 				= new Gson();
	    JsonArray jsonArray 	= new JsonArray();
	    JsonParser jsonParser   = new JsonParser();
	    String gitID        ="";
	    String gitPassword  ="";
	    String parameterStr ="";
//	    parameterStr	= "[{\"repository\":\"phase3\",\"project\":\"autoraidclass\", \"branch\":phase3,\"tagName\":\"testTag4\" },{\"repository\":\"phase3\",\"project\":\"raidcmd3.0\",\"branch\":phase3,\"tagName\":\"testTag4\" }]";
	    if(args.length>=1){
	    	gitID = args[0];
		}
	    if(args.length>=2){
			gitPassword = args[1];
		}
		if(args.length>=3){
			parameterStr = args[2];
		}
	    JsonElement jsonElement = jsonParser.parse(parameterStr);
	    jsonArray 				= jsonElement.getAsJsonArray();
	    
	    ArrayList gitTagList= new ArrayList();
	    for(int i=0 ; i < jsonArray.size() ; i++){
	    	GitTag gitTag = gson.fromJson(jsonArray.get(i), GitTag.class);
//	    	System.out.println("repo:"+gitTag.getRepository()+" project:"+gitTag.getProject()+" branch:"+gitTag.getBranch()+" tabName:"+gitTag.getTagName());
	    	gitTagList.add(gitTag);
	    }
	    
	    addGitTag(gitTagList,gitID,gitPassword);
	}
	public static void addGitTag(ArrayList<GitTag> list,String gitID, String gitPassword){
		GitController gitController = new GitController();
		gitController.loginGitLab(gitID,gitPassword);
		for(int i = 0 ; i < list.size() ; i++){
			GitTag gitTag = list.get(i);
			gitController.addTag(gitTag.getRepository(),gitTag.getProject(), gitTag.getBranch(), gitTag.getTagName());
		}
		gitController.close();
	}

}
