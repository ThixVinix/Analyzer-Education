# CodeTask
## _Sistema de apoio ao ensino na área de programação_ 

### o CodeTask foi criado como um sistema de apoio ao ensino de programação. Esse sistema foi planejado para atender os docentes e discentes, de forma que soluções simples e variadas sejam trabalhadas com premissas customizadas e testes dinâmicos automatizados, com uma interface amigável e de fácil compreensão.

&nbsp;

## Tecnologias utilizadas

Com o intuito de desenvolver uma aplicação WEB, foi utilizada as seguintes tecnologias para compor o sistema:

### Front-end:

- HTML 5;
- CSS; 
- Bootstrap 5; 
- Thymeleaf; 
- Ace Editor; 
- JavaScript. 

### Back-end:
- Spring Framework; 
- Java; 
- Hibernate. 

&nbsp;

## Funcionalidades Principais

![image](https://user-images.githubusercontent.com/30930784/170154291-40f440ab-fa1a-4104-81e2-3b697dd7095d.png)

&nbsp;

### Tarefa:

O CodeTask possibilita ao docente a criação de tarefas, onde seus aspectos consistem em: título e descrição, tendo como proposta indicar ao discente todas as instruções para a criação e execução do código relacionado a mesma.  A tarefa pode ou não conter testes e/ou premissas, dependendo da necessidade do docente. As tarefas criadas são exibidas em uma tabela, com as seguintes colunas: identificação, título, descrição, testes, premissas e opções da tarefa. As tarefas são adicionadas por intermédio do botão  "Criar nova tarefa".

![image](https://user-images.githubusercontent.com/30930784/170157703-df4181a7-4c59-4e8b-acdc-e72beb55e638.png)

&nbsp;

### Teste:

Com o propósito de oferecer apoio de forma automatizada, a funcionalidade de teste está integrada ao CodeTask. O sistema permite a realização de testes unitários para as tarefas criadas. Testes só poderão ser criados a partir de uma tarefa existente.
Desse modo os testes são categorizados de acordo com as tarefas cadastradas. Sendo assim, as atribuições de entradas e saídas podem ser realizadas de forma generalizada, permitindo adaptabilidade com qualquer tarefa proposta, ou já estabelecida de acordo com a mesma. Todos os testes cadastrados são exibidos em uma tabela.

![image](https://user-images.githubusercontent.com/30930784/170157829-41a7a8d8-3a62-4e22-ae1a-3fda41fa9635.png)

### Premissa:

O CodeTask oferece um cadastro de premissas, onde as mesmas assumem o papel de critério da avaliação e análise do código-fonte desenvolvido pelo discente. Dessa forma, o sistema identifica se o discente utilizou as premissas solicitadas para a realização da tarefa. As opções de criação das premissas podem ser inúmeras. Podem ser utilizadas para a detecção de variáveis solicitadas, estruturas de decisão ou repetição, nomenclatura de métodos/funções requisitados. Todas as premissas cadastradas são exibidas em uma tabela.

![image](https://user-images.githubusercontent.com/30930784/170157842-e7601885-888f-45ee-9695-5e000f9b2a79.png)

## Realizando a Tarefa

Após o discente selecionar uma tarefa, o sistema redirecionará para tela de resolução da tarefa. Primeiramente são exibidos o título e descrição da tarefa escolhida:

![image](https://user-images.githubusercontent.com/30930784/170157935-725de8e5-46a1-4aa8-85c8-0b4f36120bf3.png)

Logo em seguida, caso a tarefa contenha ao menos uma premissa vinculada, outro
painel de exibição será exibido contendo uma tabela com o “Nome” e “Quantidade
demandada” para cada premissa. são demonstradas as premissas que
estão vinculadas a tarefa e que poderão ser utilizadas durante a sua solução. As
premissas servem como base de instruções para elaboração da resolução.

![image](https://user-images.githubusercontent.com/30930784/170157985-61adac1a-5574-4465-b865-131b89fe2ec9.png)


No ambiente de desenvolvimento da solução para a tarefa acessada, o discente
encontra o campo de edição e criação do código-fonte, dentro do próprio CodeTask.

![image](https://user-images.githubusercontent.com/30930784/170158225-772b9070-a53d-4c75-9377-906c2771f9d6.png)

Após o desenvolvimento da solução, o discente irá submeter o código-fonte criado.
Será gerado um resultado de sua progressão com base nos testes e/ou premissas
estabelecidos pelo docente. Essa progressão é representada de forma visual a partir
das barras de progresso em suas respectivas porcentagens alcançadas com base na
tarefa proposta.
Em fatores de sua composição, o componente “Console” se encarrega da exibição
após a execução do códigofonte feito pelo discente. Dessa maneira, os resultados
da execução do códigofonte criado são exibidos. 

![image](https://user-images.githubusercontent.com/30930784/170158133-9ab2b147-5fcc-4503-a02a-3217c2f65e5a.png)

Para cada teste atribuído a tarefa, é estabelecido uma avaliação através de um
nível de porcentagem de acertos ou erros, com base nos testes estabelecidos. 
É demonstrado o componente de “Taxa de acurácia do(s) teste(s)”, onde o
discente poderá expandir a situação de cada teste individualmente, e dessa forma,
visualizá-lo.

![image](https://user-images.githubusercontent.com/30930784/170158292-81c9baa8-95e9-4e19-baf6-8e6023b65dd8.png)

Ao expandir a situação de cada teste, é mostrado de forma detalhada o resultado de um teste que obteve
sucesso e outro que obteve fracasso.

![image](https://user-images.githubusercontent.com/30930784/170158328-74920582-446f-4a7a-aceb-9bcc786e38fa.png)

Posteriormente a execução do código criado pelo discente, também é exibido o componente
de “Taxa de utilização da(s) premissa(s)”. Na Figura 24 é possível observar as quan
tidades das premissas que foram utilizadas pelo discente durante a sua resolução.

![image](https://user-images.githubusercontent.com/30930784/170158558-6be95a55-ef1b-4928-9e81-56f9ec3b516c.png)

Além disso, um outro componente denominado "Informações Adicionais" é exibido após a execução do código-fonte do discente.
São retratadas a quantidade de linhas de código, quantidade de métodos, quantidade
de classes e quantidade de linhas de comentários utilizados.

![image](https://user-images.githubusercontent.com/30930784/170158620-22915d45-c96f-4193-beb6-4e2f339c4776.png)

## Trabalhos Futuros:

Em trabalhos futuros, planejamos a integração ao GitHub, para armazenamento
dos códigos desenvolvidos. Outra melhoria proposta, refere-se à integração ao Google
Classroom por se tratar de uma aplicação educacional.
O suporte para outras linguagens de programação, é mais uma proposta fundamental
que agrega no auxílio do ensino de disciplinas introdutórias de programação,
de modo que a implementação com relação a compatibilidade com outras linguagens,
possibilitaria novos recursos não explorados que podem ser identificados a partir dos
indícios à sua adição. Ou seja, suas funcionalidades adicionais condizentes as lingua
gens proporcionariam novas perspectivas que possam ser atendidas.
Gamificação do sistema, transformação de tarefas, desafios com recompensas, ní
veis de progressão dos discentes, mapeamento de habilidades com base na evolução,
são fatores que podem contribuir com o CodeTask.


