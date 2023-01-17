package aten.com.backoffice.util.web.service;

public class HtmlCreateUtil {
	
	/*public boolean ContentFileCreate (String htmlFile,  String pathGubun){
		boolean htmlCreate = false;
		try{
			 String fileName = "";
			 fileName =	 contentMuti.selectContentFileInfo(conSeq);
			 String filePath = propertiesService.getString("Globals.fileStorePath") ;
			 File  htm_file = null;
			 	 
					 
			  if (  !fileName.equals("N") ){							
					htm_file = new File (filePath +"/" + fileName);
					if (htm_file.isFile()){
						htm_file.delete();
					}							
			  }else {
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				fileName = sdf.format(date)+"_"+conSeq+".html";
				
			  }
						 
			  BufferedWriter fw = new BufferedWriter(new FileWriter(filePath +"/"+fileName, true));
			  fw.write(htmlFile);
	          fw.flush();
	          fw.close(); 
	          ContentMutiInfo vo = new ContentMutiInfo();
	          vo.setConSeq(conSeq);
	          vo.setConFile(fileName);
			  int ret =      contentMuti.updateContentMutiFile(vo);
			 if (ret > 0){
				 htmlCreate = true;
			 }else {
				 htmlCreate = false;
			 }
		}catch(Exception e){
			LOGGER.debug("CREATE HTML FILE ERROR:" + e.toString()  );
			htmlCreate = false;					
		}
		return htmlCreate;
	}*/
}
