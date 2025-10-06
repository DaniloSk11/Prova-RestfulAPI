# API REST - Sistema de Gerenciamento de Academia

## Descrição do Projeto

Sistema completo para gerenciamento de academia desenvolvido com Spring Boot e banco de dados H2 em memória, implementando operações CRUD para alunos, planos, treinos e pagamentos.

---

## Como Executar o Aplicativo

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- IntelliJ IDEA (recomendado) ou Eclipse
- Postman (para testar a API)

### Passo 1: Clonar/Abrir o Projeto

Abra o projeto no IntelliJ IDEA ou na sua IDE preferida.

### Passo 2: Verificar Dependências

Certifique-se que o `pom.xml` contém a dependência do H2:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Passo 3: Configurar application.properties

O arquivo já está configurado para usar H2 (banco em memória):

```properties
spring.datasource.url=jdbc:h2:mem:academia_db
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Não precisa configurar senha ou criar banco de dados!**

### Passo 4: Executar a Aplicação

**Opção A - Pelo IntelliJ IDEA:**
1. Abra o projeto no IntelliJ
2. Localize `ProvaResfulApiApplication.java` na pasta `src/main/java/com/main/ProvaResfulAPI/`
3. Clique com botão direito → **"Run 'ProvaResfulApiApplication'"**
4. Aguarde até ver no console: `Started ProvaResfulApiApplication in X seconds`

**Opção B - Via Terminal:**
```bash
mvn clean install
mvn spring-boot:run
```

### Passo 5: Acessar o Console H2

Abra o navegador e acesse:
```
http://localhost:8080/h2-console
```

**Configurações de Login:**
- **JDBC URL:** `jdbc:h2:mem:academia_db`
- **User Name:** `sa`
- **Password:** (deixe em branco)

Clique em **"Connect"** e você verá todas as tabelas criadas automaticamente!

---

## Demonstração Completa para o Professor

### Ferramentas Necessárias na Apresentação:
1. **Postman** - Para fazer requisições
2. **Navegador** - Console H2 aberto em `http://localhost:8080/h2-console`
3. **IDE** - Mostrando o código rodando

---

### ROTEIRO DE DEMONSTRAÇÃO (15-20 minutos)

## PARTE 1: Mostrar a Estrutura do Projeto (2 min)

**Mostre no IntelliJ:**
```
ProvaResfulAPI/
├── controller/    ← Endpoints REST
├── service/       ← Lógica de negócio
├── repository/    ← Acesso ao banco
├── entity/        ← Modelos de dados
└── ProvaResfulApiApplication.java ← Main
```

---

## PARTE 2: Executar e Mostrar o Console H2 (2 min)

1. **Execute a aplicação** pelo IntelliJ
2. **Mostre o console** com os logs do Spring Boot
3. **Abra o H2 Console** no navegador: `http://localhost:8080/h2-console`
4. **Faça login** e mostre as tabelas vazias criadas automaticamente:
   - ALUNOS
   - PLANOS
   - TREINOS
   - PAGAMENTOS
   - TREINO_ALUNO (tabela de relacionamento)

---

## PARTE 3: Demonstração Prática (10-15 min)

### TESTE 1: Cadastrar Planos

**No Postman:**
```http
POST http://localhost:8080/api/planos
Content-Type: application/json

{
  "nome": "Plano Mensal",
  "descricao": "Acesso ilimitado por 30 dias",
  "valor": 89.90,
  "duracao": 30
}
```

**Resposta esperada:**
```json
{
  "id": 1,
  "nome": "Plano Mensal",
  "descricao": "Acesso ilimitado por 30 dias",
  "valor": 89.90,
  "duracao": 30,
  "ativo": true
}
```

**Mostre no H2 Console:**
```sql
SELECT * FROM PLANOS;
```

---

### TESTE 2: Cadastrar Aluno

```http
POST http://localhost:8080/api/alunos
Content-Type: application/json

{
  "nome": "João Pedro Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "(62) 98765-4321",
  "dataNascimento": "1995-03-15",
  "plano": {
    "id": 1
  }
}
```

**Destaque para o professor:**
- `dataMatricula` preenchida automaticamente com a data de hoje
- `ativo` definido como `true` automaticamente
- Plano associado corretamente

**Mostre no H2:**
```sql
SELECT * FROM ALUNOS;
```

---

### TESTE 3: Regra de Negócio - CPF Único

Tente cadastrar outro aluno com o mesmo CPF:

```http
POST http://localhost:8080/api/alunos
Content-Type: application/json

{
  "nome": "Maria Santos",
  "cpf": "12345678901",
  "email": "maria@email.com",
  "telefone": "(62) 91111-1111",
  "dataNascimento": "1998-08-20"
}
```

**Resposta esperada:**
```
400 Bad Request
"CPF já cadastrado"
```

**Explique:** A regra de negócio está funcionando!

---

### TESTE 4: Cadastrar Treinos

```http
POST http://localhost:8080/api/treinos
Content-Type: application/json

{
  "nome": "Treino A - Peito e Tríceps",
  "descricao": "Supino reto, inclinado, crucifixo, tríceps",
  "dificuldade": "Intermediário"
}
```

```http
POST http://localhost:8080/api/treinos
Content-Type: application/json

{
  "nome": "Treino B - Costas e Bíceps",
  "descricao": "Puxada, remada, rosca direta",
  "dificuldade": "Intermediário"
}
```

**Mostre no H2:**
```sql
SELECT * FROM TREINOS;
```

---

### TESTE 5: Associar Aluno aos Treinos

```http
POST http://localhost:8080/api/treinos/1/alunos/1
```

```http
POST http://localhost:8080/api/treinos/2/alunos/1
```

**Destaque:** Um aluno pode ter vários treinos!

**Mostre no H2:**
```sql
SELECT * FROM TREINO_ALUNO;
```

---

### TESTE 6: Registrar Pagamento

```http
POST http://localhost:8080/api/pagamentos
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

**Destaque:** A `dataPagamento` é preenchida automaticamente!

**Mostre no H2:**
```sql
SELECT * FROM PAGAMENTOS;
```

---

### TESTE 7: Consultar Dados

**Listar todos os alunos:**
```http
GET http://localhost:8080/api/alunos
```

**Listar apenas alunos ativos:**
```http
GET http://localhost:8080/api/alunos/ativos
```

**Listar pagamentos de um aluno:**
```http
GET http://localhost:8080/api/pagamentos/aluno/1
```

---

### TESTE 8: Atualizar Aluno

```http
PUT http://localhost:8080/api/alunos/1
Content-Type: application/json

{
  "telefone": "(62) 99999-8888"
}
```

**Verifique no H2:**
```sql
SELECT * FROM ALUNOS WHERE ID = 1;
```

---

### TESTE 9: Regra de Negócio - Não Deletar Treino com Alunos

Tente deletar um treino que tem alunos associados:

```http
DELETE http://localhost:8080/api/treinos/1
```

**Resposta esperada:**
```
400 Bad Request
"Não é possível remover um treino que possui alunos associados"
```

**Explique:** Regra de negócio implementada!

---

### TESTE 10: Desassociar e Deletar

Primeiro desassocie:
```http
DELETE http://localhost:8080/api/treinos/1/alunos/1
```

Agora delete:
```http
DELETE http://localhost:8080/api/treinos/1
```

**Sucesso!** Agora funciona porque não há alunos associados.

---

### TESTE 11: Inativar Aluno

```http
PATCH http://localhost:8080/api/alunos/1/inativar
```

**Listar apenas ativos:**
```http
GET http://localhost:8080/api/alunos/ativos
```

**Resultado:** Lista vazia (aluno foi inativado)

---

## Consultas SQL para Mostrar no H2

```sql
-- Ver estrutura completa
SELECT * FROM ALUNOS;
SELECT * FROM PLANOS;
SELECT * FROM TREINOS;
SELECT * FROM PAGAMENTOS;
SELECT * FROM TREINO_ALUNO;

-- Consulta com JOIN (mostrar relacionamento)
SELECT 
    a.NOME as ALUNO,
    p.NOME as PLANO,
    p.VALOR as VALOR_PLANO
FROM ALUNOS a
LEFT JOIN PLANOS p ON a.PLANO_ID = p.ID;

-- Ver treinos por aluno
SELECT 
    a.NOME as ALUNO,
    t.NOME as TREINO
FROM ALUNOS a
JOIN TREINO_ALUNO ta ON a.ID = ta.ALUNO_ID
JOIN TREINOS t ON ta.TREINO_ID = t.ID;

-- Ver pagamentos por aluno
SELECT 
    a.NOME as ALUNO,
    pg.VALOR,
    pg.DATA_PAGAMENTO,
    pg.STATUS
FROM PAGAMENTOS pg
JOIN ALUNOS a ON pg.ALUNO_ID = a.ID;
```

---

## Checklist de Funcionalidades Implementadas

### Alunos
- [x] Cadastrar aluno
- [x] Listar todos os alunos
- [x] Listar apenas alunos ativos
- [x] Buscar aluno por ID
- [x] Atualizar dados do aluno
- [x] Inativar aluno
- [x] Validação de CPF único
- [x] Validação de email único
- [x] Data de matrícula automática

### Planos
- [x] Cadastrar plano
- [x] Listar planos
- [x] Atualizar plano
- [x] Inativar plano

### Treinos
- [x] Cadastrar treino
- [x] Listar treinos
- [x] Associar aluno a treino
- [x] Desassociar aluno de treino
- [x] Impedir remoção de treino com alunos associados

### Pagamentos
- [x] Registrar pagamento
- [x] Data de pagamento automática
- [x] Listar pagamentos
- [x] Listar pagamentos por aluno

### Regras de Negócio
- [x] Um aluno só pode ter um plano por vez
- [x] Um treino pode ter vários alunos
- [x] Data de pagamento preenchida automaticamente
- [x] Não deletar treinos com alunos associados
- [x] CPF e email únicos

---

## Endpoints Disponíveis

### Alunos
- `POST /api/alunos` - Cadastrar
- `GET /api/alunos` - Listar todos
- `GET /api/alunos/ativos` - Listar ativos
- `GET /api/alunos/{id}` - Buscar por ID
- `PUT /api/alunos/{id}` - Atualizar
- `PATCH /api/alunos/{id}/inativar` - Inativar
- `DELETE /api/alunos/{id}` - Deletar

### Planos
- `POST /api/planos` - Cadastrar
- `GET /api/planos` - Listar todos
- `GET /api/planos/ativos` - Listar ativos
- `GET /api/planos/{id}` - Buscar por ID
- `PUT /api/planos/{id}` - Atualizar
- `PATCH /api/planos/{id}/inativar` - Inativar
- `DELETE /api/planos/{id}` - Deletar

### Treinos
- `POST /api/treinos` - Cadastrar
- `GET /api/treinos` - Listar todos
- `GET /api/treinos/ativos` - Listar ativos
- `GET /api/treinos/{id}` - Buscar por ID
- `PUT /api/treinos/{id}` - Atualizar
- `POST /api/treinos/{treinoId}/alunos/{alunoId}` - Associar aluno
- `DELETE /api/treinos/{treinoId}/alunos/{alunoId}` - Desassociar aluno
- `PATCH /api/treinos/{id}/inativar` - Inativar
- `DELETE /api/treinos/{id}` - Deletar

### Pagamentos
- `POST /api/pagamentos` - Registrar
- `GET /api/pagamentos` - Listar todos
- `GET /api/pagamentos/aluno/{alunoId}` - Listar por aluno
- `GET /api/pagamentos/{id}` - Buscar por ID
- `PUT /api/pagamentos/{id}` - Atualizar
- `DELETE /api/pagamentos/{id}` - Deletar

---

## Troubleshooting

### Erro: "Port 8080 is already in use"
**Solução:** Mude a porta no `application.properties`:
```properties
server.port=8081
```

### Erro: Lombok não funciona
**Solução:**
1. Instale o plugin Lombok no IntelliJ
2. Habilite Annotation Processing em Settings

### Console H2 não abre
**Verificar:** `spring.h2.console.enabled=true` no `application.properties`

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Lombok
- Maven
- RESTful API

---

## Autor

Desenvolvido como projeto acadêmico de API RESTful com Spring Boot.
