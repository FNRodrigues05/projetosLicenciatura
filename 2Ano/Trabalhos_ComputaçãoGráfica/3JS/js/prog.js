// Cena
const scene = new THREE.Scene();

// Gradiente como plano de fundo
const gradientGeometry = new THREE.PlaneGeometry(100, 100);
const gradientMaterial = new THREE.ShaderMaterial({
    uniforms: {
        topColor: { value: new THREE.Color(0xff7f50) },
        bottomColor: { value: new THREE.Color(0x87ceeb) },
    },
    vertexShader: `
        varying vec2 vUv;
        void main() {
            vUv = uv;
            gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 0.2);
        }
    `,
    fragmentShader: `
        varying vec2 vUv;
        uniform vec3 topColor;
        uniform vec3 bottomColor;
        void main() {
            gl_FragColor = vec4(mix(bottomColor, topColor, vUv.y), 0.2);
        }
    `,
    side: THREE.DoubleSide
});
const gradientPlane = new THREE.Mesh(gradientGeometry, gradientMaterial);
gradientPlane.position.z = -50;
gradientPlane.rotation.x = Math.PI;
scene.add(gradientPlane);

// Câmara
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.set(0, 5, 15);

// Render
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// Controle de órbita da camara
const controls = new THREE.OrbitControls(camera, renderer.domElement);

// Luz
const light = new THREE.PointLight(0xffffff, 1, 100);
light.position.set(10, 10, 10);
scene.add(light);

// Luz Ambiente Principal
const ambientLight = new THREE.AmbientLight(0xffddaa, 0.5);
scene.add(ambientLight);


// Material para as letras
const material = new THREE.MeshPhongMaterial({ color: 0x44aa88 });

// Função para criar uma letra extrudada a partir de um caminho
function createExtrudedLetter(path) {
    const extrudeSettings = {
        steps: 2,
        depth: 0.5,
        bevelEnabled: true,
        bevelThickness: 0.1,
        bevelSize: 0.1,
        bevelSegments: 2
    };
    const geometry = new THREE.ExtrudeGeometry(path, extrudeSettings);
    return new THREE.Mesh(geometry, material);
}

// Criando cada letra
function createLetterE() {
    const path = new THREE.Shape();
    path.moveTo(0, 3);
    path.lineTo(0, 0);
    path.lineTo(1.5, 0);
    path.lineTo(1.5, 0.5);
    path.lineTo(0.5, 0.5);
    path.lineTo(0.5, 1.25);
    path.lineTo(1.5, 1.25);
    path.lineTo(1.5, 1.75);
    path.lineTo(0.5, 1.75);
    path.lineTo(0.5, 2.5);
    path.lineTo(1.5, 2.5);
    path.lineTo(1.5, 3);
    path.lineTo(0, 3);
    return createExtrudedLetter(path);
}

function createLetterL() {
    const path = new THREE.Shape();
    path.moveTo(0, 3);
    path.lineTo(0, 0);
    path.lineTo(1.5, 0);
    path.lineTo(1.5, 0.5);
    path.lineTo(0.5, 0.5);
    path.lineTo(0.5, 3);
    path.lineTo(0, 3);
    return createExtrudedLetter(path);
}

function createLetterI() {
    const path = new THREE.Shape();
    path.moveTo(0.5, 3);
    path.lineTo(0.5, 0);
    path.lineTo(1, 0);
    path.lineTo(1, 3);
    path.lineTo(0.5, 3);
    return createExtrudedLetter(path);
}

function createLetterN() {
    const path = new THREE.Shape();
    path.moveTo(0.5, 2);
    path.lineTo(.5, 0);
    path.lineTo(0, 0);
    path.lineTo(0, 3);
    path.lineTo(.5, 3);
    path.lineTo(1.5, 1);
    path.lineTo(1.5, 3);
    path.lineTo(2, 3);
    path.lineTo(2, 0);
    path.lineTo(1.5, 0);
    path.lineTo(.5, 2);
    return createExtrudedLetter(path);
}

function createLetterA() {
    const path = new THREE.Shape();
    path.moveTo(0, 0);
    path.lineTo(1.25, 3);
    path.lineTo(2.5, 0);
    path.lineTo(2, 0);
    path.lineTo(1.25, 2.5);
    path.lineTo(1, 1.5);
    path.lineTo(1.5, 1.5);
    path.lineTo(1.75, 1);
    path.lineTo(.75, 1);
    path.lineTo(.5, 0);
    path.lineTo(0, 0);
    return createExtrudedLetter(path);
}

function createLetterR() {
    const path = new THREE.Shape();
    path.moveTo(0, 0);
    path.lineTo(0, 3);
    path.lineTo(2, 3);
    path.quadraticCurveTo(2, 1.5, 1, 1.5);
    path.lineTo(2, 0);
    path.lineTo(1.5, 0);
    path.lineTo(.5, 1.5);
    path.lineTo(.5, 2);
    path.quadraticCurveTo(1.5, 2, 1.5, 2.5);
    path.lineTo(.5, 2.5);
    path.lineTo(.5, 0);
    path.lineTo(0, 0);
    return createExtrudedLetter(path);
}

// Criar a palavra
const word = new THREE.Group();
const spacing = 3;

const letters = [
    createLetterE(),
    createLetterL(),
    createLetterI(),
    createLetterN(),
    createLetterE(),
    createLetterA(),
    createLetterR(),
    createLetterA()
];


const letterAppearTime = 200;
let startTime = Date.now();

letters.forEach((letter, index) => {
    letter.position.x = index * spacing;
    letter.position.y = 15;
    word.add(letter);
});

word.position.x = -((letters.length - 1) * spacing) / 2; // Centralizar a palavra
scene.add(word);

// Função para animar a entrada das letras
function animateLetters() {
    const elapsed = Date.now() - startTime;

    letters.forEach((letter, index) => {
        const delay = index * letterAppearTime;

        if (elapsed > delay) {
            const progress = Math.min((elapsed - delay) / 1000, 1); // Progresso da animação
            letter.position.y = 10 * (1 - progress); // Movimenta a letra do topo para sua posição final
        }
    });
}

function animate() {
    requestAnimationFrame(animate);
    animateLetters();
    controls.update();
    renderer.render(scene, camera);
}

animate();
