package DTO;

import Factory.Conexao;
import Constants.Querys;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InteraLog {

    public static boolean verificaLog(boolean flag) throws SQLException {

        Connection conexao = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        conexao = Conexao.processosConexao();
        st = conexao.createStatement();
        ps = conexao.prepareStatement(Querys.verificaLog );
        try {
            ps.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            rs = ps.executeQuery();
            while(rs.next()) {
                while (rs.getString(1)!= null) {
                    System.out.println("EMAIL JÁ ENVIADO - "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    flag = true;
                    break;
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static  void insertLog(){
        Connection conexao = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = Conexao.processosConexao();
            st = conexao.createStatement();
            ps = conexao.prepareStatement(Querys.insertLog);

            try {
                ps.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                ps.executeUpdate();
                System.out.println("LOG INSERIDO");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
