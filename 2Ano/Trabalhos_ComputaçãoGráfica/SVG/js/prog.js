function main() {
    const arena = document.getElementById("arena");
    const ariete = document.getElementById("ariete");
    const terminal = document.getElementById("terminal");
    const arenaWidth = arena.getAttribute("width");
    const arenaHeight = arena.getAttribute("height");

    let doors = [];
    let gamePhase = 0; // 0: jogando, 2: derrota
    let age = 0; // Contador de tempo
    let arieteAction = 0; // 0: parado, 1: subir, 2: descer
    const arieteSpeed = 4;

    // Função para criar uma nova porta
    function newDoor() {
        const doorHeight = Math.random() * 120 + 40; // Altura entre 40 e 160
        const y = Math.random() < 0.5 ? 0 : arenaHeight - doorHeight; // Porta no topo ou base
        const door = document.createElementNS("http://www.w3.org/2000/svg", "rect");
        door.setAttribute("x", arenaWidth);
        door.setAttribute("y", y);
        door.setAttribute("width", 20);
        door.setAttribute("height", doorHeight);
        door.setAttribute("fill", "url(#doorTexture)"); // Usando a textura
        door.dataset.vel = Math.random() * 2 + 2; // Velocidade entre 2 e 4
        arena.appendChild(door);
        doors.push(door);
    }

    // Função para verificar colisão
    function isColliding(a, b) {
        const ax = parseFloat(a.getAttribute("x"));
        const ay = parseFloat(a.getAttribute("y"));
        const aw = parseFloat(a.getAttribute("width"));
        const ah = parseFloat(a.getAttribute("height"));

        const bx = parseFloat(b.getAttribute("x"));
        const by = parseFloat(b.getAttribute("y"));
        const bw = parseFloat(b.getAttribute("width"));
        const bh = parseFloat(b.getAttribute("height"));

        return !(ax + aw < bx || ax > bx + bw || ay + ah < by || ay > by + bh);
    }

    // Atualização do jogo
    function update() {
        if (gamePhase === 2 || gamePhase === 3) return;

        // Movimento do aríete
        const arieteY = parseFloat(ariete.getAttribute("y"));
        switch (arieteAction) {
            case 1:
                ariete.setAttribute("y", Math.max(0, arieteY - arieteSpeed));
                break;
            case 2:
                ariete.setAttribute("y", Math.min(arenaHeight - ariete.getAttribute("height"), arieteY + arieteSpeed));
                break;
        }

        // Movimento das portas
        doors.forEach((door, index) => {
            const doorX = parseFloat(door.getAttribute("x"));
            const doorVel = parseFloat(door.dataset.vel);
            door.setAttribute("x", doorX - doorVel);

            if (isColliding(ariete, door)) {
                doors.splice(index, 1);
                arena.removeChild(door);
            }

            // Verificar se a porta saiu da arena
            if (doorX + door.getAttribute("width") < 0) {
                gamePhase = 2;
            }
        });

        // Adicionar novas portas periodicamente
        if (age % 120 === 0) {
            newDoor();
        }

        age++;
    }

    // Renderização do terminal
    function renderTerminal() {
        if (gamePhase === 2) {
            terminal.textContent = `Derrota! Pressione "R" para reiniciar.`;
        } else {
            terminal.textContent = `Pontuação: ${(age / 60).toFixed(1)}`;
        }
    }

    // Loop do jogo
    function gameLoop() {
        update();
        renderTerminal();
        if (gamePhase !== 2 && gamePhase !== 3) {
            requestAnimationFrame(gameLoop);
        }
    }

    // Reiniciar o jogo
    function resetGame() {
        doors.forEach((door) => arena.removeChild(door));
        doors = [];
        gamePhase = 0;
        age = 0;
        ariete.setAttribute("y", "180");
        gameLoop();
    }

    // Controle do teclado
    document.addEventListener("keydown", (e) => {
        switch (e.key.toLowerCase()) {
            case "w":
                arieteAction = 1;
                break; // Subir
            case "s":
                arieteAction = 2;
                break; // Descer
            case "r":
                resetGame();
                break; // Reiniciar
        }
    });

    document.addEventListener("keyup", () => {
        arieteAction = 0; // Parar o movimento
    });

    gameLoop();
}

main();