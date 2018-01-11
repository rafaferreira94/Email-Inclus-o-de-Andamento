package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import DAO.TratamentoDatas;
import Factory.Conexao;

import Constants.Querys;

public class DataSantander {

    private Connection conexao = null;

    public List<Object> recebeSantander() throws SQLException {

        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Object> listaArquivos = new ArrayList<Object>();

        try {
            conexao = Conexao.processosConexao();
            st = conexao.createStatement();
            ps = conexao.prepareStatement(Querys.recebeSantander);

            try {
                ps.setString(1, TratamentoDatas.substractDiaDataDB(1,true)+" 10:00:000");
                ps.setString(2, TratamentoDatas.dataAtuaDB()+" 10:00:000");
                ps.setString(3, TratamentoDatas.substractDiaDataDB(1,true)+" 10:00:000");
                ps.setString(4, TratamentoDatas.dataAtuaDB()+" 10:00:000");
                rs = ps.executeQuery();
            } catch(SQLException e) {
                e.printStackTrace();
            }
            if (rs != null){
                while (rs.next()){
                    Object campo = new Object();
                        campo.setCliente(rs.getString("CLIENTE"));
                        campo.setSetor(rs.getString("SETOR"));
                        campo.setSituacao(rs.getString("SITUACAO"));
                        campo.setPasta_cliente(rs.getString("PASTA_CLIENTE"));
                        campo.setAndamento_cliente(rs.getString("ANDAMENTO_CLIENTE"));
                        campo.setField6(rs.getString(6));
                        campo.setField7(rs.getString(7));
                        campo.setDocumento(rs.getString("DOCUMENTO"));
                        campo.setTipo_documento(rs.getString("TIPO_DOCUMENTO"));
                        campo.setPasta_espaider(rs.getString("PASTA_ESPAIDER"));
                        campo.setAndamento_espaider(rs.getString("ANDAMENTO_ESPAIDER"));
                        campo.setDesdobramento(rs.getString("DESDOBRAMENTO"));
                        campo.setField13(rs.getString(13));
                        campo.setData_status(rs.getString("DATA_STATUS"));
                        campo.setData_criacao(rs.getString("DATA_CRIACAO"));
                        campo.setPrazo(rs.getString("PRAZO"));
                        listaArquivos.add(campo);
                    }
                }
        } finally {
            rs.close();
            ps.close();
            st.close();
            conexao.close();
        }
        return listaArquivos;
    }
}
