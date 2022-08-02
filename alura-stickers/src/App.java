import java.net.URL;
import java.io.InputStream;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexao HTTP e buscar os top filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscarDados(url);

        // exibir e manipular os dados
        // var extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new CriadorDeStickers();

        for (int i = 0; i< 3; i++) {
            Conteudo conteudo = conteudos.get(i); 
           
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
            // System.out.println(filme.get("imDbRating"));
            // System.out.println(filme.get("image"));
        }

    }
}
