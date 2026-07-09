# Animação C2D

Este projeto foi desenvolvido no âmbito da disciplina de **Computação Gráfica** da Universidade de Évora. O objetivo é a criação de uma cena animada utilizando a tecnologia HTML5 Canvas e a linguagem JavaScript.

## Sobre o Projeto

O programa consiste numa animação 2D que integra vários elementos gráficos e efeitos visuais, incluindo:

* **Cenário Dinâmico**: Uma paisagem composta por céu, mar e elementos de vegetação/construção.
* **Animação de Objetos**: Um barco que se desloca lateralmente através do canvas com uma velocidade definida.
* **Efeitos Visuais**: Utilização de gradientes (lineares e radiais) para criar efeitos de luz e profundidade no sol e na água.
* **Renderização**: O desenho é feito utilizando o contexto 2D do Canvas, com funções modulares para cada elemento da cena.

## Tecnologias Utilizadas

* **HTML5**: Estrutura da página.
* **CSS**: Estilização básica e centralização do canvas.
* **JavaScript (Canvas API)**: Lógica de desenho e animação dos elementos gráficos.

## Estrutura dos Ficheiros

* `index.html`: Ficheiro principal que define a estrutura da página e integra o script de animação.
* `prog.js`: Ficheiro com toda a lógica da animação, incluindo as funções `drawBackground`, `drawSun`, `updatePositions` e o ciclo de renderização.

## Como Executar

1. Não é necessário nenhum servidor especial ou compilação.
2. Certifica-te de que o ficheiro `index.html` e a pasta `js/` (contendo o `prog.js`) estão no mesmo diretório.
3. Abre o ficheiro `index.html` diretamente em qualquer navegador web moderno (Chrome, Firefox, Edge, etc.).
4. A animação começará automaticamente ao carregar a página através da função `main()`.