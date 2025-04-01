import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        //pedir a url ao usuário
        System.out.print("Digite a URL da página: ");
        String urlPagina = scanner.nextLine();

        //conectar ao site
        Document doc = Jsoup.connect(urlPagina).get();
        Elements pdfLinks = doc.select("[href$=.pdf]");

        //lista para armazenar arquivos a serem baixados
        List<String> arquivosBaixados = new ArrayList<>();

        for (Element link : pdfLinks) {
            String urlArquivo = link.absUrl("href");
            System.out.print("Digite o nome do arquivo para: " + urlArquivo + " (ou pressione Enter para ignorar): ");
            String nomeArquivo = scanner.nextLine().trim();

            if (!nomeArquivo.isEmpty()) {
                nomeArquivo += ".pdf";
                downloadFile(urlArquivo, nomeArquivo);
                arquivosBaixados.add(nomeArquivo);
            }
        }

        if (!arquivosBaixados.isEmpty()) {
            System.out.print("Digite o nome do arquivo ZIP: ");
            String nomeZip = scanner.nextLine().trim();
            if (!nomeZip.endsWith(".zip")) {
                nomeZip += ".zip";
            }
            compactarEmZip(nomeZip, arquivosBaixados.toArray(new String[0]));
            System.out.println("Arquivos compactados com sucesso!");
        } else {
            System.out.println("Nenhum arquivo foi baixado.");
        }

        scanner.close();
    }

    private static void downloadFile(String fileURL, String caminho) throws IOException {
        Files.copy(new URL(fileURL).openStream(), Paths.get(caminho));
        System.out.println("Arquivo salvo: " + caminho);
    }

    public static void compactarEmZip(String arquivoZip, String... arquivos) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(arquivoZip))) {
            for (String nomeArquivo : arquivos) {
                try (FileInputStream fis = new FileInputStream(nomeArquivo)) {
                    zos.putNextEntry(new ZipEntry(new File(nomeArquivo).getName()));
                    byte[] buffer = new byte[1024];
                    int bytesLidos;
                    while ((bytesLidos = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesLidos);
                    }
                    zos.closeEntry();
                }
            }
            System.out.println("Arquivos compactados com sucesso no arquivo: " + arquivoZip);
        } catch (IOException e) {
            System.out.println("Erro ao compactar arquivos: " + e.getMessage());
        }
    }
}
