# Animação 3JS

Este projeto foi desenvolvido no âmbito da unidade curricular de **Computação Gráfica** da Universidade de Évora. O objetivo principal foi a criação de uma cena 3D interativa utilizando a biblioteca **Three.js**.

## Sobre o Projeto

O projeto foca-se na manipulação de geometrias 3D e renderização de cenas no navegador. A aplicação demonstra o uso de:

* **Geometrias Extrudidas**: Criação de letras 3D personalizadas através de caminhos (`path`) e extrusão (`THREE.ExtrudeGeometry`).
* **Iluminação e Câmara**: Configuração de uma câmara de perspectiva (`THREE.PerspectiveCamera`) com controlos de órbita (`OrbitControls`) para navegação na cena.
* **Shaders Personalizados**: Implementação de um `ShaderMaterial` para criar um plano de fundo com gradiente linear dinâmico.
* **Animação**: Lógica de entrada animada para os elementos da cena com base no tempo decorrido.

## Tecnologias Utilizadas

* **JavaScript**: Linguagem base para a lógica do projeto.
* **Three.js**: Biblioteca principal para renderização 3D WebGL.
* **HTML5/CSS3**: Estrutura e estilização básica do canvas.

## Estrutura do Repositório

* `index.html`: Ficheiro principal que carrega as dependências (Three.js e OrbitControls) e o script da aplicação.
* `js/prog.js`: Código-fonte com a definição da cena, geometria extrudida das letras, shaders de fundo e lógica de animação.
* `lib/threejs/`: Diretório contendo os ficheiros minificados da biblioteca Three.js e controlos adicionais.

## Como Executar

1. Este projeto requer um servidor web local para carregar os ficheiros corretamente (devido às políticas de segurança do navegador para módulos/scripts).
2. Se usares o VS Code, podes usar a extensão **Live Server**.
3. Em alternativa, podes utilizar o terminal para iniciar um servidor simples:
   * **Python**: `python -m http.server 8000`
   * **Node.js**: `npx serve`
4. Após iniciar o servidor, acede a `http://localhost:8000` no teu navegador.
