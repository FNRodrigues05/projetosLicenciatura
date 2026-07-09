# Visualização X3D

Este projeto foi desenvolvido no âmbito da unidade curricular de **Computação Gráfica** da Universidade de Évora. O objetivo foi a construção e animação de uma cena 3D utilizando a biblioteca **X3DOM** para visualização baseada em XML.

## Sobre o Projeto

O projeto consiste numa cena 3D que representa uma estrutura arquitetónica urbana, integrando elementos de animação e iluminação. Os principais componentes incluem:

* **Edifício Principal**: Estrutura volumétrica definida através de primitivas `box`.
* **Elementos Urbanos**: Representação de ruas com iluminação direcional e materiais com propriedades especulares.
* **Animação**: Utilização de `routes` para coordenar animações temporizadas (como janelas e portas) dentro do espaço 3D.

## Tecnologias Utilizadas

* **X3DOM**: Framework para renderização de gráficos 3D declarativos em HTML5.
* **X3D (XML3D)**: Linguagem utilizada para a descrição da cena, geometrias, transformações e lógica de animação.
* **HTML5/CSS3**: Estrutura e estilização do canvas de visualização.

## Estrutura do Repositório

* `index.html`: Ficheiro de entrada que integra a biblioteca X3DOM e chama a cena definida no ficheiro `.x3d`.
* `js/prog.x3d`: Ficheiro principal que contém a definição de toda a cena, geometrias, luzes e animações (routes).
* `lib/x3dom/`: Pasta contendo os recursos da biblioteca X3DOM (`x3dom.js` e `x3dom.css`).

## Como Executar

1. Para visualizar o projeto, é necessário servir os ficheiros através de um **servidor web local** devido às políticas de segurança do navegador em relação a ficheiros XML/X3D.
2. Se utilizares o VS Code, podes abrir o `index.html` e usar a extensão **Live Server**.
3. Em alternativa, podes usar o terminal:
   * **Python**: `python -m http.server 8000`
   * **Node.js**: `npx serve`
4. Após iniciar o servidor, acede a `http://localhost:8000` no teu navegador.