# Jogo de Ação em SVG

Este projeto foi desenvolvido para a unidade curricular de **Computação Gráfica** da Universidade de Évora. O trabalho tem como objetivo a implementação de uma cena animada e interativa utilizando **SVG** (Scalable Vector Graphics) e manipulação dinâmica através de JavaScript.

## Sobre o Projeto

O jogo apresenta um sistema onde o jogador controla um elemento gráfico (ariete) para evitar obstáculos (portas) que surgem no ecrã.

### Funcionalidades Principais
* **Renderização SVG**: Utilização da tag `<svg>` para definir a arena de jogo, portas e elementos dinâmicos.
* **Interatividade**: O jogador pode mover o ariete verticalmente utilizando as teclas 'W' (subir) e 'S' (descer).
* **Dinâmica de Jogo**:
    * As portas são geradas dinamicamente com alturas aleatórias, posicionando-se no topo ou na base da arena.
    * O sistema deteta colisões e termina o jogo caso o ariete embata nas portas.
* **Texturização**: Implementação de padrões (`<pattern>`) para aplicar texturas personalizadas às portas e elementos do jogo.

## Estrutura de Ficheiros

* `index.html`: Define a estrutura da arena SVG e as definições de texturas (`defs`).
* `prog.js`: Contém toda a lógica de jogo, incluindo a movimentação, o ciclo de atualização (`gameLoop`), a criação de obstáculos e a gestão da pontuação.
* `media/`: Pasta contendo os recursos visuais (`porta.png`, `ariete.png`, `arena.png`) utilizados nas texturas do jogo.

## Como Executar

1. Abre o ficheiro `index.html` num navegador web moderno (Chrome, Firefox ou Edge).
2. Utiliza as teclas **'W'** para subir e **'S'** para descer.
3. O objetivo é manter o ariete dentro da área de jogo enquanto as portas se deslocam horizontalmente.
4. Caso percas, prime a tecla **'R'** para reiniciar a partida.