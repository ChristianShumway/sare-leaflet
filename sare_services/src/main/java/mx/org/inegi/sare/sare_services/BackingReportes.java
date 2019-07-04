/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dao.DaoTransformaCartografia;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceReportes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingReportes")
public class BackingReportes extends DaoTransformaCartografia {
    
    @Autowired
    @Qualifier("datosConexionDao")
    private InterfaceReportes InterfaceReportes;
   
    
    public cat_respuesta_services getReporte(Integer proyecto,String tipo, String report, String coordinacion, HttpServletRequest request, HttpServletResponse response){
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        String nombreArchivoJRXMLavanceGabinete = request.getServletContext().getRealPath("/WEB-INF/reportes/registroAvancesPunteados_prueba.jrxml");
        String nombreArchivoJRXMLtecnico = request.getServletContext().getRealPath("/WEB-INF/reportes/reporGeogra_prueba.jrxml");
        String tipoArchivo = tipo;
        String reporte = report;
        String ce = coordinacion;
        String whereReporte = "";
        String nombreArchivo = "";
        String nombreArchivoAdescargar = "reporte";
        Connection conne = null;
        String URLImagen = "";
        Map params = new HashMap();
        URLImagen = request.getServletContext().getRealPath("/WEB-INF/reportes/imagenes/");
         try {

            if (reporte.equals("2")) {
                nombreArchivo = nombreArchivoJRXMLtecnico;
                nombreArchivoAdescargar = "EstablecimientosPendientesPunteo";
                conne = InterfaceReportes.getDs().getConnection();
                if (ce.equals("00")) {
                    whereReporte = "and 1=1";
                } else {
                    whereReporte = " and cestatal='" + ce + "'";
                }
            } else if (reporte.equals("1")) {
                nombreArchivo = nombreArchivoJRXMLavanceGabinete;
                nombreArchivoAdescargar = "AvanceRegistrosPunteados";
                conne = InterfaceReportes.getDs().getConnection();
                if (ce.equals("00")) {
                    whereReporte = " and 1=1";
                } else {
                    whereReporte = " and cestatal='" + ce + "'";
                }
            }
            params.put("where", whereReporte);
            ProyectosEnum proyectos;
            proyectos=getProyecto(proyecto);
            params.put("esquema", getEsquemaPostgres(proyectos));
            params.put("DIR",request.getServletContext().getRealPath("/WEB-INF/"));
            params.put("URL", URLImagen);
            if (tipoArchivo.equals("EXCEL")) {
                exportaExcel(response, nombreArchivo, conne, params, nombreArchivoAdescargar);
            } else if (tipoArchivo.equals("PDF") && (conne!=null)) {
                exportaPDF(response, nombreArchivo, conne, params, nombreArchivoAdescargar);
            } else if (tipoArchivo.equals("CSV")) {
                exportaCSV(response, nombreArchivo, conne, params, nombreArchivoAdescargar);
            }else{
                Respuesta.setMensaje(new cat_mensaje("false","No selecciono ningun reporte"));
            }
        } catch (Exception es) {
            es.printStackTrace();
        } finally {
            try {
                if(conne!=null){
                    conne.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
       return Respuesta;
    }
    
    private void exportaPDF(HttpServletResponse response, String nombreArchivo, Connection conne, Map params, String nombreArchivoAdescargar) throws JRException {
        response.setContentType("application/pdf");
        JasperReport js;
        JasperPrint jp;
        try {
            ServletOutputStream out = response.getOutputStream();
            js = JasperCompileManager.compileReport(nombreArchivo);
            jp = JasperFillManager.fillReport(js, params, conne);
            byte[] bites = JasperExportManager.exportReportToPdf(jp);
            response.setHeader("Content-disposition", "inline; filename=" + nombreArchivoAdescargar + ".pdf");
            response.setContentLength(bites.length);
            out = response.getOutputStream();
            out.write(bites);
            out.flush();
            out.close();
        } catch (IOException | JRException e) {
        } 
    }

    private void exportaExcel(HttpServletResponse response, String nombreArchivo, Connection conne, Map params, String nombreArchivoAdescargar) {
        response.setContentType("application/vnd.ms-excel");
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        JRXlsExporter exporterXLS = new JRXlsExporter();
        try (ServletOutputStream out = response.getOutputStream()) {
            JasperReport report = JasperCompileManager.compileReport(nombreArchivo);
            JasperPrint print = JasperFillManager.fillReport(report, params, conne);
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
            exporterXLS.exportReport();
            response.setHeader("Content-disposition", "attachment; filename=" + nombreArchivoAdescargar + ".xls");
            response.setContentLength(arrayOutputStream.toByteArray().length);
            out.write(arrayOutputStream.toByteArray());
            out.flush();
        } catch (IOException | JRException e) {
        }
    }
    
     private void exportaCSV(HttpServletResponse response, String nombreArchivo, Connection conne, Map params, String nombreArchivoAdescargar) {
        response.setContentType("text/csv");
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        JRCsvExporter exporterXLS = new JRCsvExporter();
        try (ServletOutputStream out = response.getOutputStream()) {
            JasperReport report = JasperCompileManager.compileReport(nombreArchivo);
            JasperPrint print = JasperFillManager.fillReport(report, params, conne);
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
            exporterXLS.setParameter(JRCsvExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
            exporterXLS.exportReport();
            response.setHeader("Content-disposition", "inline; filename=" + nombreArchivoAdescargar + ".csv");
            response.setContentLength(arrayOutputStream.toByteArray().length);
            out.write(arrayOutputStream.toByteArray());
            out.flush();
        } catch (IOException | JRException e) {
        }
     }
     
     
    
}
