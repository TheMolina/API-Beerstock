# Beerstock Frontend

Instalação e execução:

Front-end da aplicação Beerstock (React + Vite). Este README explica como configurar o ambiente de desenvolvimento, executar o servidor local e rodar testes E2E.

Pré-requisitos
- Node.js 18+ e npm

Desenvolvimento (passo a passo)

1. Na raiz do repositório, entre na pasta do frontend:

```
cd frontend
```

2. Instale dependências com `npm ci` (recomendado para CI) ou `npm install`:

```
npm ci
```

3. Inicie o servidor de desenvolvimento:

```
npm run dev
```

O Vite normalmente abrirá `http://localhost:3000`. Se essa porta já estiver em uso, o Vite escolherá a próxima disponível (por exemplo `3001`).

Integração com o backend
- O frontend consome a API em `http://localhost:8081` (por padrão). Antes de usar a interface, garanta que o backend esteja rodando (veja `README.md` raiz).

Testes E2E (Playwright)

```bash
cd frontend
npx playwright test
```
