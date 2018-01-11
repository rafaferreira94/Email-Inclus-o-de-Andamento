package Main;

import DTO.*;
import DTO.Object;
import Util.CriaExcel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class App {

    public static void start (boolean flag) throws SQLException,IOException {
        DataSantander santander = new DataSantander();
        DataItau itau = new DataItau();

            flag = InteraLog.verificaLog(flag); // Verificação de LOG

            if (flag == true) {
                System.out.println("CICLO ENCERRADO");
                return;
            } else {
                List<DTO.Object> campoSantader = new ArrayList<>();
                List<Object> campoItau = new ArrayList<>();

                System.out.println(" >>>> RESGATANDO DADOS SANTANDER <<<< ");
                campoSantader = santander.recebeSantander();
                System.out.println("Retornou " + campoSantader.size() + " Registros.");

                System.out.println(" >>>> RESGATANDO DADOS ITAU <<<< ");
                campoItau = itau.recebeItau();
                System.out.println("Retornou " + campoItau.size() + " Registros");

                CriaExcel email = new CriaExcel().geraPlanilhas(campoItau, campoSantader);
                return;
            }
    }

   public static void main(String[] args) throws IOException, SQLException {
        App iniciar = new App();
       iniciar.start(false);

    }
}
