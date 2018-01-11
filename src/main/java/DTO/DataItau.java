package DTO;

import DAO.TratamentoDatas;
import Factory.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import Constants.Querys;

public class DataItau{
    public static List<Object> recebeItau() throws SQLException {

        Connection conexao = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Object> listaArquivos = new ArrayList<Object>();

        try {
            conexao = Conexao.processosConexao();
            st = conexao.createStatement();
            ps = conexao.prepareStatement(Querys.recebeItau);

            try {
                ps.setString(1, TratamentoDatas.substractDiaDataDB(1,true)+" 10:00:000");
                ps.setString(2, TratamentoDatas.dataAtuaDB()+" 10:00:000");

                rs = ps.executeQuery();

            } catch (SQLException e){
                e.printStackTrace();
            }
            if (rs!= null){
                while (rs.next()){
                    Object campo = new Object();
                    campo.setCliente(rs.getString("CLIENTE"));
                    campo.setSetor(rs.getString("SETOR"));
                    campo.setSituacao(rs.getString("SITUACAO"));
                    campo.setPasta_cliente(rs.getString("PASTA_CLIENTE"));
                    campo.setPasta_cliente(rs.getString("ANDAMENTO_CLIENTE"));
                    campo.setField6(rs.getString(6));
                    campo.setField7(rs.getString(7));
                    campo.setDocumento(rs.getString("DOCUMENTO"));
                    campo.setTipo_documento(rs.getString("TIPO_DOCUMENTO"));
                    campo.setPasta_espaider(rs.getString("PASTA_ESPAIDER"));
                    campo.setAndamento_espaider(rs.getString("ANDAMENTO_ESPAIDER"));
                    campo.setPasta_espaider(rs.getString("DESDOBRAMENTO"));
                    campo.setField13(rs.getString(13));
                    campo.setField13(rs.getString("DATA_STATUS"));
                    campo.setData_criacao(rs.getString("DATA_CRIACAO"));
                    campo.setPrazo(rs.getString("PRAZO"));
                    listaArquivos.add(campo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao obter conexão " + e.getMessage());
        } finally {
            rs.close();
            ps.close();
            st.close();
            conexao.close();
        }
        return listaArquivos;
    }
}
