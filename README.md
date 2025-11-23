# API-Beerstock

Projeto exemplo para gerenciar um estoque de cervejas.

Resumo simples:
- Backend: Spring Boot (Java 17) — API REST em `/api/v1/beers`.
- Frontend: React + Vite (pasta `frontend/`).

Como executar (rápido)
1. Iniciar backend:

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
mvn -DskipTests=false spring-boot:run
```

2. Iniciar frontend (em outro terminal):

```bash
cd frontend
npm ci
npm run dev
```

URLs importantes (padrão):
- API: `http://localhost:8081`
- Swagger UI: `http://localhost:8081/swagger-ui/index.html`
- Frontend (Vite): `http://localhost:3000` (ou porta alternativa se 3000 estiver em uso)

Principais endpoints
- `POST /api/v1/beers` — criar cerveja
- `GET /api/v1/beers` — listar cervejas
- `GET /api/v1/beers/{id}` — obter por id
- `PUT /api/v1/beers/{id}` — atualizar
- `DELETE /api/v1/beers/{id}` — remover
- `PATCH /api/v1/beers/{id}/increment` — incrementar quantidade
- `PATCH /api/v1/beers/{id}/decrement` — decrementar quantidade
- `PATCH /api/v1/beers/{id}/price` — atualizar preço

Testes
- Java: `mvn test`
- Frontend E2E: `cd frontend && npx playwright test`
- 
