# API Explorer Java

Uma ferramenta interativa (CLI e GUI) para explorar qualquer API REST, com suporte a autenticação, histórico de requisições e organização por data.

## Funcionalidades

- [x] Suporte a métodos HTTP: `GET`, `POST`, `PUT`, `PATCH`, `DELETE`
- [x] Autenticação `Bearer` e `Basic`
- [x] CLI com Apache Commons CLI
- [x] GUI com Swing
- [x] Histórico salvo em JSON por data (`historico/AAAA-MM-DD/`)
- [x] Projeto Maven com Java 17+

---

## Requisitos

- Java 17+
- Maven 3.8+
- (Opcional) GitHub CLI (`gh`)

---

## Como usar

### 1. Compilar

```bash
mvn clean package
```

### 2. Executar CLI

```bash
java -cp target/api-explorer-1.0-SNAPSHOT.jar com.explorer.MainCli \
  -m GET \
  -u https://jsonplaceholder.typicode.com/posts/1
```

Autenticação (exemplos):

```bash
# Bearer
-a bearer:SEU_TOKEN

# Basic
-a basic:usuario:senha
```

---

### 3. Executar GUI

```bash
java -cp target/api-explorer-1.0-SNAPSHOT.jar com.explorer.ApiExplorerGUI
```

---

## Exemplo de histórico salvo

```json
{
  "data": "2025-05-14T10:20:45.123",
  "method": "GET",
  "url": "https://jsonplaceholder.typicode.com/posts/1",
  "body": "",
  "status": 200,
  "response": "{...}"
}
```

---

## Estrutura

```
src/
├── main/java/com/explorer/
│   ├── AuthType.java
│   ├── ApiRequestData.java
│   ├── ApiRequestExecutor.java
│   ├── HistoryManager.java
│   ├── MainCli.java
│   └── ApiExplorerGUI.java
└── test/java/
```

---

## Contribuição

1. Fork o projeto
2. Crie uma branch: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -m 'Adiciona nova feature'`
4. Push para o fork: `git push origin minha-feature`
5. Crie um Pull Request

---

## Autor

Desenvolvido por Ricardo Nunes.

---

## Licença

Este projeto está licenciado sob a licença MIT.
