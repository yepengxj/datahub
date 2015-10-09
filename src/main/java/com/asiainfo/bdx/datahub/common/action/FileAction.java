package com.asiainfo.bdx.datahub.common.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.dataitemmgr.service.IDataItemMgrService;
import com.asiainfo.bdx.datahub.model.Dataitem;

@SuppressWarnings("serial")
public class FileAction extends BaseAction {
	//记录上传和下载日志（即购买）使用
	private Long dataitemId;
	
	/**
	 * 刷新周期类型
	 */
	private String refreshType;
	/**
	 * 上传周期
	 */
	private Date refreshDate;
	
    private File file;
    
    private String fileFileName;
    
    private String fileFileContentType;
    
    private String status = "0"; // 0格式错误;1成功(文件路径);2失败
    
    private String message;//返回信息
    
    private String filePath;
    
    //数据文件上传路径（应用服务器本地硬盘）
    private String uploadPath;
    
    @Resource
	private IDataItemMgrService dataItemMgrService;

    /**
	 * @return the dataitemId
	 */
	public Long getDataitemId() {
		return dataitemId;
	}

	/**
	 * @param dataitemId the dataitemId to set
	 */
	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
		if(dataitemId == 0l){
			this.dataitemId = Long.parseLong(this.getRequest().getParameter("dataitemId"));
		}
	}

	public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    /**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
	 * @return the refreshType
	 */
	public String getRefreshType() {
		return refreshType;
	}

	/**
	 * @param refreshType the refreshType to set
	 */
	public void setRefreshType(String refreshType) {
		this.refreshType = refreshType;
	}

	/**
	 * @return the refreshDate
	 */
	public Date getRefreshDate() {
		return refreshDate;
	}

	/**
	 * @param refreshDate the refreshDate to set
	 */
	public void setRefreshDate(Date refreshDate) {
		this.refreshDate = refreshDate;
	}

	public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType() {
        return fileFileContentType;
    }

    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }
    
    public String pubUpload()throws Exception{
    	
    	return "toPubUpload";
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public String execute() throws Exception {
    	String accurateRefreshDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(refreshDate);
    	String inaccuracyRefreshDate = new SimpleDateFormat("yyyy-MM-dd").format(refreshDate);
    	uploadPath = DHConstants.DATAFILE_UPLOADPATH;
    	if(StringUtils.isEmpty(uploadPath)){//如果在在cfg.properties中没有配置DATAFILE_UPLOADPATH，则设置默认变量
    		uploadPath = ServletActionContext.getRequest().getRealPath(File.separator + "upload");
    	}
        File file = new File(uploadPath); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(uploadPath + File.separator + dataitemId); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        
        file = new File(uploadPath + File.separator + dataitemId + File.separator + inaccuracyRefreshDate); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        String absolutePath = uploadPath + File.separator + dataitemId + File.separator + inaccuracyRefreshDate + File.separator;
        String relativePath = File.separator + dataitemId + File.separator + inaccuracyRefreshDate + File.separator;
        
        //String[] fileSuffix = new String[] { "exe" };// 禁止文件
        String[] permitFileSuffix = {};
        if(StringUtils.isNotEmpty(DHConstants.PERMIT_UPLOAD_FILE_TYPE)){
        	permitFileSuffix = DHConstants.PERMIT_UPLOAD_FILE_TYPE.toLowerCase().split(",");
        }
        fileFileName = fileFileName.toLowerCase();
        try {
            File f = this.getFile();
            // 判断文件格式
            boolean canUpload = false;
            for (int i = 0; i < permitFileSuffix.length; i++) {
                if (fileFileName.endsWith(permitFileSuffix[i])) {
                	canUpload = true;
                	break;
                }
            }
            if(!canUpload){
            	status = "0";
            	message = "只允许上传的文件格式为：" + DHConstants.PERMIT_UPLOAD_FILE_TYPE;
                return ERROR;
            }
           /* for(int j = 0;j < fileSuffix.length;j++){
            	if (fileFileName.endsWith(fileSuffix[j])) {
            		status = "0";
            		message = "只允许上传的文件格式为：" + DHConstants.PERMIT_UPLOAD_FILE_TYPE;
                    return ERROR;
                }
            }*/
            FileInputStream inputStream = new FileInputStream(f);
            if(dataitemId == 0l){
            	dataitemId = 0l;
            }
            String filePath = absolutePath + fileFileName;
            FileOutputStream outputStream = new FileOutputStream(filePath);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            status = "1";
            message = filePath;
            
            /*
             * 记录上传日志
             */
            Dataitem dataitem = new Dataitem();
            
            dataitem.setDataitemId(dataitemId);
            //dataitem.setSampleFilename(filePath);
            dataitem.setFileType(StringUtils.isNoneEmpty(fileFileName) 
            		? "未知" : fileFileName.substring(fileFileName.lastIndexOf(".") + 1));
            dataitem.setRefreshDate(new SimpleDateFormat("yyyy-MM-dd").parse(inaccuracyRefreshDate));
            dataitem.setDataDate(accurateRefreshDate);
            dataItemMgrService.insertUploadLog(dataitem,relativePath + fileFileName);
        } catch (Exception e) {
            e.printStackTrace();
            status = "2";
            message = "上传失败，请稍后重试！";
        }
        return SUCCESS;
    }

    public String download() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        String downDate = this.getRequest().getParameter("downDate");
        
        try {
        	//查找文件名
        	String sampleFilename  = dataItemMgrService.queryDataitemById(dataitemId);
        	//String sampleFilename = dataitem.getSampleFilename();
        	if (!StringUtils.isNotEmpty(sampleFilename)) {
        		throw  new Exception("样例文件路径为空");
        	}
        	uploadPath = DHConstants.DATAFILE_UPLOADPATH;
        	if(StringUtils.isEmpty(uploadPath)){//如果在在cfg.properties中没有配置DATAFILE_UPLOADPATH，则设置默认变量
        		uploadPath = ServletActionContext.getRequest().getRealPath("/upload");
        	}
        	//拼接文件全局路径
        	filePath = uploadPath + File.separator  + dataitemId + File.separator +downDate+sampleFilename;
        	
            // path是指欲下载的文件的路径。
            File file = new File(filePath);
            
            // 取得文件名。
            String filename = file.getName();
            
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1)
                    .toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String filenameString = new String(filename.getBytes("utf-8"),
                    "iso-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + filenameString);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response
                    .getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}