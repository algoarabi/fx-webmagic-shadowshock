package com.leong;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by mike on 2016/5/7.
 *
 */
public class ShadowSocketProcessor implements PageProcessor {

    public static void main(String[] args) throws Exception {
    	int index;
    	do{
    		System.out.println("请输入一个>=0，<=3的数字:");
    		while (!scanner.hasNextInt()) {
    	        System.out.println("这不是数字！");
    	        scanner.next(); // this is important!
    	    }
    		index = scanner.nextInt();
    	}while(index < 0 || index > 3);
    	System.out.println("Thank you! Got " + index);
		URL url = new ShadowSocketProcessor().getClass().getProtectionDomain().getCodeSource().getLocation();
		String path = URLDecoder.decode(url.getPath(), "utf-8");
		path = path.substring(1, path.lastIndexOf("/"));
		scanner = new Scanner(System.in);
		ShadowSocketProcessor processor = new ShadowSocketProcessor();
		if (!StringUtils.isEmpty(String.valueOf(index)))
			processor.setServerIndex(index);
		path = "";
		if (!StringUtils.isEmpty(path))
			processor.setPath(path);
		Spider.create(processor).addUrl("http://www.ishadowsocks.net/").thread(1).start();
		
    }
	
	String server = "shaofan.org";
	String port = "1090";
	String password = "ap1QVA";
	int siteIndex = 3;
	String path = "D:\\Software\\shadow\\";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUseGzip(true);
	private static Scanner scanner = new Scanner(System.in);
    
    public ShadowSocketProcessor() {
		super();
	}

	public void process(Page page) {
		String headerPath 		= "//*[@id=\"free\"]/div/div[2]/div[" + this.siteIndex;
		String serverTailPath 	= "]/h4[1]/text()";
		String portTailPath 	= "]/h4[2]/text()";
		String passwordTailPath = "]/h4[3]/text()";
        
        String json = "{\"configs\" : [  {\"server\" : \"SSSSSS\",\"server_port\" : PPPPPP,\"password\" : \"XXXXXX\",\"method\" : \"aes-256-cfb\",\"remarks\" : \"\"}],\"strategy\" : null,\"index\" : 0,\"global\" : false,\"enabled\" : true,\"shareOverLan\" : false,\"isDefault\" " +
                ": false,\"localPort\" : 1080,\"pacUrl\" : null,\"useOnlinePac\" : false,\"availabilityStatistics\" : false}";
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(path + File.separator + "gui-config.json"));) {
        	server 		= String.valueOf(page.getHtml().xpath(headerPath + serverTailPath)).substring(7).toUpperCase();
        	port 		= String.valueOf(page.getHtml().xpath(headerPath + portTailPath)).substring(3);
        	password 	= String.valueOf(page.getHtml().xpath(headerPath + passwordTailPath)).substring(4);
            json = json.replaceAll("SSSSSS", server).replaceAll("PPPPPP", port).replaceAll("XXXXXX",password);
            System.out.printf("Path= %s; Server= %s; Port= %s; Password= %s \n", path, server, port, password);
            writer.write(json);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String findCmd = "tasklist";
		String killCmd = "taskkill /PID ";
		String startCmd = this.path + File.separator  + "Shadowsocks.exe";		
		Runtime run = Runtime.getRuntime();
		try {
			Process p = run.exec(findCmd);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			
			String lineStr;
			String ssPid ="";
			while ((lineStr = inBr.readLine()) != null){
				if(lineStr.startsWith("Shadowsocks.exe")) {					
					ssPid = lineStr.split("\\s+")[1];									
					if(ssPid.length() > 0){
						killCmd += ssPid + " /F";
						//先杀死进程
						run.exec(killCmd);					
					}						
				}		
				
			}
			Thread.sleep(1000);
			
			run.exec(startCmd);	
			
			inBr.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public int getSiteIndex() {
		return siteIndex;
	}

	public void setServerIndex(int siteIndex) {
		this.siteIndex = siteIndex;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	 public Site getSite() {
        return site;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
