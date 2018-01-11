package Util;

import DAO.TratamentoDatas;
import DTO.Object;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.mail.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CriaExcel {

    public static final String pathExcel_SANTANDER = "\\\\192.168.0.4\\TI\\Processos\\RELATORIO EMAIL - SANCHEZ INCLUSAO DE ANDAMENTOS\\Relatorio Inclusao de Andamentos Sanchez "+
            TratamentoDatas.substractDiaPlanilha(1,true)+ " a " + TratamentoDatas.dataAtualPlanilha()+" [SANTANDER].xlsx";

    public static final String pathExcel_ITAU = "\\\\192.168.0.4\\TI\\Processos\\RELATORIO EMAIL - SANCHEZ INCLUSAO DE ANDAMENTOS\\Relatorio Inclusao de Andamentos Sanchez "+
            TratamentoDatas.substractDiaPlanilha(1,true)+ " a "+ TratamentoDatas.dataAtualPlanilha()+" [ITAU].xlsx";

    public CriaExcel geraPlanilhas(List<Object> itau, List<Object> santander) {

        try {
            Email conexaoEmail = new Email();
            SendEmail corpoEmail = new SendEmail();
            Session sessao = conexaoEmail.logar();
            boolean relatorioSantander = false;
            boolean relatorioItau = false;

            if (!itau.isEmpty()) {
                geraExcel_Itau(itau);
                relatorioItau = true;
            } else System.out.println("Sem dados na planilha");

            if (!santander.isEmpty()){
                geraExcel_Santander(santander);
                relatorioSantander = true;
            } else System.out.println("Sem dados na planilha");

            if (relatorioItau == true && relatorioSantander == true ) {
                corpoEmail.enviaEmail(sessao);
            } else if (relatorioItau == false && relatorioSantander == true) {
                corpoEmail.enviaSingle(sessao, true);
            } else if (relatorioItau == true && relatorioSantander == false)  {
                corpoEmail.enviaSingle(sessao, false);
            } else {
                System.out.println("Não gerou nenhuma planilha");
            }
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return  null;
    }

    public CriaExcel geraExcel_Itau (List<Object> objetos) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Relatório Sanchez");;
        sheet.createFreezePane(0,1);

        XSSFRow row;

        CreationHelper helper = workbook.getCreationHelper();
        CellStyle estilo = workbook.createCellStyle();
        CellStyle estilo2 = workbook.createCellStyle();
        Font fonte = workbook.createFont();

        fonte.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fonte.setColor(IndexedColors.WHITE.getIndex());
        estilo.setFont(fonte);
        estilo.setFillForegroundColor(IndexedColors.RED.getIndex());
        estilo.setFillPattern(CellStyle.SOLID_FOREGROUND);


        estilo2.setDataFormat(helper.createDataFormat().getFormat("dd/mm/yyyy"));

        int rowId = 0;
        row = sheet.createRow(rowId++);

        row.createCell(0).setCellValue("SISTEMA");
        row.createCell(1).setCellValue("SETOR");
        row.createCell(2).setCellValue("SITUAÇÃO");
        row.createCell(3).setCellValue("DOSSIE/PASTA");
        row.createCell(4).setCellValue("ANDAMENTO SISTEMA CLIENTE");
        row.createCell(5).setCellValue("DATA ANDAMENTO");
        row.createCell(6).setCellValue("TEXTO ANDAMENTO");
        row.createCell(7).setCellValue("TEM DOCUMENTO?");
        row.createCell(8).setCellValue("TIPO DOCUMENTO");
        row.createCell(9).setCellValue("PASTA DO ESPAIDER");
        row.createCell(10).setCellValue("ANDAMENTO ESPAIDER");
        row.createCell(11).setCellValue("DESDOBRAMENTO ESPAIDER");
        row.createCell(12).setCellValue("STATUS DA EXECUÇÃO");
        row.createCell(13).setCellValue("DATA DA EXECUÇÃO");
        row.createCell(14).setCellValue("DATA DE CRIAÇÃO ESPAIDER");
        row.createCell(15).setCellValue("PRAZO");

        for (int i = 0; i < 16; i++) {
            row.getCell(i).setCellStyle(estilo);
        }

        for (Object objeto : objetos) {
            row = sheet.createRow(rowId++);
            row.createCell(0).setCellValue(objeto.getCliente());
            row.createCell(1).setCellValue(objeto.getSetor());
            row.createCell(2).setCellValue(objeto.getSituacao());
            row.createCell(3).setCellValue(objeto.getPasta_cliente());
            row.createCell(4).setCellValue(objeto.getAndamento_cliente());
            row.createCell(5).setCellValue(objeto.getField6());
            row.createCell(6).setCellValue(objeto.getField7());
            row.createCell(7).setCellValue(objeto.getDocumento());
            row.createCell(8).setCellValue(objeto.getTipo_documento());
            row.createCell(9).setCellValue(objeto.getPasta_espaider());
            row.createCell(10).setCellValue(objeto.getAndamento_espaider());
            row.createCell(11).setCellValue(objeto.getDesdobramento());
            row.createCell(12).setCellValue(objeto.getField13());
            row.createCell(13).setCellValue(objeto.getData_status());
            row.createCell(14).setCellValue(objeto.getData_criacao());
            row.createCell(15).setCellValue(objeto.getPrazo());
            row.getCell(14).setCellStyle(estilo2);

        }
        FileOutputStream out = new FileOutputStream(new File(pathExcel_ITAU));
        workbook.write(out);
        out.close();
        System.out.println("PLANILHA ITAU GERADA COM SUCESSO");
        return null;
    }

    public CriaExcel geraExcel_Santander (List<Object> objetos) throws IOException {
    if (objetos != null) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Relatório Sanchez");
        sheet.createFreezePane(0, 1);

        XSSFRow row;

        CreationHelper helper = workbook.getCreationHelper();
        CellStyle estilo = workbook.createCellStyle();
        Font fonte = workbook.createFont();

        fonte.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fonte.setColor(IndexedColors.WHITE.getIndex());
        estilo.setFont(fonte);
        estilo.setFillForegroundColor(IndexedColors.RED.getIndex());
        estilo.setFillPattern(CellStyle.SOLID_FOREGROUND);

        int rowId = 0;
        row = sheet.createRow(rowId++);

        row.createCell(0).setCellValue("SISTEMA");
        row.createCell(1).setCellValue("SETOR");
        row.createCell(2).setCellValue("SITUAÇÃO");
        row.createCell(3).setCellValue("DOSSIE/PASTA");
        row.createCell(4).setCellValue("ANDAMENTO SISTEMA CLIENTE");
        row.createCell(5).setCellValue("DATA ANDAMENTO");
        row.createCell(6).setCellValue("TEXTO ANDAMENTO");
        row.createCell(7).setCellValue("TEM DOCUMENTO?");
        row.createCell(8).setCellValue("TIPO DOCUMENTO");
        row.createCell(9).setCellValue("PASTA DO ESPAIDER");
        row.createCell(10).setCellValue("ANDAMENTO ESPAIDER");
        row.createCell(11).setCellValue("DESDOBRAMENTO ESPAIDER");
        row.createCell(12).setCellValue("STATUS DA EXECUÇÃO");
        row.createCell(13).setCellValue("DATA DA EXECUÇÃO");
        row.createCell(14).setCellValue("DATA DE CRIAÇÃO ESPAIDER");
        row.createCell(15).setCellValue("PRAZO");

        for (int i = 0; i < 16; i++) {
            row.getCell(i).setCellStyle(estilo);
        }

        for (Object objeto : objetos) {
            row = sheet.createRow(rowId++);
            row.createCell(0).setCellValue(objeto.getCliente());
            row.createCell(1).setCellValue(objeto.getSetor());
            row.createCell(2).setCellValue(objeto.getSituacao());
            row.createCell(3).setCellValue(objeto.getPasta_cliente());
            row.createCell(4).setCellValue(objeto.getAndamento_cliente());
            row.createCell(5).setCellValue(objeto.getField6());
            row.createCell(6).setCellValue(objeto.getField7());
            row.createCell(7).setCellValue(objeto.getDocumento());
            row.createCell(8).setCellValue(objeto.getTipo_documento());
            row.createCell(9).setCellValue(objeto.getPasta_espaider());
            row.createCell(10).setCellValue(objeto.getAndamento_espaider());
            row.createCell(11).setCellValue(objeto.getDesdobramento());
            row.createCell(12).setCellValue(objeto.getField13());
            row.createCell(13).setCellValue(objeto.getData_status());
            row.createCell(14).setCellValue(objeto.getData_criacao());
            row.createCell(15).setCellValue(objeto.getPrazo());
         }
        FileOutputStream out = new FileOutputStream(new File(pathExcel_SANTANDER));
        workbook.write(out);
        out.close();
        System.out.println("PLANILHA SANTANDER GERADA COM SUCESSO");
    } else {
        System.out.println("NÃO RETORNOU PENDÊNCIAS");
    }
        return null;
    }
}


