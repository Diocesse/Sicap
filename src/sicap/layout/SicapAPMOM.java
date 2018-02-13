/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.layout;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.Persistence;
import org.sicap.consultas.DaoAdminAutenticacao;
import org.sicap.consultas.DaoAssociacao;
import org.sicap.consultas.DaoAssociado;
import org.sicap.consultas.DaoCargo;
import org.sicap.consultas.DaoProfissao;
import org.sicap.negocio.Administrador;
import org.sicap.negocio.Associacao;
import org.sicap.negocio.Associado;
import org.sicap.negocio.Cargo;
import org.sicap.negocio.Funcionario;
import org.sicap.negocio.Pescador;
import org.sicap.negocio.Profissao;

/**
 *
 * @author leandro
 */
public class SicapAPMOM extends Application {

    private Stage stage;

    public static SicapAPMOM instance() {
        return new SicapAPMOM();

    }

    @Override
    public void start(Stage primaryStage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("AssocicaoSicap.fxml"));
            primaryStage.setTitle("Autenticação Sistema");
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension tamTela = kit.getScreenSize();

            primaryStage.setScene(new Scene(root, tamTela.width / 2, tamTela.height / 2 + 200));
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(" Erro: " + e.getLocalizedMessage());
        }
    }

    public void openPrincipal(String fxml, String title) {
        try {

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            stage.setTitle(title);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension tamTela = kit.getScreenSize();
            stage.setScene(new Scene(root, tamTela.width, tamTela.height));
            stage.show();

        } catch (Exception e) {
            System.out.println(" Erro: " + e.getLocalizedMessage());
        }
    }

    public void carregarFormCargosProfissao(String fxml, String title, Stage stage) {
        try {

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            stage.setTitle(title);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension tamTela = kit.getScreenSize();
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (Exception e) {
            System.out.println(" Erro: " + e.getLocalizedMessage());
        }
    }

    DaoCargo dc = new DaoCargo();
    DaoProfissao dp = new DaoProfissao();
    DaoAssociado da = new DaoAssociado();
    DaoAssociacao df = new DaoAssociacao();

    public void testarAplicacao() throws ParseException {
        Persistence.createEntityManagerFactory("SICAP");
        Profissao p = dp.getById(Profissao.class, 1L);
        // p.setProfissao("Pescador Artesanal");
        //dp.save(p);
        Cargo c = dc.getById(Cargo.class, 1L);
        //c.setCargo("Presidente");
        //dc.save(c);

        Associacao a = df.getById(Associacao.class, 1L);
        /* a.setAssociacao("Ouro do Mar");
        a.setCNPJ("1332523523");
        a.setCNPJ("52543463463");
        a.setEmail("ouromar@outlook.com");
        a.setEndereco("Porto");
        a.setFixo("4234255253");
        a.setLogo(null);
        a.setNumero(123);
        a.setOrdemUtilidade("94934796");
        a.setTelefoneCelular("37468437069");
        a.setBairro("Acupe");
        a.setCidade("Santo Amaro");
        a.setEstado("BA");
        a.setCep("53252-999");
        df.save(a);
         */
        DateFormat dfs = new SimpleDateFormat("dd/MM/yyyy");

        Funcionario f = new Funcionario();
        f.setAssociacao(a);
        f.setCIE("89728892");
        f.setCPF("8743693789");
        f.setCargo(c);
        f.setCidade("Santo Amaro");
        f.setDataCadastro(dfs.parse("12/05/2018"));
        f.setDataNascimento(dfs.parse("12/05/1989"));
        f.setEmail("diocesse@gmail.com");
        f.setEndereco("Nova Brasilia");
        f.setEstado("BA/Brasil");
        f.setNIT("899874679367");
        f.setNome("Gabriel Marinho");
        f.setNumero(120);
        f.setProfissao(p);
        f.setRG("98375983257");
        f.setSenha("admin");
        f.setSexo("Masculino");
        f.setTelefoneCelular("65296296");
        f.setTelefoneFixo("0984206983");
        f.setUser("admin");
        f.setAssociacao(a);

        da.save(f);

        Pescador p1 = new Pescador();
        p1.setAssociacao(a);
        p1.setCIE("9728892");
        p1.setCPF("43693789");
        p1.setReferencia("Sanda santos");
        p1.setCidade("Santo Amaro");
        p1.setDataCadastro(dfs.parse("12/05/2018"));
        p1.setDataNascimento(dfs.parse("12/05/1989"));
        p1.setEmail("diocesse@outlook.com");
        p1.setEndereco("Nova Brasilia");
        p1.setEstado("BA/Brasil");
        p1.setNIT("98746793");
        p1.setNome("Gabriel Marinho");
        p1.setNumero(120);
        p1.setProfissao(p);
        p1.setRG("9759837");
        p1.setPontoReferencia("Posto saude");
        p1.setSexo("Masculino");
        p1.setTelefoneCelular("656296");
        p1.setTelefoneFixo("09806983");

        da.save(p1);

        List<Associacao> listax = this.df.listAssociations("");
        for (Associacao ax : listax) {
            System.out.println("" + ax.getAssociacao());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // new SicapAPMOM().testarAplicacao();
            DaoAdminAutenticacao autenticacao = new DaoAdminAutenticacao();
            Administrador a = new Administrador();
            a.setIdAdmin(0);
            a.setUsuario("admin");
            a.setSenha("admin");
            autenticacao.save(a);

        } catch (Exception e) {
        }
        launch(args);
    }

}
