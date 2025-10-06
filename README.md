# API REST - Sistema de Gerenciamento de Academia

## Endpoints Disponíveis

### 1. ALUNOS (`/api/alunos`)

#### Cadastrar Aluno
```http
POST /api/alunos
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "(62) 99999-9999",
  "dataNascimento": "1995-05-15",
  "plano": {
    "id": 1
  }
}
```

#### Listar Todos os Alunos
```http
GET /api/alunos
```

#### Listar Apenas Alunos Ativos
```http
GET /api/alunos/ativos
```

#### Buscar Aluno por ID
```http
GET /api/alunos/1
```

#### Atualizar Aluno
```http
PUT /api/alunos/1
Content-Type: application/json

{
  "nome": "João Silva Santos",
  "telefone": "(62) 88888-8888"
}
```

#### Inativar Aluno
```http
PATCH /api/alunos/1/inativar
```

#### Deletar Aluno
```http
DELETE /api/alunos/1
```

---

### 2. PLANOS (`/api/planos`)

#### Cadastrar Plano
```http
POST /api/planos
Content-Type: application/json

{
  "nome": "Plano Mensal",
  "descricao": "Acesso ilimitado durante 30 dias",
  "valor": 89.90,
  "duracao": 30
}
```

#### Listar Todos os Planos
```http
GET /api/planos
```

#### Listar Apenas Planos Ativos
```http
GET /api/planos/ativos
```

#### Buscar Plano por ID
```http
GET /api/planos/1
```

#### Atualizar Plano
```http
PUT /api/planos/1
Content-Type: application/json

{
  "valor": 99.90,
  "descricao": "Acesso ilimitado com acompanhamento nutricional"
}
```

#### Inativar Plano
```http
PATCH /api/planos/1/inativar
```

#### Deletar Plano
```http
DELETE /api/planos/1
```

---

### 3. TREINOS (`/api/treinos`)

#### Cadastrar Treino
```http
POST /api/treinos
Content-Type: application/json

{
  "nome": "Treino de Peito e Tríceps",
  "descricao": "Treino focado em hipertrofia",
  "dificuldade": "Intermediário"
}
```

#### Listar Todos os Treinos
```http
GET /api/treinos
```

#### Listar Apenas Treinos Ativos
```http
GET /api/treinos/ativos
```

#### Buscar Treino por ID
```http
GET /api/treinos/1
```

#### Atualizar Treino
```http
PUT /api/treinos/1
Content-Type: application/json

{
  "dificuldade": "Avançado"
}
```

#### Associar Aluno ao Treino
```http
POST /api/treinos/1/alunos/2
```

#### Desassociar Aluno do Treino
```http
DELETE /api/treinos/1/alunos/2
```

#### Inativar Treino
```http
PATCH /api/treinos/1/inativar
```

#### Deletar Treino
```http
DELETE /api/treinos/1
```
**Observação:** Não é possível deletar treinos com alunos associados.

---

### 4. PAGAMENTOS (`/api/pagamentos`)

#### Registrar Pagamento
```http
POST /api/pagamentos
Content-Type: application/json

{
  "aluno": {
    "id": 1
  },
  "valor": 89.90,
  "status": "PAGO",
  "metodoPagamento": "PIX"
}
```
**Observação:** A data do pagamento é preenchida automaticamente.

#### Listar Todos os Pagamentos
```http
GET /api/pagamentos
```

#### Listar Pagamentos por Aluno
```http
GET /api/pagamentos/aluno/1
```

#### Buscar Pagamento por ID
```http
GET /api/pagamentos/1
```

#### Atualizar Pagamento
```http
PUT /api/pagamentos/1
Content-Type: application/json

{
  "status": "PENDENTE"
}
```

#### Deletar Pagamento
```http
DELETE /api/pagamentos/1
```

---

## Regras de Negócio Implementadas

1. ✅ **Aluno matriculado em apenas um plano por vez**
2. ✅ **Um treino pode ser associado a vários alunos**
3. ✅ **Data de pagamento preenchida automaticamente**
4. ✅ **Não é possível remover treinos com alunos associados**
5. ✅ **Validação de CPF e email únicos**
6. ✅ **Data de matrícula preenchida automaticamente**

---

## Testando com cURL

### Exemplo: Cadastrar um aluno
```bash
curl -X POST http://localhost:8080/api/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Santos",
    "cpf": "98765432100",
    "email": "maria@email.com",
    "telefone": "(62) 91111-1111",
    "dataNascimento": "1998-08-20",
    "plano": {"id": 1}
  }'
```

### Exemplo: Associar aluno a um treino
```bash
curl -X POST http://localhost:8080/api/treinos/1/alunos/1
```

### Exemplo: Registrar pagamento
```bash
curl -X POST http://localhost:8080/api/pagamentos \
  -H "Content-Type: application/json" \
  -d '{
    "aluno": {"id": 1},
    "valor": 89.90,
    "status": "PAGO",
    "metodoPagamento": "CARTAO"
  }'
```
