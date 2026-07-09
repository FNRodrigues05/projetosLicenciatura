function main() {
    let gc = document
        .getElementById("animationCanvas")
        .getContext("2d");
    const canvas = document.getElementById("animationCanvas");

    // Propriedades do barco
    let boatX = 50, boatY = 300, boatSpeed = 1.5;

    // Propriedades do sol
    let sunX = boatX + 150, sunY = boatY - 250;

    function drawBackground() {
        // Mar com gradiente
        const seaGradient = gc.createLinearGradient(0, canvas.height / 2, 0, canvas.height);
        seaGradient.addColorStop(0, "#1E90FF");
        seaGradient.addColorStop(1, "#104E8B");
        gc.fillStyle = seaGradient;
        gc.fillRect(0, canvas.height / 2, canvas.width, canvas.height / 2);

        // Relva com gradiente
        const grassGradient = gc.createLinearGradient(0, canvas.height / 2 - 10, 0, canvas.height / 2 + 40);
        grassGradient.addColorStop(0, "#00FF7F");
        grassGradient.addColorStop(1, "#006400");
        gc.fillStyle = grassGradient;
        gc.fillRect(0, canvas.height / 2 - 10, canvas.width, 50);

        // Prédio
        gc.fillStyle = "#d4eb87";
        gc.fillRect(100, 185, 50, 30);
        gc.strokeStyle = "black";
        gc.lineWidth = 2;
        gc.strokeRect(100, 185, 50, 30);

        gc.fillStyle = "#FFF";
        gc.fillRect(100, 130, 50, 60);
        gc.strokeRect(100, 130, 50, 60);

        gc.fillStyle = "#4d3319";
        gc.fillRect(117, 195, 15, 20);

        gc.fillStyle = "#d9d9d9";
        gc.fillRect(107, 160, 15, 10);
        gc.fillRect(127, 160, 15, 10);
    }

    function drawBoat(x, y, inverted) {
        gc.save();

        if (inverted) {
            gc.scale(-1, 1);
            x = -x - 120;
        }

        // Primeiro nível do barco
        gc.fillStyle = "black";
        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 100, y);
        gc.lineTo(x + 120, y - 20);
        gc.lineTo(x + 100, y - 17);
        gc.lineTo(x + 50, y - 17);
        gc.lineTo(x + 25, y - 17);
        gc.lineTo(x + 10, y - 7);
        gc.lineTo(x, y - 7);
        gc.closePath();
        gc.fill();

        // Segundo nível do barco
        gc.fillStyle = "black";
        gc.beginPath();
        gc.moveTo(x + 100, y - 17);
        gc.lineTo(x + 40, y - 17);
        gc.lineTo(x + 40, y - 27);
        gc.lineTo(x + 90, y - 27);
        gc.lineTo(x + 100, y - 17);
        gc.closePath();
        gc.fill();

        // Cabine do barco
        gc.fillStyle = "black";
        gc.fillRect(x + 50, y - 47, 31, 20);

        // Janelas da cabine
        gc.fillStyle = "lightblue";
        gc.fillRect(x + 57, y - 44, 18, 10);

        // Teto da cabine
        gc.fillStyle = "black";
        gc.beginPath();
        gc.moveTo(x + 78, y - 47);
        gc.lineTo(x + 54, y - 57);
        gc.lineTo(x + 50, y - 57);
        gc.lineTo(x + 74, y - 47);
        gc.closePath();
        gc.fill();

        gc.restore();
    }

    function drawSun(x, y) {
        // Sol com gradiente
        const sunGradient = gc.createRadialGradient(x, y, 10, x, y, 30);
        sunGradient.addColorStop(0, "yellow");
        sunGradient.addColorStop(1, "orange");

        gc.fillStyle = sunGradient;
        gc.beginPath();
        gc.arc(x, y, 30, 0, Math.PI * 2);
        gc.closePath();
        gc.fill();
    }

    function updatePositions() {
        // Movimento do barco no eixo X
        boatX += boatSpeed;
        if (sunX < 0 || sunX + 30 > canvas.width) {
            boatSpeed *= -1;
        }

        if (boatSpeed > 0) {
            sunX = boatX + 150;
        } else {
            sunX = boatX - 50;
        }
    }

    function animate() {
        gc.clearRect(0, 0, canvas.width, canvas.height);
        drawBackground();
        drawBoat(boatX, boatY, boatSpeed < 0);
        drawSun(sunX, sunY);
        updatePositions();
        requestAnimationFrame(animate);
    }

    animate();
}
