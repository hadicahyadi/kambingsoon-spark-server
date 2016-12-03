package com.curiouslabs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.curiouslabs.util.Datasource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportService {
	
//	public void generateReport(){
////		ConnecCtion conn = Datasource.getConnection().getConnection();
//	}
	
	public void generate(String reportName,Map<String,Object> mapParam){
        try {
            JasperReport report = JasperCompileManager.compileReport(reportName);
            JasperPrint print = JasperFillManager.fillReport(report, mapParam, Datasource.getConnection().getConnection());
            DateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
            File pdf = File.createTempFile("struct_"+df.format(new Date()), ".pdf");
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
