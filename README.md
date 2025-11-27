### Identificação

Nome: Diogo Dalbianco dos Santos 
Curso: Sistemas de Informação

### Proposta

Fazer um quiz educativo para treinamentos da NR 35, essa norma regulamenta os requisitos mínimos de segurança para trabalho em altura, a ideia é fazer uma sequencia de perguntas para o usuário, fazer um score com as respostas certas, mostrar as resoluções das perguntas e se o usuário seria aprovado pelo treinamento(baseado no score).

### Processo de desenvolvimento

No início do processo tive vários problemas com o gradle e o codespaces, pois configurei o .devcontainer diretamente pelo codespaces o que ocasionou em vários erros(porque eu não sabia como configurar corretamente), mas um tempo depois consegui configurar essa parte certa, mas a versão do gradle ainda estava dando problema, então tentei voltar para versões anteriores, e finalmente deu certo.


Sobre a programação do projeto, tive vários problemas iniciais por não ter ido nas aulas em que foi explicado de forma mais detalhada o libGDX, então na semana da JAI fiquei estagnado sem saber para onde ir, comecei pelos assets iniciais pedi ajuda para uma amiga, para fazer as coisas básicas do jogo, com a experiência dela conseguimos deixar um visual agradável, ela me ajudou com os assets de background, personagem, plataforma, andaime, parede e a tela final de parabéns. Com essa base consegui fazer uma montagem inicial para poder entregar no dia 14/11, mas deu tudo errado por conta de problemas de localização e pixels, mas consegui arrumar.


Ainda faltavam as perguntas, então decidi fazê las eu mesmo, tentei achar algum lugar que poderia fazer as perguntas para baixar a imagem, mas não tive sucesso, então tentei fazer umas gambiarras até que saiu algo minimamente apresentável, de início fiz 4 questões simples para poder fazer os primeiros testes, mas elas seguiram até o final do projeto. Obtive muitos problemas com a adaptação das perguntas para o formato do jogo, infelizmente isso é algo que ainda preciso melhorar, mesmo testando diversas alterações tanto em código como nas próprias imagens não tive sucesso, então as questões estão um pouco feias e achatadas na tela, mas funciona.


Falando mais sobre o código e a criação das classes, foi me recomendado estudar e usar como exemplos códigos dos jogos do ano passado, eu escolhi me basear no jogo **aya adventures**, li bastante dos códigos e como eles funcionavam e a partir disso comecei fazendo o meu, tive vários problemas com pixeis para poder encaixar as imagens e as áreas de clique, e por isso tive problema com as últimas duas questões, que por algum motivo estão com local de clique muito específicos. Sobre o restante foi divertido e estressante ter que implementar funções que não tinha conhecimento, mas consegui aplicar tudo certinho.


Este projeto ainda está incompleto, no início sugeri fazer um sistema de score para aprovar um resultado, mas no final resolvi fazer um sistema de vidas para deixar a gamificação mais simples, mas não exibe os corações na tela.


### Diagrama de classes

<img width="1676" height="1603" alt="Diagrama" src="https://github.com/user-attachments/assets/b7665651-795d-41bb-848f-19ded0188e4b" />

### Orientações para execução

Não foi necessário usar extenções, somente abrir o codespaces e usar os comandos

   1. Build the HTML project
   ```
   ./gradlew html:dist
   ```
   2. Run the HTML project
   ```
   cd html/build/dist
   python -m http.server
   ```

### Resultado final

![2025-11-10-23-42-31](https://github.com/user-attachments/assets/46466801-483b-463a-9851-a76fc8ea1147)

          
### Referências e créditos
[Aya Adventures](https://github.com/elc117/game-2024b-ayaadventures/tree/main)
[Exemplo em aula](https://github.com/AndreaInfUFSM/java-libgdx-extended-drop-example/tree/master)

