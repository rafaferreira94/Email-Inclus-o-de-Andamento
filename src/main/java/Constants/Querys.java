package Constants;

public class Querys {

    public static final String recebeSantander = " SELECT\n" +
            " 'SANTANDER' AS 'CLIENTE',\n" +
            " CASE WHEN SETOR IS NULL THEN\n" +
            "   STATUS_EXECUCAO\n" +
            " ELSE\n" +
            "  SETOR END AS \"SETOR\",\n" +
            " SITUACAO,\n" +
            " PASTA_CLIENTE ,\n" +
            " ANDAMENTO_CLIENTE,\n" +
            " CONVERT (VARCHAR, DATA_ANDAMENTO, 103),\n" +
            " REPLACE(REPLACE(REPLACE(CONVERT(VARCHAR(MAX),TEXTO), CHAR(13), ' '),char(10), ' '),char(9), ' '),\n" +
            " DOCUMENTO,\n" +
            " TIPO_DOCUMENTO,\n" +
            " PASTA_ESPAIDER,\n" +
            " ANDAMENTO_ESPAIDER,\n" +
            " DESDOBRAMENTO,\n" +
            " REPLACE(REPLACE(REPLACE(CONVERT(VARCHAR(MAX),STATUS_EXECUCAO), CHAR(13), ' '),char(10), ' '),char(9), ' '),\n" +
            " DATA_STATUS,\n" +
            " DATA_CRIACAO,\n" +
            " CONVERT (VARCHAR, DATEADD(day, 14, DATA_CRIACAO), 3) AS PRAZO\n" +
            "FROM\n" +
            " SANCHEZ_BENNER_REVISIONAL_0032\n" +
            "WHERE\n" +
            " STATUS_EXECUCAO NOT LIKE '%OK - Andamento inserido com sucesso' \n" +
            " AND STATUS_EXECUCAO != 'ERRO - ANDAMENTO NÃO RELACIONADO'\n" +
            " AND DATA_STATUS BETWEEN ? AND ?\n" +
            "UNION\n" +
            " SELECT\n" +
            "  'SANTANDER' AS 'CLIENTE',\n" +
            "  CASE WHEN SETOR IS NULL THEN\n" +
            "    STATUS_EXECUCAO\n" +
            "  ELSE\n" +
            "   SETOR END AS \"SETOR\",\n" +
            "  SITUACAO,\n" +
            "  PASTA_CLIENTE,\n" +
            "  ANDAMENTO_CLIENTE,\n" +
            "  CONVERT (VARCHAR, DATA_ANDAMENTO, 103),\n" +
            "  REPLACE(REPLACE(REPLACE(CONVERT(VARCHAR(MAX),TEXTO), CHAR(13), ' '),char(10), ' '),char(9), ' '),\n" +
            "  DOCUMENTO,\n" +
            "  TIPO_DOCUMENTO,\n" +
            "  PASTA_ESPAIDER,\n" +
            "  ANDAMENTO_ESPAIDER,\n" +
            "  DESDOBRAMENTO,\n" +
            "  REPLACE(REPLACE(REPLACE(CONVERT(VARCHAR(MAX),STATUS_EXECUCAO), CHAR(13), ' '),char(10), ' '),char(9), ' '),\n" +
            "  DATA_STATUS,\n" +
            " DATA_CRIACAO,\n" +
            "  CONVERT (VARCHAR, DATEADD(day, 14, DATA_CRIACAO), 3) AS PRAZO\n" +
            " FROM\n" +
            "  SANCHEZ_BENNER_INDENIZATORIO_0032\n" +
            " WHERE\n" +
            "  STATUS_EXECUCAO NOT LIKE '%OK - Andamento inserido com sucesso' \n" +
            "  AND STATUS_EXECUCAO != 'ERRO - ANDAMENTO NÃO RELACIONADO'\n" +
            "  AND DATA_STATUS BETWEEN ? AND ?\n" +
            " ORDER BY\n" +
            "   DATA_CRIACAO";

    public static final String recebeItau = "SELECT\n" +
            " 'ITAU' AS 'CLIENTE',\n" +
            " CASE WHEN CARTEIRA IS NULL THEN\n" +
            "  STATUS_EXECUCAO\n" +
            " ELSE\n" +
            "  CARTEIRA END AS \"SETOR\",\n" +
            " SITUACAO,\n" +
            " PASTA_CLIENTE,\n" +
            " ANDAMENTO_CLIENTE,\n" +
            " CONVERT (VARCHAR, DATA_ANDAMENTO, 103),\n" +
            " REPLACE(REPLACE(REPLACE(CONVERT(VARCHAR(MAX),TEXTO), CHAR(13), ' '),char(10), ' '),char(9), ' '),\n" +
            " DOCUMENTO,\n" +
            " TIPO_DOCUMENTO,\n" +
            " PASTA_ESPAIDER,\n" +
            " ANDAMENTO_ESPAIDER,\n" +
            " DESDOBRAMENTO,\n" +
            " REPLACE(REPLACE(REPLACE(CONVERT(VARCHAR(MAX),STATUS_EXECUCAO), CHAR(13), ' '),char(10), ' '),char(9), ' '),\n" +
            " DATA_STATUS,\n" +
            " DATA_CRIACAO,\n" +
            " CONVERT (VARCHAR, DATEADD(day, 14, DATA_CRIACAO), 3) AS PRAZO\n" +
            "FROM\n" +
            " SANCHEZ_ITAU_0032\n" +
            "WHERE\n" +
            " STATUS_EXECUCAO NOT LIKE '%OK - Andamento inserido com sucesso' \n" +
            " AND STATUS_EXECUCAO != 'ERRO - ANDAMENTO NÃO RELACIONADO'\n" +
            " AND DATA_STATUS BETWEEN ? AND ?\n" +
            " ORDER BY\n" +
            " DATA_CRIACAO";

    public static final String verificaLog = "SELECT * FROM RELATORIO_SANCHEZ_INCLUSAOANDAMENTOS_LOG WHERE DATA_LOG >= ?";

    public static final String insertLog = "INSERT INTO RELATORIO_SANCHEZ_INCLUSAOANDAMENTOS_LOG (STATUS_LOG,DATA_LOG) VALUES ('Email já enviado', ?)";
}
