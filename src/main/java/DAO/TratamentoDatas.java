package DAO;

import Factory.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TratamentoDatas {

    public static boolean verificaFeriado (String hoje){ //VERIFICAÇÃO DE FERIADOS NA BASE DE DADOS
        boolean feriado = false;

        try{
            Connection conexao = Conexao.processosConexao();
            PreparedStatement ps = null;
            String query = "SELECT * FROM BPA_FERIADO WHERE DATA = ?";

            ps = conexao.prepareStatement(query);
            ps.setString(1, hoje);
            ResultSet rs = ps.executeQuery();
            while (rs!=null && rs.next()) {
                feriado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feriado;
    }

    public static int validaDiasUteis(LocalDate dataX, int i) { //RETORNA SE O DIA DE HOJE É ÚTIL
        if ((dataX.getDayOfWeek().equals(DayOfWeek.SATURDAY)) || (dataX.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            || (verificaFeriado(dataX.toString() ) ) ){
            i -= 1;
        }
        return i;
    }

    public static String substractDiaDataDB(int dias, boolean util) { //MÉTODO PARA RETORNAR O DIA DE ONTEM
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate hoje = LocalDate.now();
        LocalDate dataX = hoje.minusDays(dias);

        String dateSistem = "";

        int dia = 1;
        util = true;
        for (int i = 0; i < dias; i++) {
            dataX = hoje.minusDays(dia);
            dia += 1;
            i = validaDiasUteis(dataX, i);
        }

        dateSistem = dataX.format(formatador);
        return dateSistem;

    }

        public static String dataAtuaDB() { //FORMATA A DATA DE HOJE
            LocalDate hoje = LocalDate.now();
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return hoje.format(formatador);
    }

        public static String dataAtualPlanilha(){
            LocalDate hoje = LocalDate.now();
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM");

            return hoje.format(formatador);

        }

    public static String substractDiaPlanilha(int dias, boolean util) { //MÉTODO PARA RETORNAR O DIA DE ONTEM
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM");

        LocalDate hoje = LocalDate.now();
        LocalDate dataX = hoje.minusDays(dias);

        String dateSistem = "";

        int dia = 1;
        util = true;
        for (int i = 0; i < dias; i++) {
            dataX = hoje.minusDays(dia);
            dia += 1;
            i = validaDiasUteis(dataX, i);
        }

        dateSistem = dataX.format(formatador);
        return dateSistem;

    }



    }

