# API-Beerstock

Uma aplicação exemplo para gerenciar um estoque de cervejas.

Este repositório contém:
- Backend: API REST em Java (Spring Boot) que expõe endpoints para CRUD e operações de estoque (`/api/v1/beers`).
- Frontend: SPA em React usando Vite (pastas em `frontend/`).
---

## Requisitos
- Java 17 (OpenJDK 17)
- Maven 3.x
- Node.js 18+ e npm
- (Opcional) Playwright para testes E2E

## Rápido: executar a aplicação localmente

1) Abra um terminal e inicie o backend (API):

```bash
# defina JAVA_HOME se necessário (exemplo Linux)
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"

# na raiz do repositório
mvn -DskipTests=false spring-boot:run
```

Por padrão a API sobe em `http://localhost:8081` e a documentação Swagger fica em `http://localhost:8081/swagger-ui/index.html`.

2) Em outro terminal, inicie o frontend:

```bash
cd frontend
npm ci
npm run dev
```

O Vite normalmente abre `http://localhost:3000`. Se 3000 estiver ocupada, o Vite irá escolher outra porta (ex.: 3001).

3) Teste manualmente via UI (frontend) ou via Swagger (backend).

---

## Executar testes

- Testes Java (unit / integration):

```bash
mvn test
```

- Testes frontend (Playwright):

```bash
cd frontend
npx playwright test
```

---

## Endpoints principais

- `POST /api/v1/beers` — criar cerveja
- `GET /api/v1/beers` — listar todas
- `GET /api/v1/beers/{id}` — obter por id
- `PUT /api/v1/beers/{id}` — atualizar cerveja
- `DELETE /api/v1/beers/{id}` — remover
- `PATCH /api/v1/beers/{id}/increment` — incrementar estoque
- `PATCH /api/v1/beers/{id}/decrement` — decrementar estoque
- `PATCH /api/v1/beers/{id}/price` — atualizar preço

Use o Swagger UI para ver exemplos de request/response.