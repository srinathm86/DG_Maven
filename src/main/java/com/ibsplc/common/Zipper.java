package com.ibsplc.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


 
/**
 * Class to add the last run report and screenshots to reports.zip folder 
 * @author A-7868 Krishna
 * <01/03/2018>
 */
public class Zipper {
 
    public static void zip() throws Exception{
 
        String sourceFolderName =  new File(".").getCanonicalPath()+"/reports";
        String logFolder =  new File(".").getCanonicalPath()+"/logs";
        String outputFileName = new File(".").getCanonicalPath()+"/reports.zip";
 
        FileOutputStream fos = new FileOutputStream(outputFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);
        //level - the compression level (0-9)
        zos.setLevel(9); 
        System.out.println("Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
        addFolder(zos, sourceFolderName, sourceFolderName); 
        addFile(zos, logFolder+"/execution_log.log" , logFolder);
        zos.close();
        
    }
 
    private static void addFolder(ZipOutputStream zos,String folderName,String baseFolderName)throws Exception{
        File f = new File(folderName);
        if(f.exists()){
 
            if(f.isDirectory()){
                if(folderName.equalsIgnoreCase("CustomReport") || folderName.equalsIgnoreCase("ScreenShots") || folderName.equalsIgnoreCase("img")){
                    String entryName = folderName.substring(baseFolderName.length()+1,folderName.length()) + File.separatorChar;
                    ZipEntry ze= new ZipEntry(entryName);
                    zos.putNextEntry(ze);    
                }
                File f2[] = f.listFiles();
                for(int i=0;i<f2.length;i++){
                    addFolder(zos,f2[i].getAbsolutePath(),baseFolderName);    
                }
            }else{
                //add file
                //extract the relative name for entry purpose
                String entryName = folderName.substring(baseFolderName.length()+1,folderName.length());
                if((entryName.contains("CustomReport ") && entryName.contains(ReportValues.reportFileName)) || !entryName.contains("CustomReport ") || entryName.contains("execution_log")){
	                ZipEntry ze= new ZipEntry(entryName);
	                zos.putNextEntry(ze);
	                FileInputStream in = new FileInputStream(folderName);
	                int len;
	                byte buffer[] = new byte[1024];
	                while ((len = in.read(buffer)) > 0) {
	                    zos.write(buffer, 0, len);
	                }
	                in.close();
	                zos.closeEntry();	                
                }
            }
        }else{
            System.out.println("File or directory not found " + folderName);
        }
         
    }
 
    private static void addFile(ZipOutputStream zos,String fileName,String baseFolderName)throws Exception{
        File f = new File(fileName);
        if(f.exists()){
 
                //add file
                //extract the relative name for entry purpose
                String entryName = fileName.substring(baseFolderName.length()+1,fileName.length());
               ZipEntry ze= new ZipEntry(entryName);
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(fileName);
                int len;
                byte buffer[] = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.closeEntry();                 
        }else{
            System.out.println("File or directory not found " + fileName);
        }
    }
        
}