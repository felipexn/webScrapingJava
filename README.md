# PDF Downloader & Zipper

Este programa em Java permite baixar arquivos PDF de uma página da web e compactá-los em um arquivo ZIP. O usuário pode inserir a URL da página, escolher quais arquivos baixar e nomeá-los antes do download.

## 📌 Funcionalidades

- Extrai todos os links de arquivos PDF de uma página web.
- Permite que o usuário escolha quais arquivos baixar e nomeá-los.
- Compacta os arquivos baixados em um único arquivo ZIP.

## 🛠️ Pré-requisitos

- Java 8 ou superior
- Biblioteca [JSoup](https://jsoup.org/) para parsing HTML (já utilizada no código)

## 🚀 Como Usar

1. **Compilar o código:**
   ```sh
   javac Main.java
   ```
2. **Executar o programa:**
   ```sh
   java Main
   ```
3. **Inserir os dados solicitados:**
   - Informe a URL da página contendo os PDFs.
   - Escolha o nome para cada arquivo ou pressione `Enter` para ignorá-lo.
   - Defina o nome do arquivo ZIP para compactação.

## 📂 Estrutura do Código

- `Jsoup` é usado para extrair links de PDFs da página fornecida.
- A função `downloadFile` faz o download dos arquivos.
- A função `compactarEmZip` compacta os PDFs baixados.
- O programa interage com o usuário via `Scanner` para personalizar os downloads.

## 🔥 Exemplo de Uso

```
Digite a URL da página: https://www.exemplo.com/documentos
Digite o nome do arquivo para: https://www.exemplo.com/doc1.pdf (ou pressione Enter para ignorar): Relatorio1
Digite o nome do arquivo para: https://www.exemplo.com/doc2.pdf (ou pressione Enter para ignorar):
Digite o nome do arquivo ZIP: documentos.zip
```

- O arquivo `Relatorio1.pdf` será baixado.
- O segundo arquivo será ignorado.
- `Relatorio1.pdf` será compactado em `documentos.zip`.

## 📝 Notas

- O programa ignora PDFs se o usuário não inserir um nome.
- O arquivo ZIP é gerado somente se houver downloads.
- É necessário conexão com a internet para o download dos PDFs.

## 📜 Licença

Este projeto é de uso livre. Modifique e use conforme necessário! 😊

