let modo = "";
let jugadorX = "";
let jugadorO = "";
let turno = "X";
let tablero = ["", "", "", "", "", "", "", "", ""];
let juegoActivo = false;

const seleccion = document.getElementById("seleccion");
const pantalla1 = document.getElementById("pantalla1");
const pantalla2 = document.getElementById("pantalla2");
const fichasAmigo = document.getElementById("fichas-amigo");
const fichasPC = document.getElementById("fichas-pc");
const tabla = document.querySelector("table");
const celdas = document.querySelectorAll("td");


document.getElementById("pc").addEventListener("change", () => {
    modo = "pc";
    seleccion.style.display = "none";
    pantalla1.style.display = "block";
});

document.getElementById("amigo").addEventListener("change", () => {
    modo = "amigo";
    seleccion.style.display = "none";
    pantalla2.style.display = "block";
});



function iniciarPC() {
    let nombre = document.getElementById("j1").value;
    if (nombre === "") {
        alert("Ingresá tu nombre");
        return;
    }
    pantalla1.style.display = "none";
    fichasPC.style.display = "block";
}

function iniciarAmigo() {
    let j1 = document.getElementById("j1a").value;
    let j2 = document.getElementById("j2").value;

    if (j1 === "" || j2 === "") {
        alert("Completá los nombres");
        return;
    }

    pantalla2.style.display = "none";
    fichasAmigo.style.display = "block";
}


function confirmarFichas() {

    if (modo === "amigo") {
        let ficha = document.querySelector('input[name="ficha"]:checked');
        if (!ficha) {
            alert("Elegí quién juega con X");
            return;
        }

        if (ficha.value === "j1") {
            jugadorX = document.getElementById("j1a").value;
            jugadorO = document.getElementById("j2").value;
        } else {
            jugadorX = document.getElementById("j2").value;
            jugadorO = document.getElementById("j1a").value;
        }

        fichasAmigo.style.display = "none";
    }

    if (modo === "pc") {
        let ficha = document.querySelector('input[name="ficha-pc"]:checked');
        if (!ficha) {
            alert("Elegí tu ficha");
            return;
        }

        if (ficha.value === "X") {
            jugadorX = document.getElementById("j1").value;
            jugadorO = "PC";
        } else {
            jugadorX = "PC";
            jugadorO = document.getElementById("j1").value;
        }

        fichasPC.style.display = "none";
    }

    tabla.style.display = "table";
    juegoActivo = true;

    if (modo === "pc") {
        turno = (jugadorX === "PC") ? "X" : "O";
        setTimeout(jugarPC, 400);
    } else {
        turno = "X";
    }
}


function jugar(pos) {
    if (!juegoActivo || tablero[pos] !== "") return;

    tablero[pos] = turno;
    celdas[pos].innerText = turno;

    if (verificarGanador(turno)) {
        alert("Ganó " + (turno === "X" ? jugadorX : jugadorO));
        juegoActivo = false;
        return;
    }

    if (!tablero.includes("")) {
        alert("Empate");
        juegoActivo = false;
        return;
    }

    turno = turno === "X" ? "O" : "X";

    if (
        modo === "pc" &&
        ((turno === "X" && jugadorX === "PC") ||
         (turno === "O" && jugadorO === "PC"))
    ) {
        setTimeout(jugarPC, 400);
    }
}

function jugarPC() {
    if (!juegoActivo) return;

    let movimiento;

    movimiento = encontrarMejorMovimiento("O");
    if (movimiento !== null) {
        jugar(movimiento);
        return;
    }

    movimiento = encontrarMejorMovimiento("X");
    if (movimiento !== null) {
        jugar(movimiento);
        return;
    }

    if (tablero[4] === "") {
        jugar(4);
        return;
    }

    const esquinas = [0, 2, 6, 8].filter(i => tablero[i] === "");
    if (esquinas.length > 0) {
        jugar(esquinas[Math.floor(Math.random() * esquinas.length)]);
        return;
    }

    const libres = tablero
        .map((v, i) => v === "" ? i : null)
        .filter(v => v !== null);

    jugar(libres[Math.floor(Math.random() * libres.length)]);
}


function encontrarMejorMovimiento(simbolo) {
    const combos = [
        [0,1,2], [3,4,5], [6,7,8],
        [0,3,6], [1,4,7], [2,5,8],
        [0,4,8], [2,4,6]
    ];

    for (let c of combos) {
        let valores = c.map(i => tablero[i]);

        if (
            valores.filter(v => v === simbolo).length === 2 &&
            valores.includes("")
        ) {
            return c[valores.indexOf("")];
        }
    }
    return null;
}


function verificarGanador(simbolo) {
    const combos = [
        [0,1,2], [3,4,5], [6,7,8],
        [0,3,6], [1,4,7], [2,5,8],
        [0,4,8], [2,4,6]
    ];

    for (let c of combos) {
        if (
            tablero[c[0]] === simbolo &&
            tablero[c[1]] === simbolo &&
            tablero[c[2]] === simbolo
        ) {
            resaltarGanadoras(c, simbolo);
            return true;
        }
    }
    return false;
}

function resaltarGanadoras(combinacion, simbolo) {
    combinacion.forEach(i => {
        celdas[i].classList.add(
            simbolo === "X" ? "ganadora-x" : "ganadora-o"
        );
    });
}
