package Factory;

import Constants.ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection processosConexao() {
        Connection conn = null;
        //Class.forName(DRIVER).newInstance();

        try {
            conn = DriverManager.getConnection(
                    String.format(ConexaoBanco.URL, ConexaoBanco.PROCESSOS_IP, ConexaoBanco.PROCESSOS_NAME),
                    ConexaoBanco.PROCESSOS_USER, ConexaoBanco.PROCESSOS_PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return conn;
    }
}
