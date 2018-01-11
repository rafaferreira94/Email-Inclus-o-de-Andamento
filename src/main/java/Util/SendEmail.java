package Util;

import DAO.TratamentoDatas;
import DTO.InteraLog;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmail {
    private static final String from = "bpa@finchsolucoes.com.br";

    public static void enviaEmail (Session sessao){

        try{
            MimeMessage mensagem = new MimeMessage(sessao);
            Multipart multipart = new MimeMultipart();

            mensagem.setFrom(new InternetAddress(from));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("cadastros@sanchezadv.com.br"));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("monitoramentorobo@finchsolucoes.com.br"));
            //mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("rafaelmartin@finchsolucoes.com.br"));

            String assunto = "[Sanchez & Sanchez - Inclusão de Andamentos ] - Relatório de erros: "+
                    TratamentoDatas.substractDiaPlanilha(1,true)+ " à " + TratamentoDatas.dataAtualPlanilha();

            mensagem.setSubject(assunto, "utf-8");

            BodyPart corpo = new MimeBodyPart();

            StringBuffer corpoEmail = new StringBuffer ("<html><head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" + "<style>" + "body {"
                    + "font-family: 'Arial'; font-size: 14px;" + "}" + "</style>" + "</head>" + "</body>"
                    + "<p> Prezados, " + DefineSaudacao.saudacao() + "<b></b>" + "<br>"
                    + "<p> Segue em anexo, relatório de erros."
                    + "<p>Att," + "<br><br>");

            corpoEmail.append("<b>Rafael Martin Alves Ferreira</b><br>"
                    + "rafaelmartin@finchsolucoes.com.br <br><br>"
                    + "<font color='red'><b>FINCH SOLUÇÕES</b></font><br>"
                    + "Rua Rio Branco, 27-41  CEP 17017-220<br>"
                    + "CEP 170212-330 - Jardim Paulista - Bauru - SP"
                    + "<br>Fone: (14) 3205-7200");

            corpo.setContent( corpoEmail.toString(), "text/html; charset=utf-8" );

            multipart.addBodyPart(corpo);

            MimeBodyPart attachPart = new MimeBodyPart();
            MimeBodyPart attachPartSanches = new MimeBodyPart();

            attachPart.attachFile(CriaExcel.pathExcel_SANTANDER.toString());
            multipart.addBodyPart(attachPart);
            attachPartSanches.attachFile(CriaExcel.pathExcel_ITAU);
            multipart.addBodyPart(attachPartSanches);

            mensagem.setContent(multipart);
            Transport.send(mensagem);

            System.out.println("EMAIL ENVIADO");
            //AQUI VOCÊ VAI INSERIR O LOG APÓS ENVIAR O EMAIL
            InteraLog.insertLog();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void enviaSingle (Session sessao, boolean flag){

        try{
            MimeMessage mensagem = new MimeMessage(sessao);
            Multipart multipart = new MimeMultipart();

            mensagem.setFrom(new InternetAddress(from));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("cadastros@sanchezadv.com.br"));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("monitoramentorobo@finchsolucoes.com.br"));
            //mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("rafaelmartin@finchsolucoes.com.br"));

            String assunto = "[Sanchez & Sanchez - Inclusão de Andamentos ] - Relatório de erros: "+
                    TratamentoDatas.substractDiaPlanilha(1,true)+ " à " + TratamentoDatas.dataAtualPlanilha();

            mensagem.setSubject(assunto, "utf-8");

            BodyPart corpo = new MimeBodyPart();

            StringBuffer corpoEmail = new StringBuffer ("<html><head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" + "<style>" + "body {"
                    + "font-family: 'Arial'; font-size: 14px;" + "}" + "</style>" + "</head>" + "</body>"
                    + "<p> Prezados, " + DefineSaudacao.saudacao() + "<b></b>" + "<br>"
                    + "<p> Segue em anexo, relatório de erros."
                    + "<p> OBS: Uma das planilhas não foi enviada devido a não haver erros."
                    + "<p>Att," + "<br><br>");

            corpoEmail.append("<b>Rafael Martin Alves Ferreira</b><br>"
                    + "rafaelmartin@finchsolucoes.com.br <br><br>"
                    + "<font color='red'><b>FINCH SOLUÇÕES</b></font><br>"
                    + "Rua Rio Branco, 27-41  CEP 17017-220<br>"
                    + "CEP 170212-330 - Jardim Paulista - Bauru - SP"
                    + "<br>Fone: (14) 3205-7200");

            corpo.setContent( corpoEmail.toString(), "text/html; charset=utf-8" );

            multipart.addBodyPart(corpo);

            if (flag) {
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(CriaExcel.pathExcel_SANTANDER.toString());
                multipart.addBodyPart(attachPart);
            } else {
                MimeBodyPart attachPartSanches = new MimeBodyPart();
                attachPartSanches.attachFile(CriaExcel.pathExcel_ITAU);
                multipart.addBodyPart(attachPartSanches);
            }

            mensagem.setContent(multipart);
            Transport.send(mensagem);

            System.out.println("EMAIL ENVIADO");
            //AQUI VOCÊ VAI INSERIR O LOG APÓS ENVIAR O EMAIL
            InteraLog.insertLog();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    }
