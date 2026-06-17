/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
import Equipamento from "./Equipamento.js";

window.mudarBloco = async function mudarBloco(mapa, codigos, botao) {
    trocarMapa(mapa);

    const equipamentos = await Promise.all(
        codigos.map(codigo => getEquipamento(codigo))
    );

    const equipamentosValidos = equipamentos.filter(eq => eq);

    const lista = criarListaBlocos(equipamentosValidos);

    const divEquipamentos =
        botao.parentElement.querySelector(".equipamentos");
    
    document.querySelectorAll(".equipamentos")
    .forEach(div => div.innerHTML = "");
    
    divEquipamentos.appendChild(lista);
}

function trocarMapa(mapa) {
    document.getElementById("mapa-container").innerHTML = "<img src='img/" + mapa + "' alt='Mapa' class='mapa-imagem'>";
}

async function getEquipamento(codigo) {
    try {
        const response = await fetch("BuscarEquipamento", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "codigo=" + codigo
        });

        const data = await response.json();

        if (data.erro) {
            console.error(data.erro);
            return null;
        }

        return new Equipamento(data.equipamento);

    } catch (error) {
        console.error("Erro:", error);
        return null;
    }
}

function criarListaBlocos(equipamentos) {
    const container = document.createElement("div");

    equipamentos.forEach(eq => {
        const botao = document.createElement("input");

        botao.type = "button";
        botao.value = eq.nome;

        botao.onclick = () => mostrarEquipamento(eq.codigo);

        botao.style.display = "block";

        container.appendChild(botao);
    });

    return container;
}

window.simularConsumo = async function(codigo) {
    const equipamento = await getEquipamento(codigo);

    const horas =
        parseFloat(document.getElementById("horasInput").value);
    const qtde =
        parseFloat(document.getElementById("qtde").value);

    const consumo =
        (equipamento.potencia *
         horas *
         equipamento.diasMes *
         qtde) / 1000;

    const custo = consumo * 1.05;

    document.getElementById("consumo")
        .textContent = consumo.toFixed(2);

    document.getElementById("custo")
        .textContent = "R$ " + custo.toFixed(2);
};


async function mostrarEquipamento(codigo) {
    const equipamento = await getEquipamento(codigo);

    if (!equipamento)
        return;
    
    const TARIFA = 1.05; // R$/kWh
    const div = document.getElementById("descEquipamento");
    
    const consumoBase =
    (equipamento.potencia *
     equipamento.horasDiarias *
     equipamento.diasMes *
     equipamento.qtde) / 1000;

    const custoBase = consumoBase * TARIFA;

    div.innerHTML = `
        <img src="${equipamento.imagem}">

        <h2>${equipamento.nome}</h2>
        
        <div class="info-box" hidden>
            <span>Consumo Mensal</span>
            <strong id="consumo">${consumoBase.toFixed(2)}</strong>
            <small>kWh</small>
        </div>

        <div class="info-box">
            <span>Custo Mensal</span>
            <strong id="custo">R$ ${custoBase.toFixed(2)}</strong>
        </div>

        <h3>Simular Uso</h3>

        <p>
            Horas de uso por dia:
        </p>

        <input
            type="number"
            id="horasInput"
            min="0"
            max="24"
            value="${equipamento.horasDiarias}"
            oninput="simularConsumo(${equipamento.codigo})"
        >
        <p>
            Quantidade:
        </p>

        <input
            type="number"
            id="qtde"
            min="0"
            value="${equipamento.qtde}"
            oninput="simularConsumo(${equipamento.codigo})"
        >

    `;
    div.hidden = false;
}
window.mostrarEquipamento = mostrarEquipamento;